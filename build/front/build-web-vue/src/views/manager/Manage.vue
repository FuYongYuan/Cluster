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
        <a-col :span="5">
          <a-form-item label="账号" name="account">
            <a-input v-model:value="searchFormData.account" placeholder="账号" allowClear/>
          </a-form-item>
        </a-col>
        <a-col :span="5">
          <a-form-item label="手机号码" name="mobile">
            <a-input v-model:value="searchFormData.mobile" placeholder="手机号码" allowClear/>
          </a-form-item>
        </a-col>
        <a-col :span="5">
          <a-form-item label="邮箱" name="mail">
            <a-input v-model:value="searchFormData.mail" placeholder="邮箱" allowClear/>
          </a-form-item>
        </a-col>
        <a-col :span="5">
          <a-form-item label="管理员名称" name="managerName">
            <a-input v-model:value="searchFormData.managerName" placeholder="管理员名称" allowClear/>
          </a-form-item>
        </a-col>
        <a-col :span="4">
          <a-form-item label="状态" name="state">
            <a-select v-model:value="searchFormData.state">
              <a-select-option :value=0>正常</a-select-option>
              <a-select-option :value=1>冻结</a-select-option>
              <a-select-option :value=99>删除</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
    <div class="search-result">
      <a-table
          :columns="columns"
          :row-key="(record:ManagerDTO) => record.id"
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
          <template v-if="column.dataIndex === 'sex'">
            <span v-if="record[column.dataIndex] === 0" style="color: #f39c12">
              保密
            </span>
            <span v-if="record[column.dataIndex] === 1" style="color: #2980b9">
              男
            </span>
            <span v-if="record[column.dataIndex] === 2" style="color: #e74c3c">
              女
            </span>
          </template>
          <template v-if="column.dataIndex === 'action'">
              <span>
                <a-divider type="vertical"/>
                  <a-button class="ant-btn-daybreak" @click="handleShowDetail(record.id)">
                  修改
                  </a-button>
                <a-divider v-if="!searchFormData.showRecover" type="vertical"/>
                  <a-popconfirm
                      v-if="!searchFormData.showRecover"
                      title="您确定要冻结选中数据嘛？"
                      ok-text="确定"
                      cancel-text="取消"
                      @confirm="freezeById(record.id)"
                  >
                    <template #icon>
                      <icon-font type="icon-minus-circle"/>
                    </template>
                    <a-button class="ant-btn-sunset">冻结</a-button>
                  </a-popconfirm>
                <a-divider v-if="searchFormData.showRecover" type="vertical"/>
                  <a-popconfirm
                      v-if="searchFormData.showRecover"
                      title="您确定要恢复选中数据嘛？"
                      ok-text="确定"
                      cancel-text="取消"
                      @confirm="recoverFreezeById(record.id)"
                  >
                    <template #icon>
                      <icon-font type="icon-rollback"/>
                    </template>
                    <a-button class="ant-btn-green">恢复</a-button>
                  </a-popconfirm>
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
      <manager-detail
          ref="managerDetail"
          v-model:open="managerDetailVisible"
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
import {
	deleteManager,
	freezeManager,
	queryManager,
	recoverFreezeManager,
} from "@src/apis/member/service";
import type { ManagerDTO } from "@src/apis/member/dto";
import ManagerDetail from "@src/views/manager/Detail.vue";

