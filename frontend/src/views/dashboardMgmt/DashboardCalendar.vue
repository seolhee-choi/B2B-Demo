<template>
  <div class="calendar-container">
    <h3>Calendar</h3>
    <el-calendar v-model="value"/>
  </div>
</template>

<script setup>
import {ref} from 'vue';
import 'element-plus/es/components/calendar/style/css'
import {ElCalendar} from 'element-plus'

const value = ref(new Date());

</script>

<style scoped>
.calendar-container {
  display: flex;
  flex-direction: column;
  height: 100%;
}
</style>
