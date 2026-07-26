<template>
  <a-modal
      :open="open"
      width="60%"
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

        <a-form-item label="菜单" name="menuIds">
          <a-transfer
              ref="transferRef"
              :target-keys="transferHave"
              :data-source="transferData"
              show-search
              :filter-option="transferFilterOption"
              :render="(item:TransferData) => item.title"
              :show-select-all="false"
              class="tree-transfer"
              @change="transferHandleChange"
              @search="transferHandleSearch"
          >
            <template #children="{ direction, onItemSelect }: any">
              <a-tree
                  v-if="direction === 'left'"
                  block-node
                  checkable
                  check-strictly
                  :expanded-keys="leftExpandedKeys"
                  :checked-keys="[...transferHave, ...leftCheckedKeys]"
                  :tree-data="menuTreeData"
                  @expand="onLeftExpand"
                  @check="(checked: any, e: any) => onLeftTreeCheck(checked, e, onItemSelect)"
              />
              <a-tree
                  v-else
                  block-node
                  checkable
                  check-strictly
                  :expanded-keys="rightExpandedKeys"
                  :checked-keys="rightCheckedKeys"
                  :tree-data="rightTreeData"
                  @expand="onRightExpand"
                  @check="(checked: any, e: any) => onRightTreeCheck(checked, e, onItemSelect)"
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

/**
 * check-strictly模式下Tree勾选事件首个参数（兼容数组格式）
 */
