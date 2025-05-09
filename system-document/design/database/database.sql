# create by Astolfo
#
# 使用RBAC权限模型。
#
# 如果表是实体表或实体表（存在关系），则默认具有以下属性：
#     `enabled`                   BOOLEAN DEFAULT true,                                                                   -- 是否可用
#     `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间
#     `update_time`               DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,                         -- 更新时间
#     `is_deleted`                BOOLEAN DEFAULT false                                                                   -- 是否被删除（软删）
#
# 如果表是关系表，则默认具有以下属性：
#
#    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间
#    `is_deleted`                BOOLEAN DEFAULT false,                                                                  -- 是否被删除（软删）



DROP DATABASE IF EXISTS project3;

CREATE DATABASE IF NOT EXISTS project3;

USE project3;



# 实体表
CREATE TABLE IF NOT EXISTS `user` (
    `id`                        BIGINT PRIMARY KEY AUTO_INCREMENT,                                                      -- 用户ID

    `email`                     VARCHAR(256) UNIQUE NOT NULL,                                                           -- 用户邮箱
    `username`                  VARCHAR(128) UNIQUE NOT NULL,                                                           -- 用户名（唯一）
    `password`                  VARCHAR(128) NOT NULL,                                                                  -- 用户密码
    `avatar`                    VARCHAR(256),                                                                           -- 用户头像
    `gender`                    ENUM('MALE', 'FEMALE', 'UNKNOWN') DEFAULT 'UNKNOWN',                                    -- 用户性别
    `introduction`              TEXT,                                                                                   -- 用户简介
    `last_login_time`           DATETIME,                                                                               -- 最后在线时间

    `enabled`                   BOOLEAN DEFAULT true,                                                                   -- 是否可用
    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间
    `update_time`               DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,                         -- 更新时间
    `is_deleted`                BOOLEAN DEFAULT false                                                                   -- 是否被删除（软删）

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# 关系表
CREATE TABLE IF NOT EXISTS `user_role` (
    `user_id`                   BIGINT NOT NULL,                                                                        -- 用户ID
    `role_id`                   BIGINT NOT NULL,                                                                        -- 角色ID

    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间
    `is_deleted`                BOOLEAN DEFAULT false,                                                                  -- 是否被删除（软删）

    PRIMARY KEY (`user_id`, `role_id`),

    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (role_id) REFERENCES role(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# 实体表
CREATE TABLE IF NOT EXISTS `role` (
    `id`                        BIGINT PRIMARY KEY AUTO_INCREMENT,                                                      -- 角色ID

    `name`                      VARCHAR(128) UNIQUE NOT NULL,                                                           -- 角色名（如 USER / ADMIN / SYSTEM）
    `description`               VARCHAR(256),                                                                           -- 角色描述

    `enabled`                   BOOLEAN DEFAULT true,                                                                   -- 是否可用
    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间
    `update_time`               DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,                         -- 更新时间
    `is_deleted`                BOOLEAN DEFAULT false                                                                   -- 是否被删除（软删）

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# 关系表
CREATE TABLE IF NOT EXISTS `role_menu` (
    `role_id`                   BIGINT NOT NULL,                                                                        -- 角色ID
    `menu_id`                   BIGINT NOT NULL,                                                                        -- 权限ID

    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间
    `is_deleted`                BOOLEAN DEFAULT false,                                                                  -- 是否被删除（软删）

    PRIMARY KEY (`role_id`, `menu_id`),

    FOREIGN KEY (role_id) REFERENCES role(id),
    FOREIGN KEY (menu_id) REFERENCES menu(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# 实体表
CREATE TABLE IF NOT EXISTS `menu` (
    `id`                        BIGINT PRIMARY KEY AUTO_INCREMENT,                                                      -- 菜单/权限ID

    `permission`                VARCHAR(128) UNIQUE NOT NULL,                                                           -- 权限标识（如 article:read、user:update）
    `description`               VARCHAR(256),                                                                           -- 菜单描述（例如：文章管理）
    `url`                       VARCHAR(512),                                                                           -- 接口url或前端路径
    `request_method`            ENUM('GET', 'POST', 'PUT', 'DELETE') NOT NULL,                                          -- HTTP方法（GET、POST、PUT、DELETE）
    `type`                      ENUM('MENU', 'BUTTON') DEFAULT 'MENU',                                                  -- 类型：菜单 or 按钮（权限点）
    `order_num`                 INT DEFAULT 0,                                                                          -- 排序值

    `enabled`                   BOOLEAN DEFAULT true,                                                                   -- 是否可用
    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间
    `update_time`               DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,                         -- 更新时间
    `is_deleted`                BOOLEAN DEFAULT false                                                                   -- 是否被删除（软删）

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# 实体表（存在关系）
CREATE TABLE IF NOT EXISTS `article` (
    `id`                        BIGINT PRIMARY KEY AUTO_INCREMENT,                                                      -- 文章ID

    `author_id`                 BIGINT NOT NULL,                                                                        -- 作者ID

    `title`                     VARCHAR(128),                                                                           -- 文章标题
    `summary`                   VARCHAR(512),                                                                           -- 文章摘要
    `content`                   LONGTEXT,                                                                               -- 文章内容
    `cover_image`               VARCHAR(512),                                                                           -- 文章封面
    `stage`                     ENUM('DRAFT', 'PENDING', 'PUBLISHED') DEFAULT 'DRAFT',                                  -- 文章状态
    `is_public`                 BOOLEAN DEFAULT true,                                                                   -- 是否公开
    `view_counts`               BIGINT DEFAULT 0,                                                                       -- 浏览量
    `like_counts`               BIGINT DEFAULT 0,                                                                       -- 点赞量

    `enabled`                   BOOLEAN DEFAULT true,                                                                   -- 是否可用
    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间
    `update_time`               DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,                         -- 更新时间
    `is_deleted`                BOOLEAN DEFAULT false,                                                                  -- 是否被删除（软删）

    FOREIGN KEY (author_id) REFERENCES user(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# 关系表
CREATE TABLE IF NOT EXISTS `article_tag` (
    `article_id`                BIGINT NOT NULL,                                                                        -- 文章ID
    `tag_id`                    BIGINT NOT NULL,                                                                        -- 标签ID

    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间
    `is_deleted`                BOOLEAN DEFAULT false,                                                                  -- 是否被删除（软删）

    PRIMARY KEY (article_id, tag_id),

    FOREIGN KEY (article_id) REFERENCES article(id),
    FOREIGN KEY (tag_id) REFERENCES tag(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# 实体表
CREATE TABLE IF NOT EXISTS `tag` (
    `id`                        BIGINT PRIMARY KEY AUTO_INCREMENT,                                                      -- 标签ID

    `name`                      VARCHAR(128) UNIQUE NOT NULL,                                                           -- 标签名（唯一）

    `enabled`                   BOOLEAN DEFAULT true,                                                                   -- 是否可用
    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间
    `update_time`               DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,                         -- 修改时间
    `is_deleted`                BOOLEAN DEFAULT false                                                                   -- 是否被删除（软删）

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# 关系表
CREATE TABLE IF NOT EXISTS `article_like` (
    `article_id`                BIGINT NOT NULL,                                                                        -- 文章ID
    `liker_id`                  BIGINT NOT NULL,                                                                        -- 点赞者ID

    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间
    `is_deleted`                BOOLEAN DEFAULT false,                                                                  -- 是否被删除（软删）

    PRIMARY KEY (article_id, liker_id),

    FOREIGN KEY (article_id) REFERENCES article(id),
    FOREIGN KEY (liker_id) REFERENCES user(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# 实体表（存在关系）
CREATE TABLE IF NOT EXISTS `comment` (
    `id`                        BIGINT PRIMARY KEY AUTO_INCREMENT,                                                      -- 评论ID

    `parent_id`                 BIGINT NOT NULL,                                                                        -- 父评论ID
    `root_id`                   BIGINT NOT NULL,                                                                        -- 根评论ID

    `article_id`                BIGINT NOT NULL,                                                                        -- 所属文章ID
    `reviewer_id`               BIGINT NOT NULL,                                                                        -- 评论者ID
    `content`                   TEXT,                                                                                   -- 评论内容

    `enabled`                   BOOLEAN DEFAULT true,                                                                   -- 是否可用
    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间
    `update_time`               DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,                         -- 修改时间
    `is_deleted`                BOOLEAN DEFAULT false,                                                                  -- 是否被删除（软删）

    FOREIGN KEY (parent_id) REFERENCES comment(id),                                                                     -- 自引用外键：指向父评论
    FOREIGN KEY (root_id) REFERENCES comment(id),                                                                       -- 自引用外键：指向根评论

    FOREIGN KEY (article_id) REFERENCES article(id),
    FOREIGN KEY (reviewer_id) REFERENCES user(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# 关系表
CREATE TABLE IF NOT EXISTS `user_follow` (
    `follower_id`               BIGINT NOT NULL,                                                                        -- 关注者ID
    `followee_id`               BIGINT NOT NULL,                                                                        -- 被关注者ID

    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间
    `is_deleted`                BOOLEAN DEFAULT false,                                                                  -- 是否被删除（软删）

    PRIMARY KEY (`follower_id`, `followee_id`),

    FOREIGN KEY (follower_id) REFERENCES user(id),
    FOREIGN KEY (followee_id) REFERENCES user(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# 实体表（存在关系）
CREATE TABLE IF NOT EXISTS `notification` (
    `id`                        BIGINT PRIMARY KEY AUTO_INCREMENT,                                                      -- 通知ID

    `sender_id`                 BIGINT NOT NULL,                                                                        -- 触发者ID（有SYSTEM用户）

    `type`                      ENUM('OTHERS', 'SYSTEM') NOT NULL,                                                      -- 通知类型
    `content`                   TEXT,                                                                                   -- 通知内容

    `enabled`                   BOOLEAN DEFAULT true,                                                                   -- 是否可用
    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间
    `update_time`               DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,                         -- 修改时间
    `is_deleted`                BOOLEAN DEFAULT false,                                                                  -- 是否被删除（软删）

    FOREIGN KEY (sender_id) REFERENCES user(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# 关系表
CREATE TABLE IF NOT EXISTS `notification_receiver` (
    `notification_id`           BIGINT NOT NULL,                                                                        -- 通知ID
    `receiver_id`               BIGINT NOT NULL,                                                                        -- 接收者ID

    `is_read`                   BOOLEAN DEFAULT false,                                                                  -- 是否已读

    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间
    `is_deleted`                BOOLEAN DEFAULT false,                                                                  -- 是否被删除（软删）

    PRIMARY KEY (notification_id, receiver_id),

    FOREIGN KEY (notification_id) REFERENCES notification(id),
    FOREIGN KEY (receiver_id) REFERENCES user(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# 关系表
CREATE TABLE IF NOT EXISTS `article_bookmark` (
    `collector_id`              BIGINT NOT NULL,                                                                        -- 收藏者ID
    `article_id`                BIGINT NOT NULL,                                                                        -- 收藏的文章ID

    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间
    `is_deleted`                BOOLEAN DEFAULT false,                                                                  -- 是否被删除（软删）

    PRIMARY KEY (`collector_id`, `article_id`),

    FOREIGN KEY (collector_id) REFERENCES user(id),
    FOREIGN KEY (article_id) REFERENCES article(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

