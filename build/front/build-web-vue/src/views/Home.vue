<template>
  <a-alert
      v-if="visible"
      message="测试测试测试测试测试测试测试测试测试测试测试测试"
      type="success"
      closable
      :after-close="handleClose"
  />
  <div :style="{ background: '#fff' }">
    <a-button type="primary" @click="notification">测试通知</a-button>
    <br/>
    顶部
    <br/>
    Really
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    long
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    ...
    <br/>
    content
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import { showInfoNotification } from "@src/antd/notification";

export default defineComponent({
	name: "Home",
	setup() {
		//------------------------------------------------------------------------------------------------------------------参数
		//
		const visible = ref<boolean>(true);

		//------------------------------------------------------------------------------------------------------------------方法
		//
		const handleClose = () => {
			visible.value = false;
		};

		const notificationOnClick = (): void => {
			console.log("notificationOnClick");
		};

		const notificationOnClose = (): void => {
			console.log("notificationOnClose");
		};

		const notification = () => {
			showInfoNotification({
				message: "测试标题",
				description: "测试内容",
				onClick: notificationOnClick,
				onClose: notificationOnClose,
			});
		};

		//------------------------------------------------------------------------------------------------------------------返回
		return {
			notification,
			visible,
			handleClose,
		};
	},
});
</script>
