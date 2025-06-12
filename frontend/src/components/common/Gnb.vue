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
import { onMounted, ref } from 'vue'
import { menuData } from '@/data/menuData'
import { useLnbStore } from '@/stores/lnb'

const menus = ref([])
const selectedId = ref('')
const lnbStore = useLnbStore()

function select(id) {
  selectedId.value = id
  const selected = menus.value.find((m) => m.id === id)
  lnbStore.setItems(selected.children)
}

onMounted(() => {
  menus.value = menuData
  selectedId.value = menus.value[0].id
})
</script>

<style scoped>
.gnb {
  background-color: #2c3e50;
  padding: 0.5rem 1rem;
  color: white;
}
.gnb ul {
  display: flex;
  gap: 1.5rem;
  list-style: none;
  margin: 0;
  padding: 0;
}
.gnb li {
  cursor: pointer;
  padding: 0.5rem;
  transition: color 0.3s;
}
.gnb li:hover {
  color: #1abc9c;
}
</style>
