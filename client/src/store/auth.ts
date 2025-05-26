// src/store/auth.ts

import { defineStore } from "pinia";
import * as authService from "@/services/authService"; // 导入你之前创建的 authService

// 定义用户类型
interface User {
  id: number;
  username: string;
  email: string;
  // 其他用户属性...
}

// 定义认证状态的类型
interface AuthState {
  user: User | null;
  isAuthenticated: boolean;
  token: string | null;
  loading: boolean;
  error: string | null;
}

export const useAuthStore = defineStore("auth", {
  state: (): AuthState => ({
    user: null,
    isAuthenticated: false,
    token: null,
    loading: false,
    error: null,
  }),

  getters: {
    isLoggedIn: (state): boolean => state.isAuthenticated,
    currentUser: (state): User | null => state.user,
    authToken: (state): string | null => state.token,
  },

  actions: {
    async login(username: string, password: string): Promise<boolean> {
      this.loading = true;

      this.error = null;

      try {
        const response: any = await authService.login(username, password);

        this.token = response.token;
        this.user = response.user;
        this.isAuthenticated = true;

        localStorage.setItem("authToken", response.token);
        localStorage.setItem("currentUser", JSON.stringify(response.user));

        return true;
      } catch (error: any) {
        this.error = error.message || "登录失败，请检查用户名和密码。";

        this.isAuthenticated = false;

        localStorage.removeItem("authToken");

        localStorage.removeItem("currentUser");

        throw error;
      } finally {
        this.loading = false;
      }
    },

    async register(
      username: string,
      email: string,
      password: string
    ): Promise<boolean> {
      this.loading = true;
      this.error = null;
      try {
        await authService.register(username, email, password);
        return true;
      } catch (error: any) {
        this.error = error.message || "注册失败。";
        throw error;
      } finally {
        this.loading = false;
      }
    },

    async logout(): Promise<boolean> {
      this.loading = true;
      this.error = null;
      try {
        await authService.logout();

        this.user = null;
        this.isAuthenticated = false;
        this.token = null;
        localStorage.removeItem("authToken");
        localStorage.removeItem("currentUser");
        return true;
      } catch (error: any) {
        console.error("注销失败（可能后端没有注销接口或网络问题）", error);
        // 即使失败，也要清除本地状态，确保安全
        this.user = null;
        this.isAuthenticated = false;
        this.token = null;
        localStorage.removeItem("authToken");
        localStorage.removeItem("currentUser");
        throw error;
      } finally {
        this.loading = false;
      }
    },

    initializeAuth(): void {
      const token = localStorage.getItem("authToken");
      const userString = localStorage.getItem("currentUser");
      if (token && userString) {
        try {
          const parsedUser: User = JSON.parse(userString);
          this.token = token;
          this.user = parsedUser;
          this.isAuthenticated = true;
        } catch (e) {
          console.error("解析用户数据失败", e);
          this.logout(); // 解析失败则清除登录状态
        }
      } else {
        this.isAuthenticated = false;
        this.user = null;
        this.token = null;
      }
    },
  },
});
