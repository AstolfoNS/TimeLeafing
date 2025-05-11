package com.astolfo.infrastructure.persistence.mapper.provider;

import org.apache.ibatis.annotations.Param;

public class MenuSqlProvider {

    String findMenuEntityListByUserUsername(@Param("username") String username) {
        return """
            SELECT
                DISTINCT menu.*
            FROM
                user
            JOIN
                user_role
            ON
                user.id = user_role.user_id
            JOIN
                role
            ON
                user_role.role_id = role.id
            JOIN
                role_menu
            ON
                role.id = role_menu.role_id
            JOIN
                menu
            ON
                role_menu.menu_id = menu.id
            WHERE
                user.username = #{username}
            ORDER BY
                menu.order_num ASC
        """;
    }

}
