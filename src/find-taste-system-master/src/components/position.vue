<template>
  <div class="positon">
    <!--默认使用-->
    <elui-china-area-dht placeholder="省市区" @change="onChange" style="width:350px;height: 50px"></elui-china-area-dht>
    <!--带isall参数和leave参数示例-->
  </div>
</template>

<script>

import { EluiChinaAreaDht } from 'elui-china-area-dht'
const chinaData = new EluiChinaAreaDht.ChinaArea().chinaAreaflat
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'position',
  components: {
    EluiChinaAreaDht,
  },
  setup(props,content) {
    let province = '';
    let city = '';
    let country = '';

    function onChange(e) {
      province = chinaData[e[0]]
      city = chinaData[e[1]]
      country = chinaData[e[2]]
      console.log(province,city,country)
      content.emit('change',province,city,country)
    }
    return {
      onChange,
    }
  },
}
</script>


<style lang="scss" scoped>
.position{
  padding-left: 10px;
  margin-top: 20px;

}
</style>

