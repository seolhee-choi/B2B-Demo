<template>
  <div>
    <!-- 테이블 -->
    <el-table
      :data="tableData"
      border
      style="width: 95%; margin-bottom: 20px;"
    >
      <!-- <el-table-column prop="custNm" label="이름" width="100" header-align="center" /> -->
      <el-table-column label="이름" width="100" header-align="center" align="center">
        <template #default="scope">
          <router-link
            :to="{ name: 'clientEdit', params: { id: scope.row.custNm } }"
            class="link"
          >
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
      <el-table-column prop="addr1" label="기본주소" width="350" header-align="center" align="center" />
      <el-table-column prop="addr2" label="상세주소" width="100" header-align="center" align="center" />
      <el-table-column prop="createdAt" label="가입일자" width="120" header-align="center" align="center" />
      <el-table-column fixed="right" label="옵션" min-width="40" align="center">
        <template #default="scope">
          <el-button
            v-if="!scope.row.isNew"
            link
            type="primary"
            size="small"
            @click.prevent="deleteRow(scope.$index)"
          >
            <svg xmlns="http://www.w3.org/2000/svg" fill="none"
                 viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
                 style="width: 20px; height: 20px;">
              <path stroke-linecap="round" stroke-linejoin="round"
                    d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21
                      c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79
                      m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562
                      c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916
                      c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0
                      c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0"
              />
            </svg>
          </el-button>

          <el-button
            v-else
            link
            type="primary"
            size="small"
            @click.prevent="addRow(scope.$index)"
          >
            <svg xmlns="http://www.w3.org/2000/svg" fill="none"
                 viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
                 style="width: 20px; height: 20px;">
              <path stroke-linecap="round" stroke-linejoin="round"
                    d="M9 12.75 11.25 15 15 9.75M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"
              />
            </svg>
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-button class="mt-4" style="width: 100%" @click="onAddItem">
      테스트 행추가
    </el-button>
    <!-- 페이지네이션과 검색창을 같은 줄에 정렬 -->
    <div style="display: flex; justify-content: space-between; align-items: center; width: 80%; margin: 0 auto;">
      <!-- 페이지네이션 -->
<!--      <el-pagination
        background
        layout="prev, pager, next"
        :total="tableData.length"
        :page-size="pageSize"
        v-model:current-page="currentPage"
      />-->

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
import { ref, nextTick } from 'vue'
import apiClient from '@/utils/ApiClient.js'
import {now} from "lodash";

// 반응형 데이터
const tableData = ref([])
const currentPage = ref(1)
// const pageSize = 5

// 검색
const searchType = ref('name')  // 'name' 또는 'tel'
const searchKeyword = ref('')

/*
// 페이지에 따른 데이터 계산
const paginatedData = computed(() => {
  const start = (currentPage.value - 1) * pageSize
  return tableData.value.slice(start, start + pageSize)
})
*/

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
    const response = await apiClient.post(`/api/customer/list?sword=${searchKeyword.value}`)
    tableData.value = response.data.body  // 응답 데이터 구조에 맞게 조정

    currentPage.value = 1 // 검색 후 페이지를 1로 초기화
  } catch (error) {
    console.error('데이터를 불러오는 중 오류 발생:', error)
  }
}

const deleteRow = (index) => {
  tableData.value.splice(index, 1);
}

const onAddItem = () => {
  tableData.value.push({
    // custId: `temp-${Date.now()}`, // 고유 ID 생성 (임시)
    custNm: '새 고객',            // 이름 컬럼은 custNm임!
    age: '20',
    gender: '여성',
    phone: '010-1234-5678',
    postCode: '12345',
    addr1: '기본주소',
    addr2: '상세주소',
    createdAt: new Date().toISOString().slice(0, 10), // YYYY-MM-DD 형식
    isNew: true
  })
}

const addRow = (index) => {
  alert(index + '가 저장되었습니다.')
}


// 초기 데이터 불러오기
fetchData()
</script>

<style scoped>
.link {
  text-decoration: none;
  color: inherit;
}
</style>

