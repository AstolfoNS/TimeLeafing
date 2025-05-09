server-admin/                   # 后台管理系统模块 (面向管理者)
   ├── src/main/java/com/astolfo
   │   │
   │   ├── api/                      # 后台管理系统表现层 (REST API)
   │   │   ├── controller/           # 面向管理员的控制器
   │   │   │   ├── UserController.java         # 管理员管理用户 (禁用、分配角色等)
   │   │   │   ├── RoleController.java         # 管理员管理角色
   │   │   │   ├── MenuController.java         # 管理员管理权限/菜单
   │   │   │   ├── ArticleController.java      # 管理员管理文章 (审核、强制下线等)
   │   │   │   ├── TagController.java          # 管理员管理标签
   │   │   │   └── NotificationController.java # 管理员发送系统通知
   │   │   │
   │   │   ├── dto/                  # 后台管理系统特有的 DTO
   │   │   │   ├── user/             # 用户管理相关的 DTO
   │   │   │   │   ├── UserManagementDto.java
   │   │   │   │   └── AssignRoleCommand.java
   │   │   │   ├── article/          # 文章管理相关的 DTO
   │   │   │   └── ...
   │   │   │
   │   │   └── exceptionhandler/     # 后台管理系统层面的异常处理
   │   │
   │   └── application/              # 后台管理系统应用层 (协调用例)
   │       ├── service/              # 面向管理员的应用服务 (调用 common 的 Repository 和 Domain Service)
   │       │   ├── UserManagementService.java      # 处理管理员对用户的各种管理操作
   │       │   ├── RbacManagementService.java      # 处理角色和权限的管理，用户角色分配
   │       │   ├── ArticleManagementService.java   # 处理文章的审核、强制删除等管理流程
   │       │   └── NotificationManagementService.java # 处理管理员发送通知等
   │       │
   │       └── ...                   # 其他应用层组件
   │
   └── pom.xml (或 build.gradle)     # admin-system 模块的构建文件 (依赖 common)

