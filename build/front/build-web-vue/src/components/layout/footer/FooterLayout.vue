<template>
    <a-layout-footer class="app-footer"> FYY</a-layout-footer>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
	name: "FooterLayout",
});
</script>

<style scoped>
.app-footer {
  height: 48px;
  text-align: center;
  background: #fff;
  border-top: solid 1px #001529;
}
</style>