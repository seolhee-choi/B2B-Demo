<template>
  <div>
    <h2>팝업 창</h2>
    <div v-if="user">
      <p><strong>이름:</strong> {{ user.username }}</p>
      <p><strong>역할:</strong> {{ user.role }}</p>
      <p><strong>시간:</strong> {{ new Date(user.timestamp).toLocaleString() }}</p>
      <input v-model="inputValue"/>
      <button @click="sendMessageToParent">발송</button>
    </div>
    <div v-else>
      <p>데이터를 기다리는 중...</p>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const user = ref(null)
const inputValue = ref(null)

function sendMessageToParent() {
  if (window.opener) {
    window.opener.postMessage(
      {
        type: 'INPUT_RESULT',
        payload: {
          input: inputValue.value,
          sentAt: Date.now()
        }
      },
      location.origin // 보안 체크 필수
    )
  }
}

onMounted(() => {
  window.addEventListener('message', (event) => {
    if (event.origin !== location.origin) return
    if (event.data?.type === 'USER_INFO') {
      user.value = event.data.payload
    }
  })
})
</script>
