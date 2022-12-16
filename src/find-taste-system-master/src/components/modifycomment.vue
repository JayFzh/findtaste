<template>
    <div class="commentContainer">
      <div class="comment-left">
        <img style="width:30px;height:30px;" src="../assets/logo.jpg" alt=""></div>
      <div class="comment-right">
        <div>
          <h1>{{ commentInfo.username }}{{':'}}</h1>
          <p>{{ commentInfo.commentbody }}</p>
          
          <el-button v-if="isShow" @click.stop="modify"  style="margin-left: 10px;margin-right: 5px;margin-top: 10px">修改</el-button>
          
          <el-button v-if="isShow" @click.stop="reject"  style="margin-right: 10px;margin-top: 10px"> 删除</el-button>
        </div>
        <p>{{ commentInfo.time }}</p>
        <div v-if="isEdit" class="editBox">
            <!-- 改修改框样式去下面style里 -->
            <input v-model="backUp" class="ipt" />
              <button :class="['btn','btn1']" @click.stop="submit">提交</button>
              <button :class="['btn','btn1']"  @click.stop="cancel">取消</button>
          </div>
      </div>
    </div> <!-- /container -->
  </template>
  
  <script>
  import {modifyRespond, deleteRespond} from "@/utils/api";
  import {ElMessage} from "element-plus";
  
  export default {
    name: 'commentModule',
    data () {
      return {
        isEdit: false,
        backUp: "",
      }
    },
    methods: {
     cancel() {
      this.isEdit = false;
     },
      modify(){
        this.isEdit = true
      },
      submit(){
        let data={
          'commentId': this.commentInfo.id,
          'commentBody':this.backUp
        }
        console.log(data)
        modifyRespond(data).then(res => {
          console.log('接受响应===', res);
          this.isLoading = false;
          if (res.status === 200) {
            ElMessage.success('成功修改');
            this.$emit('modify',this.index)
            this.$props.isShow = !this.$props.isShow
          } else {
            ElMessage.error(res.statusText);
          }
        })
      },
      reject(){
        let data={
          'commentId': this.commentInfo.id
        }
        deleteRespond(data).then(res => {
          console.log('拒绝响应===', res);
          this.isLoading = false;
          if (res.status === 200) {
            ElMessage.success('成功拒绝');
            this.$emit('reject',this.index)
          } else {
            ElMessage.error(res.statusText);
          }
        })
        this.$emit('reject',this.index)
      }
    },
    props:['commentInfo','index','isShow']
  
  }
  </script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
  .commentContainer{
    display: flex;
    flex-direction: row;
    align-items: center;
    margin:10px 0;
  }
  .commentContainer> .comment-left{
    margin-right: 20px;
  }
  .commentContainer> .comment-right{
    display: flex;
    flex-direction: column;
    justify-content: space-between;
  }
  .commentContainer> .comment-right >div{
    display: flex;
    flex-direction: row;
    align-items: center;
  }
  .commentContainer> .comment-right >div >h1{
    padding: 0;
    margin: 0;
    font-size: 15px;
    line-height: 15px;
    opacity: 0.9;
  }
  .commentContainer> .comment-right>div >p{
    padding: 0;
    margin: 0;
    font-size: 10px;
    line-height: 10px;
    opacity: 0.8;
    margin-left: 5px;
  }
  .commentContainer> .comment-right>p{
    padding: 0;
    margin: 0;
    font-size: 10px;
    opacity: 0.8;
    margin-top: 10px;
  }
  </style>