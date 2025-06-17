<template>
  <div class="header-container">
    <h2>header</h2>
    <p
      v-if="accountStore.account.userId"
      class="logout"
      @click="logout"
    >
      로그아웃
    </p>
  </div>
</template>

<script setup>
import router from "@/router/index.js";
import { useAccountStore } from "@/stores/useAccountStore.js";

const accountStore = useAccountStore();

const logout = async () => {
  try {
    await accountStore.logout();
    router.push("/");
  } catch (err) {
    console.error("로그아웃 실패", err);
  }
}
</script>


<style scoped>
.header-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.logout {
  cursor: pointer;
}
</style>
