// src/stores/tabStore.js
import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useTabStore = defineStore('tab', () => {
  const tabs = ref([])
  const currentTabId = ref(null)

  function addTab(tab) {
    const exists = tabs.value.find(t => t.id === tab.id)
    if (!exists) tabs.value.push(tab)
    currentTabId.value = tab.id
  }

  function removeTab(tab) {
    tabs.value = tabs.value.filter(t => t.id !== tab.id)
    if (currentTabId.value === tab.id) {
      const lastTab = tabs.value.at(-1)
      currentTabId.value = lastTab?.id || null
    }
  }

  function setCurrentTab(tab) {
    currentTabId.value = tab.id
  }

  return {
    tabs,
    currentTabId,
    addTab,
    removeTab,
    setCurrentTab
  }
})
