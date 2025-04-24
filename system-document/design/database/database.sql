# CREATE DATABASE project3;

DROP DATABASE project3;

CREATE DATABASE project3;

USE project3;



CREATE TABLE IF NOT EXISTS `user` (
    `user_id`                   BIGINT PRIMARY KEY AUTO_INCREMENT,                                                      -- 用户ID
    `username`                  VARCHAR(128) UNIQUE NOT NULL,                                                           -- 用户名
    `password`                  VARCHAR(128) NOT NULL,                                                                  -- 用户密码
    `email`                     VARCHAR(256) UNIQUE NOT NULL,                                                           -- 用户邮箱
    `avatar`                    VARCHAR(256),                                                                           -- 用户头像
    `introduction`              TEXT,                                                                                   -- 用户简介
    `enabled`                   BOOLEAN DEFAULT true,                                                                   -- 账号状态
    `is_deleted`                BOOLEAN DEFAULT false,                                                                  -- 账号是否被删除（软删）
    `role`                      ENUM('USER', 'ADMIN', 'SYSTEM') DEFAULT 'USER',                                         -- 用户角色
    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间
    `update_time`               DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,                         -- 更新时间
    `last_login_time`           DATETIME,                                                                               -- 最后在线时间

    INDEX index_username        (username)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



CREATE TABLE IF NOT EXISTS `article` (
    `article_id`                BIGINT PRIMARY KEY AUTO_INCREMENT,                                                      -- 文章ID
    `author_name`               VARCHAR(128) NOT NULL,                                                                  -- 作者姓名
    `title`                     VARCHAR(256) NOT NULL,                                                                  -- 文章标题
    `content`                   LONGTEXT NOT NULL,                                                                      -- 文章内容
    `summary`                   VARCHAR(512),                                                                           -- 文章摘要
    `cover_image`               VARCHAR(256),                                                                           -- 文章封面
    `status`                    ENUM('DRAFT', 'ARTICLE') DEFAULT 'DRAFT',                                               -- 文章状态
    `is_deleted`                BOOLEAN DEFAULT false,                                                                  -- 文章是否被删除（软删）
    `is_public`                 BOOLEAN DEFAULT true,                                                                   -- 是否公开
    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间
    `update_time`               DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,                         -- 更新时间
    `view_counts`               BIGINT DEFAULT 0,                                                                       -- 浏览量
    `like_counts`               BIGINT DEFAULT 0,                                                                       -- 点赞量

    FOREIGN KEY (author_name) REFERENCES user(username),

    INDEX index_author_name     (author_name),
    INDEX index_create_time     (create_time)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



CREATE TABLE IF NOT EXISTS `tag` (
    `tag_id`                    BIGINT PRIMARY KEY AUTO_INCREMENT,                                                      -- 标签ID
    `tag_name`                  VARCHAR(128) UNIQUE NOT NULL,                                                           -- 标签名
    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间
    `is_deleted`                BOOLEAN DEFAULT false,                                                                  -- 标签是否被删除（软删）

    INDEX index_tag_name        (tag_name)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



CREATE TABLE IF NOT EXISTS `article_tag` (
    `article_id`                BIGINT NOT NULL,                                                                        -- 文章ID
    `tag_name`                  VARCHAR(128) NOT NULL,                                                                  -- 标签名
    `is_deleted`                BOOLEAN DEFAULT false,                                                                  -- 标签是否被删除（软删）

    PRIMARY KEY (article_id, tag_name),

    FOREIGN KEY (article_id) REFERENCES article(article_id),
    FOREIGN KEY (tag_name) REFERENCES tag(tag_name),

    INDEX index_tag_name          (tag_name)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



CREATE TABLE IF NOT EXISTS `article_like` (
    `article_id`                BIGINT NOT NULL,                                                                        -- 文章ID
    `liker_name`                VARCHAR(128) NOT NULL,                                                                  -- 点赞者用户名
    `is_deleted`                BOOLEAN DEFAULT false,                                                                  -- 点赞是否被取消（软删）
    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间

    PRIMARY KEY (article_id, liker_name),

    FOREIGN KEY (liker_name) REFERENCES user(username),
    FOREIGN KEY (article_id) REFERENCES  article(article_id),

    INDEX article_id            (article_id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



CREATE TABLE IF NOT EXISTS `comment` (
    `comment_id`                BIGINT PRIMARY KEY AUTO_INCREMENT,                                                      -- 评论ID
    `article_id`                BIGINT NOT NULL,                                                                        -- 所属文章
    `reviewer_name`             VARCHAR(128) NOT NULL,                                                                  -- 评论者用户名
    `content`                   TEXT NOT NULL,                                                                          -- 评论内容
    `parent_id`                 BIGINT DEFAULT NULL,                                                                    -- 父评论ID（回复评论， NULL表示没有父评论）
    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 评论时间
    `is_deleted`                BOOLEAN DEFAULT false,                                                                  -- 评论是否被删除（软删）

    FOREIGN KEY (article_id) REFERENCES article(article_id),
    FOREIGN KEY (reviewer_name) REFERENCES user(username),
    FOREIGN KEY (parent_id) REFERENCES comment(comment_id),

    INDEX index_article_id      (article_id),
    INDEX index_reviewer_name   (reviewer_name)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



CREATE TABLE IF NOT EXISTS `follow` (
    `follower_name`             VARCHAR(128) NOT NULL,                                                                  -- 关注者用户名
    `followee_name`             VARCHAR(128) NOT NULL,                                                                  -- 被关注者用户名
    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 关注时间
    `is_deleted`                BOOLEAN DEFAULT false,                                                                  -- 关注是否被取消（软删）

    PRIMARY KEY (`follower_name`, `followee_name`),

    FOREIGN KEY (follower_name) REFERENCES user(username),
    FOREIGN KEY (followee_name) REFERENCES user(username),

    INDEX index_followee_name     (followee_name)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



CREATE TABLE IF NOT EXISTS `notification` (
    `notification_id`           BIGINT PRIMARY KEY AUTO_INCREMENT,                                                      -- 通知ID
    `receiver_name`             VARCHAR(128) NOT NULL,                                                                  -- 接收者用户名
    `sender_name`               VARCHAR(128) NOT NULL,                                                                  -- 触发者用户名
    `type`                      ENUM('LIKE', 'COMMENT', 'FOLLOW', 'SYSTEM') NOT NULL,                                   -- 通知类型
    `article_id`                BIGINT DEFAULT NULL,                                                                    -- 关联文章（如点赞、评论，NULL表示没有关联文章）
    `is_read`                   BOOLEAN DEFAULT false,                                                                  -- 是否已读
    `content`                   TEXT,                                                                                   -- 通知内容
    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 创建时间
    `is_deleted`                BOOLEAN DEFAULT false,                                                                  -- 通知是否被删除（软删）

    FOREIGN KEY (receiver_name) REFERENCES user(username),
    FOREIGN KEY (sender_name) REFERENCES user(username),
    FOREIGN KEY (article_id) REFERENCES article(article_id),

    INDEX index_receiver_name     (receiver_name),
    INDEX index_type_time       (type, create_time)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



CREATE TABLE IF NOT EXISTS `bookmark` (
    `collector_name`            VARCHAR(128) NOT NULL,                                                                  -- 收藏者用户名
    `article_id`                BIGINT NOT NULL,                                                                        -- 收藏的文章
    `create_time`               DATETIME DEFAULT CURRENT_TIMESTAMP,                                                     -- 收藏时间
    `is_deleted`                BOOLEAN DEFAULT false,                                                                  -- 是否取消收藏（软删）

    PRIMARY KEY (`collector_name`, `article_id`),

    FOREIGN KEY (collector_name) REFERENCES user(username),
    FOREIGN KEY (article_id) REFERENCES article(article_id),

    INDEX index_article_id      (article_id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

