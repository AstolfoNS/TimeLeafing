<template>
  <div class="login-container">
    <h2>登录</h2>
    <form @submit.prevent="handleLogin">
      <input v-model="username" placeholder="用户名" />
      <input v-model="password" type="password" placeholder="密码" />
      <button type="submit">登录</button>
    </form>
    <p v-if="error">{{ error }}</p>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { login } from '@/api/user'
import { useUserStore } from '@/store/user'

const router = useRouter()
const userStore = useUserStore()

const username = ref('')
const password = ref('')
const error = ref('')

const handleLogin = async () => {
  try {
    const res = await login({ username: username.value, password: password.value });

    userStore.setToken(res.data.token);

    await router.push('/');
  } catch (err: any) {
    error.value = err?.response?.data?.msg || '登录失败'
  }
}
</script>

<style scoped>
.login-container {
  max-width: 300px;
  margin: 100px auto;
}
input {
  display: block;
  margin: 10px 0;
  width: 100%;
  padding: 8px;
}
</style>
