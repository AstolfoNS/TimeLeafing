<template>
  <v-form @submit.prevent="submitForm">
    <v-text-field
      v-model="username"
      label="用户名"
      prepend-inner-icon="mdi-account-circle"
      variant="outlined"
      class="mb-4"
      :rules="[rules.required]"
      required
      :disabled="loading"
    />

    <v-text-field
      v-model="password"
      label="密码"
      prepend-inner-icon="mdi-lock"
      variant="outlined"
      :type="showPassword ? 'text' : 'password'"
      :append-inner-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
      @click:append-inner="togglePasswordVisibility"
      class="mb-4"
      :rules="[rules.required, rules.minPasswordLength]"
      required
      :disabled="loading"
    />

    <div class="d-flex justify-end">
      <v-btn
        color="info"
        type="submit"
        :disabled="!isFormValid || loading"
        :loading="loading"
        >登录</v-btn
      >
    </div>
  </v-form>
</template>

<script setup lang="ts">
import { ref, computed } from "vue";

const username = ref<string>("");

const password = ref<string>("");

const showPassword = ref<boolean>(false);

interface Props {
  loading?: boolean;
}

const props = defineProps<Props>();

interface Emits {
  (e: "submit-login", payload: { username: string; password: string }): void;
}

const emit = defineEmits<Emits>();

const togglePasswordVisibility = (): void => {
  showPassword.value = !showPassword.value;
};

// 简单的表单验证规则
const rules = {
  required: (value: string) => !!value || "此项为必填。",
  minPasswordLength: (value: string) =>
    value.length >= 6 || "密码至少需要6位字符。",
};

const isFormValid = computed<boolean>(() => {
  return !!username.value && !!password.value && password.value.length >= 6;
});

const submitForm = (): void => {
  if (isFormValid.value) {
    emit("submit-login", {
      username: username.value,
      password: password.value,
    });
  }
};
</script>
