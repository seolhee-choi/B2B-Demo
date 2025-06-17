<template>
      <h1>Login Page</h1>
      <el-form-item class="form-floating">
        <label for="floatingInput">ID</label>
        <el-input
          v-model="state.form.email"
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

      <hr>

      <el-button>
        모달1 열기
      </el-button>
      <el-button>
        모달2 열기
      </el-button>
</template>

<script setup>
import _ from 'lodash'
import router from '@/router/index.js'
import apiClient from '@/utils/ApiClient.js'
import ValidationUtils  from '@/utils/ValidationUtils.js'
import { reactive } from 'vue'
import { useAccountStore } from "@/stores/useAccountStore.js";

const accountStore = useAccountStore();
const state = reactive({
  form : {
    email: '',
    password: '',
  }
});
const submit = async () => {
  const errors = ValidationUtils.validateLogin(state.form);

  if (_.isEmpty(errors)) {
    try {
      const res = await apiClient.post('/api/login', state.form);
      await accountStore.setAccount(res.data);
      alert("로그인 성공")
      router.push("/");
      return res.data;
    } catch (err) {
      console.log(err);
      return false;
    }
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
