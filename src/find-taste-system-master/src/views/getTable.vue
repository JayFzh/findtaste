<template>
<!-- <div class="rersonal-con-list">
    <el-form-item label="时间：">
      <el-col :span="11">
            <el-date-picker
            v-model="personal.meetApplyStartdate"
            format="yyyy-MM-dd HH:mm:ss"
            value-format="yyyy-MM-dd HH:mm:ss"
            type="datetime"
            placeholder="选择开始日期"
            :picker-options="pickerOptionsStart"
            @change="TimeStart"
            >
          </el-date-picker>
      </el-col>
      <el-col class="line" :span="2">-</el-col>
      <el-col :span="11">
        <el-date-picker
          v-model="personal.meetApplyEnddate"
          format="yyyy-MM-dd HH:mm:ss"
          value-format="yyyy-MM-dd HH:mm:ss"
          type="datetime"
          placeholder="选择结束日期"
          :picker-options="pickerOptionsEnd"
          >
        </el-date-picker>
      </el-col>
    </el-form-item>
  </div> -->
  <position style="padding-top: 30px" @change="getBack"></position>


  <Button
          class="loginBtn"
          type="primary"
          @click.stop="getChart">
          立即绘图
        </Button>
</template> 
<script>
import position from "@/components/position.vue"
import {postChart} from "@/utils/api";
import {ElMessage} from "element-plus"
import router from "@/router";
export default {
  name: 'getTable',
  components:{
    position
  },
  data(){
      return{
        addressForm: {
          province: '',
          city: '',
          county: '',
        },
        personal: {
          meetApplyStartdate: "", // 开始时间
          meetApplyEnddate: "", // 结束时间
        },
        pickerOptionsStart: {}, // 开始时间
        pickerOptionsEnd:{}, // 结束时间
      }
  },
  mounted(){
    this.TimeStart(new Date())
  },
  methods: {
    getBack(val1,val2,val3) {
      this.addressForm.province = val1;
      this.addressForm.city = val2;
      this.addressForm.county = val3;
      this.address = val1;
      console.log(this.addressForm)
    },
    // 开始日期触发事件
    TimeStart(date){
      console.log(date)
      // this.pickerOptionsStart = Object.assign({},this.pickerOptionsStart,{
      //   disabledDate(time){
      //     return time.getTime() < Date.now() - 8.64e7;
      //   }
      // })
      // this.personal.meetApplyEnddate = ''; // 结束时间至空
      // // 将选择的时间转为时间戳
      // const date1 = Date.parse(new Date(date));
      // this.pickerOptionsEnd = Object.assign({},this.pickerOptionsEnd,{
      //   disabledDate(time){
      //     return time.getTime() < date1;
      //   }
      // })
    },
    getChart(){
      let data = {
        position: this.addressForm,
        starttime: this.personal.meetApplyStartdate,
        endtime: this.personal.meetApplyStartdate
      }
      console.log(data)
      postChart(data).then(res => {
        console.log('接受响应===', res);
        this.isLoading = false;
        if (res.status === 200) {
          this.$store.dispatch('userInfo/saveChartInfo', res.data);
          ElMessage.success('绘图成功');
          router.push('/getChart');
        } else {
          ElMessage.error(res.statusText);
        }
      }
      )
    }
  }
}
</script>

<style lang="scss" scoped>
.loginBtn {
      width: 100%;
      height: 45px;
      margin-top: 40px;
      font-size: 15px;
    }
</style>

