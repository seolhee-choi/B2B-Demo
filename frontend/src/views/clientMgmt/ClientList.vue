<template>
  <div>
    <!-- 테이블 -->
    <el-table
      :data="paginatedData"
      border
      style="width: 95%; margin-bottom: 20px;"
    >
      <!-- <el-table-column prop="custNm" label="이름" width="100" header-align="center" /> -->
      <el-table-column label="이름" width="100" header-align="center">
        <template #default="scope">
          <router-link :to="{ name: 'clientEdit', params: { id: scope.row.custNm } }">
            {{ scope.row.custNm }}
          </router-link>
        </template>
      </el-table-column>

      <!--<el-table-column prop="age" label="나이" width="80" header-align="center" align="center" />-->
      <!-- 나이 컬럼 -->
      <el-table-column label="나이" width="80" header-align="center" align="center">
        <template #default="scope">
          <div v-if="editingRowId === scope.row.custId && editingField === 'age'">
            <el-input
              v-model="editableValue"
              size="small"
              style="width: 60px"
              @keyup.enter="updateOne(scope.row.custId)"
              @blur="cancelEdit"
              ref="ageInput"
            />
          </div>
          <div v-else @click="startEdit(scope.row.custId, 'age', scope.row.age)" style="cursor: pointer;">
            {{ scope.row.age }}
          </div>
        </template>
      </el-table-column>

      <!--<el-table-column prop="gender" label="성별" width="80" header-align="center" align="center" />-->
      <!-- 성별 컬럼 -->
      <el-table-column label="성별" width="80" header-align="center" align="center">
        <template #default="scope">
          <div v-if="editingRowId === scope.row.custId && editingField === 'gender'">
            <el-radio-group v-model="editableValue" @change="updateOne(scope.row.custId)">
              <el-radio label="남성">남성</el-radio>
              <el-radio label="여성">여성</el-radio>
            </el-radio-group>
          </div>
          <div v-else @click="startEdit(scope.row.custId, 'gender', scope.row.gender)" style="cursor: pointer;">
            {{ scope.row.gender }}
          </div>
        </template>
      </el-table-column>

      <!--<el-table-column prop="phone" label="연락처" width="150" header-align="center" align="center" />-->
      <!-- 연락처 컬럼 -->
      <el-table-column label="연락처" width="150" header-align="center" align="center">
        <template #default="scope">
          <div v-if="editingRowId === scope.row.custId && editingField === 'phone'">
            <el-input
              v-model="editableValue"
              size="small"
              style="width: 120px"
              @keyup.enter="updateOne(scope.row.custId)"
              @blur="cancelEdit"
              ref="phoneInput"
            />
          </div>
          <div v-else @click="startEdit(scope.row.custId, 'phone', scope.row.phone)" style="cursor: pointer;">
            {{ scope.row.phone }}
          </div>
        </template>
      </el-table-column>

      <el-table-column prop="postCode" label="우편번호" width="100" header-align="center" align="center" />
      <el-table-column prop="addr1" label="기본주소" header-align="center" />
      <el-table-column prop="addr2" label="상세주소" header-align="center" />
      <el-table-column prop="createdAt" label="가입일자" width="100" header-align="center" />
    </el-table>

    <!-- 페이지네이션과 검색창을 같은 줄에 정렬 -->
    <div style="display: flex; justify-content: space-between; align-items: center; width: 80%; margin: 0 auto;">
      <!-- 페이지네이션 -->
      <el-pagination
        background
        layout="prev, pager, next"
        :total="tableData.length"
        :page-size="pageSize"
        v-model:current-page="currentPage"
      />

      <!-- 검색 영역 -->
      <div style="display: flex; align-items: center;">
        <el-select v-model="searchType" placeholder="검색 조건" style="width: 120px; margin-right: 10px;">
          <el-option label="이름" value="name" />
          <!--<el-option label="연락처" value="tel" />-->
        </el-select>

        <el-input
          v-model="searchKeyword"
          placeholder="검색어를 입력하세요"
          style="width: 200px; margin-right: 10px;"
          @keyup.enter="fetchData"
        />

        <el-button type="primary" @click="fetchData">검색</el-button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, nextTick } from 'vue'
import apiClient from '@/utils/ApiClient.js'

// 반응형 데이터
const tableData = ref([])
const currentPage = ref(1)
const pageSize = 5

// 검색
const searchType = ref('name')  // 'name' 또는 'tel'
const searchKeyword = ref('')

// 페이지에 따른 데이터 계산
const paginatedData = computed(() => {
  const start = (currentPage.value - 1) * pageSize
  return tableData.value.slice(start, start + pageSize)
})

// 편집 관련 상태
const editingRowId = ref(null) // 현재 편집 중인 행 custId
const editingField = ref('')   // 현재 편집 중인 필드 (age, gender, phone)
const editableValue = ref('')  // 편집 중인 값

// 편집 시작: 클릭 시 호출
const startEdit = (custId, field, value) => {
  editingRowId.value = custId
  editingField.value = field
  editableValue.value = value
  nextTick(() => {
    const input = document.querySelector('input.el-input__inner')
    if (input) input.focus()
  })
}

// 편집 취소 (blur 등에서 호출)
const cancelEdit = () => {
  editingRowId.value = null
  editingField.value = ''
  editableValue.value = ''
}

const fieldMapping = {
  age: 'AGE',
  gender: 'GENDER',
  phone: 'PHONE',
  postCode: 'POST_CODE'
}

// 항목 업데이트 함수
const updateOne = async (custId) => {
  if (editableValue.value === '') {
    alert('값을 입력해주세요.')
    return
  }

  try {

    await apiClient.post(`/api/customer/updateOne?custId=${custId}&field=${fieldMapping[editingField.value]}&value=${editableValue.value}`)

    const idx = tableData.value.findIndex(item => item.custId === custId)
    if (idx !== -1) {
      tableData.value[idx][editingField.value] = editableValue.value
    }
    cancelEdit()
  } catch (error) {
    console.error('수정 중 오류 발생:', error)
    alert('수정에 실패했습니다. 다시 시도해주세요.')
  }
}

// 데이터 요청 함수
const fetchData = async () => {
  try {
    const response = await apiClient.post('/api/customer/list', {
      sword: searchKeyword.value
    })
    tableData.value = response.data.body  // 응답 데이터 구조에 맞게 조정

    currentPage.value = 1 // 검색 후 페이지를 1로 초기화
  } catch (error) {
    console.error('데이터를 불러오는 중 오류 발생:', error)
  }
}

// 초기 데이터 불러오기
fetchData()
</script>
