<template>
  <div class="calendar-container">
    <el-calendar v-model="value">
      <template #date-cell="{ data }">
        <div
          :class="[
            'cell',
            isSelected(data.day) ? 'selected' : '']"
          @click="selectDate(data.day)"
        >
          <div class="date">{{ new Date(data.day).getDate() }}</div>
          <div v-if="eventsMap[data.day]" class="event"
               v-html="eventsMap[data.day].map(e => e.cust_nm).join('<br>')">
          </div>

        </div>
      </template>
    </el-calendar>

    <div class="event-details" v-if="selectedEvents.length">
      <h4>{{ formatDate(selectedDate) }} 예약 고객님 목록</h4>
      <ul>
        <li v-for="(event, index) in selectedEvents" :key="index">
          <strong>{{ event.time }}</strong> - {{ event.cust_nm }} ({{ event.type }})
          <ul class="tag-list">
            <li v-for="(tag, idx) in event.interest_pd" :key="idx" class="tag">{{ tag }}</li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import {ref, computed, onMounted} from 'vue';
import 'element-plus/es/components/calendar/style/css';
import apiClient from "@/utils/ApiClient.js";

// 기본 선택 날짜
const value = ref(new Date());
const selectedDate = ref(formatDateKey(value.value));

const eventsMap = ref({}); // API로 받아서 설정
const selectedEvents = computed(() => eventsMap.value[selectedDate.value] || []);

// 날짜 선택 시 업데이트
function selectDate(date) {
  selectedDate.value = formatDateKey(date);
}

// 날짜 포맷: YYYY-MM-DD
function formatDateKey(date) {
  const d = new Date(date);
  return d.toISOString().split('T')[0];
}

function formatDate(dateStr) {
  const d = new Date(dateStr);
  return `${d.getFullYear()}년 ${d.getMonth() + 1}월 ${d.getDate()}일`;
}

function isSelected(date) {
  return formatDateKey(date) === selectedDate.value;
}

// API 호출하여 예약 데이터 가져오기
async function fetchReservations() {
  try {
    const res = await apiClient.post('/api/reserv/getReservationsByDate');
    console.log("RES::::::::::: ", res)
    if (res && res.data && res.data.body) {
      eventsMap.value = convertToEventsMap(res.data.body);
    }
  } catch (e) {
    console.error('예약 데이터 로딩 실패:', e);
  }
}

// API 데이터 → eventsMap 변환
function convertToEventsMap(apiData) {
  const map = {};
  for (const date in apiData) {
    map[date] = apiData[date].map(item => ({
      time: item.time,
      cust_nm: item.cust_nm,
      type: item.type,
      interest_pd: item.interest_pd, // 태그는 배열로 유지
      title: `${item.cust_nm} 고객님 ${item.type} 예약`
    }));
  }
  return map;
}

onMounted(() => {
  fetchReservations();
});

// 날짜별 이벤트 맵 (예시)
// const eventsMap = {
//   '2025-06-16': [
//     { time: '11:00', title: '이재혁 고객님 상담 예약' },
//     { time: '11:30', title: '최수정 고객님 상담 예약' }
//   ],
//   '2025-06-20': [
//     { time: '15:00', title: '홍길동 고객님 예약' }
//   ],
//   '2025-06-18': [
//     { time: '15:00', title: '서강준 고객님 예약' }
//   ]
// }


</script>

<style scoped>
.calendar-container {
  display: flex;
  flex-direction: column;
  height: 100%;

}

.cell {
  padding: 6px;
  border-radius: 6px;
  cursor: pointer;
  height: 60px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
}

.today {
  background-color: #f0f9ff;
  border: 1px solid #409eff;
}

.selected {
  background-color: #e0f7e9;
  border: 1px solid #67c23a;
}

.date {
  font-weight: bold;
  font-size: 14px;
}

.event {
  font-size: 12px;
  margin-top: 4px;
  color: #409eff;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.event-details {
  margin-top: 1rem;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 6px;
  background-color: #f9f9f9;
}
</style>
