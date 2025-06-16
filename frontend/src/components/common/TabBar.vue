<template>
  <div class="tabs">
    <div
      v-for="tab in tabStore.tabs"
      :key="tab.id"
      class="tab"
      :class="{ active: tab.id === tabStore.currentTabId }"
      @click="selectTab(tab)"
    >
      {{ tab.name }}
      <span class="close-btn" @click.stop="closeTab(tab)">×</span>
    </div>
  </div>
</template>

<script setup>
import { useTabStore } from '@/stores/tab'
import { useRouter } from 'vue-router'
import {watch} from "vue";

const tabStore = useTabStore()
const router = useRouter()

function selectTab(tab) {
  tabStore.setCurrentTab(tab)
  router.push(tab.route)
}

function closeTab(tab) {
  tabStore.removeTab(tab)
}

watch(
  () => tabStore.currentTabId,
  (newId) => {
    const tab = tabStore.tabs.find(t => t.id === newId)

    if (tab) {
      router.push(tab.route) // 각 탭에 route 속성이 있어야 함
    } else {
      router.push('/') // 모든 탭이 닫히면 루트로 이동
    }
  },
  { immediate: true }
)
</script>

<style scoped>
.tabs {
  display: flex;
  border-bottom: 1px solid #ccc;
}
.tabs div {
  padding: 6px 12px;
  border-right: 1px solid #ddd;
  cursor: pointer;
}
.tabs div.active {
  font-weight: bold;
  background-color: #eee;
}
</style>
