<template>
  <div class="chart-container">
    <h3>Chart</h3>
    <div class="chart-wrapper">
      <canvas ref="chartRef"></canvas>
    </div>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue';
import {Chart, registerables} from 'chart.js';

Chart.register(...registerables);

const chartRef = ref(null);

// 예시 데이터
const rawData = [
  {uid: "1", product_category: "냉장고", price: "90000", create_at: "2025-01-10"},
  {uid: "2", product_category: "냉장고", price: "95000", create_at: "2025-01-20"},
  {uid: "3", product_category: "냉장고", price: "85000", create_at: "2025-03-10"},
  {uid: "4", product_category: "TV", price: "60000", create_at: "2025-02-15"},
  {uid: "5", product_category: "TV", price: "87000", create_at: "2025-02-25"},
  {uid: "6", product_category: "식기세척기", price: "67000", create_at: "2025-02-28"},
  {uid: "7", product_category: "TV", price: "90000", create_at: "2025-03-15"},
  {uid: "8", product_category: "식기세척기", price: "90000", create_at: "2025-03-18"},
];

// 월 기준으로 그룹핑: { "2025-01": { 냉장고: 180000 }, ... }
const monthlyCategorySales = {}
rawData.forEach(item => {
  const month = item.create_at.slice(0, 7); // "2025-01"
  const category = item.product_category;
  const price = parseInt(item.price);

  if (!monthlyCategorySales[month]) monthlyCategorySales[month] = {};
  if (!monthlyCategorySales[month][category]) monthlyCategorySales[month][category] = 0;

  monthlyCategorySales[month][category] += price;
})

const allMonths = Object.keys(monthlyCategorySales).sort();               // 모든 월을 정렬된 배열로 저장 (x축으로 사용)
const allCategories = [...new Set(rawData.map(d => d.product_category))]; // 전체에서 유니크한 카테고리 추출 (구분용)

// 카테고리별 dataset 구성
const categoryColors = {
  '냉장고': '#4e79a7',
  'TV': '#f28e2c',
  '식기세척기': '#76b7b2'
}

const datasets = allCategories.map(category => {
  return {
    label: category,
    data: allMonths.map(month => monthlyCategorySales[month]?.[category] || 0),   // 모든 월에 대해 해당 카테고리의 판매금액 (없으면 0)
    backgroundColor: categoryColors[category] || '#ccc',
    stack: 'total'
  }
})


onMounted(() => {
  new Chart(chartRef.value, {
    type: 'bar',
    data: {
      labels: allMonths,
      datasets
    },
    options: {
      responsive: true,           // 반응형
      maintainAspectRatio: false, // 비율유지
      plugins: {
        tooltip: {   // 숫자에 원단위 표시
          callbacks: {
            label: ctx => `${ctx.dataset.label}: ${ctx.raw.toLocaleString()}원`
          }
        }
      },
      scales: {
        x: {
          stacked: true, // 누적 표현
        },
        y: {
          stacked: true,
          beginAtZero: true,
          ticks: {
            callback: value => `${value.toLocaleString()}원`
          }
        }
      }
    }
  })
})

</script>

<style scoped>
.chart-container {
  display: flex;
  flex-direction: column;
  height: 100%; /* 부모가 높이를 가지고 있어야 유효함 */
}

.chart-wrapper {
  flex: 1;
  width: 100%;
  height: 100%;
}

canvas {
  width: 100% !important;
  height: 100% !important;
}

</style>
