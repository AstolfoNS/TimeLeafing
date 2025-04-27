# CREATE DATABASE project3;

DROP DATABASE IF EXISTS project3;

CREATE DATABASE IF NOT EXISTS project3;

USE project3;


# 实体表
CREATE TABLE IF NOT EXISTS `user` (
    `id`                        BIGINT PRIMARY KEY AUTO_INCREMENT,                                                      -- 用户ID
    `username`                  VARCHAR(128) UNIQUE NOT NULL,                                                           -- 用户名（唯一）
    `password`                  VARCHAR(128) NOT NULL,                                                                  -- 用户密码
    `email`                     VARCHAR(256) UNIQUE NOT NULL,                                                           -- 用户邮箱
    `avatar`                    VARCHAR(256),                                                                           -- 用户头像
    `introduction`              TEXT,                                                                                   -- 用户简介
    `enabled`                   BOOLEAN DEFAULT true,                                                                   -- 账号状态
    `role`                      ENUM('USER', 'ADMIN', 'SYSTEM') DEFAULT 'USER',                                         -- 用户角色
    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间
    `update_time`               DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,                         -- 更新时间
    `last_login_time`           DATETIME,                                                                               -- 最后在线时间
    `is_deleted`                BOOLEAN DEFAULT false                                                                   -- 账号是否被删除（软删）

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


# 实体表
CREATE TABLE IF NOT EXISTS `article` (
    `id`                        BIGINT PRIMARY KEY AUTO_INCREMENT,                                                      -- 文章ID
    `author_id`                 BIGINT NOT NULL,                                                                        -- 作者ID（不关联user）
    `title`                     VARCHAR(256) NOT NULL,                                                                  -- 文章标题（不唯一）
    `content`                   LONGTEXT NOT NULL,                                                                      -- 文章内容
    `summary`                   VARCHAR(512),                                                                           -- 文章摘要
    `cover_image`               VARCHAR(256),                                                                           -- 文章封面
    `status`                    ENUM('DRAFT', 'ARTICLE') DEFAULT 'DRAFT',                                               -- 文章状态
    `is_public`                 BOOLEAN DEFAULT true,                                                                   -- 是否公开
    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间
    `update_time`               DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,                         -- 更新时间
    `view_counts`               BIGINT DEFAULT 0,                                                                       -- 浏览量
    `like_counts`               BIGINT DEFAULT 0,                                                                       -- 点赞量
    `is_deleted`                BOOLEAN DEFAULT false                                                                   -- 文章是否被删除（软删）

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


# 实体表
CREATE TABLE IF NOT EXISTS `tag` (
    `id`                        BIGINT PRIMARY KEY AUTO_INCREMENT,                                                      -- 标签ID
    `tag_name`                  VARCHAR(128) UNIQUE NOT NULL,                                                           -- 标签名（唯一）
    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间
    `is_deleted`                BOOLEAN DEFAULT false                                                                   -- 标签是否被删除（软删）

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


# 关系表
CREATE TABLE IF NOT EXISTS `article_tag` (
    `article_id`                BIGINT NOT NULL,                                                                        -- 文章ID
    `tag_id`                    BIGINT NOT NULL,                                                                        -- 标签ID

    PRIMARY KEY (article_id, tag_id),

    FOREIGN KEY (article_id) REFERENCES article(id),
    FOREIGN KEY (tag_id) REFERENCES tag(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


# 关系表
CREATE TABLE IF NOT EXISTS `article_like` (
    `article_id`                BIGINT NOT NULL,                                                                        -- 文章ID
    `liker_id`                  BIGINT NOT NULL,                                                                        -- 点赞者ID
    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间

    PRIMARY KEY (article_id, liker_id),

    FOREIGN KEY (liker_id) REFERENCES user(id),
    FOREIGN KEY (article_id) REFERENCES  article(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


# 实体表（存在关系）
CREATE TABLE IF NOT EXISTS `comment` (
    `id`                        BIGINT PRIMARY KEY AUTO_INCREMENT,                                                      -- 评论ID
    `root_id`                   BIGINT DEFAULT NULL,                                                                    -- 根评论ID（关联comment）
    `parent_id`                 BIGINT DEFAULT NULL,                                                                    -- 父评论ID（关联comment，回复评论， NULL表示没有父评论）
    `article_id`                BIGINT NOT NULL,                                                                        -- 所属文章ID（不关联article）
    `reviewer_id`               BIGINT NOT NULL,                                                                        -- 评论者ID（不关联user）
    `content`                   TEXT NOT NULL,                                                                          -- 评论内容（不唯一）
    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 评论时间
    `is_deleted`                BOOLEAN DEFAULT false,                                                                  -- 评论是否被删除（软删）

    FOREIGN KEY (root_id) REFERENCES comment(id),
    FOREIGN KEY (parent_id) REFERENCES comment(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


# 关系表
CREATE TABLE IF NOT EXISTS `follow` (
    `follower_id`               BIGINT NOT NULL,                                                                        -- 关注者ID
    `followee_id`               BIGINT NOT NULL,                                                                        -- 被关注者ID
    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 关注时间

    PRIMARY KEY (`follower_id`, `followee_id`),

    FOREIGN KEY (follower_id) REFERENCES user(id),
    FOREIGN KEY (followee_id) REFERENCES user(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


# 实体表（存在关系）
CREATE TABLE IF NOT EXISTS `notification` (
    `id`                        BIGINT PRIMARY KEY AUTO_INCREMENT,                                                      -- 通知ID
    `receiver_id`               BIGINT NOT NULL,                                                                        -- 接收者ID（不关联user）
    `sender_id`                 BIGINT NOT NULL,                                                                        -- 触发者ID（不关联user）
    `article_id`                BIGINT DEFAULT NULL,                                                                    -- 关联文章ID（不关联article，如点赞、评论，NULL表示没有关联文章）
    `type`                      ENUM('LIKE', 'COMMENT', 'FOLLOW', 'SYSTEM') NOT NULL,                                   -- 通知类型
    `is_read`                   BOOLEAN DEFAULT false,                                                                  -- 是否已读
    `content`                   TEXT NOT NULL,                                                                          -- 通知内容（不唯一）
    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间
    `is_deleted`                BOOLEAN DEFAULT false                                                                   -- 通知是否被删除（软删）

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


# 关系表
CREATE TABLE IF NOT EXISTS `bookmark` (
    `collector_id`              BIGINT NOT NULL,                                                                        -- 收藏者ID
    `article_id`                BIGINT NOT NULL,                                                                        -- 收藏的文章ID
    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 收藏时间

    PRIMARY KEY (`collector_id`, `article_id`),

    FOREIGN KEY (collector_id) REFERENCES user(id),
    FOREIGN KEY (article_id) REFERENCES article(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

