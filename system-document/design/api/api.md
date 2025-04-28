# 项目接口设计

+ ## 1. 首页接口设计
    + ### 1- 首页列表展示
        + #### api 
        ```
            // page默认值为1
            // size默认值为6
            // sort默认采用创建时间，sort参数有：viewCounts，likeCounts，createTime
      
            GET /article/homepage?page=1&size=6&sort=viewCount
        ``` 
        + #### 数据格式
        ```json
        {
            "code": 200,
            "message": "操作成功",
            "data": {
                "records": [
                    {
                        "id": 3,
                        "authorName": "alice",
                        "title": "algorithm",
                        "createTime": "2025-04-27T14:02:53.000+00:00",
                        "viewCounts": 0,
                        "likeCounts": 0,
                        "tags": [
                            "动态规划",
                            "贪心"
                        ]
                    },
                    {
                        "id": 4,
                        "authorName": "alice",
                        "title": "language large model",
                        "createTime": "2025-04-27T14:02:53.000+00:00",
                        "viewCounts": 0,
                        "likeCounts": 0,
                        "tags": []
                    }
                ],
                "total": 4,
                "pages": 2,
                "current": 2,
                "size": 2
            }
        }
        ```
    + ### 2- 文章详情
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
                "id": 1,
                "authorName": "astolfo",
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
                    "后端开发",
                    "java"
                ]
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
                "id": 1,
                "authorName": "astolfo",
                "title": "spring",
                "createTime": "2025-04-27T14:02:53.000+00:00",
                "viewCounts": 0,
                "likeCounts": 0,
                "tags": [
                    "后端开发",
                    "java"
                ]
            }
        }
        ```
    + ### 4- 获取文章标签
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
                "后端开发",
                "python"
            ]
        }
        ```
    + ### 5- 增加浏览量
        + #### api
        ```
            PUT /article/{articleId}/add-view-count
        ```
    
+ ## 2. 搜索接口设计
+ ## 3. 写作接口设计
+ ## 4. 收藏接口设计
+ ## 5. 作品接口设计
+ ## 6. 通知接口设计
+ ## 7. 竞赛接口设计
+ ## 8. 用户接口设计

