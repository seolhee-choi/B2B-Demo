<template>
  <li>
    <div class="item-label" @click="toggleOrOpen">
      <span v-if="hasChildren" class="arrow">{{ expanded ? '▼' : '▶' }}</span>
      {{ item.name }}
      <button
        v-if="!hasChildren"
        class="bookmark-btn"
        @click.stop="toggleBookmark(item.id)"
      >
        {{ isBookmarked ? '★' : '☆' }}
      </button>
    </div>
    <ul v-if="hasChildren && expanded">
      <LnbItem
        v-for="child in item.children"
        :key="child.id"
        :item="child"
        @open="$emit('open', $event)"
      />
    </ul>
  </li>
</template>

<script setup>
import { ref, computed } from 'vue'
import LnbItem from './LnbItem.vue'
import { useLnbStore } from '@/stores/lnb.js'

const lnbStore = useLnbStore();

const props = defineProps({
  item: Object,
})

const emit = defineEmits(['open'])

const expanded = ref(false)

const hasChildren = computed(() =>
  Array.isArray(props.item.children) && props.item.children.length > 0
)

// 현재 북마크 목록에서 포함 여부 확인
const isBookmarked = computed(() =>
  lnbStore.currentBookmarks.includes(props.item.id)
)

function toggleOrOpen() {
  if (hasChildren.value) {
    expanded.value = !expanded.value
  } else {
    emit('open', props.item)
  }
}

// 북마크 토글 핸들러
function toggleBookmark(menuId) {
  lnbStore.toggleBookmark(menuId)
}

defineOptions({ name: 'LnbItem' })
</script>

<style scoped>
li {
  list-style: none;
}

.item-label {
  cursor: pointer;
  padding: 5px;
  user-select: none;
  align-items: center;
  justify-content: space-between;
}

.arrow {
  margin-right: 5px;
  font-size: 12px;
}

.bookmark-btn {
  margin-left: auto;
  background: none;
  border: none;
  font-size: 14px;
  cursor: pointer;
}
</style>
