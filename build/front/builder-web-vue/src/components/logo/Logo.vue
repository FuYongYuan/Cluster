<template>
    <div class="logo">
        <img src="~@/assets/images/logo.png" alt="">
        <h3 v-show="!collapsed" class="title">AiFuWaiWai</h3>
    </div>
</template>

<script lang="ts">
export default {
    name: "Logo",
    props: {
        collapsed: {
            type: Boolean
        }
    }
}
</script>

<style lang="less" scoped>
.logo {
  display: flex;
  align-items: center;
  height: 64px;
  padding-left: 24px;
  overflow: hidden;
  line-height: 64px;
  white-space: nowrap;
  border-bottom: 1px solid rgba(255, 255, 255, 0.65);

  img {
    height: 32px;
    margin-right: 24px;
  }

  .title {
    margin-bottom: 0;
    color: white;
  }
}
</style>
