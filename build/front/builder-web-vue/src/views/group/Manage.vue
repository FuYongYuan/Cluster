<template>
    <div>group manage</div>
</template>

<script lang="ts">
import {defineComponent} from "vue";

export default defineComponent({
    name: "GroupManage",
    created() {
        console.log("GroupManage 创建");
    },
    unmounted() {
        console.log("GroupManage 销毁");
    },
    activated() {
        console.log("GroupManage 激活");
    },
    deactivated() {
        console.log("GroupManage 停用");
    }
})
</script>