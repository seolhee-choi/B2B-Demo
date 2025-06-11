// src/stores/lnbStore.js
import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useLnbStore = defineStore('lnb', () => {
  const items = ref([])

  function setItems(newItems) {
    items.value = newItems
  }

  return {
    items,
    setItems
  }
})
