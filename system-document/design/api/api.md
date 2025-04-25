# 项目接口设计

+ ## 1. 首页接口设计
    + ### 1- 首页列表展示
        + #### api 
        ```
            // sort默认采用文章访问数
            // sort参数有：viewCounts，likeCounts，createTime
      
            GET /article/homepage?page=1&size=6&sort=viewCount
        ``` 
        + #### 数据格式
        ```
            {
                
            }    
        ```
    + ### 2- 文章详情
        ```
            GET /article/{articlId}
        ```
    + ### 3- 增加浏览量
        ```
            PUT /article/{articleId}/add-view-count
        ```
    + ### 4- 文章点赞数
        ```
            GET /article/{articleId}/likes
        ```
    + ### 5- 文章标签
        ```
            GET /article/{articleId}/tags
        ```
+ ## 2. 搜索接口设计
+ ## 3. 写作接口设计
+ ## 4. 收藏接口设计
+ ## 5. 作品接口设计
+ ## 6. 通知接口设计
+ ## 7. 竞赛接口设计
+ ## 8. 用户接口设计