export default defineComponent({
	// 页面名称
	name: "ManagerManage",
	// 组件
	components: {
		// 图标库
		IconFont,
		// 参数详情页
		ManagerDetail,
	},
	// 数据
	data() {
		return {
			// 表格列
			columns: [
				{
					title: "账号",
					dataIndex: "account",
					resizable: true,
					width: "10%",
					sorter: {
						multiple: 1,
					},
				},
				{
					title: "名称",
					dataIndex: "managerName",
					width: "10%",
					sorter: {
						multiple: 2,
					},
				},
				{
					title: "邮箱",
					dataIndex: "mail",
					resizable: true,
					width: "15%",
					ellipsis: true,
					sorter: {
						multiple: 3,
					},
				},
				{
					title: "手机号码",
					dataIndex: "mobile",
					ellipsis: true,
					width: "10%",
					sorter: {
						multiple: 4,
					},
				},
				{
					title: "年龄",
					dataIndex: "age",
					ellipsis: true,
					width: "5%",
					sorter: {
						multiple: 5,
					},
				},
				{
					title: "性别",
					dataIndex: "sex",
					ellipsis: true,
					width: "5%",
					sorter: {
						multiple: 6,
					},
				},
				{
					title: "修改人",
					dataIndex: "updaterName",
					width: "7%",
					sorter: {
						multiple: 7,
					},
				},
				{
					title: "修改时间",
					dataIndex: "updateTime",
					width: "13%",
					sorter: {
						multiple: 8,
					},
					defaultSortOrder: "descend",
				},
				{
					dataIndex: "action",
					title: "操作",
					fixed: "right",
				},
			],
		};
	},
	// 执行
	setup() {
		//------------------------------------------------------------------------------------------------------------------参数
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
		const managerDetail = ref();

		// 管理页面数据
		const manageData = reactive({
			// 排序
			pageSorts: "",
			// 选中行数据Key
			selectedRowKeys: [],
			// 搜索条件表单
			searchFormData: {
				// 账号
				account: "",
				// 手机号码
				mobile: "",
				// 邮箱
				mail: "",
				// 管理员名称
				managerName: "",
				// 状态
				state: 0,
				// 显示恢复
				showRecover: false,
			},
			// 搜索结果集合
			searchResult: {
				// 数据
				dataSource: ref<ManagerDTO[]>([]),
			},
			// 是否加载状态
			loadingState: false,
			// 参数详情页显示
			managerDetailVisible: false,
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
			const result = await queryManager(
				pagination.current !== undefined ? pagination.current - 1 : 0,
				pagination.pageSize !== undefined ? pagination.pageSize : 10,
				manageData.pageSorts,
				manageData.searchFormData.account,
				manageData.searchFormData.mobile,
				manageData.searchFormData.mail,
				manageData.searchFormData.managerName,
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
			// 判断是否显示恢复按钮
			manageData.searchFormData.showRecover =
				manageData.searchFormData.state === 1;
			// 结束
			manageData.loadingState = false;
		};

		// 加载完成触发事件
		onMounted(async () => {
			manageData.pageSorts = "updateTime-descend";
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
				const result = await deleteManager(
					manageData.selectedRowKeys.join(","),
				);
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
				const result = await deleteManager(ids);
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

		// 冻结
		const freezeById = async (ids: string) => {
			if (ids) {
				// 开始
				manageData.loadingState = true;
				// 冻结
				const result = await freezeManager(ids);
				if (result !== undefined && result > 0) {
					message.success("成功冻结 " + result + " 条数据！");
					await query();
				}
				// 结束
				manageData.loadingState = false;
			} else {
				message.error("冻结失败！");
			}
		};

		// 解冻
		const recoverFreezeById = async (ids: string) => {
			if (ids) {
				// 开始
				manageData.loadingState = true;
				// 冻结
				const result = await recoverFreezeManager(ids);
				if (result !== undefined && result > 0) {
					message.success("成功解冻 " + result + " 条数据！");
					await query();
				}
				// 结束
				manageData.loadingState = false;
			} else {
				message.error("解冻失败！");
			}
		};

		// 显示详情
		const handleShowDetail = (id: number) => {
			manageData.managerDetailVisible = true;
			managerDetail.value.getById(id);
		};

		//------------------------------------------------------------------------------------------------------------------返回
		return {
			// 基础方法
			...toRefs(manageData),
			pagination,
			rowSelection,
			handleResizeColumn,
			loading,
			handleTableChange,
			searchForm,
			query,
			deleteAll,
			deleteById,
			managerDetail,
			handleShowDetail,
			freezeById,
			recoverFreezeById,
			// 添加方法
		};
	},
});
</script>

<style scoped>
@import "@src/assets/css/button.css";
@import "@src/assets/css/manage.css";

</style>