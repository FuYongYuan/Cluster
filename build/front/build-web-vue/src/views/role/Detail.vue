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

        <a-form-item label="菜单权限" name="menuIds">
          <div class="transfer-wrapper">
            <a-transfer
                ref="menuTransferRef"
                :target-keys="menuTransferHave"
                :data-source="menuTransferData"
                show-search
                :filter-option="transferFilterOption"
                :render="(item:TransferData) => item.title"
                class="tree-transfer"
                @change="menuTransferHandleChange"
                @search="menuTransferHandleSearch"
                @selectChange="menuTransferHandleSelectChange"
            >
              <template #children="{ direction, onItemSelect }: any">
                <a-tree
                    v-if="direction === 'left'"
                    block-node
                    checkable
                    check-strictly
                    :selected-keys="[]"
                    :expanded-keys="menuLeftExpandedKeys"
                    :checked-keys="[...menuTransferHave, ...menuLeftCheckedKeys]"
                    :tree-data="menuLeftTreeData"
                    @expand="(keys: any, info: any) => onMenuExpand(keys, info, 'left')"
                    @check="(checked: any, e: any) => onMenuLeftTreeCheck(checked, e, onItemSelect)"
                    @select="(_keys: any, e: any) => onMenuLeftTreeSelect(e, onItemSelect)"
                />
                <a-tree
                    v-else
                    block-node
                    checkable
                    check-strictly
                    :selected-keys="[]"
                    :expanded-keys="menuRightExpandedKeys"
                    :checked-keys="menuRightCheckedKeys"
                    :tree-data="menuRightTreeData"
                    @expand="(keys: any, info: any) => onMenuExpand(keys, info, 'right')"
                    @check="(checked: any, e: any) => onMenuRightTreeCheck(checked, e, onItemSelect)"
                    @select="(_keys: any, e: any) => onMenuRightTreeSelect(e, onItemSelect)"
                />
              </template>
            </a-transfer>
            <icon-font type="icon-down" class="dropdown-trigger dropdown-trigger-left"/>
            <div class="panel-dropdown panel-dropdown-left">
              <a-button size="small" @click="menuSelectAll('left')">全选</a-button>
              <a-button size="small" @click="menuDeselectAll('left')">取消全选</a-button>
              <a-button size="small" @click="menuExpandAll('left')">全部展开</a-button>
              <a-button size="small" @click="menuCollapseAll('left')">全部折叠</a-button>
            </div>
            <icon-font type="icon-down" class="dropdown-trigger dropdown-trigger-right"/>
            <div class="panel-dropdown panel-dropdown-right">
              <a-button size="small" @click="menuSelectAll('right')">全选</a-button>
              <a-button size="small" @click="menuDeselectAll('right')">取消全选</a-button>
              <a-button size="small" @click="menuExpandAll('right')">全部展开</a-button>
              <a-button size="small" @click="menuCollapseAll('right')">全部折叠</a-button>
            </div>
          </div>
        </a-form-item>

        <a-form-item label="访问权限" name="apiIds">
          <div class="transfer-wrapper">
            <a-transfer
                ref="apiTransferRef"
                :target-keys="apiTransferHave"
                :data-source="apiTransferData"
                show-search
                :filter-option="transferFilterOption"
                :render="(item:TransferData) => item.title"
                class="tree-transfer"
                @change="apiTransferHandleChange"
                @search="apiTransferHandleSearch"
                @selectChange="apiTransferHandleSelectChange"
            >
              <template #children="{ direction, onItemSelect }: any">
                <a-tree
                    v-if="direction === 'left'"
                    block-node
                    checkable
                    check-strictly
                    :selected-keys="[]"
                    :expanded-keys="apiLeftExpandedKeys"
                    :checked-keys="apiLeftTreeCheckedKeys"
                    :tree-data="apiLeftTreeData"
                    @expand="(keys: any, info: any) => onApiExpand(keys, info, 'left')"
                    @check="(checked: any, e: any) => onApiLeftTreeCheck(checked, e, onItemSelect)"
                    @select="(_keys: any, e: any) => onApiLeftTreeSelect(e, onItemSelect)"
                />
                <a-tree
                    v-else
                    block-node
                    checkable
                    check-strictly
                    :selected-keys="[]"
                    :expanded-keys="apiRightExpandedKeys"
                    :checked-keys="apiRightCheckedKeys"
                    :tree-data="apiRightTreeData"
                    @expand="(keys: any, info: any) => onApiExpand(keys, info, 'right')"
                    @check="(checked: any, e: any) => onApiRightTreeCheck(checked, e, onItemSelect)"
                    @select="(_keys: any, e: any) => onApiRightTreeSelect(e, onItemSelect)"
                />
              </template>
            </a-transfer>
            <icon-font type="icon-down" class="dropdown-trigger dropdown-trigger-left"/>
            <div class="panel-dropdown panel-dropdown-left">
              <a-button size="small" @click="apiSelectAll('left')">全选</a-button>
              <a-button size="small" @click="apiDeselectAll('left')">取消全选</a-button>
              <a-button size="small" @click="apiExpandAll('left')">全部展开</a-button>
              <a-button size="small" @click="apiCollapseAll('left')">全部折叠</a-button>
            </div>
            <icon-font type="icon-down" class="dropdown-trigger dropdown-trigger-right"/>
            <div class="panel-dropdown panel-dropdown-right">
              <a-button size="small" @click="apiSelectAll('right')">全选</a-button>
              <a-button size="small" @click="apiDeselectAll('right')">取消全选</a-button>
              <a-button size="small" @click="apiExpandAll('right')">全部展开</a-button>
              <a-button size="small" @click="apiCollapseAll('right')">全部折叠</a-button>
            </div>
          </div>
        </a-form-item>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script lang="ts">
