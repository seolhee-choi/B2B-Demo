<template>
  <aside class="lnb">
    <ul>
      <li
        v-for="item in lnbStore.items"
        :key="item.id"
        @click="openTab(item)"
      >
        {{ item.name }}
      </li>
    </ul>
  </aside>
</template>

<script setup>
import { useLnbStore } from '@/stores/lnb'
import { useTabStore } from '@/stores/tab'
import { useRouter } from 'vue-router'

const lnbStore = useLnbStore()
const tabStore = useTabStore()
const router = useRouter()

function openTab(item) {
  // tabStore.addTab(item)
  router.push(item.route)
}
</script>

<style scoped>
.lnb {
  width: 200px;
  padding: 10px;
}
.lnb li {
  cursor: pointer;
  margin: 5px 0;
}
</style>
