<template>
  <a-result
      status="404"
      title="404"
      sub-title="抱歉，您访问的页面不存在！"
  >
    <template #extra>
      <a-button type="primary"  @click="goHome">返回首页</a-button>
    </template>
  </a-result>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useRouter } from "vue-router";

export default defineComponent({
	name: "Error_404",
	setup() {
		const router = useRouter();
		const goHome = () => {
			router.push("/home");
		};
		return { goHome };
	},
});
</script>
