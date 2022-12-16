<template>
  <div class="post-container" style="max-width: 300px">
    <div id="writepost">
      <findInfo v-for="(postInfo,index) in postList" :key="index" :index="index" :postInfo='postInfo' :isRespond="false" :modifyRes="true" @remove="remove" />
    </div>

  </div>

</template>

<script>

import store from "@/store";
import { foundPostList } from "@/utils/api";
import findInfo from "@/components/findInfo";

const params = {
  id:store.state.userInfo.data.name
}
let dataFetched;
export default {
  name: "postList",
  components:{
    findInfo
  },
  beforeRouteEnter(to, from, next){
    foundPostList(params).then(res=>{
      console.log("GlgG")
      console.log(res.data.type)
      dataFetched = res.data;
      console.log(dataFetched)
      next();
    });
  },
  data() {
    return {
      postList : dataFetched
    }
  },
  methods: {
    remove(n) {
      this.postList.splice(n,1)
    }
  }
}
</script>

<style scoped>

.post-container{
  position: absolute;
  width: 600px;
  min-width: 600px;
  /* height: 2000px; */
  /* background-color: white; */
  margin-top: 20px ;
  margin-right: 15px;
  margin-left: 200px;
  display:flex;
  flex-direction: column;

}
#writepost{
  width: 100%;
  background-color: white;
  border-radius: 3px;
  display:flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-bottom: 15px;
}
</style>