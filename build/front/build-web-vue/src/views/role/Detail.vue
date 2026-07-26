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
              ref="menuTransferRef"
              :target-keys="menuTransferHave"
              :data-source="menuTransferData"
              show-search
              :filter-option="transferFilterOption"
              :render="(item:TransferData) => item.title"
              :show-select-all="false"
              class="tree-transfer"
              @change="menuTransferHandleChange"
              @search="menuTransferHandleSearch"
          >
            <template #children="{ direction, onItemSelect }: any">
              <a-tree
                  v-if="direction === 'left'"
                  block-node
                  checkable
                  check-strictly
                  :expanded-keys="menuLeftExpandedKeys"
                  :checked-keys="[...menuTransferHave, ...menuLeftCheckedKeys]"
                  :tree-data="menuLeftTreeData"
                  @expand="(keys: any, info: any) => onMenuExpand(keys, info, 'left')"
                  @check="(checked: any, e: any) => onMenuLeftTreeCheck(checked, e, onItemSelect)"
              />
              <a-tree
                  v-else
                  block-node
                  checkable
                  check-strictly
                  :expanded-keys="menuRightExpandedKeys"
                  :checked-keys="menuRightCheckedKeys"
                  :tree-data="menuRightTreeData"
                  @expand="(keys: any, info: any) => onMenuExpand(keys, info, 'right')"
                  @check="(checked: any, e: any) => onMenuRightTreeCheck(checked, e, onItemSelect)"
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
			menuTransferQueryData();
		});
		//------------------------------------------------------------------------------------------------------------------参数
		// 详情DOM
		const detail = ref<FormInstance>();

		// 菜单穿梭框DOM
		const menuTransferRef = ref<any>();

		// 详情页面数据
		const detailData = reactive({
			// 表单
			form: ref<RoleDTO>({
				id: -1,
			}),
			// 菜单穿梭框拥有（右侧目标keys）
			menuTransferHave: ref<string[]>([]),
			// 菜单穿梭框数据（拍平后供Transfer内部管理）
			menuTransferData: ref<TransferData[]>([]),
			// 菜单原始树数据
			menuTreeDataSource: ref<MenuDTO[]>([]),
			// 菜单左侧树待穿梭勾选keys（已勾选，待点击向右穿梭）
			menuLeftCheckedKeys: ref<string[]>([]),
			// 菜单右侧树勾选keys（已勾选，待点击向左穿梭）
			menuRightCheckedKeys: ref<string[]>([]),
			// 菜单左侧树手动折叠的节点keys（配合expanded-keys实现"默认全展开、用户可折叠"）
			menuLeftCollapsedKeys: ref<string[]>([]),
			// 菜单右侧树手动折叠的节点keys
			menuRightCollapsedKeys: ref<string[]>([]),
			// 菜单左侧面板搜索关键字
			menuLeftSearchValue: ref(""),
			// 菜单右侧面板搜索关键字
			menuRightSearchValue: ref(""),

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
			detailData.menuTransferHave = [];
			// 清理穿梭勾选状态
			detailData.menuLeftCheckedKeys = [];
			detailData.menuRightCheckedKeys = [];
			// 清理搜索关键字
			detailData.menuLeftSearchValue = "";
			detailData.menuRightSearchValue = "";
			// 重置穿梭框内部选中状态（避免遗留待穿梭选中项导致按钮残留亮起）
			menuTransferRef.value?.handleSelectChange("left", []);
			menuTransferRef.value?.handleSelectChange("right", []);

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
						detailData.menuTransferHave = result.menuIds.split(",");
					}
				}

				// 结束
				detailData.loadingState = false;
			}
		};

		//------------------------------------------------------------------------------------------------------------------菜单穿梭框
		// 查询所有菜单（树形结构）
		const menuTransferQueryData = async () => {
			// 开始
			detailData.loadingState = true;

			// 查询
			const result = await queryMenuAllTree();
			if (result !== undefined) {
				detailData.menuTreeDataSource = result;
			}

			// 穿梭框数据（递归拍平所有层级）
			detailData.menuTransferData = [];
			flattenMenuTree(detailData.menuTreeDataSource);

			// 结束
			detailData.loadingState = false;
		};

		// 递归拍平菜单树数据为穿梭框一维数据
		const flattenMenuTree = (list: MenuDTO[]) => {
			list.forEach((item) => {
				detailData.menuTransferData.push({
					key: item.id.toString(),
					title: item.menuName ?? "无名称",
				});
				if (item.sub && item.sub.length > 0) {
					flattenMenuTree(item.sub);
				}
			});
		};

		// 菜单左侧树数据（全部节点，已拥有节点勾选置灰，按搜索关键字过滤）
		const menuLeftTreeData = computed(() => {
			const filteredNodes = filterMenuTreeByKeyword(
				detailData.menuTreeDataSource,
				detailData.menuLeftSearchValue,
			);
			return buildMenuLeftTreeData(filteredNodes, detailData.menuTransferHave);
		});

		// 菜单右侧树数据（仅已选节点 + 祖先上下文，按搜索关键字过滤）
		const menuRightTreeData = computed(() => {
			const filteredNodes = filterMenuTreeByKeyword(
				detailData.menuTreeDataSource,
				detailData.menuRightSearchValue,
			);
			return buildMenuRightTreeData(filteredNodes, detailData.menuTransferHave);
		});

		// 收集菜单树全部可展开节点keys（拥有子级的节点）
		const collectMenuExpandableKeys = (treeNodes: MenuDTO[]): string[] => {
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

		// 菜单左侧树展开keys（默认可展开节点全展开，剔除手动折叠的）
		const menuLeftExpandedKeys = computed(() => {
			const collapsedSet = new Set(detailData.menuLeftCollapsedKeys);
			return collectMenuExpandableKeys(detailData.menuTreeDataSource).filter(
				(key) => !collapsedSet.has(key),
			);
		});

		// 菜单右侧树展开keys（默认可展开节点全展开，剔除手动折叠的）
		const menuRightExpandedKeys = computed(() => {
			const collapsedSet = new Set(detailData.menuRightCollapsedKeys);
			return collectMenuExpandableKeys(detailData.menuTreeDataSource).filter(
				(key) => !collapsedSet.has(key),
			);
		});

		// 按关键字递归过滤菜单树节点（保留匹配节点及其祖先路径）
		const filterMenuTreeByKeyword = (
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
						? filterMenuTreeByKeyword(node.sub, trimKeyword)
						: [];
				if (isMatch) {
					result.push(node);
				} else if (filteredChildren.length > 0) {
					result.push({ ...node, sub: filteredChildren });
				}
			}
			return result;
		};

		// 构建菜单左侧树数据（全部节点，已拥有节点勾选置灰禁用）
		const buildMenuLeftTreeData = (
			treeNodes: MenuDTO[],
			targetKeys: string[] = [],
		): any[] => {
			return treeNodes.map(({ sub, ...props }) => {
				const nodeKey = props.id?.toString() ?? "";
				return {
					...props,
					key: nodeKey,
					title: props.menuName ?? "无名称",
					disabled: targetKeys.includes(nodeKey),
					children:
						sub && sub.length > 0 ? buildMenuLeftTreeData(sub, targetKeys) : [],
				};
			});
		};

		// 构建菜单右侧树数据（仅保留已选节点及其祖先上下文）
		const buildMenuRightTreeData = (
			treeNodes: MenuDTO[],
			targetKeys: string[],
		): any[] => {
			const result: any[] = [];
			for (const node of treeNodes) {
				const nodeKey = node.id?.toString();
				const isSelected = targetKeys.includes(nodeKey);
				const filteredChildren =
					node.sub && node.sub.length > 0
						? buildMenuRightTreeData(node.sub, targetKeys)
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

		// 递归收集菜单树指定keys的全部祖先节点keys
		const findMenuAncestorKeys = (
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

		// 递归收集菜单树指定keys的全部子孙节点keys
		const findMenuDescendantKeys = (
			treeNodes: MenuDTO[],
			targetKeySet: Set<string>,
		): string[] => {
			const descendants: string[] = [];
			const collectSubtreeKeys = (nodes: MenuDTO[]) => {
				for (const node of nodes) {
					descendants.push(node.id?.toString() ?? "");
					if (node.sub && node.sub.length > 0) {
						collectSubtreeKeys(node.sub);
					}
				}
			};
			const traverse = (nodes: MenuDTO[]) => {
				for (const node of nodes) {
					const nodeKey = node.id?.toString() ?? "";
					if (targetKeySet.has(nodeKey)) {
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

		// 菜单左侧树勾选回调（勾选/取消父级级联全部子孙，仅更新待穿梭状态并联动穿梭向右按钮亮起）
		const onMenuLeftTreeCheck = (
			checked: TreeCheckStrictlyEvent | any[],
			e: any,
			onItemSelect: (key: string, selected: boolean) => void,
		) => {
			let checkedKeys = normalizeCheckedKeys(checked);

			const eventKey = (e?.node?.eventKey ?? e?.node?.key)?.toString() ?? "";
			const isCheckedNow = e?.checked ?? e?.node?.checked ?? false;

			if (eventKey !== "") {
				const descendantKeys = findMenuDescendantKeys(
					detailData.menuTreeDataSource,
					new Set([eventKey]),
				);
				if (isCheckedNow) {
					checkedKeys = [...new Set([...checkedKeys, ...descendantKeys])];
				} else {
					const descendantSet = new Set(descendantKeys);
					checkedKeys = checkedKeys.filter((key) => !descendantSet.has(key));
				}
			}

			const haveSet = new Set(detailData.menuTransferHave);
			const newPendingKeys = checkedKeys.filter((key) => !haveSet.has(key));

			for (const key of newPendingKeys) {
				if (!detailData.menuLeftCheckedKeys.includes(key)) {
					onItemSelect(key, true);
				}
			}
			for (const key of detailData.menuLeftCheckedKeys) {
				if (!newPendingKeys.includes(key)) {
					onItemSelect(key, false);
				}
			}

			detailData.menuLeftCheckedKeys = newPendingKeys;
		};

		// 菜单右侧树勾选回调（勾选后联动穿梭向左按钮亮起，不直接移动）
		const onMenuRightTreeCheck = (
			checked: TreeCheckStrictlyEvent | any[],
			_e: any,
			onItemSelect: (key: string, selected: boolean) => void,
		) => {
			const checkedKeys = normalizeCheckedKeys(checked);

			for (const key of checkedKeys) {
				if (!detailData.menuRightCheckedKeys.includes(key)) {
					onItemSelect(key, true);
				}
			}
			for (const key of detailData.menuRightCheckedKeys) {
				if (!checkedKeys.includes(key)) {
					onItemSelect(key, false);
				}
			}

			detailData.menuRightCheckedKeys = checkedKeys;
		};

		// 菜单树展开/折叠回调（左右共用，记录手动折叠的节点）
		const onMenuExpand = (
			_keys: any,
			info: any,
			direction: "left" | "right",
		) => {
			const eventKey =
				(info?.node?.eventKey ?? info?.node?.key)?.toString() ?? "";
			if (eventKey === "") {
				return;
			}
			if (direction === "left") {
				if (info?.expanded) {
					detailData.menuLeftCollapsedKeys =
						detailData.menuLeftCollapsedKeys.filter((key) => key !== eventKey);
				} else if (!detailData.menuLeftCollapsedKeys.includes(eventKey)) {
					detailData.menuLeftCollapsedKeys = [
						...detailData.menuLeftCollapsedKeys,
						eventKey,
					];
				}
			} else {
				if (info?.expanded) {
					detailData.menuRightCollapsedKeys =
						detailData.menuRightCollapsedKeys.filter((key) => key !== eventKey);
				} else if (!detailData.menuRightCollapsedKeys.includes(eventKey)) {
					detailData.menuRightCollapsedKeys = [
						...detailData.menuRightCollapsedKeys,
						eventKey,
					];
				}
			}
		};

		// 穿梭框内部过滤（自定义树渲染，恒为true，实际搜索由@search事件处理）
		const transferFilterOption = (
			_inputValue: string,
			_option: TransferData,
		) => {
			return true;
		};

		// 菜单穿梭框搜索回调
		const menuTransferHandleSearch = (direction: string, value: string) => {
			if (direction === "left") {
				detailData.menuLeftSearchValue = value;
			} else {
				detailData.menuRightSearchValue = value;
			}
		};

		// 菜单穿梭框变化处理（点击中间穿梭按钮后执行实际移动）
		const menuTransferHandleChange = (
			targetKeys: string[],
			direction: string,
			moveKeys: string[],
		) => {
			if (direction === "right") {
				const ancestorKeys = findMenuAncestorKeys(
					detailData.menuTreeDataSource,
					new Set(moveKeys),
				);
				const keysToAdd = [...new Set([...moveKeys, ...ancestorKeys])].filter(
					(key) => !targetKeys.includes(key),
				);
				detailData.menuTransferHave = [...targetKeys, ...keysToAdd];
				detailData.menuLeftCheckedKeys = [];
			} else {
				const descendantKeys = findMenuDescendantKeys(
					detailData.menuTreeDataSource,
					new Set(moveKeys),
				);
				const keysToRemove = new Set([...moveKeys, ...descendantKeys]);
				detailData.menuTransferHave = targetKeys.filter(
					(key) => !keysToRemove.has(key),
				);
				detailData.menuRightCheckedKeys = [];
			}
			detailData.form.menuIds = detailData.menuTransferHave.toString();
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
			menuTransferRef,
			rules,
			getById,
			handleCancel,
			handleOk,
			menuLeftTreeData,
			menuRightTreeData,
			menuLeftExpandedKeys,
			menuRightExpandedKeys,
			onMenuLeftTreeCheck,
			onMenuRightTreeCheck,
			onMenuExpand,
			transferFilterOption,
			menuTransferHandleSearch,
			menuTransferHandleChange,
		};
	},
});
</script>

<style scoped>
@import "@src/assets/css/button.css";

</style>

