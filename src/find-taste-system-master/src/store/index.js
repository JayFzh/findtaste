
import Vuex from 'vuex'
import userInfo from './modules/userInfo'
import createPersistedState from 'vuex-persistedstate'



export default new Vuex.Store({
  // state: {
  // },
  // mutations: {
  // },
  // actions: {
  // },
modules: {
	// eslint-disable-next-line no-mixed-spaces-and-tabs
  	userInfo
  },
	getters: {
		isLogined: state => {
			return state.userInfo.isLogined
		}
	},
	plugins: [createPersistedState({
		// eslint-disable-next-line no-mixed-spaces-and-tabs
	    key: 'store',
		// eslint-disable-next-line no-mixed-spaces-and-tabs
	    storage: window.localStorage,
	})]
})