import type { RoleDTO } from "@src/apis/authorization/dto";
import { getRole, saveRole } from "@src/apis/authorization/service";
import type { ApiDTO, MenuDTO } from "@src/apis/capability/dto";
import { queryApiAll, queryMenuAllTree } from "@src/apis/capability/service";
import type {
	TransferData,
	TreeCheckStrictlyEvent,
} from "@src/apis/commons/dto";
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
 * API树分组节点（按className去重构建的父级Controller节点）
 */
type ApiGroupNode = {
	key: string;
	className: string;
	classExplain: string;
	children: ApiDTO[];
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
			apiTransferQueryData();
		});
		//------------------------------------------------------------------------------------------------------------------参数
		// 详情DOM
		const detail = ref<FormInstance>();

		// 菜单穿梭框DOM
		const menuTransferRef = ref<any>();

		// API穿梭框DOM
		const apiTransferRef = ref<any>();

		// 详情页面数据
		const detailData = reactive({
			// 表单
			form: ref<RoleDTO>({
				id: "",
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

			// API穿梭框拥有（右侧目标keys，仅API方法ID）
			apiTransferHave: ref<string[]>([]),
			// API穿梭框数据（拍平后供Transfer内部管理）
			apiTransferData: ref<TransferData[]>([]),
			// API原始平铺数据
			apiDataSource: ref<ApiDTO[]>([]),
			// API左侧树待穿梭勾选keys（已勾选，待点击向右穿梭）
			apiLeftCheckedKeys: ref<string[]>([]),
			// API右侧树勾选keys（已勾选，待点击向左穿梭）
			apiRightCheckedKeys: ref<string[]>([]),
			// API左侧树手动折叠的节点keys
			apiLeftCollapsedKeys: ref<string[]>([]),
			// API右侧树手动折叠的节点keys
			apiRightCollapsedKeys: ref<string[]>([]),
			// API左侧面板搜索关键字
			apiLeftSearchValue: ref(""),
			// API右侧面板搜索关键字
			apiRightSearchValue: ref(""),

			loadingState: false,
		});

		//------------------------------------------------------------------------------------------------------------------方法
		// 关闭方法
		const handleCancel = () => {
			// 关闭显示
			context.emit("update:open", false);
			// 清理表单
			detailData.form = {
				id: "",
			};
			detailData.menuTransferHave = [];
			detailData.apiTransferHave = [];
			// 清理穿梭勾选状态
			detailData.menuLeftCheckedKeys = [];
			detailData.menuRightCheckedKeys = [];
			detailData.apiLeftCheckedKeys = [];
			detailData.apiRightCheckedKeys = [];
			// 清理搜索关键字
			detailData.menuLeftSearchValue = "";
			detailData.menuRightSearchValue = "";
			detailData.apiLeftSearchValue = "";
			detailData.apiRightSearchValue = "";
			// 重置穿梭框内部选中状态（避免遗留待穿梭选中项导致按钮残留亮起）
			menuTransferRef.value?.handleSelectChange("left", []);
			menuTransferRef.value?.handleSelectChange("right", []);
			apiTransferRef.value?.handleSelectChange("left", []);
			apiTransferRef.value?.handleSelectChange("right", []);

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
					await message.success(result);
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
		const getById = async (searchId: string) => {
			if (searchId !== "") {
				// 开始
				detailData.loadingState = true;

				// 查询
				const result = await getRole(searchId);
				if (result !== undefined) {
					detailData.form = result;
					if (result.menuIds !== undefined && result.menuIds !== "") {
						detailData.menuTransferHave = result.menuIds.split(",");
					}
					if (result.apiIds !== undefined && result.apiIds !== "") {
						detailData.apiTransferHave = apiIdsToKeys(result.apiIds);
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

		// 菜单顶部全选多选框联动（内置header多选框勾选/取消时同步树勾选状态）
		const menuTransferHandleSelectChange = (
			sourceSelectedKeys: string[],
			targetSelectedKeys: string[],
		) => {
			detailData.menuLeftCheckedKeys = [...sourceSelectedKeys];
			detailData.menuRightCheckedKeys = [...targetSelectedKeys];
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

		// 菜单左侧树点击文字勾选回调（selected-keys恒为空，每次点击均为选中事件，按当前勾选状态取反）
		const onMenuLeftTreeSelect = (
			e: any,
			onItemSelect: (key: string, selected: boolean) => void,
		) => {
			const key = (e?.node?.eventKey ?? e?.node?.key)?.toString() ?? "";
			if (key === "") {
				return;
			}
			const isCurrentlyChecked =
				detailData.menuLeftCheckedKeys.includes(key) ||
				detailData.menuTransferHave.includes(key);
			if (isCurrentlyChecked) {
				detailData.menuLeftCheckedKeys = detailData.menuLeftCheckedKeys.filter(
					(k) => k !== key,
				);
				onItemSelect(key, false);
			} else if (!detailData.menuTransferHave.includes(key)) {
				detailData.menuLeftCheckedKeys = [
					...detailData.menuLeftCheckedKeys,
					key,
				];
				onItemSelect(key, true);
			}
		};

		// 菜单右侧树点击文字勾选回调
		const onMenuRightTreeSelect = (
			e: any,
			onItemSelect: (key: string, selected: boolean) => void,
		) => {
			const key = (e?.node?.eventKey ?? e?.node?.key)?.toString() ?? "";
			if (key === "") {
				return;
			}
			const isCurrentlyChecked = detailData.menuRightCheckedKeys.includes(key);
			if (isCurrentlyChecked) {
				detailData.menuRightCheckedKeys =
					detailData.menuRightCheckedKeys.filter((k) => k !== key);
				onItemSelect(key, false);
			} else {
				detailData.menuRightCheckedKeys = [
					...detailData.menuRightCheckedKeys,
					key,
				];
				onItemSelect(key, true);
			}
		};

		// 菜单全选（指定方向全部未拥有节点勾选）
		const menuSelectAll = (direction: "left" | "right") => {
			if (direction === "left") {
				const haveSet = new Set(detailData.menuTransferHave);
				const allKeys = detailData.menuTransferData
					.map((item) => item.key)
					.filter((key) => !haveSet.has(key));
				detailData.menuLeftCheckedKeys = allKeys;
				menuTransferRef.value?.handleSelectChange("left", allKeys);
			} else {
				const allKeys = [...detailData.menuTransferHave];
				detailData.menuRightCheckedKeys = allKeys;
				menuTransferRef.value?.handleSelectChange("right", allKeys);
			}
		};

		// 菜单取消全选
		const menuDeselectAll = (direction: "left" | "right") => {
			if (direction === "left") {
				detailData.menuLeftCheckedKeys = [];
				menuTransferRef.value?.handleSelectChange("left", []);
			} else {
				detailData.menuRightCheckedKeys = [];
				menuTransferRef.value?.handleSelectChange("right", []);
			}
		};

		// 菜单全部展开
		const menuExpandAll = (direction: "left" | "right") => {
			if (direction === "left") {
				detailData.menuLeftCollapsedKeys = [];
			} else {
				detailData.menuRightCollapsedKeys = [];
			}
		};

		// 菜单全部折叠
		const menuCollapseAll = (direction: "left" | "right") => {
			if (direction === "left") {
				detailData.menuLeftCollapsedKeys = collectMenuExpandableKeys(
					detailData.menuTreeDataSource,
				);
			} else {
				detailData.menuRightCollapsedKeys = collectMenuExpandableKeys(
					detailData.menuTreeDataSource,
				);
			}
		};

		//------------------------------------------------------------------------------------------------------------------API穿梭框
		// 生成API子级节点唯一key（className::methodName）
		const apiItemKey = (api: ApiDTO): string =>
			`${api.className ?? "未知Controller"}::${api.methodName ?? ""}`;

		// 查询所有API信息（平铺结构，前端按className分组构建树）
		const apiTransferQueryData = async () => {
			// 开始
			detailData.loadingState = true;

			// 查询
			const result = await queryApiAll();
			if (result !== undefined) {
				detailData.apiDataSource = result;
			}

			// 穿梭框数据（仅子级方法节点，父级分组节点仅作树渲染分组不参与Transfer内部计数）
			detailData.apiTransferData = [];
			for (const api of detailData.apiDataSource) {
				detailData.apiTransferData.push({
					key: apiItemKey(api),
					title: api.methodExplain ?? api.methodName ?? "无名称",
				});
			}

			// 结束
			detailData.loadingState = false;
		};

		// 按className去重分组构建API树结构（支持关键字过滤）
		const groupApiByClassName = (
			apiList: ApiDTO[],
			keyword: string,
		): ApiGroupNode[] => {
			const trimKeyword = keyword.trim();
			const groupMap = new Map<string, ApiGroupNode>();
			for (const api of apiList) {
				const className = api.className ?? "未知Controller";
				if (!groupMap.has(className)) {
					groupMap.set(className, {
						key: `class::${className}`,
						className: className,
						classExplain: api.classExplain ?? className,
						children: [],
					});
				}
				const group = groupMap.get(className);
				if (group) {
					const isChildMatch =
						trimKeyword === "" ||
						(api.methodExplain ?? "").includes(trimKeyword) ||
						(api.methodName ?? "").includes(trimKeyword);
					if (isChildMatch) {
						group.children.push(api);
					}
				}
			}
			const result: ApiGroupNode[] = [];
			for (const group of groupMap.values()) {
				const isParentMatch =
					trimKeyword !== "" &&
					(group.classExplain.includes(trimKeyword) ||
						group.className.includes(trimKeyword));
				if (isParentMatch) {
					// 父级匹配：无关键字过滤时children已全量，父级关键字匹配时补充全量子级
					if (group.children.length === 0) {
						group.children = detailData.apiDataSource.filter(
							(api) => (api.className ?? "未知Controller") === group.className,
						);
					}
					result.push(group);
				} else if (group.children.length > 0) {
					result.push(group);
				}
			}
			return result;
		};

		// API左侧树数据（全部分组节点，已拥有方法节点勾选置灰禁用）
		const apiLeftTreeData = computed(() => {
			const groups = groupApiByClassName(
				detailData.apiDataSource,
				detailData.apiLeftSearchValue,
			);
			return groups.map((group) => ({
				key: group.key,
				title: group.classExplain,
				disabled: false,
				children: group.children.map((api) => {
					const childKey = apiItemKey(api);
					return {
						key: childKey,
						title: api.methodExplain ?? api.methodName ?? "无名称",
						disabled: detailData.apiTransferHave.includes(childKey),
					};
				}),
			}));
		});

		// API右侧树数据（仅已选方法节点 + 父级分组上下文）
		const apiRightTreeData = computed(() => {
			const groups = groupApiByClassName(
				detailData.apiDataSource,
				detailData.apiRightSearchValue,
			);
			const result: any[] = [];
			for (const group of groups) {
				const selectedChildren = group.children.filter((api) =>
					detailData.apiTransferHave.includes(apiItemKey(api)),
				);
				if (selectedChildren.length > 0) {
					result.push({
						key: group.key,
						title: group.classExplain,
						// 父级仅作分组上下文展示，禁用不可操作
						disabled: true,
						children: selectedChildren.map((api) => ({
							key: apiItemKey(api),
							title: api.methodExplain ?? api.methodName ?? "无名称",
							disabled: false,
						})),
					});
				}
			}
			return result;
		});

		// API树全部可展开节点keys（父级分组节点）
		const collectApiExpandableKeys = (): string[] => {
			const classNames = [
				...new Set(
					detailData.apiDataSource.map(
						(api) => api.className ?? "未知Controller",
					),
				),
			];
			return classNames.map((className) => `class::${className}`);
		};

		// API左侧树展开keys（默认全展开，剔除手动折叠的）
		const apiLeftExpandedKeys = computed(() => {
			const collapsedSet = new Set(detailData.apiLeftCollapsedKeys);
			return collectApiExpandableKeys().filter((key) => !collapsedSet.has(key));
		});

		// API左侧树勾选keys（子级keys + 全部子级均已勾选的父级keys，父级仅用于视觉展示）
		const apiLeftTreeCheckedKeys = computed(() => {
			const checkedSet = new Set([
				...detailData.apiTransferHave,
				...detailData.apiLeftCheckedKeys,
			]);
			const parentCheckedKeys: string[] = [];
			const groupMap = new Map<string, { total: number; checked: number }>();
			for (const api of detailData.apiDataSource) {
				const className = api.className ?? "未知Controller";
				const key = `${className}::${api.methodName ?? ""}`;
				if (!groupMap.has(className)) {
					groupMap.set(className, { total: 0, checked: 0 });
				}
				const group = groupMap.get(className);
				if (group) {
					group.total++;
					if (checkedSet.has(key)) {
						group.checked++;
					}
				}
			}
			for (const [className, { total, checked }] of groupMap) {
				if (total > 0 && total === checked) {
					parentCheckedKeys.push(`class::${className}`);
				}
			}
			return [...checkedSet, ...parentCheckedKeys];
		});

		// API右侧树展开keys（默认全展开，剔除手动折叠的）
		const apiRightExpandedKeys = computed(() => {
			const collapsedSet = new Set(detailData.apiRightCollapsedKeys);
			return collectApiExpandableKeys().filter((key) => !collapsedSet.has(key));
		});

		// 收集指定父级分组节点下全部子级API方法keys
		const findApiChildKeys = (parentKey: string): string[] => {
			const className = parentKey.replace("class::", "");
			return detailData.apiDataSource
				.filter((api) => (api.className ?? "未知Controller") === className)
				.map((api) => apiItemKey(api));
		};

		// API左侧树勾选回调（勾选父级级联全部子级方法，仅更新待穿梭状态并联动穿梭向右按钮亮起）
		const onApiLeftTreeCheck = (
			_checked: TreeCheckStrictlyEvent | any[],
			e: any,
			onItemSelect: (key: string, selected: boolean) => void,
		) => {
			const eventKey = (e?.node?.eventKey ?? e?.node?.key)?.toString() ?? "";
			const isCheckedNow = e?.checked ?? e?.node?.checked ?? false;

			if (eventKey === "") {
				return;
			}

			let newCheckedKeys: string[];

			if (eventKey.startsWith("class::")) {
				// 操作节点为父级Controller分组，级联其全部子级方法
				const childKeys = findApiChildKeys(eventKey);
				if (isCheckedNow) {
					newCheckedKeys = [
						...new Set([...detailData.apiLeftCheckedKeys, ...childKeys]),
					];
				} else {
					const childSet = new Set(childKeys);
					newCheckedKeys = detailData.apiLeftCheckedKeys.filter(
						(key) => !childSet.has(key),
					);
				}
			} else {
				// 操作节点为子级API方法
				if (isCheckedNow) {
					newCheckedKeys = [...detailData.apiLeftCheckedKeys, eventKey];
				} else {
					newCheckedKeys = detailData.apiLeftCheckedKeys.filter(
						(key) => key !== eventKey,
					);
				}
			}

			// 剔除已拥有keys（右侧已存在，勾选置灰不可操作），得到本次待穿梭keys
			const haveSet = new Set(detailData.apiTransferHave);
			const newPendingKeys = newCheckedKeys.filter((key) => !haveSet.has(key));

			// 与Transfer内部选中状态同步（控制中间穿梭按钮亮起/置灰）
			for (const key of newPendingKeys) {
				if (!detailData.apiLeftCheckedKeys.includes(key)) {
					onItemSelect(key, true);
				}
			}
			for (const key of detailData.apiLeftCheckedKeys) {
				if (!newPendingKeys.includes(key)) {
					onItemSelect(key, false);
				}
			}

			detailData.apiLeftCheckedKeys = newPendingKeys;
		};

		// API右侧树勾选回调（勾选后联动穿梭向左按钮亮起，不直接移动）
		const onApiRightTreeCheck = (
			checked: TreeCheckStrictlyEvent | any[],
			_e: any,
			onItemSelect: (key: string, selected: boolean) => void,
		) => {
			// 父级分组节点已禁用，仅子级方法参与
			const checkedKeys = normalizeCheckedKeys(checked).filter(
				(key) => !key.startsWith("class::"),
			);

			for (const key of checkedKeys) {
				if (!detailData.apiRightCheckedKeys.includes(key)) {
					onItemSelect(key, true);
				}
			}
			for (const key of detailData.apiRightCheckedKeys) {
				if (!checkedKeys.includes(key)) {
					onItemSelect(key, false);
				}
			}

			detailData.apiRightCheckedKeys = checkedKeys;
		};

		// API树展开/折叠回调（左右共用，记录手动折叠的节点）
		const onApiExpand = (
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
					detailData.apiLeftCollapsedKeys =
						detailData.apiLeftCollapsedKeys.filter((key) => key !== eventKey);
				} else if (!detailData.apiLeftCollapsedKeys.includes(eventKey)) {
					detailData.apiLeftCollapsedKeys = [
						...detailData.apiLeftCollapsedKeys,
						eventKey,
					];
				}
			} else {
				if (info?.expanded) {
					detailData.apiRightCollapsedKeys =
						detailData.apiRightCollapsedKeys.filter((key) => key !== eventKey);
				} else if (!detailData.apiRightCollapsedKeys.includes(eventKey)) {
					detailData.apiRightCollapsedKeys = [
						...detailData.apiRightCollapsedKeys,
						eventKey,
					];
				}
			}
		};

		// API穿梭框搜索回调
		const apiTransferHandleSearch = (direction: string, value: string) => {
			if (direction === "left") {
				detailData.apiLeftSearchValue = value;
			} else {
				detailData.apiRightSearchValue = value;
			}
		};

		// API顶部全选多选框联动（内置header多选框勾选/取消时同步树勾选状态，剔除父级分组keys）
		const apiTransferHandleSelectChange = (
			sourceSelectedKeys: string[],
			targetSelectedKeys: string[],
		) => {
			detailData.apiLeftCheckedKeys = sourceSelectedKeys.filter(
				(key) => !key.startsWith("class::"),
			);
			detailData.apiRightCheckedKeys = targetSelectedKeys.filter(
				(key) => !key.startsWith("class::"),
			);
		};

		// API穿梭框变化处理（点击中间穿梭按钮后执行实际移动）
		const apiTransferHandleChange = (
			targetKeys: string[],
			direction: string,
			moveKeys: string[],
		) => {
			// 父级分组节点不参与穿梭和持久化，仅保留子级方法keys
			const apiMoveKeys = moveKeys.filter((key) => !key.startsWith("class::"));
			if (direction === "right") {
				detailData.apiTransferHave = [
					...new Set([...targetKeys, ...apiMoveKeys]),
				].filter((key) => !key.startsWith("class::"));
				detailData.apiLeftCheckedKeys = [];
			} else {
				detailData.apiTransferHave = targetKeys.filter(
					(key) => !apiMoveKeys.includes(key),
				);
				detailData.apiRightCheckedKeys = [];
			}
			// 将复合keys转换为实际API ID用于后端持久化
			detailData.form.apiIds = apiKeysToIds(detailData.apiTransferHave);
		};

		// API左侧树点击文字勾选回调（selected-keys恒为空，每次点击均为选中事件，按当前勾选状态取反）
		const onApiLeftTreeSelect = (
			e: any,
			onItemSelect: (key: string, selected: boolean) => void,
		) => {
			const key = (e?.node?.eventKey ?? e?.node?.key)?.toString() ?? "";
			if (key === "" || key.startsWith("class::")) {
				return;
			}
			const isCurrentlyChecked =
				detailData.apiLeftCheckedKeys.includes(key) ||
				detailData.apiTransferHave.includes(key);
			if (isCurrentlyChecked) {
				detailData.apiLeftCheckedKeys = detailData.apiLeftCheckedKeys.filter(
					(k) => k !== key,
				);
				onItemSelect(key, false);
			} else if (!detailData.apiTransferHave.includes(key)) {
				detailData.apiLeftCheckedKeys = [...detailData.apiLeftCheckedKeys, key];
				onItemSelect(key, true);
			}
		};

		// API右侧树点击文字勾选回调
		const onApiRightTreeSelect = (
			e: any,
			onItemSelect: (key: string, selected: boolean) => void,
		) => {
			const key = (e?.node?.eventKey ?? e?.node?.key)?.toString() ?? "";
			if (key === "" || key.startsWith("class::")) {
				return;
			}
			const isCurrentlyChecked = detailData.apiRightCheckedKeys.includes(key);
			if (isCurrentlyChecked) {
				detailData.apiRightCheckedKeys = detailData.apiRightCheckedKeys.filter(
					(k) => k !== key,
				);
				onItemSelect(key, false);
			} else {
				detailData.apiRightCheckedKeys = [
					...detailData.apiRightCheckedKeys,
					key,
				];
				onItemSelect(key, true);
			}
		};

		// API全选（指定方向全部未拥有子级方法勾选）
		const apiSelectAll = (direction: "left" | "right") => {
			if (direction === "left") {
				const haveSet = new Set(detailData.apiTransferHave);
				const allKeys = detailData.apiTransferData
					.map((item) => item.key)
					.filter((key) => !key.startsWith("class::") && !haveSet.has(key));
				detailData.apiLeftCheckedKeys = allKeys;
				apiTransferRef.value?.handleSelectChange("left", allKeys);
			} else {
				const allKeys = [...detailData.apiTransferHave];
				detailData.apiRightCheckedKeys = allKeys;
				apiTransferRef.value?.handleSelectChange("right", allKeys);
			}
		};

		// API取消全选
		const apiDeselectAll = (direction: "left" | "right") => {
			if (direction === "left") {
				detailData.apiLeftCheckedKeys = [];
				apiTransferRef.value?.handleSelectChange("left", []);
			} else {
				detailData.apiRightCheckedKeys = [];
				apiTransferRef.value?.handleSelectChange("right", []);
			}
		};

		// API全部展开
		const apiExpandAll = (direction: "left" | "right") => {
			if (direction === "left") {
				detailData.apiLeftCollapsedKeys = [];
			} else {
				detailData.apiRightCollapsedKeys = [];
			}
		};

		// API全部折叠
		const apiCollapseAll = (direction: "left" | "right") => {
			if (direction === "left") {
				detailData.apiLeftCollapsedKeys = collectApiExpandableKeys();
			} else {
				detailData.apiRightCollapsedKeys = collectApiExpandableKeys();
			}
		};

		// 复合keys → API ID字符串（保存用）
		const apiKeysToIds = (keys: string[]): string => {
			const ids: string[] = [];
			for (const key of keys) {
				const api = detailData.apiDataSource.find(
					(item) => apiItemKey(item) === key,
				);
				if (api) {
					ids.push(api.id.toString());
				}
			}
			return ids.join(",");
		};

		// API ID字符串 → 复合keys（回显用）
		const apiIdsToKeys = (apiIds: string): string[] => {
			const idSet = new Set(apiIds.split(","));
			return detailData.apiDataSource
				.filter((api) => idSet.has(api.id.toString()))
				.map((api) => apiItemKey(api));
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
		};

		//------------------------------------------------------------------------------------------------------------------返回
		return {
			...toRefs(detailData),
			detail,
			menuTransferRef,
			apiTransferRef,
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
			onMenuLeftTreeSelect,
			onMenuRightTreeSelect,
			menuSelectAll,
			menuDeselectAll,
			menuExpandAll,
			menuCollapseAll,
			transferFilterOption,
			menuTransferHandleSearch,
			menuTransferHandleSelectChange,
			menuTransferHandleChange,
			apiLeftTreeData,
			apiRightTreeData,
			apiLeftExpandedKeys,
			apiLeftTreeCheckedKeys,
			apiRightExpandedKeys,
			onApiLeftTreeCheck,
			onApiRightTreeCheck,
			onApiExpand,
			onApiLeftTreeSelect,
			onApiRightTreeSelect,
			apiSelectAll,
			apiDeselectAll,
			apiExpandAll,
			apiCollapseAll,
			apiTransferHandleSearch,
			apiTransferHandleSelectChange,
			apiTransferHandleChange,
		};
	},
});
</script>

<style scoped>
@import "@src/assets/css/button.css";

.transfer-wrapper {
  position: relative;
}

.tree-transfer :deep(.ant-transfer-list) {
  height: 250px;
}

.tree-transfer :deep(.ant-transfer-list-body) {
  flex: 1;
  overflow-y: auto;
}

.tree-transfer :deep(.ant-transfer-list-body-search-wrapper) {
  position: sticky;
  top: 0;
  z-index: 2;
  background: #fff;
}

.tree-transfer :deep(.ant-transfer-list-header-dropdown) {
  display: none;
}

.tree-transfer :deep(.ant-transfer-list-header-selected) {
  margin-left: 8px;
}

.dropdown-trigger {
  position: absolute;
  top: 0;
  z-index: 5;
  padding: 13px 10px 23px;
  font-size: 12px;
  color: rgba(0, 0, 0, 0.45);
  cursor: pointer;
}

.dropdown-trigger-left {
  left: calc(50% - 64px);
}

.dropdown-trigger-right {
  right: 4px;
}

.panel-dropdown {
  position: absolute;
  top: 30px;
  z-index: 10;
  display: none;
  flex-direction: column;
  gap: 4px;
  padding: 6px;
  background: #fff;
  border-radius: 6px;
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.12);
}

.panel-dropdown-left {
  right: calc(50% + 42px);
}

.panel-dropdown-right {
  right: 14px;
}

.dropdown-trigger-left:hover ~ .panel-dropdown-left,
.panel-dropdown-left:hover {
  display: flex;
}

.dropdown-trigger-right:hover ~ .panel-dropdown-right,
.panel-dropdown-right:hover {
  display: flex;
}

</style>

