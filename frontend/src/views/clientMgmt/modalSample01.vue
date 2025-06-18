<template>
  <h1>Client Page</h1>
  <el-button type="primary" @click="dialogOpen = true">회원가입</el-button>
  <CommonDialog
    v-model="dialogOpen"
    title="회원정보 입력1"
    cancelButtonTitle="취소(닫기)"
    okButtonTitle="회원가입"
    :width="'700px'"
    :fields="[
      {
        name: 'id',
        label: '아이디',
        type: 'text',
        placeholder: '아이디 입력',
        props: { readonly: true }
      },
      {
        name: 'name',
        label: '이름',
        type: 'text',
        placeholder: '이름 입력'
      },
      {
        name: 'birthdate',
        label: '생년월일',
        type: 'date'
      },
      {
        name: 'gender',
        label: '성별',
        type: 'radio',
        options: [
          { label: '남', value: 'M' },
          { label: '여', value: 'F' },
        ]
      },
      {
        name: 'hobby',
        label: '취미',
        type: 'checkbox',
        options: [
          { label: '독서', value: 'reading' },
          { label: '운동', value: 'sports' },
          { label: '음악 감상', value: 'music' },
        ]
      },
      {
        name: 'role',
        label: '권한',
        type: 'select',
        options: [
          { label: '관리자', value: 'admin' },
          { label: '사용자', value: 'user' },
        ]
      },
      {
        name: 'bio',
        label: '자기소개',
        type: 'textarea',
        placeholder: '자기소개를 입력해주세요',
        props: { rows: 6 }
      },
    ]"
    :rules="{
      id: [{ required: true, message: '아이디는 필수입니다', trigger: 'blur' }],
      name: [{ required: true, message: '이름은 필수입니다', trigger: 'blur' }],
      birthdate: [{ required: true, message: '생년월일 선택은 필수입니다.', trigger: 'change', type: 'date' }],
      hobbies: [{ type: 'array', required: true, message: '취미를 한 가지 이상 선택하세요.', trigger: 'change' }],
    }"
    :initialData="initialData"
    @submit="handleSubmit"
  />
</template>

<script setup>
  import { ref } from 'vue'
  import CommonDialog from '@/components/common/CommonDialog.vue' // 공통 모달창 컴포넌트 가져오기

  /////////// 모달창 Start ////////////
  /* 모달창으로 전달할 초기값 설정 */
  const initialData = {
    gender: 'F',
    role: 'user',
    birthdate: '2025-06-12',
    id: 'kentagon',
    hobby: ['sports', 'music'],
  }

  /* 모달창 활성/비활성 설정 */
  const dialogOpen = ref(false)

  /* 모달창 데이타 전송 */
  function handleSubmit(data) {
    console.log('제출 데이터:', data)
  }
  defineOptions({ name: 'ClientList' })
  //////////// 모달창 End /////////////
</script>

<style scoped>

</style>
