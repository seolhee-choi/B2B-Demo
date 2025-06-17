<script setup>
import { ref } from 'vue'
import CommonDialog from '@/components/common/CommonDialog.vue'

const dialogOpen1 = ref(false)
const dialogOpen2 = ref(false)

// 초기 데이터
const initialData1 = {
  id: 'kentagon',
  gender: 'F',
  role: 'user',
  birthdate: '2025-06-12',
  hobbies: ['sports']
}

const initialData2 = {
  id: 'kerigon',
  gender: 'M',
  birthdate: '2025-01-01'
}

// 유효성
const rules1 = {
  id: [{ required: true, message: '아이디는 필수입니다', trigger: 'blur' }],
  name: [{ required: true, message: '이름은 필수입니다', trigger: 'blur' }],
  birthdate: [{ required: true, message: '생년월일 선택은 필수입니다.', trigger: 'change', type: 'date' }],
  hobbies: [{ type: 'array', required: true, message: '취미 선택 필요', trigger: 'change' }]
}
const rules2 = {
  id: [{ required: true, message: '아이디는 필수입니다', trigger: 'blur' }],
  name: [{ required: true, message: '이름은 필수입니다', trigger: 'blur' }],
  birthdate: [{ required: true, message: '생년월일 선택은 필수입니다.', trigger: 'change', type: 'date' }]
}

// 필드 정의
const fields1 = [
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
      { label: '여', value: 'F' }
    ]
  },
  {
    name: 'hobbies',
    label: '취미',
    type: 'checkbox',
    options: [
      { label: '독서', value: 'reading' },
      { label: '운동', value: 'sports' },
      { label: '음악 감상', value: 'music' }
    ]
  },
  {
    name: 'role',
    label: '권한',
    type: 'select',
    options: [
      { label: '관리자', value: 'admin' },
      { label: '사용자', value: 'user' }
    ]
  },
  {
    name: 'bio',
    label: '자기소개',
    type: 'textarea',
    placeholder: '자기소개를 입력해주세요',
    props: { rows: 6 }
  }
]

const fields2 = [
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
      { label: '여', value: 'F' }
    ]
  }
]

// 제출 핸들러
function handleSubmit1(data) {
  console.log('회원가입1 데이터:', data)
}

function handleSubmit2(data) {
  console.log('회원가입2 데이터:', data)
}
defineOptions({ name: 'ClientCreate' })
</script>

<template>
      <h1>고객생성</h1>

      <el-button type="primary" @click="dialogOpen1 = true">회원가입1</el-button>

      <!-- 회원가입1 모달 -->
      <CommonDialog
        v-model="dialogOpen1"
        title="회원정보 입력1"
        cancelButtonTitle="닫기"
        okButtonTitle="회원가입"
        :width="'700px'"
        :fields="fields1"
        :rules="rules1"
        :initialData="initialData1"
        @submit="handleSubmit1"
      >
        <!-- 추가 버튼을 커스텀 슬롯에 삽입 -->
        <template #addTemplate>
          <el-form-item>
            <el-button type="success" @click="dialogOpen2 = true">회원가입2</el-button>
          </el-form-item>
        </template>
      </CommonDialog>

      <!-- 회원가입2 모달 -->
      <CommonDialog
        v-model="dialogOpen2"
        title="회원정보 입력2"
        cancelButtonTitle="취소"
        okButtonTitle="등록"
        :width="'800px'"
        :fields="fields2"
        :rules="rules2"
        :initialData="initialData2"
        @submit="handleSubmit2"
      />

</template>

<style scoped>

</style>
