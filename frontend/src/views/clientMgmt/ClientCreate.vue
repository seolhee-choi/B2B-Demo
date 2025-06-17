<template>
  <div style="max-width: 500px; margin: 0 auto;">
    <el-card>
      <h2 style="text-align: center;">회원가입</h2>

      <el-form :model="form" :rules="rules" ref="formRef" label-width="100px">
        <el-form-item label="이름" prop="custNm">
          <el-input v-model="form.custNm" />
        </el-form-item>

        <el-form-item label="나이" prop="age">
          <el-input v-model="form.age" placeholder="" />
        </el-form-item>

        <el-form-item label="성별" prop="gender">
          <el-radio-group v-model="form.gender">
            <el-radio label="남성">남성</el-radio>
            <el-radio label="여성">여성</el-radio>
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
          <el-button type="primary" @click="submitForm">가입하기</el-button>
          <el-button @click="resetForm" style="margin-left: 10px;">초기화</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'

const router = useRouter()  // 라우터 인스턴스 사용

const formRef = ref(null)

const form = ref({
  custNm: '',
  age: '',
  gender: '',
  phone: '',
  postCode: '',
  addr1: '',
  addr2: '',
})
const rules = {
  custNm: [{ required: true, message: '이름을 입력하세요', trigger: 'blur' }],
  phone: [{ required: true, message: '연락처를 입력하세요', trigger: 'blur' }],
  postCode: [{ required: true, message: '우편번호를 입력하세요', trigger: 'blur' }],
  addr1: [{ required: true, message: '기본주소를 입력하세요', trigger: 'blur' }],
  addr2: [{ required: true, message: '상세주소를 입력하세요', trigger: 'blur' }],
}

const submitForm = () => {
  formRef.value.validate(async valid => {
    if (valid) {
      try {
        const res = await axios.post('/api/customer/save', form.value)
        ElMessage.success('회원가입이 완료되었습니다.')
        router.push({ name: 'clientList' })
      } catch (error) {
        console.error(error)
        ElMessage.error('회원가입 중 오류가 발생했습니다.')
      }
    }
  })
}

const resetForm = () => {
  formRef.value.resetFields()
}
</script>
