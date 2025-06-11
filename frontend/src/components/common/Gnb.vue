<template>
  <nav class="gnb">
    <ul>
      <li
        v-for="menu in menus"
        :key="menu.id"
        :class="{ active: menu.id === selectedId }"
        @click="select(menu.id)"
      >
        {{ menu.name }}
      </li>
    </ul>
  </nav>
</template>

<script setup>
import { ref, watch } from 'vue'
import { menuData } from '@/data/menuData'
import { useLnbStore } from '@/stores/lnb'

const menus = menuData
const selectedId = ref(menus[0].id)
const lnbStore = useLnbStore()

function select(id) {
  selectedId.value = id
  const selected = menus.find(m => m.id === id)
  lnbStore.setItems(selected.children)
}
</script>

<style scoped>
.gnb ul {
  display: flex;
  list-style: none;
  padding: 0;
}
.gnb li {
  margin-right: 20px;
  cursor: pointer;
}
.gnb li.active {
  font-weight: bold;
}
</style>
