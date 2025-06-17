<template>
  <h1>Sales List</h1>

  <el-table :data="salesList" style="width: 100%;" stripe border>
    <el-table-column prop="productCode" label="제품코드" min-width="140" />
    <el-table-column prop="productName" label="제품명" min-width="120" />
    <el-table-column
      prop="unitPrice"
      label="판매단가"
      min-width="120"
      :formatter="({ unitPrice }) => formatCurrency(unitPrice)"
    />
    <el-table-column
      prop="discount"
      label="할인금액"
      min-width="120"
      :formatter="({ discount }) => formatCurrency(discount)"
    />
    <el-table-column
      prop="saleAmount"
      label="판매금액"
      min-width="120"
      :formatter="({ saleAmount }) => formatCurrency(saleAmount)"
    />
    <el-table-column prop="deliveryDate" label="배송요청일" min-width="130" />
    <el-table-column prop="createdAt" label="생성일" min-width="130" />
  </el-table>
</template>

<script setup>
defineOptions({ name: 'SalesList' })

import { ref, onMounted } from 'vue'
import axios from 'axios'

// 통화 포맷 함수
const formatCurrency = (value) => {
  return '₩' + Number(value).toLocaleString()
}

const salesList = ref([])

const fetchSalesList = async () => {
  try {
    const res = await axios.get('/api/sales/list')
    const rawList = res.data.data

    // 필드 매핑해서 UI에 맞게 변환
    salesList.value = rawList.map((item) => ({
      productCode: item.proCd,
      productName: item.proNm,
      unitPrice: item.price,
      discount: item.disPrice,
      saleAmount: item.finPrice,
      deliveryDate: item.deliveryDate,
      createdAt: item.createdAt ?? '', // API에 없으면 빈 문자열로 처리
    }))
  } catch (error) {
    console.error('판매 목록 조회 실패:', error)
  }
}

// 컴포넌트 마운트 시 API 호출
onMounted(() => {
  fetchSalesList()
})
</script>

<style scoped>
h1 {
  margin-bottom: 20px;
  font-size: 20px;
  font-weight: bold;
}
</style>
