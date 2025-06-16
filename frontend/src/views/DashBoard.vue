<template>
  <h1>Dash Board</h1>
  <div class="dashboard-container">
    <div class="row">
      <DashboardChart class="chart"/>
      <DashboardCalendar class="calendar"/>
    </div>
    <div class="row">
      <DashboardToday class="today"/>
      <DashboardRecentCustomer class="customer" />
    </div>
  </div>
</template>

<script setup>
import DashboardCalendar from "@/views/dashboardMgmt/DashboardCalendar.vue";
import DashboardChart from "@/views/dashboardMgmt/DashboardChart.vue";
import DashboardToday from "@/views/dashboardMgmt/DashboardToday.vue";
import DashboardRecentCustomer from "@/views/dashboardMgmt/DashboardRecentCustomer.vue";
</script>

<style scoped>
.dashboard-container {
  display: flex;
  flex-direction: column;
  gap: 20px;
  //display: flex;
  //flex-wrap: nowrap;
  //width: 100%;
  //gap: 0; /* 간격 없이 반반 */
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

.today {
  flex: 1;
  padding: 10px;
  box-sizing: border-box;
}

.customer {
  flex: 1;
  padding: 10px;
  box-sizing: border-box;
}

.row {
  display: flex;
  gap: 20px;
}

.row > * {
  flex: 1;
  min-width: 0;
}
</style>
