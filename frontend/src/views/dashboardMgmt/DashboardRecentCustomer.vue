<template>
  <div class="customer-container">
    <h3>Recent Customers</h3>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="name" label="이름" />
      <el-table-column prop="tel" label="연락처" />
      <el-table-column prop="post" label="우편번호" />
      <el-table-column prop="addr1" label="기본주소" />
      <el-table-column prop="addr2" label="상세주소" />
      <el-table-column prop="created_at" label="가입일" />
    </el-table>
  </div>
</template>

<script setup>
import {ref, computed} from 'vue';

const rawData = [
  { created_at: "2024-01-01", name: "홍길동1", tel: "010-1235-0001", post: "12345", addr1: "인천광역시 남동구 구월동 LG자이", addr2: "101동1호" },
  { created_at: "2024-01-01", name: "홍길동2", tel: "010-1235-0002", post: "12346", addr1: "인천광역시 남동구 구월동 LG자이", addr2: "102동2호" },
  { created_at: "2024-02-01", name: "홍길동3", tel: "010-1235-0003", post: "12347", addr1: "인천광역시 남동구 구월동 LG자이", addr2: "103동3호" },
  { created_at: "2024-01-01", name: "홍길동4", tel: "010-1235-0004", post: "12348", addr1: "인천광역시 남동구 구월동 LG자이", addr2: "104동4호" },
  { created_at: "2024-03-11", name: "홍길동5", tel: "010-1235-0005", post: "12349", addr1: "인천광역시 남동구 구월동 LG자이", addr2: "105동5호" },
  { created_at: "2024-01-01", name: "홍길동6", tel: "010-1235-0006", post: "12350", addr1: "인천광역시 남동구 구월동 LG자이", addr2: "106동6호" },
  { created_at: "2024-01-01", name: "홍길동7", tel: "010-1235-0007", post: "12351", addr1: "인천광역시 남동구 구월동 LG자이", addr2: "107동7호" },
  { created_at: "2024-01-01", name: "홍길동8", tel: "010-1235-0008", post: "12352", addr1: "인천광역시 남동구 구월동 LG자이", addr2: "108동8호" },
  { created_at: "2024-01-01", name: "홍길동9", tel: "010-1235-0009", post: "12353", addr1: "인천광역시 남동구 구월동 LG자이", addr2: "109동9호" },
  { created_at: "2024-06-01", name: "홍길동10", tel: "010-1235-0010", post: "12354", addr1: "인천광역시 남동구 구월동 LG자이", addr2: "1010동10호" },
  { created_at: "2024-04-01", name: "홍길동11", tel: "010-1235-0011", post: "12355", addr1: "인천광역시 남동구 구월동 LG자이", addr2: "1011동11호" },
  { created_at: "2024-01-01", name: "홍길동12", tel: "010-1235-0012", post: "12356", addr1: "인천광역시 남동구 구월동 LG자이", addr2: "1012동12호" },
  { created_at: "2024-01-01", name: "홍길동13", tel: "010-1235-0013", post: "12357", addr1: "인천광역시 남동구 구월동 LG자이", addr2: "1013동13호" },
  { created_at: "2024-03-01", name: "홍길동14", tel: "010-1235-0014", post: "12358", addr1: "인천광역시 남동구 구월동 LG자이", addr2: "1014동14호" },
  { created_at: "2024-01-01", name: "홍길동15", tel: "010-1235-0015", post: "12359", addr1: "인천광역시 남동구 구월동 LG자이", addr2: "1015동15호" },
  { created_at: "2024-05-01", name: "홍길동16", tel: "010-1235-0016", post: "12360", addr1: "인천광역시 남동구 구월동 LG자이", addr2: "1016동16호" },
  { created_at: "2024-01-01", name: "홍길동17", tel: "010-1235-0017", post: "12361", addr1: "인천광역시 남동구 구월동 LG자이", addr2: "1017동17호" },
  { created_at: "2024-06-11", name: "홍길동18", tel: "010-1235-0018", post: "12362", addr1: "인천광역시 남동구 구월동 LG자이", addr2: "1018동18호" },
];

// 날짜 기준 내림차순 정렬 후 상위 10개만 반환
const tableData = computed(() => {
  return [...rawData]     // sort()가 원본 배열 변경하므로, rawData 한번 복사해서 가공하는 게 안전
    .sort((a, b) => new Date(b.created_at) - new Date(a.created_at))
    .slice(0, 5)
})

</script>

<style scoped>
.customer-container {
  display: flex;
  flex-direction: column;
  height: 100%;
}
</style>
