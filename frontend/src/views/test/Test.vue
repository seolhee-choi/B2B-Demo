<template>
  <button @click="openPopup('/popup-page', 'my-name')">새 창 열기</button>
  <button @click="openPopup('/popup-page2', 'my-name2')">새 창 열기</button>
  <div>팝업으로부터 메시지 : {{msg}}</div>
</template>

<script setup>
import { onMounted, ref } from 'vue'

const msg = ref('')

function openPopup(url) {
  const popup = window.open(
    url, // 실제 라우팅 경로 또는 정적 html 경로
    'popup_' + Date.now(),
    'width=600,height=400,resizable,scrollbars',
  )

  const payload = {
    username: 'test',
    role: 'admin',
    timestamp: Date.now(),
  }

  // 팝업이 열리고 난 후 데이터를 보내기 (약간의 delay 필요)
  setTimeout(() => {
    popup?.postMessage(
      {
        type: 'USER_INFO',
        payload,
      },
      location.origin, // 보안상 origin 일치
    )
  }, 500)
}

onMounted(() => {
  window.addEventListener('message', (event) => {
    if (event.origin !== location.origin) return

    if (event.data?.type === 'INPUT_RESULT') {
      const result = event.data.payload
      msg.value = result.input
      console.log('팝업에서 받은 메시지:', result)
      // result.input, result.sentAt 사용 가능
    }
  })
})
</script>
