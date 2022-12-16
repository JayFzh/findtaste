<template>
  <div style="max-width: 400px;margin-left: 300px;padding-top: 10px" class="container">
    <div style="padding-bottom: 20px" class="text-container">
      <span>味道描述</span>
      <br/>
      <!-- <m-textarea  v-model="postText"  placeholder='请输入发布内容' :rows='6' :maxlength='200'/> -->
      <div class="input-box">
          <input
              autocomplete="off"
              type="text"
              v-model="textbox"
              placeholder="请输入发布内容"
          />
        </div>
    </div>
    <div style="padding-bottom: 20px" class="select-container">
      <select-box :select="selectInfo" @changeOption="selectChange"></select-box>
    </div>

    <span>图片描述</span>
    <el-upload
        ref="refUpload"
        :auto-upload="false"
        :limit="5"
        class="upload-files-box"
        drag
        :action="url+uploadUrl"
        multiple
        :headers="upLoadHeaders"
        :on-success="upLoadSuccess"
        v-model:file-list="fileList"
        :on-change="handleChange"
        :on-remove="handleRemove"

    >
      <el-icon class="el-icon--upload">
        <upload-filled />
      </el-icon>
      <div class="el-upload__text">拖曳文件至此或点击选择要上传的文件</div>
      <template #tip>
        <div class="el-upload__tip">
          每个文件大小不超过5M，每次最多上传5个文件。
        </div>
      </template>
    </el-upload>
    <el-button
        class="upLoadBtn"
        type="success"
        @click="submitUpload"
        :disabled="fileList.length === 0"
    >
      上传到服务器
    </el-button>
  </div>
</template>
<script setup name="UploadFilesBox">

import { ref, reactive } from 'vue'
import service from '../utils/network'
import { ElMessage } from 'element-plus'
// import MTextarea from './textArea'
import selectBox from './select'
import store from "@/store";
import {submitUp} from "@/utils/api";


const url=service.defaults.baseURL
const uploadUrl='/upload'
let tasteOption=''
// let postText = ref('')
// let postText = ''
let textbox = ''
let selectInfo = [{value:'广东风味'}, {value:'江浙沪风味'},{value:'湖南风味'},{value:'四川风味'},{value:'北京风味'}]
const fileList = ref([])



const upLoadHeaders = reactive({
  Authorization: 'Bearer ' + window.localStorage.getItem('token')
  // 'Content-Type': 'multipart/form-data', // 不要此参数，不要此参数，不要此参数
})

const refUpload = ref()
const selectChange = option => {
  tasteOption = option
}

const submitUpload = () => {
  let data={
    'id':store.state.userInfo.data.name,
    'taste':tasteOption,
    'body':textbox
  }
  console.log(data)
  submitUp(data).then(res=>{
    if(res.status === 200){
      ElMessage.success('上传成功');
    }
  })
  
  // console.log(tasteOption)
  // console.log(postText)
  // refUpload.value.append('id',store.state.userInfo.data.name)
  // refUpload.value.append('taste',tasteOption)
  // refUpload.value.append('body',postText)
  // refUpload.value.submit()
  // console.log("11")
}

const upLoadSuccess = () => {
  // console.log(response)
  refUpload.value.clearFiles()
  ElMessage.success('上传成功！')
}

const handleChange = (file, files) => {
  fileList.value = files
  // console.log(files)
}
const handleRemove = (file, files) => {
  fileList.value = files
  // console.log(files)
}



</script>

<style lang="scss" scoped>
span {
  margin: auto;
  width: 20px;
  height: 30px;
  line-height: 30px;
  text-align: center;
  font-size: 15px;
  font-weight: bold;
  color: transparent;
  background: linear-gradient(45deg,yellowgreen, pink, #03a9f4);
  background-size: cover;
  -webkit-background-clip: text;
}
:deep(.upload-files-box) {
  .el-upload {
    --el-upload-dragger-padding-horizontal: 40px;
    --el-upload-dragger-padding-vertical: 10px;
    width: 100%;
    left: 50%;


    .el-upload-dragger {
      padding: var(--el-upload-dragger-padding-horizontal)
      var(--el-upload-dragger-padding-vertical);
      background-color: var(--el-fill-color-blank);
      border: 1px dashed var(--el-border-color);
      border-radius: 6px;
      box-sizing: border-box;
      text-align: center;
      cursor: pointer;
      position: relative;
      overflow: hidden;
      width: auto;
      min-width: 180px;

      .el-icon {
        width: auto;
        height: auto;

        svg {
          height: 4em;
          width: 4em;
        }
      }
    }
  }
}

.upLoadBtn {
  margin-top: 20px;
  margin-left: 50%;
  transform: translateX(-50%);
}
/** 默认样式 */
.input-border-style{
  border:1px solid #47c9FF;
  border-radius:3px;
  padding-left: 10px;
}
/** el-input disabled时的文字颜色*/
.el-input.is-disabled .el-input__inner{
  color:#d61616;
}
/** el-input disabled时的背景和边框*/
.el-input.is-disabled{
  background:#dbdada;
  border: 1px solid #868686;
}
/** el-input 正常模式下、readonly模式下的文字颜色 */
.el-input__inner{
  color:#00ccff;
}


</style>