<template>
  <aside class="lnb">
    <ul>
      <LnbItem
        v-for="item in lnbStore.items"
        :key="item.id"
        :item="item"
        @open="openTab">
        {{ item.name }}
      </LnbItem>
    </ul>
  </aside>
</template>

<script setup>
import { useLnbStore } from '@/stores/lnb'
import { useTabStore } from '@/stores/tab'
import { useRouter } from 'vue-router'
import LnbItem from '@/components/common/LnbItem.vue'

const lnbStore = useLnbStore()
const tabStore = useTabStore()
const router = useRouter()

function openTab(item) {
  tabStore.addTab(item)
  router.push(item.route)
}
</script>

<style scoped>
.lnb {
  width: 200px;
  background-color: #f4f6f8;
  border-right: 1px solid #ddd;
  padding: 1rem;
  overflow-y: auto;
}

.lnb li {
  cursor: pointer;
  margin: 5px 0;
  list-style: none;
}
</style>
