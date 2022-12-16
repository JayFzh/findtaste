<template>
  <div class="containerx" >
    <div class="body">
      <div class="left"><img src="../assets/logo.jpg" alt=""></div>
      <div class="right">
        <span class="username" >{{this.$props.postInfo.username}}</span>
        <span class="username" >{{this.$props.postInfo.commentbody}}</span>
        <span clss="flavour">{{ this.$props.postInfo.flavour}}</span>
        <span class="introduction">{{ this.$props.postInfo.introduction }}</span>
        <p>{{this.$props.postInfo.body}}</p>
        <img-list :imgs=" this.$props.postInfo.imgList"></img-list>
      </div>
    </div>
    <div class="footer">
      <div @click="toggleCommentShowFlag()"><i class="iconfont iconpinglun"></i>响应数：{{this.$props.postInfo.respondNum}}</div>
    </div>

    <div v-if="respondShowFlag" class="commentBody">
      <div v-if="isRespond"  class="writeComment">
        <div class="wctop">
          <div>
            <input v-model="commentText" type="text">
          </div>
        </div>
        <div class="wcbottom">
          <div @click="postRespond()">评论</div>
        </div>
      </div>
      <div v-if=(!isRespond)>
        <!-- /container -->
        <!-- Comment v-for="(respondInfo,index) in respondList" :key="index" :commentInfo="respondInfo" /-->
        <modifycomment v-for="(respondInfo,index) in respondList"
               @modify="modify"
               @reject="reject"
               :key="index" :index="index"
               :isShow="true"
               :commentInfo="respondInfo" style="max-width: 500px"/>
      </div>
    </div>

  </div>

</template>
<script>
//import Comment from "./commentModule";
import {getRespond, newComment} from "@/utils/api";
import {ElMessage} from "element-plus";
import modifycomment from "@/components/modifycomment";
import store from "@/store";



export default {
  data(){
    return{
      commentText:'',
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
  // Comment
  components:{modifycomment},
  props:['postInfo','index','isRespond','modifyRes'],
  methods:{
    postRespond(){
      if(!this.commentText){
        ElMessage.warning('内容不能为空')
        return
      }

      const data={
        'userid':store.state.userInfo.data.name,
        'xwdid':this.postInfo.id,
        'commentbody':this.commentText
      }
      console.log(data)
      newComment(data).then(res => {
        console.log('提交响应===', res);
        this.isLoading = false;
        if (res.data.status === 500) {
          ElMessage.success('提交成功');
          this.commentText=''
          this.respondList.splice(1,0,res.data.data)
          this.$emit('remove',this.index)
        } else {
          ElMessage.error(res.statusText);
        }
      })

    },
    deletePost(){
      this.$emit('remove',this.index)
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