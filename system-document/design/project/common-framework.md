
common/                         # 公用模块 (Common Module)
   ├── src/main/java/com/astolfo/
   │   │
   │   ├── domain/                   # 领域层 (Domain Layer) - 核心业务模型和逻辑的定义
   │   │   │
   │   │   ├── user/                 # 用户领域
   │   │   │   ├── model/            # 用户相关的实体、值对象、聚合根
   │   │   │   │   ├── User.java             # User 聚合根/实体 (包含业务行为)
   │   │   │   │   └── valueobject/      # 用户值对象 (如 EmailAddress)
   │   │   │   │       └── EmailAddress.java
   │   │   │   │
   │   │   │   └── repository/       # 用户仓储接口
   │   │   │       └── UserRepository.java # 定义获取/保存 User 聚合根的方法
   │   │   │
   │   │   ├── rbac/                 # 角色与权限领域
   │   │   │   ├── model/            # 角色、菜单实体等
   │   │   │   │   ├── Role.java             # Role 实体
   │   │   │   │   ├── Menu.java             # Menu/Permission 实体
   │   │   │   │   └── valueobject/      # RBAC 值对象 (如权限字符串)
   │   │   │   │       └── PermissionString.java
   │   │   │   │
   │   │   │   ├── repository/       # 角色与权限仓储接口
   │   │   │   │   ├── RoleRepository.java
   │   │   │   │   └── MenuRepository.java
   │   │   │   │
   │   │   │   └── service/          # RBAC 领域服务 (如权限检查, 角色分配等)
   │   │   │       └── RbacDomainService.java
   │   │   │
   │   │   ├── article/              # 文章领域
   │   │   │   ├── model/            # 文章实体、值对象、聚合根
   │   │   │   │   ├── Article.java          # Article 聚合根/实体
   │   │   │   │   └── valueobject/      # 文章值对象 (如 ArticleContent, ArticleStage)
   │   │   │   │       ├── ArticleContent.java
   │   │   │   │       └── ArticleStage.java # 文章状态枚举
   │   │   │   │
   │   │   │   └── repository/       # 文章仓储接口
   │   │   │       └── ArticleRepository.java
   │   │   │
   │   │   ├── comment/              # 评论领域
   │   │   │   ├── model/            # 评论实体
   │   │   │   │   └── Comment.java          # Comment 实体 (处理评论树结构)
   │   │   │   │
   │   │   │   └── repository/       # 评论仓储接口
   │   │   │       └── CommentRepository.java
   │   │   │
   │   │   ├── tag/                  # 标签领域
   │   │   │   ├── model/            # 标签实体
   │   │   │   │   └── Tag.java              # Tag 实体
   │   │   │   │
   │   │   │   └── repository/       # 标签仓储接口
   │   │   │       └── TagRepository.java
   │   │   │
   │   │   ├── notification/         # 通知领域
   │   │   │   ├── model/            # 通知实体
   │   │   │   │   └── Notification.java     # Notification 实体
   │   │   │   │
   │   │   │   └── repository/       # 通知仓储接口
   │   │   │       └── NotificationRepository.java
   │   │   │
   │   │   ├── social/               # 用户社交行为领域 (关注、点赞、收藏等关联)
   │   │   │   └── repository/       # 社交行为仓储接口 (处理关系表的抽象)
   │   │   │       ├── UserFollowRepository.java
   │   │   │       ├── ArticleLikeRepository.java
   │   │   │       └── ArticleBookmarkRepository.java
   │   │   │
   │   │   └── common/               # 领域层通用组件 (事件、通用值对象等)
   │   │       ├── event/            # 领域事件
   │   │       │   └── DomainEvent.java
   │   │       └── util/             # 领域层内部工具
   │   │
   │   └── infrastructure/           # 基础设施层 (Infrastructure Layer) - 技术实现
   │       │
   │       ├── persistence/          # 数据持久化实现
   │       │   ├── mapper/           # MyBatis Plus Mapper 接口 (直接对应数据库操作)
   │       │   │   ├── UserMapper.java
   │       │   │   ├── RoleMapper.java
   │       │   │   ├── MenuMapper.java
   │       │   │   ├── ArticleMapper.java
   │       │   │   ├── CommentMapper.java
   │       │   │   ├── TagMapper.java
   │       │   │   ├── NotificationMapper.java
   │       │   │   ├── UserRoleMapper.java   # 关系表 Mapper
   │       │   │   ├── RoleMenuMapper.java
   │       │   │   ├── ArticleTagMapper.java
   │       │   │   ├── ArticleLikeMapper.java
   │       │   │   ├── UserFollowMapper.java
   │       │   │   ├── NotificationReceiverMapper.java
   │       │   │   └── ArticleBookmarkMapper.java
   │       │   │
   │       │   ├── entity/           # MyBatis Plus 实体类 (与数据库表结构直接对应)
   │       │   │   ├── UserEntity.java
   │       │   │   ├── RoleEntity.java
   │       │   │   ├── MenuEntity.java
   │       │   │   ├── ArticleEntity.java
   │       │   │   ├── CommentEntity.java
   │       │   │   ├── TagEntity.java
   │       │   │   ├── UserRoleEntity.java
   │       │   │   ├── RoleMenuEntity.java
   │       │   │   ├── ArticleTagEntity.java
   │       │   │   ├── ArticleLikeEntity.java
   │       │   │   ├── UserFollowEntity.java
   │       │   │   ├── NotificationReceiverEntity.java
   │       │   │   └── ArticleBookmarkEntity.java
   │       │   │
   │       │   └── repository/impl/  # 领域层仓储接口的实现
   │       │       ├── UserRepositoryImpl.java       # 实现 domain.user.repository.UserRepository
   │       │       ├── RoleRepositoryImpl.java       # 实现 domain.rbac.repository.RoleRepository
   │       │       ├── MenuRepositoryImpl.java       # 实现 domain.rbac.repository.MenuRepository
   │       │       ├── ArticleRepositoryImpl.java    # 实现 domain.article.repository.ArticleRepository
   │       │       ├── CommentRepositoryImpl.java    # 实现 domain.comment.repository.CommentRepository
   │       │       ├── TagRepositoryImpl.java        # 实现 domain.tag.repository.TagRepository
   │       │       ├── NotificationRepositoryImpl.java # 实现 domain.notification.repository.NotificationRepository
   │       │       └── social/                       # 社交仓储实现
   │       │           ├── UserFollowRepositoryImpl.java # 实现 domain.social.repository.UserFollowRepository
   │       │           ├── ArticleLikeRepositoryImpl.java # 实现 domain.social.repository.ArticleLikeRepository
   │       │           └── ArticleBookmarkRepositoryImpl.java # 实现 domain.social.repository.ArticleBookmarkRepository
   │       │
   │       ├── security/             # 核心安全实现 (认证、授权框架)
   │       │   ├── config/           # Spring Security 通用配置
   │       │   │   └── SecurityConfig.java # 框架级别的安全配置
   │       │   ├── jwt/              # JWT 实现
   │       │   │   ├── JwtTokenProvider.java # 生成/解析 JWT
   │       │   │   └── JwtAuthenticationFilter.java # JWT 认证过滤器
   │       │   └── authentiation/    # 认证相关服务
   │       │       └── CustomUserDetailsService.java # 实现 Spring Security 的 UserDetailsService
   │       │
   │       ├── redis/                # Redis 集成和通用工具
   │       │   ├── config/
   │       │   │   └── RedisConfig.java
   │       │   └── util/
   │       │       └── RedisUtil.java
   │       │
   │       └── config/               # 其他通用基础设施配置
   │           ├── MybatisPlusConfig.java
   │           ├── JacksonConfig.java # Jackson 配置
   │           └── ...
   │
   └── src/main/resources/           # common 模块资源文件
       ├── application.yml           # 数据库、Redis 等通用配置
       └── mapper/                   # MyBatis Mapper XML 文件 (如果使用)
           ├── UserMapper.xml
           └── ...


