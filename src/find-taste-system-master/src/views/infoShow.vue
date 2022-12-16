<template >
  <div>
    <el-descriptions :column="1" border>

      <el-descriptions-item style="max-width: 100px" label="用户名" ><p class="message">{{ info.nickname }}</p>
      </el-descriptions-item>
      <el-descriptions-item style="max-width: 100px" label="手机/邮箱号"  class="message">
        <div>
          <div v-if="isEdit" class="editBox">
            <!-- 改修改框样式去下面style里 -->
            <input v-model="backUp" class="ipt" />
              <button :class="['btn','btn1']" @click.stop="submit">提交</button>
              <button :class="['btn','btn1']" @click.stop="cancel">取消</button>

          </div>
          <div v-else class="editBox">
            <!-- 自定义文字样式 -->
              {{ number }}
            <button :class="['btn','btn1']"  @click.stop="edit">编辑</button>
            <!-- 自定义编辑按钮 -->
          </div>
        </div>
      </el-descriptions-item>
      <el-descriptions-item style="max-width: 100px" label="唯一ID"  class="message"><p class="message">{{ info.userId }}</p>
      </el-descriptions-item>
      <el-descriptions-item style="max-width: 100px" label="所在地"  class="message"><p class="message">{{ info.position }}</p>
      </el-descriptions-item>


    </el-descriptions>
  </div>
</template>

<script>
import store from '@/store'
import {ElMessage} from "element-plus";
import {validEmail, validPhone} from "@/utils/valid";
import {editNumber, getInfo} from "@/utils/api";

const params = {
  id:store.state.userInfo.data.name
}
let dataFetched;
export default {
  name: "showInfo",
  beforeRouteEnter(to, from, next){
    getInfo(params).then(res=>{
      dataFetched = res.data;
      next();
    });
  },
  data() {
    return {
      info : dataFetched,
      number : store.state.userInfo.data.number,
      backUp: store.state.userInfo.data.number,
      isEdit: false,
    }
  },

  methods : {

    edit() {
      this.isEdit = true;
      this.backUp = this.number;
    },
    cancel() {
      this.isEdit = false;
    },
    submit() {
      if(!(validEmail(this.backUp)||validPhone(this.backUp))){
        ElMessage.warning("新账号不符合规范！")
        return false;
      }
      let form = {
        userId: this.info.userId,
        newNumber: this.backUp
      }
      editNumber(form).then(res => {
        console.log(res.data)

        if (res.data.status === 500) {
          this.$message.success('修改成功');
          this.number = this.backUp
          this.isEdit = false
        } else {
          this.$message.error("修改失败");
        }
      })
    }


  }
}
</script>

<style scoped>
button{
  margin: 5px;
}
.message {
  width: 200px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
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
  padding: 2px 4px;
  text-align: center;
  display: inline-block;
  font-size: 12px;
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

</style>
