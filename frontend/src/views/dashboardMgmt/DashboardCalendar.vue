<script setup>
import {onMounted, ref} from 'vue';
import 'element-plus/es/components/calendar/style/css'
import {ElCalendar} from 'element-plus'

const value = ref(new Date());

</script>

<template>
  <div class="calendar-container">
  <h3>Calendar</h3>
  <el-calendar v-model="value"/>
  </div>
</template>

<style scoped>
.calendar-container {
  display: flex;
  flex-direction: column;
  height: 100%;
}
</style>
