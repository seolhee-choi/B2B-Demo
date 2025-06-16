// src/stores/tabStore.js
import { defineStore } from 'pinia'
import { ref } from 'vue'
import { _ } from '@/utils/lodash.js'

export const useTabStore = defineStore('tab', () => {
  const tabs = ref([])
  const currentTabId = ref(null)

  function addTab(tab) {
    if (!_.find(tabs.value, { id: tab.id })) {
      tabs.value.push(tab)
    }
    currentTabId.value = tab.id
  }

  function removeTab(tab) {
    const index = _.findIndex(tabs.value, { id: tab.id })
    tabs.value = _.filter(tabs.value, t => t.id !== tab.id)

    if (currentTabId.value === tab.id) {
      const nextTab = tabs.value[index] || tabs.value[index - 1] || _.last(tabs.value)
      currentTabId.value = nextTab?.id || null
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
