
src/main/java
    └── com.example                                                     // 您的根包名
        ├── application                                                 // 应用层：编排用例，协调领域对象
        │   ├── dto                                                     // Data Transfer Objects：用于应用层与接口层之间的数据传输
        │   │   ├── LoginRequest.java                                   // 例如：登录请求的数据载体
        │   │   └── TokenResponse.java                                  // 例如：返回给客户端的Token信息
        │   │   └── UserDto.java                                        // 例如：用于展示或创建用户的非领域核心数据
        │   └── service
        │       └── AuthAppService.java                                 // 例如：认证授权相关的应用服务，编排登录、注册等流程
        │       └── UserManagementAppService.java                       // 例如：用户管理相关的应用服务
        │
        ├── domain                                                      // 领域层：业务核心，包含实体、值对象、领域服务、仓库接口
        │   ├── model                                                   // 领域模型包
        │   │   ├── user                                                // （可选）按聚合根或业务概念组织子包，例如用户聚合
        │   │   │   ├── User.java                                       // 用户聚合根实体 (Aggregate Root) - 包含核心业务逻辑和状态
        │   │   │   ├── Password.java                                   // （可选）密码值对象，可封装密码策略和比较逻辑
        │   │   │   └── UserId.java                                     // （可选）用户ID值对象
        │   │   ├── role
        │   │   │   ├── Role.java                                       // 角色实体或值对象
        │   │   │   └── Permission.java                                 // 权限值对象
        │   │   ├── rbac
        │   │   │          
        │   │   └── common                                              // （可选）通用的领域模型元素，如 AuditData (审计数据值对象)
        │   ├── repository                                              // 仓库接口定义：定义数据持久化的契约
        │   │   └── UserRepository.java                                 // 例如：用户仓库接口，声明了如何存取User聚合
        │   │   └── RoleRepository.java                                 // 例如：角色仓库接口
        │   └── service                                                 // 领域服务：处理跨多个实体的复杂业务逻辑
        │       └── UserCredentialsValidationService.java               // 例如：复杂的凭证校验规则服务
        │       └── UserFactory.java                                    // （可选）如果User创建复杂，可以使用工厂模式
        │
        ├── infrastructure                                              // 基础设施层：技术实现，如数据库交互、安全实现、外部服务集成
        │   ├── config                                                  // 配置类
        │   │   ├── SecurityConfig.java                                 // Spring Security核心配置 (认证、授权、过滤器链等)
        │   │   ├── MyBatisPlusConfig.java                              // MyBatis Plus配置 (分页插件、拦截器等)
        │   │   └── WebMvcConfig.java                                   // （可选）Spring MVC相关配置 (CORS, Interceptors等)
        │   ├── persistence                                             // 持久化实现
        │   │   ├── mybatisplus                                         // （可选）明确MyBatis Plus的实现目录
        │   │   │   ├── mapper                                          // MyBatis Plus Mapper接口
        │   │   │   │   └── UserMapper.java                             // 对应User实体的MyBatis Plus Mapper
        │   │   │   │   └── RoleMapper.java
        │   │   │   └── repository_impl                                 // 领域仓库接口的MyBatis Plus实现
        │   │   │       └── MyBatisPlusUserRepository.java              // UserRepository接口的实现类
        │   │   │       └── MyBatisPlusRoleRepository.java
        │   ├── security                                                // 安全相关基础设施的具体实现
        │   │   ├── jwt
        │   │   │   └── JwtTokenUtil.java                               // JWT生成、解析、验证工具类
        │   │   └── service
        │   │       └── UserDetailsServiceImpl.java                     // Spring Security的UserDetailsService接口实现
        │   └── external                                                // （可选）与外部服务集成的客户端实现
        │       └── EmailService.java
        │
        └── interfaces                                                  // 接口层 (或 presentation)：与外部交互，如Web API、消息消费者
            ├── web                                                     // Web交互相关
            │   ├── controller                                          // RESTful API 控制器
            │   │   └── AuthController.java                             // 认证授权相关的API端点
            │   │   └── UserController.java                             // 用户管理相关的API端点
            │   ├── filter                                              // Web过滤器
            │   │   └── JwtAuthenticationFilter.java                    // JWT认证过滤器
            │   ├── dto                                                 // （可选）接口层特有的DTO，或与application层共享
            │   │   └── CreateUserWebRequest.java                       // 可能比应用层DTO更贴近HTTP请求结构
            │   └── exceptionhandler                                    // 全局异常处理器
            │       └── GlobalExceptionHandler.java
            └── mq                                                      // （可选）消息队列消费者
                └── UserEventListener.java


```json
{
    "com.example": {
        "application": {
            "dto": ["UserCreationCommand", "TokenResponse"],
            "service": ["AuthAppService", "UserManagementAppService"]
        },
        "domain": {
            "model": {
                "user": ["User", "Password", "UserId"],
                "role": ["Role"]
            },
            "repository": ["UserRepository", "RoleRepository"],
            "service": ["UserCredentialsValidationService"]
        },
        "infrastructure": {
            "config": {
                "security": ["SecurityConfig", "SecurityPolicy"],
                "": ["WebMvcConfig"]
            },
            "persistence": {
                "mybatisplus": {
                    "mapper": ["UserMapper"],
                    "repository_impl": ["MyBatisPlusUserRepository"]
                }
            },
            "security": {
                "jwt": ["JwtTokenUtil"],
                "service": ["UserDetailsServiceImpl", "DomainUserDetailsAdapter"]
            }
        },
        "interfaces": {
            "web": {
                "controller": ["AuthController", "UserController"],
                "dto": ["CreateUserWebRequest", "LoginRequest"],
                "filter": ["JwtAuthenticationFilter"]
            }
        }
    }
}

```