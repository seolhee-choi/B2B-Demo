<script setup>

import DefaultLayout from "@/layouts/DefaultLayout.vue";
import DashboardCalendar from "@/views/dashboardMgmt/DashboardCalendar.vue";
import DashboardChart from "@/views/dashboardMgmt/DashboardChart.vue";
</script>

<template>
  <default-layout>
    <template v-slot:content>
      <h1>Dash Board</h1>
      <div class="dashboard-container">
        <DashboardChart class="chart"/>
        <DashboardCalendar class="calendar"/>
      </div>
    </template>
  </default-layout>
</template>

<style scoped>
.dashboard-container {
  display: flex;
  flex-wrap: nowrap;
  width: 100%;
  gap: 0; /* 간격 없이 반반 */
}

.chart {
  flex: 1;
  padding: 10px;
  box-sizing: border-box;
}

.calendar {
  flex: 1;
  padding: 10px;
  box-sizing: border-box;
}
</style>
