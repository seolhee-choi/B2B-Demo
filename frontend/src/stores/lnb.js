// src/stores/lnbStore.js
import { defineStore } from 'pinia'
import { computed, ref } from 'vue'

export const useLnbStore = defineStore('lnb', () => {
  const items = ref([])
  const allBookmarks = ref([])        // [{ gnbId, menuId }]
  const currentGnbId = ref(null)

  function setItems(newItems) {
    items.value = newItems
  }

  async function loadAllBookmarks() {
    // const res = await fetchBookmarks()
    // allBookmarks.value = res.bookmarks
  }

  function setCurrentGnbId(gnbId) {
    currentGnbId.value = gnbId
  }

  const currentBookmarks = computed(() =>
    allBookmarks.value
      .filter(b => b.gnbId === currentGnbId.value)
      .map(b => b.menuId)
  )

  const bookmarkMenus = computed(() => {
    const map = new Map()
    const flatten = (nodes = []) => {
      for (const node of nodes) {
        map.set(node.id, node)
        if (node.children) flatten(node.children)
      }
    }
    flatten(items.value)

    return currentBookmarks.value.map(id => map.get(id)).filter(Boolean)
  })

  async function toggleBookmark(menuId) {
    const existing = allBookmarks.value.find(
      b => b.gnbId === currentGnbId.value && b.menuId === menuId
    )

    if (existing) {
      allBookmarks.value = allBookmarks.value.filter(
        b => !(b.gnbId === currentGnbId.value && b.menuId === menuId)
      )
    } else {
      allBookmarks.value.push({
        gnbId: currentGnbId.value,
        menuId
      })
    }

    // await saveBookmark(currentGnbId.value, menuId, !existing)
  }

  return {
    items,
    allBookmarks,
    currentBookmarks,
    setItems,
    loadAllBookmarks,
    toggleBookmark,
    setCurrentGnbId,
    bookmarkMenus,
  }
})
