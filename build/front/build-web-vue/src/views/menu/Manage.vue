<template>
  <div>
    <a-form
        ref="searchForm"
        class="search-form"
        :model="searchFormData"
    >
      <a-row>
        <a-col :span="12" class="div-left">
          <a-form-item>
            <a-space>
              <a-button class="ant-btn-cyan" :loading="loadingState" @click="loading">
                <icon-font type="icon-reload" v-if="!loadingState"/>
                刷新
              </a-button>
              <a-button class="ant-btn-cyan" :loading="loadingState" @click="query">
                <icon-font type="icon-search" v-if="!loadingState"/>
                查询
              </a-button>
              <a-button class="ant-btn-sunset" :loading="loadingState"
                        @click="()=>searchForm?.resetFields()">
                <icon-font type="icon-clear" v-if="!loadingState"/>
                清除
              </a-button>
            </a-space>
          </a-form-item>
        </a-col>
        <a-col :span="12" class="div-right">
          <a-form-item>
            <a-space>
              <a-button class="ant-btn-green" :loading="loadingState" @click="handleShowDetail(0)">
                <icon-font type="icon-plus-circle" v-if="!loadingState"/>
                新增
              </a-button>
              <a-popconfirm
                  title="您确定要删除选中数据嘛？"
                  ok-text="确定"
                  cancel-text="取消"
                  @confirm="deleteAll"
              >
                <template #icon>
                  <icon-font type="icon-question-circle"/>
                </template>
                <a-button class="ant-btn-dust" :loading="loadingState">
                  <icon-font type="icon-delete" v-if="!loadingState"/>
                  删除
                </a-button>
              </a-popconfirm>
            </a-space>
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="16">
        <a-col :span="6">
          <a-form-item label="菜单名称" name="menuName">
            <a-input v-model:value="searchFormData.menuName" placeholder="菜单名称" allowClear/>
          </a-form-item>
        </a-col>
        <a-col :span="6">
          <a-form-item label="状态" name="state">
            <a-select v-model:value="searchFormData.state">
              <a-select-option :value=0>正常</a-select-option>
              <a-select-option :value=99>删除</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="6">
        </a-col>
        <a-col :span="6">
        </a-col>
      </a-row>
    </a-form>
    <div class="search-result">
      <a-table
          :columns="columns"
          :row-key="(record:MenuDTO) => record.id"
          :row-selection="rowSelection"
          :pagination="pagination"
          :loading="loadingState"
          :data-source="searchResult.dataSource"
          @change="handleTableChange"
          @resizeColumn="handleResizeColumn"
          size="small"
          bordered
      >
        <template #bodyCell="{ column,record }">
          <template v-if="column.dataIndex === 'menuUrl'">
            <a-tooltip placement="top" :title="record.menuUrl">
              {{ record.menuUrl }}
            </a-tooltip>
          </template>
          <template v-if="column.dataIndex === 'parentId'">
            <template v-for="menu in searchResult.dataSource">
              <template v-if="menu.id === record.parentId">
                {{ menu.menuName }}
              </template>
            </template>
          </template>
          <template v-if="column.dataIndex === 'isTurn'">
            <span v-if="record[column.dataIndex] === 1" style="color: #52c41a">
              是
            </span>
            <span v-if="record[column.dataIndex] !== 1" style="color: #f5222d">
              否
            </span>
          </template>
          <template v-if="column.dataIndex === 'isHome'">
            <span v-if="record[column.dataIndex] === 1" style="color: #52c41a">
              是
            </span>
            <span v-if="record[column.dataIndex] !== 1" style="color: #f5222d">
              否
            </span>
          </template>
          <template v-if="column.dataIndex === 'action'">
              <span>
                <a-divider type="vertical"/>
                  <a-button class="ant-btn-daybreak" @click="handleShowDetail(record.id)">
                    修改
                  </a-button>
                <a-divider type="vertical"/>
                  <a-popconfirm
                      title="您确定要删除选中数据嘛？"
                      ok-text="确定"
                      cancel-text="取消"
                      @confirm="deleteById(record.id)"
                  >
                    <template #icon>
                      <icon-font type="icon-question-circle"/>
                    </template>
                    <a-button class="ant-btn-dust">删除</a-button>
                  </a-popconfirm>
                <a-divider type="vertical"/>
              </span>
          </template>
        </template>
      </a-table>
    </div>
    <div>
      <menu-detail
          ref="menuDetail"
          v-model:open="menuDetailVisible"
          @query="query"
      />
    </div>
  </div>
