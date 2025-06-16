<template>
      <h1>Login Page</h1>
      <el-form-item class="form-floating">
        <label for="floatingInput">ID</label>
        <el-input
          v-model="state.form.id"
          @keyup.enter="submit"
        />
      </el-form-item>
      <el-form-item class="form-floating">
        <label for="floatingPassword">Password</label>
        <el-input
          type="password"
          v-model="state.form.password"
          @keyup.enter="submit"
        />
      </el-form-item>
      <el-button type="primary" @click="submit">
        로그인
      </el-button>
</template>

<script setup>
import _ from 'lodash'
import apiClient from '@/utils/ApiClient.js'
import ValidationUtils  from '@/utils/ValidationUtils.js'
import { reactive } from 'vue'

const state = reactive({
  form : {
    id: '',
    password: '',
  }
});
const submit = async () => {
  const errors = ValidationUtils.validateLogin(state.form);

  if (_.isEmpty(errors)) {
      return await apiClient.post('/api/login', state.form)
      .then(res => res.data)
      .catch(err => console.log(err));
  } else {
    alert(errors.id || errors.password);
    return false;
  }
}

</script>
<style scoped>
.el-input {
  width: 240px;
}
</style>
