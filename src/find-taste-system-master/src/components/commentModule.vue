<template>
  <div class="commentContainer">
    <div class="comment-left">
      <img style="width:30px;height:30px;" src="../assets/logo.jpg" alt=""></div>
    <div class="comment-right">
      <div>
        <h1>{{ commentInfo.username }}{{':'}}</h1>
        <p>{{ commentInfo.commentbody }}</p>
        <el-button v-if="isShow" @click.stop="accept"  style="margin-right: 5px;margin-top: 5px">接受</el-button>
      </div>
      <p>{{ commentInfo.time }}</p>
    </div>
  </div> <!-- /container -->
</template>

<script>
export default {
  name: 'commentModule',
  data () {
    return {}
  },
  methods: {

  },
  props:['commentInfo']
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