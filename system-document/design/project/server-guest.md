server-guest/               # 社区系统模块 (面向普通用户)
   ├── src/main/java/com/astolfo/
   │   │
   │   ├── api/                      # 社区系统表现层 (REST API)
   │   │   ├── controller/           # 面向普通用户的控制器
   │   │   │   ├── ArticleController.java      # 用户浏览/发布/修改自己的文章
   │   │   │   ├── CommentController.java      # 用户评论文章
   │   │   │   ├── TagController.java          # 用户浏览标签
   │   │   │   ├── UserController.java         # 用户个人信息, 关注/被关注列表
   │   │   │   ├── SocialController.java       # 用户执行关注/点赞/收藏操作
   │   │   │   └── NotificationController.java # 用户查看通知
   │   │   │
   │   │   ├── dto/                  # 社区系统特有的 DTO
   │   │   │   ├── article/          # 文章相关的展示 DTO
   │   │   │   │   ├── ArticleListDto.java
   │   │   │   │   └── ArticleDetailDto.java
   │   │   │   ├── comment/
   │   │   │   ├── user/
   │   │   │   └── ...
   │   │   │
   │   │   └── exceptionhandler/     # 社区系统层面的异常处理 (如果需要特定处理)
   │   │
   │   └── application/              # 社区系统应用层 (协调用例)
   │       ├── service/              # 面向普通用户的应用服务 (调用 common 的 Repository 和 Domain Service)
   │       │   ├── ArticleAppService.java      # 处理用户发布、修改、删除（软删除）文章等业务流程
   │       │   ├── CommentAppService.java      # 处理用户发表、回复评论等
   │       │   ├── UserAppService.java         # 处理用户更新个人信息，查看关注/粉丝等
   │       │   ├── SocialAppService.java       # 处理用户关注、点赞、收藏的业务流程
   │       │   └── NotificationAppService.java # 处理用户标记通知已读等
   │       │
   │       └── ...                   # 其他应用层组件
   │
   └── pom.xml (或 build.gradle)     # community-system 模块的构建文件 (依赖 common)
