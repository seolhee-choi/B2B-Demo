<template>
  <div class="customer-container">
    <h3>Recent Customers</h3>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="custNm" label="이름" />
      <el-table-column prop="phone" label="연락처" />
      <el-table-column prop="postCode" label="우편번호" />
      <el-table-column prop="addr1" label="기본주소" />
      <el-table-column prop="addr2" label="상세주소" />
      <el-table-column prop="createdAt" label="가입일" />
    </el-table>
  </div>
</template>

<script setup>
import {ref, computed, onMounted} from 'vue';
import apiClient from "@/utils/ApiClient.js";

const customerData = ref([]);

async function fetchRecentCustomer() {
  try {
    const res = await apiClient.post('/api/customer/list', {
      sword: ''
    });
    console.log("RES::::::::::: ", res.data.body);
    if (res && res.data && res.data.body) {
      customerData.value = res.data.body;
    }
  } catch (e) {
    console.error('최근고객 데이터 로딩 실패:', e);
  }
}

// 날짜 기준 내림차순 정렬 후 상위 5개만 반환
const tableData = computed(() => {
  return [...customerData.value]     // sort()가 원본 배열 변경하므로, rawData 한번 복사해서 가공하는 게 안전
    .sort((a, b) => new Date(b.created_at) - new Date(a.created_at))
    .slice(0, 5)
})

onMounted(() => {
  fetchRecentCustomer();
});

</script>

<style scoped>
.customer-container {
  display: flex;
  flex-direction: column;
  height: 100%;
}
</style>
