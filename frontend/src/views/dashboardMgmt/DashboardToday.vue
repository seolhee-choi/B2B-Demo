<template>
  <div class="today-container">
    <h3>Today's Sales</h3>

    <el-table
      :data="tableData"
      style="width: 100%; margin-bottom: 20px"
      row-key="id"
      border
      lazy
      :load="load"
      :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
    >
    <!--
         - row-key 필수
         - lazy : 자식행 필요할때만 로드
         - load : 자식 데이터 불러오는 함수
         - hasChildren : 자식이 있는 줄 알고 load() 호출
    -->
      <el-table-column prop="product_nm" label="제품명" />
      <el-table-column prop="product_cd" label="제품코드" />
      <el-table-column prop="cost" label="출고가" />
      <el-table-column prop="discount" label="할인가" />
      <el-table-column prop="price" label="판매가" />
    </el-table>
  </div>
</template>

<script setup>
import { ref } from 'vue'

// 부모 row에서 자식 row를 동적으로 불러오는 함수
const load = (row, treeNode, resolve) => {
  setTimeout(() => {
    resolve([
      {
        id: row.id * 10 + 1,
        product_nm: row.product_nm + ' - 옵션 A',
        product_cd: row.product_cd + '-A',
        cost: row.cost,
        discount: row.discount,
        price: row.price,
      },
      {
        id: row.id * 10 + 2,
        product_nm: row.product_nm + ' - 옵션 B',
        product_cd: row.product_cd + '-B',
        cost: row.cost,
        discount: row.discount,
        price: row.price,
      },
    ])
  }, 500)
}

const tableData = ref([
  {
    id: 1,
    product_nm: '비스포크 AI 냉장고 4도어 905L',
    product_cd: 'RF85DB9581A1A',
    cost: '100000',
    discount: '10000',
    price: 90000,
    hasChildren: true,
  },
  {
    id: 2,
    product_nm: '인피니트 AI 냉장고 1도어 키친핏 408L',
    product_cd: 'RF85DB9581A1B',
    cost: '100000',
    discount: '10000',
    price: 90000,
    hasChildren: true,
  },
  {
    id: 3,
    product_nm: '2025 Neo QLED 사운드바',
    product_cd: 'TV85DB9581A1B',
    cost: '100000',
    discount: '10000',
    price: 90000,
  },
  {
    id: 4,
    product_nm: '비스포크 식기세척기 카운터탑 6인용',
    product_cd: 'DW85DB9581A1A',
    cost: '100000',
    discount: '10000',
    price: 90000,
  },
  {
    id: 5,
    product_nm: '양문형 냉장고 623L',
    product_cd: 'RF85DB9581A1C',
    cost: '100000',
    discount: '10000',
    price: 90000,
  },
])
</script>

<style scoped>
.today-container {
  padding: 16px;
}
</style>
