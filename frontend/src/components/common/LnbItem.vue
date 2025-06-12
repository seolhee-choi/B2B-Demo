<template>
  <li>
    <div class="item-label" @click="toggleOrOpen">
      {{ item.name }}
      <span v-if="hasChildren" class="arrow">{{ expanded ? '▼' : '▶' }}</span>
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

const props = defineProps({
  item: Object,
})

const emit = defineEmits(['open'])
const expanded = ref(false)

const hasChildren = computed(
  () => Array.isArray(props.item.children) && props.item.children.length > 0,
)

function toggleOrOpen() {
  if (hasChildren.value) {
    expanded.value = !expanded.value
  } else {
    console.log(props.item)
    emit('open', props.item)
  }
}
defineOptions({ name: 'LnbItem' })
</script>

<style scoped>
.item-label {
  cursor: pointer;
  padding: 5px;
  user-select: none;
}

.arrow {
  margin-right: 5px;
  font-size: 12px;
}
</style>
