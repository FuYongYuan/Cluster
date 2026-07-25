<template>
  <a-modal
      :open="open"
      :confirm-loading="loadingState"
      @cancel="handleCancel"
  >
    <template #title>
      <icon-font type="icon-detail"/>
      角色详情
    </template>
    <template #footer>
      <a-button class="ant-btn-daybreak" :loading="loadingState" @click="handleOk">
        <icon-font type="icon-save" v-if="!loadingState"/>
        保存
      </a-button>
      <a-button :loading="loadingState" @click="handleCancel">
        <icon-font type="icon-close" v-if="!loadingState"/>
        关闭
      </a-button>
    </template>

    <a-spin tip="加载中......" :spinning="loadingState">
      <a-form
          ref="detail"
          :model="form"
          :rules="rules"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 20 }"
      >
        <a-form-item has-feedback label="角色名称" name="roleName">
          <a-input
              v-model:value="form.roleName"
              placeholder="角色名称"
              allowClear
          >
            <template v-slot:prefix>
              <icon-font type="icon-skin"/>
            </template>
          </a-input>
        </a-form-item>
        <a-form-item label="备注" name="remark">
          <a-textarea
              v-model:value="form.remark"
              placeholder="备注"
              :maxlength="1000"
              allowClear
              showCount
          />
        </a-form-item>

        <a-form-item has-feedback label="菜单" name="menuIds">
          <a-transfer
              v-model:target-keys="transferHave"
              :data-source="transferData"
              show-search
              :filter-option="transferFilterOption"
              :render="(item:TransferData) => item.title"
              :show-select-all="false"
              class="tree-transfer"
              @change="transferHandleChange"
          >
            <template #children="{ direction }: any">
              <a-tree
                  v-if="direction === 'left'"
                  block-node
                  checkable
                  check-strictly
                  default-expand-all
                  :checked-keys="transferHave"
                  :tree-data="menuTreeData"
                  @check="(_: any, props: any) => {onTreeCheck(props)}"
                  @select="(_: any, props: any) => {onTreeCheck(props)}"
              />
              <a-tree
                  v-else
                  block-node
                  checkable
                  check-strictly
                  default-expand-all
                  :checked-keys="transferHave"
                  :tree-data="rightTreeData"
                  @check="(_: any, props: any) => {onRightTreeCheck(props)}"
              />
            </template>
          </a-transfer>
        </a-form-item>
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script lang="ts">
import type { RoleDTO } from "@src/apis/authorization/dto";
import { getRole, saveRole } from "@src/apis/authorization/service";
import type { MenuDTO } from "@src/apis/capability/dto";
import { queryMenuAllTree } from "@src/apis/capability/service";
import type { TransferData } from "@src/apis/commons/dto";
import IconFont from "@src/assets/iconfont/icon";
import type { TreeProps } from "ant-design-vue";
import { type FormInstance, message } from "ant-design-vue";
import type { RuleObject } from "ant-design-vue/es/form";
import {
	computed,
	defineComponent,
	onMounted,
	reactive,
	ref,
	toRefs,
} from "vue";

