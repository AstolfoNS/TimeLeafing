your-project-root/
├── pom.xml (或 build.gradle)       # 根项目的构建文件，管理子模块
│
├── common/                         # 公用模块 (Common Module)
│   ├── src/main/java/com/yourcompany/project3/common/
│   │   │
│   │   ├── domain/                   # 领域层 (Domain Layer) - 核心业务模型和逻辑的定义
│   │   │   │
│   │   │   ├── user/                 # 用户领域
│   │   │   │   ├── model/            # 用户相关的实体、值对象、聚合根
│   │   │   │   │   ├── User.java             # User 聚合根/实体 (包含业务行为)
│   │   │   │   │   └── valueobject/      # 用户值对象 (如 EmailAddress)
│   │   │   │   │       └── EmailAddress.java
│   │   │   │   │
│   │   │   │   └── repository/       # 用户仓储接口
│   │   │   │       └── UserRepository.java # 定义获取/保存 User 聚合根的方法
│   │   │   │
│   │   │   ├── rbac/                 # 角色与权限领域
│   │   │   │   ├── model/            # 角色、菜单实体等
│   │   │   │   │   ├── Role.java             # Role 实体
│   │   │   │   │   ├── Menu.java             # Menu/Permission 实体
│   │   │   │   │   └── valueobject/      # RBAC 值对象 (如权限字符串)
│   │   │   │   │       └── PermissionString.java
│   │   │   │   │
│   │   │   │   ├── repository/       # 角色与权限仓储接口
│   │   │   │   │   ├── RoleRepository.java
│   │   │   │   │   └── MenuRepository.java=
│   │   │   │   │
│   │   │   │   └── service/          # RBAC 领域服务 (如权限检查, 角色分配等)
│   │   │   │       └── RbacDomainService.java
│   │   │   │
│   │   │   ├── article/              # 文章领域
│   │   │   │   ├── model/            # 文章实体、值对象、聚合根
│   │   │   │   │   ├── Article.java          # Article 聚合根/实体
│   │   │   │   │   └── valueobject/      # 文章值对象 (如 ArticleContent, ArticleStage)
│   │   │   │   │       ├── ArticleContent.java
│   │   │   │   │       └── ArticleStage.java # 文章状态枚举
│   │   │   │   │
│   │   │   │   └── repository/       # 文章仓储接口
│   │   │   │       └── ArticleRepository.java
│   │   │   │
│   │   │   ├── comment/              # 评论领域
│   │   │   │   ├── model/            # 评论实体
│   │   │   │   │   └── Comment.java          # Comment 实体 (处理评论树结构)
│   │   │   │   │
│   │   │   │   └── repository/       # 评论仓储接口
│   │   │   │       └── CommentRepository.java
│   │   │   │
│   │   │   ├── tag/                  # 标签领域
│   │   │   │   ├── model/            # 标签实体
│   │   │   │   │   └── Tag.java              # Tag 实体
│   │   │   │   │
│   │   │   │   └── repository/       # 标签仓储接口
│   │   │   │       └── TagRepository.java
│   │   │   │
│   │   │   ├── notification/         # 通知领域
│   │   │   │   ├── model/            # 通知实体
│   │   │   │   │   └── Notification.java     # Notification 实体
│   │   │   │   │
│   │   │   │   └── repository/       # 通知仓储接口
│   │   │   │       └── NotificationRepository.java
│   │   │   │
│   │   │   ├── social/               # 用户社交行为领域 (关注、点赞、收藏等关联)
│   │   │   │   └── repository/       # 社交行为仓储接口 (处理关系表的抽象)
│   │   │   │       ├── UserFollowRepository.java
│   │   │   │       ├── ArticleLikeRepository.java
│   │   │   │       └── ArticleBookmarkRepository.java
│   │   │   │
│   │   │   └── common/               # 领域层通用组件 (事件、通用值对象等)
│   │   │       ├── event/            # 领域事件
│   │   │       │   └── DomainEvent.java
│   │   │       └── util/             # 领域层内部工具
│   │   │
│   │   └── infrastructure/           # 基础设施层 (Infrastructure Layer) - 技术实现
│   │       │
│   │       ├── persistence/          # 数据持久化实现
│   │       │   ├── mapper/           # MyBatis Plus Mapper 接口 (直接对应数据库操作)
│   │       │   │   ├── UserMapper.java
│   │       │   │   ├── RoleMapper.java
│   │       │   │   ├── MenuMapper.java
│   │       │   │   ├── ArticleMapper.java
│   │       │   │   ├── CommentMapper.java
│   │       │   │   ├── TagMapper.java
│   │       │   │   ├── NotificationMapper.java
│   │       │   │   ├── UserRoleMapper.java   # 关系表 Mapper
│   │       │   │   ├── RoleMenuMapper.java
│   │       │   │   ├── ArticleTagMapper.java
│   │       │   │   ├── ArticleLikeMapper.java
│   │       │   │   ├── UserFollowMapper.java
│   │       │   │   ├── NotificationReceiverMapper.java
│   │       │   │   └── ArticleBookmarkMapper.java
│   │       │   │
│   │       │   ├── entity/           # MyBatis Plus 实体类 (与数据库表结构直接对应)
│   │       │   │   ├── UserEntity.java
│   │       │   │   ├── RoleEntity.java
│   │       │   │   ├── MenuEntity.java
│   │       │   │   ├── ArticleEntity.java
│   │       │   │   ├── CommentEntity.java
│   │       │   │   ├── TagEntity.java
│   │       │   │   ├── UserRoleEntity.java
│   │       │   │   ├── RoleMenuEntity.java
│   │       │   │   ├── ArticleTagEntity.java
│   │       │   │   ├── ArticleLikeEntity.java
│   │       │   │   ├── UserFollowEntity.java
│   │       │   │   ├── NotificationReceiverEntity.java
│   │       │   │   └── ArticleBookmarkEntity.java
│   │       │   │
│   │       │   └── repository/impl/  # 领域层仓储接口的实现
│   │       │       ├── UserRepositoryImpl.java       # 实现 domain.user.repository.UserRepository
│   │       │       ├── RoleRepositoryImpl.java       # 实现 domain.rbac.repository.RoleRepository
│   │       │       ├── MenuRepositoryImpl.java       # 实现 domain.rbac.repository.MenuRepository
│   │       │       ├── ArticleRepositoryImpl.java    # 实现 domain.article.repository.ArticleRepository
│   │       │       ├── CommentRepositoryImpl.java    # 实现 domain.comment.repository.CommentRepository
│   │       │       ├── TagRepositoryImpl.java        # 实现 domain.tag.repository.TagRepository
│   │       │       ├── NotificationRepositoryImpl.java # 实现 domain.notification.repository.NotificationRepository
│   │       │       └── social/                       # 社交仓储实现
│   │       │           ├── UserFollowRepositoryImpl.java # 实现 domain.social.repository.UserFollowRepository
│   │       │           ├── ArticleLikeRepositoryImpl.java # 实现 domain.social.repository.ArticleLikeRepository
│   │       │           └── ArticleBookmarkRepositoryImpl.java # 实现 domain.social.repository.ArticleBookmarkRepository
│   │       │
│   │       ├── security/             # 核心安全实现 (认证、授权框架)
│   │       │   ├── config/           # Spring Security 通用配置
│   │       │   │   └── SecurityConfig.java # 框架级别的安全配置
│   │       │   ├── jwt/              # JWT 实现
│   │       │   │   ├── JwtTokenProvider.java # 生成/解析 JWT
│   │       │   │   └── JwtAuthenticationFilter.java # JWT 认证过滤器
│   │       │   └── authentiation/    # 认证相关服务
│   │       │       └── CustomUserDetailsService.java # 实现 Spring Security 的 UserDetailsService
│   │       │
│   │       ├── redis/                # Redis 集成和通用工具
│   │       │   ├── config/
│   │       │   │   └── RedisConfig.java
│   │       │   └── util/
│   │       │       └── RedisUtil.java
│   │       │
│   │       └── config/               # 其他通用基础设施配置
│   │           ├── MybatisPlusConfig.java
│   │           ├── JacksonConfig.java # Jackson 配置
│   │           └── ...
│   │
│   └── src/main/resources/           # common 模块资源文件
│       ├── application.yml           # 数据库、Redis 等通用配置
│       └── mapper/                   # MyBatis Mapper XML 文件 (如果使用)
│           ├── UserMapper.xml
│           └── ...
│
├── community-system/               # 社区系统模块 (面向普通用户)
│   ├── src/main/java/com/yourcompany/project3/community/
│   │   │
│   │   ├── api/                      # 社区系统表现层 (REST API)
│   │   │   ├── controller/           # 面向普通用户的控制器
│   │   │   │   ├── ArticleController.java      # 用户浏览/发布/修改自己的文章
│   │   │   │   ├── CommentController.java      # 用户评论文章
│   │   │   │   ├── TagController.java          # 用户浏览标签
│   │   │   │   ├── UserController.java         # 用户个人信息, 关注/被关注列表
│   │   │   │   ├── SocialController.java       # 用户执行关注/点赞/收藏操作
│   │   │   │   └── NotificationController.java # 用户查看通知
│   │   │   │
│   │   │   ├── dto/                  # 社区系统特有的 DTO
│   │   │   │   ├── article/          # 文章相关的展示 DTO
│   │   │   │   │   ├── ArticleListDto.java
│   │   │   │   │   └── ArticleDetailDto.java
│   │   │   │   ├── comment/
│   │   │   │   ├── user/
│   │   │   │   └── ...
│   │   │   │
│   │   │   └── exceptionhandler/     # 社区系统层面的异常处理 (如果需要特定处理)
│   │   │
│   │   └── application/              # 社区系统应用层 (协调用例)
│   │       ├── service/              # 面向普通用户的应用服务 (调用 common 的 Repository 和 Domain Service)
│   │       │   ├── ArticleAppService.java      # 处理用户发布、修改、删除（软删除）文章等业务流程
│   │       │   ├── CommentAppService.java      # 处理用户发表、回复评论等
│   │       │   ├── UserAppService.java         # 处理用户更新个人信息，查看关注/粉丝等
│   │       │   ├── SocialAppService.java       # 处理用户关注、点赞、收藏的业务流程
│   │       │   └── NotificationAppService.java # 处理用户标记通知已读等
│   │       │
│   │       └── ...                   # 其他应用层组件
│   │
│   └── pom.xml (或 build.gradle)     # community-system 模块的构建文件 (依赖 common)
│
├── admin-system/                   # 后台管理系统模块 (面向管理者)
│   ├── src/main/java/com/yourcompany/project3/admin/
│   │   │
│   │   ├── api/                      # 后台管理系统表现层 (REST API)
│   │   │   ├── controller/           # 面向管理员的控制器
│   │   │   │   ├── UserController.java         # 管理员管理用户 (禁用、分配角色等)
│   │   │   │   ├── RoleController.java         # 管理员管理角色
│   │   │   │   ├── MenuController.java         # 管理员管理权限/菜单
│   │   │   │   ├── ArticleController.java      # 管理员管理文章 (审核、强制下线等)
│   │   │   │   ├── TagController.java          # 管理员管理标签
│   │   │   │   └── NotificationController.java # 管理员发送系统通知
│   │   │   │
│   │   │   ├── dto/                  # 后台管理系统特有的 DTO
│   │   │   │   ├── user/             # 用户管理相关的 DTO
│   │   │   │   │   ├── UserManagementDto.java
│   │   │   │   │   └── AssignRoleCommand.java
│   │   │   │   ├── article/          # 文章管理相关的 DTO
│   │   │   │   └── ...
│   │   │   │
│   │   │   └── exceptionhandler/     # 后台管理系统层面的异常处理
│   │   │
│   │   └── application/              # 后台管理系统应用层 (协调用例)
│   │       ├── service/              # 面向管理员的应用服务 (调用 common 的 Repository 和 Domain Service)
│   │       │   ├── UserManagementService.java      # 处理管理员对用户的各种管理操作
│   │       │   ├── RbacManagementService.java      # 处理角色和权限的管理，用户角色分配
│   │       │   ├── ArticleManagementService.java   # 处理文章的审核、强制删除等管理流程
│   │       │   └── NotificationManagementService.java # 处理管理员发送通知等
│   │       │
│   │       └── ...                   # 其他应用层组件
│   │
│   └── pom.xml (或 build.gradle)     # admin-system 模块的构建文件 (依赖 common)
│
└── app/                            # (可选) 应用启动模块
├── src/main/java/com/yourcompany/project3/app/
│   └── Project3Application.java    # 主应用启动类
│
└── pom.xml (或 build.gradle)     # app 模块的构建文件 (依赖 community-system 和 admin-system)