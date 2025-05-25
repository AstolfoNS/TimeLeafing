<template>
  <v-container
    fill-height
    fluid
    class="d-flex align-center justify-center bg-grey-lighten-3"
  >
    <v-card width="400" class="elevation-6">
      <v-toolbar density="compact" color="primary" title="登录"></v-toolbar>

      <v-card-text>
        <LoginForm @submit-login="handleLogin" :loading="authStore.loading" />
      </v-card-text>

      <v-divider></v-divider>

      <v-card-actions>
        <v-btn
          color="success"
          text
          @click="goToRegister"
          :disabled="authStore.loading"
          >注册</v-btn
        >
        <v-spacer />
      </v-card-actions>
    </v-card>

    <v-snackbar v-model="snackbar" :timeout="3000">
      {{ authStore.error || snackbarMessage }}
      <template v-slot:actions>
        <v-btn color="blue" variant="text" @click="snackbar = false"
          >关闭</v-btn
        >
      </template>
    </v-snackbar>
  </v-container>
</template>

<script setup lang="ts">
import { ref, watch } from "vue";
import { useRouter } from "vue-router";
import LoginForm from "@/components/auth/LoginForm.vue";
import { useAuthStore } from "@/store/auth";

// 如果你使用 vue-router/auto，可以在这里定义页面元信息
// import { definePageMeta } from 'vue-router/auto';
// definePageMeta({
//   requiresAuth: false,
//   guestOnly: true
// });

const authStore = useAuthStore();
const router = useRouter();

const snackbar = ref(false);
const snackbarMessage = ref("");

watch(
  () => authStore.error,
  (newError) => {
    if (newError) {
      snackbar.value = true;
    }
  }
);

interface LoginCredentials {
  username: string;
  password: string;
}

const handleLogin = async ({ username, password }: LoginCredentials) => {
  try {
    await authStore.login(username, password);
    snackbarMessage.value = "登录成功！";
    snackbar.value = true;
    const redirectPath =
      router.currentRoute.value.query.redirect || "/dashboard";
    router.push(redirectPath as string);
  } catch (error) {
    console.error("Login failed in component:", error);
    // 错误信息会由 authStore.error 自动触发 snackbar 显示
  }
};

const goToRegister = () => {
  router.push("/register");
};
</script>
