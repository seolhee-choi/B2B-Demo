<template>
  <div style="max-width: 500px; margin: 0 auto;">
    <el-card>
      <h2 style="text-align: center;">고객 정보 수정</h2>

      <el-form :model="form" :rules="rules" ref="formRef" label-width="100px">
        <!-- custId는 숨김 처리 -->
        <el-form-item style="display: none;">
          <el-input v-model="form.custId" />
        </el-form-item>

        <el-form-item label="이름" prop="custNm">
          <el-input v-model="form.custNm" />
        </el-form-item>

        <el-form-item label="나이" prop="age">
          <el-input v-model="form.age" />
        </el-form-item>

        <el-form-item label="성별" prop="gender">
          <el-radio-group v-model="form.gender">
            <el-radio :value="'남성'">남성</el-radio>
            <el-radio :value="'여성'">여성</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="연락처" prop="phone">
          <el-input v-model="form.phone" placeholder="010-1234-5678" />
        </el-form-item>

        <el-form-item label="우편번호" prop="postCode">
          <el-input v-model="form.postCode" />
        </el-form-item>

        <el-form-item label="기본주소" prop="addr1">
          <el-input v-model="form.addr1" />
        </el-form-item>

        <el-form-item label="상세주소" prop="addr2">
          <el-input v-model="form.addr2" />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm">수정하기</el-button>
          <el-button @click="resetForm" style="margin-left: 10px;">초기화</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import apiClient from '@/utils/ApiClient.js'
import { ElMessage } from 'element-plus'

const route = useRoute()
const router = useRouter()
const formRef = ref(null)
const customerId = route.params.id
const form = ref({})

const rules = {
  custNm: [{ required: true, message: '이름을 입력하세요', trigger: 'blur' }],
  phone: [{ required: true, message: '연락처를 입력하세요', trigger: 'blur' }],
  postCode: [{ required: true, message: '우편번호를 입력하세요', trigger: 'blur' }],
  addr1: [{ required: true, message: '기본주소를 입력하세요', trigger: 'blur' }],
  addr2: [{ required: true, message: '상세주소를 입력하세요', trigger: 'blur' }],
}

// 고객 정보 불러오기
onMounted(async () => {
  try {
    const response = await apiClient.post(`/api/customer/list?sword=${customerId}`)
    form.value = response.data.body[0]
  } catch (error) {
    //console.error(error)
    ElMessage.error('고객 정보를 불러오는 중 오류가 발생했습니다.')
  }
})

// 수정 요청
const submitForm = () => {
  formRef.value.validate(async valid => {
    if (valid) {
      try {
        await apiClient.post('/api/customer/update', form.value)
        ElMessage.success('수정이 완료되었습니다.')
        router.push({ name: 'clientList' })
      } catch (error) {
        //console.error(error)
        ElMessage.error('수정 중 오류가 발생했습니다.')
      }
    }
  })
}

const resetForm = () => {
  window.history.back();
}
//const resetForm = async () => {
//  try {
//    const response = await apiClient.post(`/api/customer/list?sword=${customerId}`)
//    form.value = response.data.body[0]
//  } catch (error) {
//    //console.error('초기화 중 오류 발생:', error)
//    //ElMessage.error('초기화 중 오류가 발생했습니다.')
//  }
//}
</script>
