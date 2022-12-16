<template>
  <!-- <img alt="Vue logo" src="./assets/logo.png"> -->
  <div class="common-layout">
    <el-container>
      <el-header style="max-height: 300px">
        <div>
          <img src="../assets/logo.jpg" style="max-width: 120px">
          <span>寻味道-国内美食第一家交流平台</span>
        </div>
        <button :class="['btn','btn1']" @mouseenter="changeButton" @mouseleave="changeButton" @click.stop="logout">退出</button>
      </el-header>
      <el-container>
        <el-aside style="max-width: 300px;">
          <Home></Home>
        </el-aside>
        <el-main>
          <router-view> </router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
  <!--
    <div>
        <p>
          <router-link to="/home">Go to Home</router-link>


          <router-link to="/about">Go to about</router-link>
          <router-view></router-view>
        </p>
      </div>
    <HelloWorld msg="Welcome to Your Vue.js App"/> -->
</template>
<script>
// import HelloWorld from './components/HelloWorld.vue'
import Home from './home.vue'
//import router from '@/router/index'
import store from "@/store";
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Layout',
  components: {
    // HelloWorld
    Home
  },
  data() {
    return{
      change : false
    }

  },
  methods: {
    logout() {
      //router.push('/login')
      store.dispatch("userInfo/logout")
    },
    changeButton() {
      this.change = !this.change
    }
  }
}
</script>
<style lang="scss" scoped>
.home-container{
  height: 100%;
}
.el-header{
  background-color: #373d41;
  display: flex; //设置显示为flex布局
  justify-content: space-between;//设置为flex左右布局
  padding-left: 0px;//左内边距为0（Logo贴左边）
  align-items: center;//元素上下居中（防止右边按钮贴上下边）
  color: #fff;
  margin-bottom: 5px;

  .div{//内嵌的div样式
    display: flex;
    align-items: center;//Logo和文字上下居中
    .span {
      margin-left: 15px;//文字左侧设置间距，防止与Logo紧贴
    }
    .img {
      margin-left: 50px;
    }
  }
  .btn {
    /* 文字颜色 */
    color: #0099CC;
    /* 清除背景色 */
    background: transparent;
    /* 边框样式、颜色、宽度 */
    border: 2px solid #0099CC;
    /* 给边框添加圆角 */
    border-radius: 6px;
    /* 字母转大写 */
    border: none;
    color: white;
    padding: 12px 24px;
    text-align: center;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
    cursor: pointer;
    text-decoration: none;
    text-transform: uppercase;
  }
  .btn1 {
    background-color: white;
    color: black;
    border: 2px solid #008CBA;
  }
  /* 悬停样式 */
  .btn1:hover {
    background-color: #008CBA;
    color: white;
  }
}
.el-aside{
  background-color: #333744;
  height: calc(100vh - 100px);
}
.el-main{
  background-color: #eaedf1;
  height: calc(100vh - 100px);
  margin-left: 5px;

}
</style>
