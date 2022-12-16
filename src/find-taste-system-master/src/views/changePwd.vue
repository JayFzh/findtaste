<template>
  <div class="change-container">
    <div class="right_content" >
      <div class="input-box">

        <input
            autocomplete="off"
            type="password"
            class="input"
            v-model="oldPassWord"
            maxlength="20"
            @keyup.enter="register"
            placeholder="请输入旧密码"
        />
        <br/>
        <input
            autocomplete="off"
            type="password"
            class="input"
            v-model="newPassWord"
            maxlength="20"
            @keyup.enter="register"
            placeholder="请输入新密码"
        />
        <br/>
        <input
            autocomplete="off"
            type="password"
            class="input"
            v-model="newPassWord2"
            maxlength="20"
            @keyup.enter="register"
            placeholder="请再次确认密码"
        />
        <br/>
      </div>
      <Button
          class="changeBtn"
          @click.stop="changePwd">
        立即修改
      </Button>
    </div>
  </div>



</template>

<script>




import valid from "@/utils/valid";
import store from "@/store"
//import router from "@/router";
import {resetPwd} from "@/utils/api";
export default {
  components:{

  },
  data() {
    return {
      oldPassWord: '',
      newPassWord: '',
      newPassWord2: '',
    }

  },

  methods: {
    changePwd() {
      if (!valid.validPass(this.oldPassWord)) {
        this.$message.error("请输入正确的密码");
        return false;
      }  else if (!valid.validPass(this.newPassWord)){
        this.$message.error("确认密码有误");
        return false;
      } else if (this.newPassWord !== this.newPassWord2){
        this.$message.error("两次密码不一致");
        return false;
      }
      let data = {
        userId : store.state.userInfo.data.name,
        oldPwd : this.oldPassWord,
        newPwd : this.newPassWord,
        newPwd2: this.newPassWord2
      }
      resetPwd(data).then(res => {
        console.log("修改密码")
        if (res.data.status === 500) {
          this.$message.success('修改成功');
          this.$store.dispatch('userInfo/saveInfo', res.data);
        } else {
          this.$message.error(res.statusText);
        }
      })
    }

  },


}
</script>

<style lang="scss" scoped>
.change-container {

  //position: relative;
  /*关键*/
  //width: 100%;
  height: 50%;

  position: absolute;
  left: 45vw;
  top: 50%;
  -webkit-transform: translateY(-20%);
  transform: translateY(-40%);
  box-sizing: border-box;
  text-align: center;
  box-shadow: 0 1px 11px rgba(0, 0, 0, 0.3);
  border-radius: 2px;
    /*margin: 100px auto 0;*/
  width: 420px;
  background: #fff fixed;
  padding: 45px 35px;
    .option {
      text-align: left;
      margin-top: 18px;
      .checked {
        padding-left: 5px;
      }
      .forget-pwd, .goback {
        float: right;
        font-size: 14px;
        font-weight: 400;
        color: #4981f2;
        line-height: 20px;
        cursor: pointer;
      }
      .protocol {
        color: #4981f2;
        cursor: pointer;
      }
    }

    .login-text {
      width: 100%;
      text-align: center;
      padding: 0 0 30px;
      font-size: 24px;
      letter-spacing: 1px;
      a {
        padding: 10px;
        color: #969696;
        &.active {
          font-weight: 600;
          color: rgba(73, 129, 242, 1);
          border-bottom: 2px solid rgba(73, 129, 242, 1);
        }
        &:hover {
          border-bottom: 2px solid rgba(73, 129, 242, 1);
        }
      }
      b {
        padding: 10px;
      }
    }
    .title {
      font-weight: 600;
      padding: 0 0 30px;
      font-size: 24px;
      letter-spacing: 1px;
      color: rgba(73, 129, 242, 1);
    }

    .input-box {
      .input {
        &:nth-child(1) {
          /*margin-top: 10px;*/
        }
        &:nth-child(2),
        &:nth-child(3),
        &:nth-child(4) {
          margin-top: 20px;
        }
      }
      .position{
        padding-left: 0px;
        padding-top: 40px;
      }
    }

    .changeBtn {
      width: 100%;
      height: 45px;
      margin-top: 40px;
      font-size: 15px;
    }

    .input {
      padding: 10px 0px;
      font-size: 15px;
      width: 350px;
      color: #2c2e33;
      outline: none; // 去除选中状态边框
      border: 1px solid #fff;
      border-bottom-color: #e7e7e7;
      background-color: rgba(0, 0, 0, 0); // 透明背景
    }


    input:focus {
      border-bottom-color: #0f52e0;
      outline: none;
    }
    .button {
      line-height: 45px;
      cursor: pointer;
      margin-top: 50px;
      border: none;
      outline: none;
      height: 45px;
      width: 350px;
      background: rgba(216, 216, 216, 1);
      border-radius: 2px;
      color: white;
      font-size: 15px;
    }


  // 滚动条样式
  ::-webkit-scrollbar {
    width: 10px;
  }
  /*
    ::-webkit-scrollbar-track {
      -webkit-box-shadow: inset006pxrgba(0, 0, 0, 0.3);
      border-radius: 8px;
    }

    ::-webkit-scrollbar-thumb {
      border-radius: 10px;
      background: rgba(0, 0, 0, 0.2);
      -webkit-box-shadow: inset006pxrgba(0, 0, 0, 0.5);
    }
  */
  ::-webkit-scrollbar-thumb:window-inactive {
    background: rgba(0, 0, 0, 0.4);
  }

  //设置更改input 默认颜色
  ::-webkit-input-placeholder {
    /* WebKit browsers */
    color: #bebebe;
    font-size: 16px;
  }

  ::-moz-placeholder {
    /* Mozilla Firefox 19+ */
    color: #bebebe;
    font-size: 16px;
  }

  :-ms-input-placeholder {
    /* Internet Explorer 10+ */
    color: #bebebe;
    font-size: 16px;
  }

  input:-webkit-autofill {
    box-shadow: 0 0 0px 1000px rgba(255, 255, 255, 1) inset;
    -webkit-box-shadow: 0 0 0px 1000px rgba(255, 255, 255, 1) inset;
    -webkit-text-fill-color: #2c2e33;
  }

  .ivu-checkbox-wrapper {
    margin-right: 0;
  }

}
</style>

