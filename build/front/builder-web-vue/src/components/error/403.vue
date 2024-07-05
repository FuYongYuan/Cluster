<template>
    <a-result
            status="403"
            title="403"
            sub-title="Sorry, you are not authorized to access this page."
    >
        <template #extra>
            <a-button type="primary">Back Home</a-button>
        </template>
    </a-result>
</template>

<script lang="ts">
import {defineComponent} from "vue";

export default defineComponent({
    name: "403",
});
</script>
