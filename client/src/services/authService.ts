// src/services/authService.ts

// 定义用户类型，与 store 中的 User 类型匹配
interface User {
  id: number;
  username: string;
  email: string;
  password?: string; // 密码字段可选，因为我们不会返回它
}

// 模拟的后端用户数据
const mockUsers: User[] = [
  {
    id: 1,
    username: "user1",
    email: "user1@example.com",
    password: "password123",
  },
  {
    id: 2,
    username: "admin",
    email: "admin@example.com",
    password: "adminpassword",
  },
];

let nextUserId =
  mockUsers.length > 0 ? Math.max(...mockUsers.map((u) => u.id)) + 1 : 1;

/**
 * 模拟登录 API 请求
 * @param {string} username
 * @param {string} password
 * @returns {Promise<{token: string, user: Omit<User, 'password'>}>}
 */
export const login = (
  username: string,
  password: string
): Promise<{ token: string; user: Omit<User, "password"> }> => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const user = mockUsers.find(
        (u) => u.username === username && u.password === password
      );

      if (user) {
        const token = `mock-jwt-token-${user.id}-${Date.now()}`;
        const { password: _, ...userWithoutPassword } = user; // 排除密码字段
        resolve({ token, user: userWithoutPassword });
      } else {
        reject(new Error("用户名或密码不正确。"));
      }
    }, 500);
  });
};

/**
 * 模拟注册 API 请求
 * @param {string} username
 * @param {string} email
 * @param {string} password
 * @returns {Promise<{message: string}>}
 */
export const register = (
  username: string,
  email: string,
  password: string
): Promise<{ message: string }> => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const existingUser = mockUsers.find(
        (u) => u.username === username || u.email === email
      );

      if (existingUser) {
        return reject(new Error("用户名或邮箱已被注册。"));
      }

      const newUser: User = {
        id: nextUserId++,
        username,
        email,
        password,
      };
      mockUsers.push(newUser);
      resolve({ message: "注册成功！" });
    }, 500);
  });
};

/**
 * 模拟注销 API 请求
 * @returns {Promise<void>}
 */
export const logout = (): Promise<void> => {
  return new Promise((resolve) => {
    setTimeout(() => {
      console.log("模拟注销成功");
      resolve();
    }, 100);
  });
};
