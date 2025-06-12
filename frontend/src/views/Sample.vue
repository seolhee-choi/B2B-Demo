<script setup>
import SampleService from "@/service/SampleService.js";
import sampleService from "@/service/SampleService.js";

const postReq = () => {
  SampleService.testPost();
};

const getReq = () => {
  sampleService.testGet();
};

</script>

<template>
      <h1>Sample Page</h1>
      <button @click="postReq">post</button>
      <button @click="getReq">get</button>
</template>

<style scoped>

</style>
