<template>
    <a-layout-header class="header app-header">
        <a-row>
            <a-col></a-col>
            <a-col>
                <div>
                    <a-dropdown>
            <span class="ant-dropdown-link">
              <a-avatar :src="headImgUrl"/>
              {{ managerName }}
              <icon-font type="icon-down"/>
            </span>
                        <!--  收纳部分template  -->
                        <template #overlay>
                            <a-menu>
                                <a-menu-item @click="handleShowPersonalInfo">个人信息</a-menu-item>
                                <a-menu-item @click="handleShowChangePassword">修改密码</a-menu-item>
                                <a-menu-item @click="logoutClick">退出登录</a-menu-item>
                            </a-menu>
                        </template>
                    </a-dropdown>
                </div>
            </a-col>
        </a-row>
        <div>
            <manager-detail-drawer
                    ref="managerDetailDrawer"
                    v-model:visible="managerDetailDrawerVisible"
            />
        </div>
        <div>
            <manager-change-password-drawer
                    ref="managerChangePasswordDrawer"
                    v-model:visible="managerChangePasswordDrawerVisible"
            />
        </div>
    </a-layout-header>
</template>

<script lang="ts">
import {defineComponent, ref} from "vue";
import {LocalStorageHeadImgUrl, LocalStorageManagerName,} from "@/apis/commons/constant";
import {logout} from "@/apis/authorization/service";
import {useRouter} from "vue-router";
import {message} from "ant-design-vue";
import IconFont from "@/assets/iconfont/icon";
import ManagerDetailDrawer from "@/views/manager/DetailDrawer.vue";
import ManagerChangePasswordDrawer from "@/views/manager/ChangePasswordDrawer.vue";

export default defineComponent({
    // 页面名称
    name: "HeaderLayout",
    // 组件
    components: {
        // 图标库
        IconFont,
        // 用户详情抽屉式
        ManagerDetailDrawer,
        // 修改密码抽屉式
        ManagerChangePasswordDrawer,
    },

    setup() {
        //------------------------------------------------------------------------------------------------------------------参数
        // 获取路由
        const router = useRouter();
        // 头像地址
        const headImgUrl = localStorage.getItem(LocalStorageHeadImgUrl);
        // 管理员名称
        const managerName = localStorage.getItem(LocalStorageManagerName);
        // 个人详情页显示
        const managerDetailDrawerVisible = ref(false);
        // 个人详情DOM
        const managerDetailDrawer = ref();
        // 个人详情页显示
        const managerChangePasswordDrawerVisible = ref(false);
        // 个人详情DOM
        const managerChangePasswordDrawer = ref();

        //------------------------------------------------------------------------------------------------------------------方法
        // 登出
        const logoutClick = async () => {
            if (await logout()) {
                message.success("登出成功");
            }
            // 登出成功跳转登录页
            await router.replace("/login");
        };

        // 打开个人信息页
        const handleShowPersonalInfo = () => {
            managerDetailDrawerVisible.value = true;
            managerDetailDrawer.value.getByJwtToken();
        }

        // 打开个人信息页
        const handleShowChangePassword = () => {
            managerChangePasswordDrawerVisible.value = true;
            managerChangePasswordDrawer.value.getByJwtToken();
        }

        //------------------------------------------------------------------------------------------------------------------返回
        return {
            headImgUrl,
            managerName,
            logoutClick,
            managerDetailDrawerVisible,
            managerDetailDrawer,
            handleShowPersonalInfo,
            managerChangePasswordDrawerVisible,
            managerChangePasswordDrawer,
            handleShowChangePassword,
        };
    },
});
</script>

<style lang="less" scoped>
.app-header {
  background: #fff;
  border-bottom: solid 1px #001529;

  .ant-row {
    justify-content: space-between;
  }

  .ant-dropdown-link {
    display: block;
    cursor: pointer;
  }
}
</style>
