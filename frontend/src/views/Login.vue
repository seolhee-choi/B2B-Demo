<template>
  <login-layout>
    <template v-slot:content>
      <h1>Login Page</h1>
      <div class="form-floating">
        <label for="floatingInput">ID</label>
        <input
          v-model="state.form.id"
          @keyup.enter="submit"
        />
      </div>
      <div class="form-floating">
        <label for="floatingPassword">Password</label>
        <input
          type="password"
          v-model="state.form.password"
          @keyup.enter="submit"
        />
      </div>
      <button @click="submit">
        로그인
      </button>
    </template>
  </login-layout>
</template>

<script setup>
import LoginLayout from '@/layouts/LoginLayout.vue'
import apiClient from '@/utils/ApiClient.js'
import ValidationUtils  from '@/utils/ValidationUtils.js'
import _ from 'lodash'
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

</style>