</template>

<script lang="ts">
import {
	computed,
	defineComponent,
	onMounted,
	reactive,
	ref,
	toRefs,
	unref,
} from "vue";
import {
	FormInstance,
	message,
	Table,
	TablePaginationConfig,
} from "ant-design-vue";
import IconFont from "@src/assets/iconfont/icon";
import { getPageSort, getSerializableSort } from "@src/apis/commons/method";
import { deleteMenu, queryMenu } from "@src/apis/capability/service";
import type { MenuDTO } from "@src/apis/capability/dto";
import MenuDetail from "@src/views/menu/Detail.vue";
import useResponsiveTableColumnWidth from "@src/hooks/use-responsive-table-column-width.ts";

export default defineComponent({
	// 页面名称
	name: "MenuManage",
	// 组件
	components: {
		// 图标库
		IconFont,
		// 详情页
		MenuDetail,
	},
	// 执行
	setup() {
		//------------------------------------------------------------------------------------------------------------------参数
		// 表格列-计算前
		const initialColumns = [
			{
				title: "菜单名称",
				dataIndex: "menuName",
				width: "10%",
				sorter: {
					multiple: 1,
				},
			},
			{
				title: "菜单地址",
				dataIndex: "menuUrl",
				width: "10%",
				sorter: {
					multiple: 2,
				},
			},
			{
				title: "菜单图标",
				dataIndex: "menuIcon",
				width: "8%",
				sorter: {
					multiple: 3,
				},
			},
			{
				title: "页面名称",
				dataIndex: "pageName",
				width: "10%",
				sorter: {
					multiple: 4,
				},
			},
			{
				title: "跳转",
				dataIndex: "isTurn",
				width: "5%",
				sorter: {
					multiple: 5,
				},
			},
			{
				title: "首页",
				dataIndex: "isHome",
				width: "5%",
				sorter: {
					multiple: 6,
				},
			},
			{
				title: "排序",
				dataIndex: "menuOrder",
				width: "5%",
				sorter: {
					multiple: 7,
				},
				defaultSortOrder: "ascend",
			},
			{
				title: "父级菜单",
				dataIndex: "parentId",
				width: "10%",
				sorter: {
					multiple: 8,
				},
			},
			{
				title: "修改人",
				dataIndex: "updaterName",
				width: "7%",
				sorter: {
					multiple: 9,
				},
			},
			{
				title: "修改时间",
				dataIndex: "updateTime",
				width: "11%",
				sorter: {
					multiple: 10,
				},
			},
			{
				dataIndex: "action",
				title: "操作",
				fixed: "right",
			},
		];

		// 表格列-计算后
		const { computedColumns: columns } =
			useResponsiveTableColumnWidth(initialColumns);

		// 分页配置
		const pagination = reactive<TablePaginationConfig>({
			// 总数
			total: 0,
			// 当前页
			current: 1,
			// 每页多少条
			pageSize: 10,
			// 显示去往第几页
			showQuickJumper: true,
			// 显示每页多少条
			showSizeChanger: true,
			// 分页条大小
			size: "default",
			// 显示总数等
			showTotal: (total: number, range: any) =>
				`第 ${range[0]} 至 ${range[1]} 条，共计 ${total} 条`,
		});

		// 行选择配置
		const rowSelection = computed(() => {
			return {
				// 存储选择内容的参数
				selectedRowKeys: unref(manageData.selectedRowKeys),
				// 选择后触发
				onChange: (selectedRowKeys: []) => {
					manageData.selectedRowKeys = selectedRowKeys;
				},
				// 下方统一设置下拉，所以去除全选和反选文字
				hideDefaultSelections: true,
				selections: [
					// 全选
					Table.SELECTION_ALL,
					// 反选
					Table.SELECTION_INVERT,
					// 清除
					Table.SELECTION_NONE,
					// 选择奇数行
					{
						key: "odd",
						text: "奇数行",
						onSelect: (changeRowKeys: []) => {
							manageData.selectedRowKeys = changeRowKeys.filter((_, index) => {
								return index % 2 === 0;
							});
						},
					},
					// 选择偶数行
					{
						key: "even",
						text: "偶数行",
						onSelect: (changeRowKeys: []) => {
							manageData.selectedRowKeys = changeRowKeys.filter((_, index) => {
								return index % 2 !== 0;
							});
						},
					},
				],
			};
		});

		// 查询表单DOM
		const searchForm = ref<FormInstance>();

		// 详情DOM
		const menuDetail = ref();

		// 管理页面数据
		const manageData = reactive({
			// 排序
			pageSorts: "",
			// 选中行数据Key
			selectedRowKeys: [],
			// 搜索条件表单
			searchFormData: {
				// 名称
				menuName: "",
				// 状态
				state: 0,
			},
			// 搜索结果集合
			searchResult: {
				// 数据
				dataSource: ref<MenuDTO[]>([]),
			},
			// 是否加载状态
			loadingState: false,
			// 详情页显示
			menuDetailVisible: false,
		});

		//------------------------------------------------------------------------------------------------------------------方法
		// 调整列宽触发
		const handleResizeColumn = (w: number, col: any) => {
			col.width = w;
		};

		// 获取数据
		const loading = async () => {
			// 开始
			manageData.loadingState = true;
			// 调用查询
			const result = await queryMenu(
				pagination.current !== undefined ? pagination.current - 1 : 0,
				pagination.pageSize !== undefined ? pagination.pageSize : 10,
				manageData.pageSorts,
				manageData.searchFormData.menuName,
				manageData.searchFormData.state,
			);
			// 处理结果
			if (result !== undefined) {
				manageData.searchResult.dataSource = result.content;
				pagination.total = result.totalElements;
				pagination.current = result.number + 1;
				pagination.pageSize = result.size;
				manageData.selectedRowKeys = [];
			}
			// 结束
			manageData.loadingState = false;
		};

		// 加载完成触发事件
		onMounted(async () => {
			manageData.pageSorts = "menuOrder-ascend";
			await loading();
		});

		// 分页、排序、筛选变化时触发
		const handleTableChange = async (
			paginationInfo: { pageSize: number; current: number },
			_: any,
			sorter: any,
		) => {
			manageData.pageSorts = getSerializableSort(getPageSort(sorter));
			pagination.current = paginationInfo.current;
			pagination.pageSize = paginationInfo.pageSize;
			await loading();
		};

		// 查询
		const query = async () => {
			pagination.current = 1;
			await loading();
		};

		// 删除全部
		const deleteAll = async () => {
			if (manageData.selectedRowKeys.length > 0) {
				// 开始
				manageData.loadingState = true;
				// 删除
				const result = await deleteMenu(manageData.selectedRowKeys.join(","));
				if (result !== undefined && result > 0) {
					message.success("成功删除 " + result + " 条数据！");
					await query();
				}
				// 结束
				manageData.loadingState = false;
			} else {
				message.warn("请选择要删除的数据！");
			}
		};

		// 删除行
		const deleteById = async (ids: string) => {
			if (ids) {
				// 开始
				manageData.loadingState = true;
				// 删除
				const result = await deleteMenu(ids);
				if (result !== undefined && result > 0) {
					message.success("成功删除 " + result + " 条数据！");
					await query();
				}
				// 结束
				manageData.loadingState = false;
			} else {
				message.error("删除失败！");
			}
		};

		// 显示详情
		const handleShowDetail = (id: number) => {
			manageData.menuDetailVisible = true;
			menuDetail.value.getById(id);
		};

		//------------------------------------------------------------------------------------------------------------------返回
		return {
			// 基础方法
			...toRefs(manageData),
			columns,
			pagination,
			rowSelection,
			handleResizeColumn,
			loading,
			handleTableChange,
			searchForm,
			query,
			deleteAll,
			deleteById,
			menuDetail,
			handleShowDetail,
			// 添加方法
		};
	},
});
</script>

<style scoped>
@import "@src/assets/css/button.css";
@import "@src/assets/css/manage.css";

</style>