<template>
  <div
    class="modal"
    :style="{
      top: position.top + 'px',
      left: position.left + 'px',
      width: width + 'px',
      height: height + 'px',
      zIndex: zIndex
    }"
    @mousedown="bringToFront"
  >
    <div class="modal-header" @mousedown.prevent="startDrag">
      <span>{{ title }}</span>
      <span class="close-btn" @click="$emit('close')">&times;</span>
    </div>
    <div class="modal-body">
      <slot></slot>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const props = defineProps({
  title: String,
  width: { type: Number, default: 300 },
  height: { type: Number, default: 200 },
  x: { type: Number, default: null },
  y: { type: Number, default: null }
})

const emit = defineEmits(['close'])

const position = ref({ top: 100, left: 100 })
const zIndex = ref(1000)
const maxZIndex = ref(1000)
let offset = { x: 0, y: 0 }
let dragging = false

function bringToFront() {
  maxZIndex.value += 1
  zIndex.value = maxZIndex.value
}

function startDrag(event) {
  dragging = true
  offset.x = event.clientX - position.value.left
  offset.y = event.clientY - position.value.top
  document.addEventListener('mousemove', onDrag)
  document.addEventListener('mouseup', stopDrag)
}

function onDrag(event) {
  if (dragging) {
    position.value.left = event.clientX - offset.x
    position.value.top = event.clientY - offset.y
  }
}

function stopDrag() {
  dragging = false
  document.removeEventListener('mousemove', onDrag)
  document.removeEventListener('mouseup', stopDrag)
}

onMounted(() => {
  position.value.top = props.y !== null ? props.y : (window.innerHeight - props.height) / 2
  position.value.left = props.x !== null ? props.x : (window.innerWidth - props.width) / 2
  bringToFront()
})
</script>

<style scoped>
.modal {
  position: absolute;
  background: #fff;
  border: 1px solid #ccc;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
  overflow: hidden;
}

.modal-header {
  background: #409EFF;
  color: white;
  padding: 10px;
  cursor: move;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.modal-body {
  padding: 20px;
  height: calc(100% - 50px);
  overflow: auto;
}

.close-btn {
  cursor: pointer;
  font-size: 18px;
}
</style>
