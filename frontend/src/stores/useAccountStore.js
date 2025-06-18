import { defineStore } from "pinia";
import apiClient from "@/utils/ApiClient.js";

export const useAccountStore = defineStore("account", {
  state: () => ({
    account: {
      userId: '',
      roleId: '',
    },
  }),

  getters: {
    isLoggedIn: (state) => !!state.account.userId,
  },

  actions: {
    async logout() {
      await apiClient.post('/api/logout');
      this.account = { userId: '', roleId: ''};
      alert("로그아웃되었습니다.");
    },

    setAccount(account) {
      this.account = account;
    },

    clearAccount() {
      this.account.userId = '';
      this.account.roleId = '';
    }
  },
  /* 현재 쿠키로 토큰을받는 형식이 아니라서 우선 localStorage에 저장 */
  persist : true
})
