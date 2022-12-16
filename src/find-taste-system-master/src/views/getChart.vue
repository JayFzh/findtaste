<template>
  <div id="app">
    <!--为echarts准备一个具备大小的容器dom-->
    <div id="main" style="width: 600px; height: 400px"></div>
  </div>
</template>
<script>
import * as echarts from "echarts"
import store from '@/store'
const info ={
  months: store.state.userInfo.chartdata.month,
  incomes:store.state.userInfo.chartdata.income
}
export default {
  name: "getChart",
  data() {
    return {
      charts: "",
      opinionData: ["3", "2", "4", "4", "5"],
    };
  },
  methods: {
    drawLine(id) {
      console.log(info)
      this.charts = echarts.init(document.getElementById(id));
      this.charts.setOption({
        tooltip: {
          trigger: "axis",
        },
        legend: {
          data: ["近月收益"],
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },
 
        toolbox: {
          feature: {
            saveAsImage: {},
          },
        },
        xAxis: {
          type: "category",
          boundaryGap: false,
          data: info.months,
        },
        yAxis: {
          type: "value",
        },
 
        series: [
          {
            name: "近月收益",
            type: "line",
            stack: "总量",
            data: info.incomes,
          },
        ],
      });
    },
  },
  //调用
  mounted() {
    this.$nextTick(function () {
      this.drawLine("main");
    });
  },
};
</script>
<style scoped>
</style>
