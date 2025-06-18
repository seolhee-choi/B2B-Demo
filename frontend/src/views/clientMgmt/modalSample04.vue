<template>
  <el-button @click="(e) => openModal('modal1', e)">1번 모달 열기</el-button>
  <el-button @click="(e) => openModal('modal2', e)">2번 모달 열기</el-button>
  <el-button @click="(e) => openModal('modal3', e)">3번 모달 열기</el-button>

  <CommonModal
    v-if="modals.modal1.visible"
    title="1번 모달"
    :width="400"
    :height="250"
    :x="modals.modal1.x"
    :y="modals.modal1.y"
    @close="closeModal('modal1')"
  >
    여기는 1번 모달입니다.
  </CommonModal>

  <CommonModal
    v-if="modals.modal2.visible"
    title="2번 모달"
    :width="450"
    :height="300"
    :x="modals.modal2.x"
    :y="modals.modal2.y"
    @close="closeModal('modal2')"
  >
    여기는 2번 모달입니다.
  </CommonModal>

  <CommonModal
    v-if="modals.modal3.visible"
    title="3번 모달"
    :width="350"
    :height="220"
    :x="modals.modal3.x"
    :y="modals.modal3.y"
    @close="closeModal('modal3')"
  >
    여기는 3번 모달입니다.
  </CommonModal>
</template>

<script setup>
  import { reactive } from 'vue'
  import CommonModal from '@/components/common/CommonModal.vue'

  const modals = reactive({
    modal1: { visible: false, x: null, y: null },
    modal2: { visible: false, x: null, y: null },
    modal3: { visible: false, x: null, y: null }
  })

  function openModal(id, event) {
    const buttonRect = event.target.getBoundingClientRect()
    const offsetY = 30 // 버튼 아래로 모달위치(px단위)

    const clickX = buttonRect.left
    const clickY = buttonRect.bottom + offsetY

    const modalWidth  = 400
    const modalHeight = 300

    const safeX = Math.min(clickX, window.innerWidth - modalWidth)
    const safeY = Math.min(clickY, window.innerHeight - modalHeight)

    modals[id].x = safeX
    modals[id].y = safeY
    modals[id].visible = true
  }

  function closeModal(id) {
    modals[id].visible = false
  }
</script>

<style scoped>
  .el-button {
    margin: 10px;
  }
</style>
