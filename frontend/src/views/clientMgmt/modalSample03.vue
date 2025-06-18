<template>
  <div>
    <el-button @click="dialog1 = true">1번 모달 열기</el-button>

    <!-- Dialog 1 -->
    <el-dialog
      v-model="dialog1"
      title="Dialog 1"
      :modal="false"
      class="draggable-dialog"
      @open="makeDraggable('dialog-1')"
      :append-to-body="true"
    >
      <p>여기는 1번 모달입니다.</p>
      <el-button @click="dialog2 = true">2번 모달 열기</el-button>
    </el-dialog>

    <!-- Dialog 2 -->
    <el-dialog
      v-model="dialog2"
      title="Dialog 2"
      :modal="false"
      class="draggable-dialog"
      @open="makeDraggable('dialog-2')"
      :append-to-body="true"
    >
      <p>여기는 2번 모달입니다.</p>
      <el-button @click="dialog3 = true">3번 모달 열기</el-button>
    </el-dialog>

    <!-- Dialog 3 -->
    <el-dialog
      v-model="dialog3"
      title="Dialog 3"
      :modal="false"
      class="draggable-dialog"
      @open="makeDraggable('dialog-3')"
      :append-to-body="true"
    >
      <p>여기는 3번 모달입니다.</p>
    </el-dialog>
  </div>
</template>

<script setup>
  import { ref } from 'vue'

  const dialog1 = ref(false)
  const dialog2 = ref(false)
  const dialog3 = ref(false)

  function makeDraggable(id) {
    setTimeout(() => {
      const dialogs = document.querySelectorAll('.el-dialog')
      const dialog = dialogs[dialogs.length - 1] // 가장 마지막에 열린 창

      if (!dialog) return
      const header = dialog.querySelector('.el-dialog__header')
      if (!header) return

      dialog.style.margin = 0
      dialog.style.position = 'absolute'
      header.style.cursor = 'move'

      let isDragging = false
      let offsetX = 0
      let offsetY = 0

      header.onmousedown = (e) => {
        isDragging = true
        offsetX = e.clientX - dialog.offsetLeft
        offsetY = e.clientY - dialog.offsetTop

        document.onmousemove = (e) => {
          if (!isDragging) return
          dialog.style.left = e.clientX - offsetX + 'px'
          dialog.style.top = e.clientY - offsetY + 'px'
        }

        document.onmouseup = () => {
          isDragging = false
          document.onmousemove = null
          document.onmouseup = null
        }
      }
    }, 50) // 약간의 딜레이를 줘야 DOM 접근 안정적
  }
</script>

<style>
  .draggable-dialog .el-dialog__header {
    cursor: move;
    user-select: none;
    background-color: #ABF200; /* 회색 계열 색상 */
    color: white; /* 글자색도 명확히 하려면 흰색으로 변경 */
  }
</style>
