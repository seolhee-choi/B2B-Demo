<template>
  <div class="layout">
    <Header />
    <Gnb />
    <Lnb />
    <TabBar />
    <main class="content">
      <slot name="content" /> <!-- 페이지 내용 slot -->
    </main>
    <Footer />
  </div>
</template>

<script setup>
import Header from '@/components/common/Header.vue'
import Footer from '@/components/common/Footer.vue'
import Gnb from '@/components/common/Gnb.vue'
import Lnb from '@/components/common/Lnb.vue'
import TabBar from '@/components/common/TabBar.vue'
</script>

<style scoped>
.layout {
  display: flex;
  flex-direction: column;
  height: 100vh;
}
.content {
  flex: 1;
  padding: 20px;
  background-color: #fff;
}
</style>
