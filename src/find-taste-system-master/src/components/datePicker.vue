<template>
    <div>
      <el-date-picker
        v-model="activeTime.startTime"
        type="date"
        size="small"
        :picker-options="pickerOptionsStart"
        value-format="yyyy-MM-dd"
        @change="dateStartChange()"
        placeholder="开始时间">
      </el-date-picker> -
      <el-date-picker
        v-model="activeTime.endTime"
        type="date"
        size="small"
        @change="dateChange()"
        :picker-options="pickerOptionsEnd"
        value-format="yyyy-MM-dd"
        placeholder="结束时间">
      </el-date-picker>
    </div>
  </template>
  
  <script>
  export default {
    name: "WTimer",
    props: {
      activeTime: {
        type: Object,
      }
    },
    data() {
      return {
        startDate: "",
        endDate: "",
       pickerOptionsStart: {
              //未发生的时间禁止选择
              disabledDate(time) {
            return time.getTime() >Date.now() 
          }},
        pickerOptionsEnd: {
              //未发生的时间禁止选择
              disabledDate(time) {
            return time.getTime() >Date.now() 
          }}
      };
    },
  
    mounted() {},
    methods: {
       //日期区间自定义
       dateStartChange(){
          if(this.activeTime.endTime){
          let enactDateBeginText=new Date(this.activeTime.startTime).getTime();
          let enactDateEndText = new Date(this.activeTime.endTime).getTime();
          if(enactDateBeginText > enactDateEndText){
            this.$message.error('开始时间不得大于结束时间,请重新选择!');
            // this.activeTime.startTime='';
          }
        }
       },
      dateChange(){
        if(this.activeTime.startTime){
          let enactDateBeginText=new Date(this.activeTime.startTime).getTime();
          let enactDateEndText = new Date(this.activeTime.endTime).getTime();
          if(enactDateBeginText>enactDateEndText){
            this.$message.error('结束时间不可小于开始时间,请重新选择!');
            //this.activeTime.endTime='';
          }
        }
      },
    },
  };
  </script>
  
  
  
  