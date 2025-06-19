<template>
  <div>
    <a-upload-dragger
        v-model:fileList="fileList"
        name="uploadFile"
        accept=".json"
        :multiple="true"
        :show-upload-list="false"
        :customRequest="uploadData"
        :before-upload="beforeUpload"
        @change="handleChange"
        @drop="handleDrop"
    >
      <p>
        <icon-font type="icon-cloud-upload" class="icon-cloud-upload"/>
      </p>
      <p>
        文件上传
      </p>
      <a-progress v-if="fileProgressShow" :percent="fileProgress" :steps="6" :show-info="false"/>
    </a-upload-dragger>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, ref, toRefs } from "vue";
import IconFont from "@src/assets/iconfont/icon";
import { message, UploadChangeParam } from "ant-design-vue";
import { uploadFormData } from "@src/apis/data/service";
import { BusinessTypeDataModel } from "@src/apis/commons/constant";
import { jsonFormatValidate } from "@src/utils/validate/regex";

export default defineComponent({
	// 页面名称
	name: "GroupDetail",
	// 组件
	components: {
		IconFont,
	},
	// 执行
	setup() {
		//------------------------------------------------------------------------------------------------------------------参数
		// 详情页面数据
		const detailData = reactive({
			// 文件列表
			fileList: ref<any>([]),
			// 加载状态
			loadingState: false,
			// 文件上传进度条
			fileProgress: 0,
			// 文件进度是否显示
			fileProgressShow: false,
		});
		//------------------------------------------------------------------------------------------------------------------方法
		const handleChange = (info: UploadChangeParam) => {
			// 必定要加判断，否则会报错
			if (info.event !== undefined) {
				// 当前进度
				detailData.fileProgress = info.event.percent;
			}
			if (info.file.status === "done") {
				detailData.fileProgressShow = false;
				// detailData.form.headImgUrl = info.file.response;
			}
		};

		const handleDrop = (e: DragEvent) => {
			console.log(e);
		};

		/**
		 * 验证图片正确性
		 *
		 * @param file 选中文件
		 */
		const beforeUpload = (file: any) => {
			const validate = jsonFormatValidate(file);
			if (validate !== undefined) {
				message.error(validate);
				return false;
			} else {
				return true;
			}
		};

		/**
		 * 文件上传
		 *
		 * @param options 上传相关内容
		 */
		const uploadData = async (options: any) => {
			const formData = new FormData();
			formData.append(options.filename, options.file);

			detailData.fileProgressShow = true;

			const result = await uploadFormData(
				BusinessTypeDataModel,
				formData,
				options,
			);

			if (result) {
				options.onSuccess(result);
			}
		};

		//------------------------------------------------------------------------------------------------------------------返回
		return {
			...toRefs(detailData),
			// 方法
			handleChange,
			handleDrop,
			beforeUpload,
			uploadData,
		};
	},
});
</script>

<style scoped>
@import "@src/assets/css/button.css";

.icon-cloud-upload {
  font-size: 40px
}
</style>