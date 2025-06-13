<template>
  <aside class="lnb">
    <section v-if="lnbStore.bookmarkMenus.length" class="bookmark-section">
      <div class="bookmark-title">즐겨찾기</div>
      <ul>
        <li v-for="bm in lnbStore.bookmarkMenus" :key="bm.id" class="bookmark-item" @click="openTab(bm)">
          {{ bm.name }}
          <button
            class="bookmark-btn"
            @click.stop="handleBookmark(bm.id)"
          >
            ★
          </button>
        </li>
      </ul>
    </section>

    <div class="bookmark-title">전체목록</div>
    <ul class="menu-tree">
      <LnbItem v-for="item in lnbStore.items" :key="item.id" :item="item" @open="openTab">
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

function handleBookmark(menuId) {
  lnbStore.toggleBookmark(menuId)
}
</script>

<style scoped>
li {
  list-style: none;
}

.lnb {
  width: 200px;
  background-color: #f4f6f8;
  border-right: 1px solid #ddd;
  padding: 1rem;
  overflow-y: auto;
}

.bookmark-section {
  border-bottom: 1px solid #ccc;
  padding-bottom: 1rem;
  margin-bottom: 1rem;
}

.bookmark-title {
  font-weight: bold;
  margin-bottom: 0.5rem;
  color: #333;
}

.bookmark-item {
  cursor: pointer;
  padding: 6px 4px;
  font-weight: 500;
  border-radius: 4px;
}

.bookmark-item:hover {
  background-color: #fff3db;
}

.menu-tree {
  padding-top: 0.5rem;
}

.bookmark-btn {
  margin-left: auto;
  background: none;
  border: none;
  font-size: 14px;
  cursor: pointer;
}
</style>
