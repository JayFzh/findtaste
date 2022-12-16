<template>
  <div>
    <span style="margin-right: 10px">选择味道</span>
    <el-select v-model="sValue" filterable :placeholder="'请选择'" @change="change">
      <el-option
          v-for="item in select"
          :key="item.value"
          :value="item.value">
      </el-option>
    </el-select>
  </div>
</template>

<script>
export default {
  name: "selectBox",
  props:{
    select:Object
  },
  data() {
    return {
      sValue: this.select.result
    }
  },
  computed:{
    value:{
      get(){
        this.select.result && this.change(this.select.result)
        return this.select.result
      }
    }
  },
  methods: {
    change(val) {
      console.log(val);
      //方法：利用select对象把值传给父组件
      // eslint-disable-next-line vue/no-mutating-props
      this.select.result = val
      //把二级子项的数据找到，并传给父组件

      this.$emit('changeOption',val)
      // this.select.chang && this.select.change(this.buildChild(val))
    },

  },
}
</script>

<style lang="scss" scoped>
span {
  margin: auto;
  width: 20px;
  height: 30px;
  line-height: 30px;
  text-align: center;
  font-size: 15px;
  font-weight: bold;
  color: transparent;
  background: linear-gradient(45deg,yellowgreen, pink, #03a9f4);
  background-size: cover;
  -webkit-background-clip: text;
}
</style>