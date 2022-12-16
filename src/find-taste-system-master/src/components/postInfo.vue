<template>
  <div class="containerx" >
    <div class="body">
      <div class="left"><img src="../assets/logo.jpg" alt=""></div>
      <div class="right">
        <span class="username" >{{this.$props.postInfo.username}}</span>
        <span class="posttime">{{ this.$props.postInfo.time }}</span>
        <span clss="flavour">{{ this.$props.postInfo.flavour}}</span>
        <div v-if="!postInfo.state">
          <span class="introduction">{{ this.$props.postInfo.introduction }}</span>
        </div>
        <div v-if="postInfo.state">
          <span class="introduction">{{ "已完成" }}</span>
        </div>
<!--        <img-list :imgs=" this.$props.postInfo.imgList"></img-list>-->

      </div>
      <el-button @click.stop="deletePost" style="margin-right: 10px;margin-top: 5px">删除</el-button>
    </div>
    <div class="footer">
      <div @click="toggleCommentShowFlag()"><i class="iconfont iconpinglun"></i>响应数：{{ this.$props.postInfo.respondNum }}</div>
    </div>
    <div v-if="respondShowFlag" class="commentBody">
      <commentForRespond v-for="(respondInfo,index) in respondList"
               @accept="accept"
               @reject="reject"
               :key="index" :index="index"
               :isShow="true"
               :commentInfo="respondInfo" style="max-width: 500px"/>
    </div>
  </div>

</template>
<script>

import {deletePost, getRespond} from "@/utils/api";
import {ElMessage} from "element-plus";
import store from "@/store";
import commentForRespond from "@/components/commentForRespond";

export default {
  data(){
    return{
      respondShowFlag:false,
      respondList:[
        {
          username: "nihao",
          commentbody: "nizaiganshenm",
          time: 11
        },
        {
          username: "nihao",
          commentbody: "nizaiganshenm",
          time: 22
        },
      ],

    }
  },
  components:{ commentForRespond },
  props:['postInfo','index'],
  methods:{
    accept(n){
      console.log(n)
      // this.respondList.splice(n+1,this.respondList.length-1-n)
      // this.respondList.splice(0,this.respondList.length-1)
    },
    reject(n){
      this.respondList.splice(n,1)
    },
    deletePost(){
      let params={
        'id': store.state.userInfo.data.name,
        'postId': this.postInfo.id
      }
      console.log(params)
      deletePost(params).then(res=>{
        console.log('删除post===', res);
        this.isLoading = false;
        if (res.status === 200) {
          ElMessage.success('删除成功');
          this.$emit('remove',this.index)
        } else {
          ElMessage.error(res.statusText);
        }
      })

    },
    toggleCommentShowFlag(){
      if(this.respondShowFlag===false){
        this.getRespond()
      }
      this.respondShowFlag=!this.respondShowFlag
    },
    getRespond(){
      let data= {
        List : this.$props.postInfo
      }
      getRespond(data).then(res=>{
        console.log('拉取评论===', res);
        if (res.data.status === 500) {
          ElMessage.success('拉取成功');
          this.respondList = res.data.data
        } else {
          ElMessage.error(res.statusText);
        }
      })
    }
  }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.containerx{
  display: flex;
  flex-direction: column;
  align-items: center;
  width:100%;
  background-color:rgba(255, 255, 255, 1);
  background-image: none;
  border-radius: 5px;
  padding-top: 20px;
  margin-bottom: 10px;
  opacity: 1;
  z-index: 1;
  border: 1px solid rgba(0,0,0,0.1);
}
.body{
  width: 100%;
  display:flex;
  border-bottom: 1px solid rgba(0,0,0,0.1);
  z-index: 1;
}
.body>.left{
  width: 13%;
  padding: 10px;
}
.body>.left>img{
  width: 100%;
  border-radius: 50%;
  cursor: pointer;
}
.body>.right{
  width: 80%;
  display: flex;
  flex-direction: column;
  padding:0 1px ;
}
.body>.right>.username{
  font-size: 16px;
  padding: 5px 0;
}
.body>.right>.posttime{
  font-size: 10px;
  opacity: 0.4;
}
/* .body>.right>.flavour{
  font-size: 16px;
  padding: 10px 0;
} */
.body>.right>.introduction{
  font-size: 18px;
  padding: 5px 0;
}
.body>.right>p{
  padding:10px 0;

}
.body>.right>.username{
  width: 100%;
}
.footer{
  display: flex;
  width: 100%;
  justify-content:space-around;
}
.footer > div{
  margin: 10px 0;
  width: 30%;
  height: 20px;
  text-align: center;
  cursor: pointer;
  font-size: 10px;
  line-height: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.footer > div:hover{
  color: rgb(226,107, 52);
}
.footer > div >i{
  padding: 5px;
}
.footer > div:nth-child(1){
  border-right:1px solid rgba(0,0,0,0.1);
}
.footer > div:nth-child(2){
  border-right:1px solid rgba(0,0,0,0.1);
}
.commentBody{
  border-top: 1px solid rgba(0,0,0,0.1);
  width: 100%;
  display: flex;
  flex-direction: column;
  /* height: 300px; */
  padding: 20px;
}
.commentBody >.writeComment{
  display: flex;
  flex-direction: column;
  border-bottom: 1px solid rgba(0,0,0,0.1);
  padding-bottom: 10px;
}
.commentBody >.writeComment >.wctop{
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-around;
}
.commentBody >.writeComment >.wctop >div{
  height: 30px;
  width: 100%;
  border:1px solid rgba(0,0,0,0.1);
  padding: 0 10px;
}
.commentBody >.writeComment >.wctop >div >input{
  width: 100%;
  height: 100%;
  outline: none;
}
.commentBody >.writeComment >.wcbottom{
  display: flex;
  flex-direction: row-reverse;
}
.commentBody >.writeComment >.wcbottom >div{
  /* width: 30px; */
  height: 25px;
  line-height: 25px;
  border-radius: 2px;
  background-color: rgb(250, 121, 55);
  margin-top: 10px;
  color: white;
  padding: 0 18px;
}
</style>