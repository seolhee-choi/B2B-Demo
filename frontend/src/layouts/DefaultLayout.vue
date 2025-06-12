<template>
  <div class="layout">
    <Header />
    <Gnb />
    <div class="main">
      <Lnb />
      <section class="content-area">
        <TabBar />
        <main class="content">
          <router-view v-slot="{ Component }">
            <keep-alive :include="tabComponentNames">
              <component :is="Component" />
            </keep-alive>
          </router-view>
        </main>
      </section>
    </div>
    <Footer />
  </div>
</template>

<script setup>
import Header from '@/components/common/Header.vue'
import Footer from '@/components/common/Footer.vue'
import Gnb from '@/components/common/Gnb.vue'
import Lnb from '@/components/common/Lnb.vue'
import TabBar from '@/components/common/TabBar.vue'
import { useTabStore } from '@/stores/tab.js'
import { computed } from 'vue'

const tabStore = useTabStore()
const tabComponentNames = computed(() =>
  tabStore.tabs.map((t) => t.name).filter(Boolean)
)
</script>

<style scoped>
.layout {
  display: flex;
  flex-direction: column;
  height: 100vh;
}

.main {
  display: flex;
  flex: 1;
  overflow: hidden;
}

.content-area {
  flex: 1;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.content {
  flex: 1;
  padding: 1rem;
  overflow-y: auto;
}
</style>
