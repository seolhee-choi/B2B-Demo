<script setup>
import SampleService from "@/service/SampleService.js";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import sampleService from "@/service/SampleService.js";

const postReq = () => {
  SampleService.testPost();
};

const getReq = () => {
  sampleService.testGet();
};

</script>

<template>
  <default-layout>
    <template v-slot:content>
      <h1>Sample Page</h1>
      <button @click="postReq">post</button>
      <button @click="getReq">get</button>
    </template>
  </default-layout>
</template>

<style scoped>

</style>
