

const userInfo = {
  namespaced: true,
  state: {
    data: {},
    chartdata:{},
    isLogined: false
  }, 

  getters: {
    userInfo: state => {
      return state.data
    }
  },

  mutations: {
    // 设置用户信息
    setUserInfo(state, userInfo) {
      state.data = userInfo
      state.data = Object.assign(state.data, userInfo)
      console.log(state.data)
      state.isLogined = true
    },
    // 清除用户信息
    clearUserInfo(state,info) {
      state.data = info
      state.isLogined = false
    },
    // 修改用户信息
    modifyUserInfo(state, newInfo) {
      state.data = Object.assign(state.data, newInfo)
    },
    // 保存自动登录
    setAutoLogin(state, isLogin) {
      state.isAutoLogin = isLogin
    },
    setChartInfo(state,chartInfo){
      state.chartdata = chartInfo
      state.chartdata = Object.assign(state.chartdata, chartInfo)
      console.log(state.chartdata)
      
    }

  },

  actions: {
    // 保存用户信息
    saveInfo({ commit }, result) {
      commit('setUserInfo', result)
    },
    // 保存自动登录
    saveAutoLogin({ commit }, isLogin) {
      commit('setAutoLogin', isLogin)
    },
    // 退出登录
    logout({commit}) {
      commit('clearUserInfo', {})
      location.href = '/login'
    },
    saveChartInfo({ commit }, result) {
      commit('setChartInfo', result)
    },
  }

}

export default userInfo
