<template>
  <div class="tabs">
    <div
      v-for="tab in tabs"
      :key="tab.id"
      class="tab"
      :class="{ active: tab.id === currentId }"
      @click="selectTab(tab)"
    >
      {{ tab.name }}
      <span class="close" @click.stop="closeTab(tab)">×</span>
    </div>
  </div>
</template>

<script setup>
import { useTabStore } from '@/stores/tab'
import { useRouter } from 'vue-router'

const tabStore = useTabStore()
const tabs = tabStore.tabs
const currentId = tabStore.currentTabId
const router = useRouter()

function selectTab(tab) {
  tabStore.setCurrentTab(tab)
  router.push(tab.route)
}

function closeTab(tab) {
  tabStore.removeTab(tab)
}
</script>

<style scoped>
.tabs {
  display: flex;
  background: #ddd;
  padding: 5px;
}
.tab {
  padding: 5px 10px;
  margin-right: 5px;
  background: #bbb;
  border-radius: 3px;
  cursor: pointer;
}
.tab.active {
  background: #999;
  font-weight: bold;
}
.close {
  margin-left: 5px;
  color: red;
  cursor: pointer;
}
</style>
