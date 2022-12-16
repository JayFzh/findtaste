<template>
  <el-row class="tac">
    <el-col :span="24">

      <el-menu default-active="2" class="el-menu-vertical-demo" :collapse-transition="true"
                :collapse="false" :router="true" :default-openeds="opends"
               background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" @open="handleOpen" @close="handleClose">
        <el-sub-menu index="/home" @click="changePersonal" >
          <template #title >
            <el-icon>
              <document />
            </el-icon>
            <span>个人信息</span>
          </template>
          <el-menu-item index="/infoShow">
            <span>基本信息</span>
          </el-menu-item>
          <el-menu-item index="/changePwd" >
            <span>修改密码</span>
          </el-menu-item>
        </el-sub-menu>

        <el-sub-menu index="/home">
          <template #title>
            <el-icon>
              <icon-menu />
            </el-icon>
            <span>寻味道</span>
          </template>
          <el-menu-item index="/reqPost">
            <span>新的寻找</span>
          </el-menu-item>
          <el-menu-item index="/postInfo">
            <span>已经发布</span>
          </el-menu-item>
        </el-sub-menu>

        <el-sub-menu index="/home">
          <template #title>
            <el-icon>
              <document />
            </el-icon>
            <span>请品鉴</span>
          </template>
          <el-menu-item index="/findPostInfo" >
            <span>当前寻找</span>
          </el-menu-item>
          <el-menu-item index="/foundPost" >
            <span>已发布</span>
          </el-menu-item>
        </el-sub-menu>

        <el-sub-menu index="/home">
          <template #title>
            <el-icon>
              <document />
            </el-icon>
            <span>数据统计</span>
          </template>

          <el-menu-item index="/getTable" >
            <span>展示图表</span>
          </el-menu-item>

        </el-sub-menu>

      </el-menu>

    </el-col>
  </el-row>
  <el-row>

  </el-row>
</template>

<script>
import {
  Document,
  Menu as IconMenu,

} from '@element-plus/icons-vue'
import routes from '../router/index.js'
import { useStore } from 'vuex'
import {getInfo} from "@/utils/api";
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Home',
  components: { Document, IconMenu, },
  data() {
    return {
      store: useStore(),
      opends: ['1'],
      routes: routes,
      show_personal: true,
      test:''
    }
  },
  methods: {
    changePersonal(){
      this.show_personal = !this.show_personal
    },
    handleOpen() {
      console.log("111")
    },
    handleClose() {
      console.log("222")
    },
    getUserInfo(userId) {
      getInfo(userId).then(res=>{
        this.test = res.data()
      })
      return this.test
    }
  }
}
</script>