export default defineComponent({
	// 页面名称
	name: "RoleDetail",
	// 组件
	components: {
		// 图标库
		IconFont,
	},
	// 父组件传递值
	props: {
		// 是否显示
		open: {
			type: Boolean,
			default: false,
		},
	},
	// 发出更新
	emits: ["update:open", "query"],
	// 执行
	setup(_, context) {
		//------------------------------------------------------------------------------------------------------------------初试事件
		// 初始化加载执行
		onMounted(() => {
			transferQueryData();
		});
		//------------------------------------------------------------------------------------------------------------------参数
		// 详情DOM
		const detail = ref<FormInstance>();

		// 详情页面数据
		const detailData = reactive({
			// 表单
			form: ref<RoleDTO>({
				id: -1,
			}),
			// 穿梭框拥有（右侧目标keys）
			transferHave: ref<string[]>([]),
			// 穿梭框数据（拍平后供Transfer内部管理）
			transferData: ref<TransferData[]>([]),
			// 菜单原始树数据
			treeDataSource: ref<MenuDTO[]>([]),

			loadingState: false,
		});

		// 左侧树数据（全部节点，已选节点禁用）
		const menuTreeData = computed(() => {
			return buildLeftTreeData(
				detailData.treeDataSource,
				detailData.transferHave,
			);
		});

		// 右侧树数据（仅已选节点 + 祖先上下文）
		const rightTreeData = computed(() => {
			return buildRightTreeData(
				detailData.treeDataSource,
				detailData.transferHave,
			);
		});

		//------------------------------------------------------------------------------------------------------------------方法
		// 关闭方法
		const handleCancel = () => {
			// 关闭显示
			context.emit("update:open", false);
			// 清理表单
			detailData.form = {
				id: -1,
			};
			detailData.transferHave = [];

			detail.value?.resetFields();
		};

		// 提交方法
		const handleOk = async () => {
			detail.value
				?.validate()
				.then(async () => {
					// 开始
					detailData.loadingState = true;

					// 保存
					const result = await saveRole(detailData.form);
					// 保存结果
					message.success(result);
					// 触发查询
					context.emit("query");

					// 结束
					detailData.loadingState = false;

					//关闭
					handleCancel();
				})
				.catch(() => {
					// 保存结果
					message.warn("请填写必要信息！");
				});
		};

		// 根据Id查询
		const getById = async (searchId: number) => {
			if (searchId > 0) {
				// 开始
				detailData.loadingState = true;

				// 查询
				const result = await getRole(searchId);
				if (result !== undefined) {
					detailData.form = result;
					if (result.menuIds !== undefined) {
						detailData.transferHave = result.menuIds.split(",");
					}
				}

				// 结束
				detailData.loadingState = false;
			}
		};

		// 查询所有菜单（树形结构）
		const transferQueryData = async () => {
			// 开始
			detailData.loadingState = true;

			// 查询
			const result = await queryMenuAllTree();
			if (result !== undefined) {
				detailData.treeDataSource = result;
			}

			// 穿梭框数据（递归拍平所有层级）
			detailData.transferData = [];
			flattenTree(detailData.treeDataSource);

			// 结束
			detailData.loadingState = false;
		};

		// 递归拍平树数据为穿梭框一维数据
		const flattenTree = (list: MenuDTO[]) => {
			list.forEach((item) => {
				detailData.transferData.push({
					key: item.id.toString(),
					title: item.menuName ?? "无名称",
				});
				if (item.sub && item.sub.length > 0) {
					flattenTree(item.sub);
				}
			});
		};

		// 构建左侧树数据（全部节点，不设置禁用）
		const buildLeftTreeData = (
			treeNodes: MenuDTO[],
			_targetKeys: string[] = [],
		): any[] => {
			return treeNodes.map(({ sub, ...props }) => ({
				...props,
				key: props.id?.toString(),
				title: props.menuName ?? "无名称",
				children:
					sub && sub.length > 0 ? buildLeftTreeData(sub, _targetKeys) : [],
			}));
		};

		// 构建右侧树数据（仅保留已选节点及其祖先上下文）
		const buildRightTreeData = (
			treeNodes: MenuDTO[],
			targetKeys: string[],
		): any[] => {
			const result: any[] = [];
			for (const node of treeNodes) {
				const nodeKey = node.id?.toString();
				const isSelected = targetKeys.includes(nodeKey);
				const filteredChildren =
					node.sub && node.sub.length > 0
						? buildRightTreeData(node.sub, targetKeys)
						: [];
				const hasSelectedDescendant = filteredChildren.length > 0;

				if (isSelected || hasSelectedDescendant) {
					result.push({
						...node,
						key: nodeKey,
						title: node.menuName ?? "无名称",
						disabled: !isSelected,
						children: filteredChildren,
					});
				}
			}
			return result;
		};

		// 左侧树勾选回调（直接操作 transferHave）
		const onTreeCheck = (
			e:
				| Parameters<NonNullable<TreeProps["onCheck"]>>[1]
				| Parameters<NonNullable<TreeProps["onSelect"]>>[1],
		) => {
			const eventKey = e.node.eventKey?.toString() ?? "";
			const isCheckedNow = e.node.checked;

			if (isCheckedNow) {
				// 勾选 → 加入右侧
				if (!detailData.transferHave.includes(eventKey)) {
					detailData.transferHave = [...detailData.transferHave, eventKey];
				}
			} else {
				// 取消勾选 → 从右侧移除
				detailData.transferHave = detailData.transferHave.filter(
					(key) => key !== eventKey,
				);
			}
			// 同步表单数据
			detailData.form.menuIds = detailData.transferHave.toString();
		};

		// 右侧树取消勾选回调（从右侧移除）
		const onRightTreeCheck = (
			e: Parameters<NonNullable<TreeProps["onCheck"]>>[1],
		) => {
			const eventKey = e.node.eventKey?.toString() ?? "";
			if (!e.node.checked) {
				// 取消勾选 → 从右侧移除
				detailData.transferHave = detailData.transferHave.filter(
					(key) => key !== eventKey,
				);
				// 同步表单数据
				detailData.form.menuIds = detailData.transferHave.toString();
			}
		};

		// 穿梭框过滤
		const transferFilterOption = (inputValue: string, option: TransferData) => {
			return option.title.indexOf(inputValue) > -1;
		};

		// 穿梭框变化处理
		const transferHandleChange = (keys: string[]) => {
			detailData.form.menuIds = keys.toString();
		};

		//------------------------------------------------------------------------------------------------------------------验证
		// 校验规则
		const rules = {
			roleName: [
				{
					required: true,
					validator: async (_: RuleObject, value: string | undefined) => {
						if (value === undefined || value === "") {
							return Promise.reject("请输入角色名称！");
						} else {
							return Promise.resolve();
						}
					},
					trigger: "change",
				},
			],
			menuIds: [
				{
					required: true,
					validator: async (_: RuleObject, value: string | undefined) => {
						if (value === undefined || value === "") {
							return Promise.reject("请选择菜单！");
						} else {
							return Promise.resolve();
						}
					},
					trigger: "change",
				},
			],
		};

		//------------------------------------------------------------------------------------------------------------------返回
		return {
			...toRefs(detailData),
			detail,
			rules,
			getById,
			handleCancel,
			handleOk,
			menuTreeData,
			rightTreeData,
			onTreeCheck,
			onRightTreeCheck,
			transferFilterOption,
			transferHandleChange,
		};
	},
});
</script>

<style scoped>
@import "@src/assets/css/button.css";

</style>

