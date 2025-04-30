# 项目接口设计

+ ## 1. 首页接口设计
    + ### 1- 首页列表展示（详细）
        + #### api 
        ```
            // page默认值为1
            // size默认值为6
            // sort默认采用创建时间，sort参数有：viewCounts，likeCounts，createTime
            
            GET /article/details?page=1&size=6&sort=viewCount
        ``` 
        + #### 数据格式
        ```json
        {
            "code": 200,
            "message": "操作成功",
            "data": {
                "records": [
                    {
                        "id": 1,
                        "author": {
                            "id": 1,
                            "username": "astolfo"
                        },
                        "title": "spring",
                        "content": "teach spring",
                        "status": "ARTICLE",
                        "isDeleted": false,
                        "isPublic": true,
                        "createTime": "2025-04-27T14:02:53.000+00:00",
                        "updateTime": "2025-04-27T14:02:53.000+00:00",
                        "viewCounts": 0,
                        "likeCounts": 0,
                        "tags": [
                            {
                                "id": 3,
                                "tagName": "后端开发"
                            },
                            {
                                "id": 4,
                                "tagName": "java"
                            }
                        ]
                    },
                    {
                        "id": 2,
                        "author": {
                            "id": 1,
                            "username": "astolfo"
                        },
                        "title": "django",
                        "content": "teach django",
                        "status": "ARTICLE",
                        "isDeleted": false,
                        "isPublic": true,
                        "createTime": "2025-04-27T14:02:53.000+00:00",
                        "updateTime": "2025-04-27T14:02:53.000+00:00",
                        "viewCounts": 0,
                        "likeCounts": 0,
                        "tags": [
                            {
                                "id": 3,
                                "tagName": "后端开发"
                            },
                            {
                                "id": 5,
                                "tagName": "python"
                            }
                        ]
                    }
                ],
                "total": 4,
                "pages": 2,
                "current": 1,
                "size": 2
            }
        }
        ```
    + ### 2- 首页列表展示（摘要）
        + #### api
        ```
            // page默认值为1
            // size默认值为6
            // sort默认采用创建时间，sort参数有：viewCounts，likeCounts，createTime 
            
            GET /article/summary?page=1&size=6&sort=viewCount
        ```
        + #### 数据格式
        ```json
        {
            "code": 200,
            "message": "操作成功",
            "data": {
                "records": [
                    {
                        "id": 1,
                        "author": {
                            "id": 1,
                            "username": "astolfo"
                        },
                        "title": "spring",
                        "createTime": "2025-04-27T14:02:53.000+00:00",
                        "viewCounts": 0,
                        "likeCounts": 0,
                        "tags": [
                            {
                                "id": 3,
                                "tagName": "后端开发"
                            },
                            {
                                "id": 4,
                                "tagName": "java"
                            }
                        ]
                    },
                    {
                        "id": 2,
                        "author": {
                            "id": 1,
                            "username": "astolfo"
                        },
                        "title": "django",
                        "createTime": "2025-04-27T14:02:53.000+00:00",
                        "viewCounts": 0,
                        "likeCounts": 0,
                        "tags": [
                            {
                                "id": 3,
                                "tagName": "后端开发"
                            },
                            {
                                "id": 5,
                                "tagName": "python"
                            }
                        ]
                    }
                ],
                "total": 4,
                "pages": 2,
                "current": 1,
                "size": 2
            }
        }
        ```
    + ### 3- 文章摘要
        + #### api
        ```
            GET /article/{id}/summary
        ```
        + #### 数据格式
        ```json
        {
            "code": 200,
            "message": "操作成功",
            "data": {
                "id": 3,
                "author": {
                    "id": 2,
                    "username": "alice"
                },
                "title": "algorithm",
                "createTime": "2025-04-27T14:02:53.000+00:00",
                "updateTime": "2025-04-27T14:02:53.000+00:00",
                "viewCounts": 0,
                "likeCounts": 0,
                "tags": [
                    {
                        "id": 1,
                        "tagName": "动态规划"
                    },
                    {
                        "id": 2,
                        "tagName": "贪心"
                    }
                ]
            }
        }
        ```
    + ### 4- 文章详情
        + #### api
        ```
            GET /article/{id}/details
        ```
        + #### 数据格式
        ```json 
        {
            "code": 200,
            "message": "操作成功",
            "data": {
                "id": 3,
                "author": {
                    "id": 2,
                    "username": "alice"
                },
                "title": "algorithm",
                "content": "teach algorithm",
                "status": "ARTICLE",
                "isDeleted": false,
                "isPublic": true,
                "createTime": "2025-04-27T14:02:53.000+00:00",
                "updateTime": "2025-04-27T14:02:53.000+00:00",
                "viewCounts": 0,
                "likeCounts": 0,
                "tags": [
                    {
                        "id": 1,
                        "tagName": "动态规划"
                    },
                    {
                        "id": 2,
                        "tagName": "贪心"
                    }
                ]
            }
        }
        ```
    + ### 5- 获取文章标签
        + #### api
        ```
            GET /article/{id}/tags
        ```
        + #### 数据格式
        ```json
        {
            "code": 200,
            "message": "操作成功",
            "data": [
                {
                    "id": 1,
                    "tagName": "动态规划"
                },
                {
                    "id": 2,
                    "tagName": "贪心"
                }
            ]
        }
        ```
    + ### 5- 增加浏览量
        + #### api
        ```
            // 可以传入增加的数量count，默认为0
      
            PUT /article/{articleId}/add-view-counts?count=3
        ```
+ ## 2. 登录接口设计
    + ### 1- 登录
        + #### api
        ```
        POST /login
        ```
        + #### 请求体
        ```json
        {
            "username": "astolfo",
            "password": "123456"    
        }
        ```
        + #### 响应格式
        ```json
        {
            "code": 200,
            "message": "",
            "data": {
                
            }
        }
        ```
+ ## 3. 搜索接口设计
+ ## 4. 创作接口设计
+ ## 5. 收藏接口设计
+ ## 6. 作品接口设计
+ ## 7. 通知接口设计
+ ## 8. 竞赛接口设计
+ ## 9. 用户接口设计