type TreeCheckStrictlyEvent = {
	checked: (string | number)[];
	halfChecked: (string | number)[];
};

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

		// 穿梭框DOM
		const transferRef = ref<any>();

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
			// 左侧树待穿梭勾选keys（已勾选，待点击向右穿梭）
			leftCheckedKeys: ref<string[]>([]),
			// 右侧树勾选keys（已勾选，待点击向左穿梭）
			rightCheckedKeys: ref<string[]>([]),
			// 左侧树手动折叠的节点keys（配合expanded-keys实现"默认全展开、用户可折叠"）
			leftCollapsedKeys: ref<string[]>([]),
			// 右侧树手动折叠的节点keys
			rightCollapsedKeys: ref<string[]>([]),
			// 左侧面板搜索关键字
			leftSearchValue: ref(""),
			// 右侧面板搜索关键字
			rightSearchValue: ref(""),

			loadingState: false,
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
			// 清理穿梭勾选状态
			detailData.leftCheckedKeys = [];
			detailData.rightCheckedKeys = [];
			// 清理搜索关键字
			detailData.leftSearchValue = "";
			detailData.rightSearchValue = "";
			// 重置穿梭框内部选中状态（避免遗留待穿梭选中项导致按钮残留亮起）
			transferRef.value?.handleSelectChange("left", []);
			transferRef.value?.handleSelectChange("right", []);

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
					if (result.menuIds !== undefined && result.menuIds !== "") {
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

		// 左侧树数据（全部节点，已拥有节点勾选置灰，按搜索关键字过滤）
		const menuTreeData = computed(() => {
			const filteredNodes = filterTreeByKeyword(
				detailData.treeDataSource,
				detailData.leftSearchValue,
			);
			return buildLeftTreeData(filteredNodes, detailData.transferHave);
		});

		// 右侧树数据（仅已选节点 + 祖先上下文，按搜索关键字过滤）
		const rightTreeData = computed(() => {
			const filteredNodes = filterTreeByKeyword(
				detailData.treeDataSource,
				detailData.rightSearchValue,
			);
			return buildRightTreeData(filteredNodes, detailData.transferHave);
		});

		// 收集全部可展开节点keys（拥有子级的节点）
		const collectExpandableKeys = (treeNodes: MenuDTO[]): string[] => {
			const keys: string[] = [];
			const traverse = (nodes: MenuDTO[]) => {
				for (const node of nodes) {
					if (node.sub && node.sub.length > 0) {
						keys.push(node.id?.toString() ?? "");
						traverse(node.sub);
					}
				}
			};
			traverse(treeNodes);
			return keys;
		};

		// 左侧树展开keys（默认可展开节点全展开，剔除手动折叠的）
		const leftExpandedKeys = computed(() => {
			const collapsedSet = new Set(detailData.leftCollapsedKeys);
			return collectExpandableKeys(detailData.treeDataSource).filter(
				(key) => !collapsedSet.has(key),
			);
		});

		// 右侧树展开keys（默认可展开节点全展开，剔除手动折叠的）
		const rightExpandedKeys = computed(() => {
			const collapsedSet = new Set(detailData.rightCollapsedKeys);
			return collectExpandableKeys(detailData.treeDataSource).filter(
				(key) => !collapsedSet.has(key),
			);
		});

		// 按关键字递归过滤树节点（保留匹配节点及其祖先路径）
		const filterTreeByKeyword = (
			treeNodes: MenuDTO[],
			keyword: string,
		): MenuDTO[] => {
			const trimKeyword = keyword.trim();
			if (trimKeyword === "") {
				return treeNodes;
			}
			const result: MenuDTO[] = [];
			for (const node of treeNodes) {
				const isMatch = (node.menuName ?? "").includes(trimKeyword);
				const filteredChildren =
					node.sub && node.sub.length > 0
						? filterTreeByKeyword(node.sub, trimKeyword)
						: [];
				if (isMatch) {
					// 自身匹配，保留完整子级
					result.push(node);
				} else if (filteredChildren.length > 0) {
					// 自身不匹配但子孙匹配，仅保留匹配分支
					result.push({ ...node, sub: filteredChildren });
				}
			}
			return result;
		};

		// 构建左侧树数据（全部节点，已拥有节点勾选置灰禁用）
		const buildLeftTreeData = (
			treeNodes: MenuDTO[],
			targetKeys: string[] = [],
		): any[] => {
			return treeNodes.map(({ sub, ...props }) => {
				const nodeKey = props.id?.toString() ?? "";
				return {
					...props,
					key: nodeKey,
					title: props.menuName ?? "无名称",
					// 已拥有（右侧存在）的节点置灰禁用，保持勾选
					disabled: targetKeys.includes(nodeKey),
					children:
						sub && sub.length > 0 ? buildLeftTreeData(sub, targetKeys) : [],
				};
			});
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
						// 仅上下文祖先（未拥有）禁用，避免无效穿梭
						disabled: !isSelected,
						children: filteredChildren,
					});
				}
			}
			return result;
		};

		// 递归收集指定keys的全部祖先节点keys
		const findAncestorKeys = (
			treeNodes: MenuDTO[],
			targetKeySet: Set<string>,
		): string[] => {
			const ancestors: string[] = [];
			const traverse = (nodes: MenuDTO[], path: string[]): boolean => {
				let found = false;
				for (const node of nodes) {
					const nodeKey = node.id?.toString() ?? "";
					let childFound = false;
					if (node.sub && node.sub.length > 0) {
						childFound = traverse(node.sub, [...path, nodeKey]);
					}
					if (targetKeySet.has(nodeKey)) {
						// 命中目标节点，收集其全部祖先
						ancestors.push(...path);
						found = true;
					} else if (childFound) {
						found = true;
					}
				}
				return found;
			};
			traverse(treeNodes, []);
			return [...new Set(ancestors)];
		};

		// 递归收集指定keys的全部子孙节点keys
		const findDescendantKeys = (
			treeNodes: MenuDTO[],
			targetKeySet: Set<string>,
		): string[] => {
			const descendants: string[] = [];
			// 收集子树全部节点keys
			const collectSubtreeKeys = (nodes: MenuDTO[]) => {
				for (const node of nodes) {
					descendants.push(node.id?.toString() ?? "");
					if (node.sub && node.sub.length > 0) {
						collectSubtreeKeys(node.sub);
					}
				}
			};
			// 遍历查找目标节点，命中则收集其全部子孙
			const traverse = (nodes: MenuDTO[]) => {
				for (const node of nodes) {
					const nodeKey = node.id?.toString() ?? "";
					if (targetKeySet.has(nodeKey)) {
						// 命中目标节点，收集其子树全部后代
						if (node.sub && node.sub.length > 0) {
							collectSubtreeKeys(node.sub);
						}
					} else if (node.sub && node.sub.length > 0) {
						traverse(node.sub);
					}
				}
			};
			traverse(treeNodes);
			return [...new Set(descendants)];
		};

		// 归一化Tree勾选事件返回的keys（check-strictly为对象格式，兼容数组格式）
		const normalizeCheckedKeys = (
			checked: TreeCheckStrictlyEvent | any[],
		): string[] => {
			const raw = Array.isArray(checked) ? checked : (checked?.checked ?? []);
			return raw.map((key) => key.toString());
		};

		// 左侧树勾选回调（勾选/取消父级级联全部子孙，仅更新待穿梭状态并联动穿梭向右按钮亮起）
		const onLeftTreeCheck = (
			checked: TreeCheckStrictlyEvent | any[],
			e: any,
			onItemSelect: (key: string, selected: boolean) => void,
		) => {
			let checkedKeys = normalizeCheckedKeys(checked);

			// 当前操作节点key及其勾选状态
			const eventKey = (e?.node?.eventKey ?? e?.node?.key)?.toString() ?? "";
			const isCheckedNow = e?.checked ?? e?.node?.checked ?? false;

			if (eventKey !== "") {
				// 操作节点的全部子孙keys
				const descendantKeys = findDescendantKeys(
					detailData.treeDataSource,
					new Set([eventKey]),
				);
				if (isCheckedNow) {
					// 勾选父级 → 级联勾选全部子孙（快速授予整个子树权限）
					checkedKeys = [...new Set([...checkedKeys, ...descendantKeys])];
				} else {
					// 取消父级 → 级联取消全部子孙
					const descendantSet = new Set(descendantKeys);
					checkedKeys = checkedKeys.filter((key) => !descendantSet.has(key));
				}
			}

			// 剔除已拥有keys（右侧已存在，勾选置灰不可操作），得到本次待穿梭keys
			const haveSet = new Set(detailData.transferHave);
			const newPendingKeys = checkedKeys.filter((key) => !haveSet.has(key));

			// 与Transfer内部选中状态同步（控制中间穿梭按钮亮起/置灰）
			for (const key of newPendingKeys) {
				if (!detailData.leftCheckedKeys.includes(key)) {
					onItemSelect(key, true);
				}
			}
			for (const key of detailData.leftCheckedKeys) {
				if (!newPendingKeys.includes(key)) {
					onItemSelect(key, false);
				}
			}

			detailData.leftCheckedKeys = newPendingKeys;
		};

		// 右侧树勾选回调（勾选后联动穿梭向左按钮亮起，不直接移动）
		const onRightTreeCheck = (
			checked: TreeCheckStrictlyEvent | any[],
			_e: any,
			onItemSelect: (key: string, selected: boolean) => void,
		) => {
			const checkedKeys = normalizeCheckedKeys(checked);

			// 与Transfer内部选中状态同步（控制中间穿梭按钮亮起/置灰）
			for (const key of checkedKeys) {
				if (!detailData.rightCheckedKeys.includes(key)) {
					onItemSelect(key, true);
				}
			}
			for (const key of detailData.rightCheckedKeys) {
				if (!checkedKeys.includes(key)) {
					onItemSelect(key, false);
				}
			}

			detailData.rightCheckedKeys = checkedKeys;
		};

		// 左侧树展开/折叠回调（记录手动折叠的节点）
		const onLeftExpand = (_keys: any, info: any) => {
			const eventKey =
				(info?.node?.eventKey ?? info?.node?.key)?.toString() ?? "";
			if (eventKey === "") {
				return;
			}
			if (info?.expanded) {
				// 展开节点：从折叠集合移除
				detailData.leftCollapsedKeys = detailData.leftCollapsedKeys.filter(
					(key) => key !== eventKey,
				);
			} else if (!detailData.leftCollapsedKeys.includes(eventKey)) {
				// 折叠节点：加入折叠集合
				detailData.leftCollapsedKeys = [
					...detailData.leftCollapsedKeys,
					eventKey,
				];
			}
		};

		// 右侧树展开/折叠回调（记录手动折叠的节点）
		const onRightExpand = (_keys: any, info: any) => {
			const eventKey =
				(info?.node?.eventKey ?? info?.node?.key)?.toString() ?? "";
			if (eventKey === "") {
				return;
			}
			if (info?.expanded) {
				detailData.rightCollapsedKeys = detailData.rightCollapsedKeys.filter(
					(key) => key !== eventKey,
				);
			} else if (!detailData.rightCollapsedKeys.includes(eventKey)) {
				detailData.rightCollapsedKeys = [
					...detailData.rightCollapsedKeys,
					eventKey,
				];
			}
		};

		// 穿梭框内部过滤（自定义树渲染，恒为true，实际搜索由@search事件处理）
		const transferFilterOption = (
			_inputValue: string,
			_option: TransferData,
		) => {
			return true;
		};

		// 穿梭框搜索回调
		const transferHandleSearch = (direction: string, value: string) => {
			if (direction === "left") {
				detailData.leftSearchValue = value;
			} else {
				detailData.rightSearchValue = value;
			}
		};

		// 穿梭框变化处理（点击中间穿梭按钮后执行实际移动）
		const transferHandleChange = (
			targetKeys: string[],
			direction: string,
			moveKeys: string[],
		) => {
			if (direction === "right") {
				// 向右穿梭：新增勾选keys，并自动补齐缺失的祖先节点
				const ancestorKeys = findAncestorKeys(
					detailData.treeDataSource,
					new Set(moveKeys),
				);
				const keysToAdd = [...new Set([...moveKeys, ...ancestorKeys])].filter(
					(key) => !targetKeys.includes(key),
				);
				detailData.transferHave = [...targetKeys, ...keysToAdd];
				// 清空左侧待穿梭勾选状态（已勾选节点保持勾选并置灰）
				detailData.leftCheckedKeys = [];
			} else {
				// 向左穿梭：移除勾选keys（左侧对应节点恢复为未勾选可选择状态）
				const descendantKeys = findDescendantKeys(
					detailData.treeDataSource,
					new Set(moveKeys),
				);
				const keysToRemove = new Set([...moveKeys, ...descendantKeys]);
				detailData.transferHave = targetKeys.filter(
					(key) => !keysToRemove.has(key),
				);
				// 清空右侧勾选状态
				detailData.rightCheckedKeys = [];
			}
			// 同步表单数据
			detailData.form.menuIds = detailData.transferHave.toString();
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
			transferRef,
			rules,
			getById,
			handleCancel,
			handleOk,
			menuTreeData,
			rightTreeData,
			leftExpandedKeys,
			rightExpandedKeys,
			onLeftTreeCheck,
			onRightTreeCheck,
			onLeftExpand,
			onRightExpand,
			transferFilterOption,
			transferHandleSearch,
			transferHandleChange,
		};
	},
});
</script>

<style scoped>
@import "@src/assets/css/button.css";

</style>

