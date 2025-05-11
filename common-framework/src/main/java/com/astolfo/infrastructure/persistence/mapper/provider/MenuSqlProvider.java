package com.astolfo.infrastructure.persistence.mapper.provider;

import org.apache.ibatis.annotations.Param;

public class MenuSqlProvider {

    String findMenuEntityListByUserUsername(@Param("username") String username) {
        return """
            SELECT
                DISTINCT m.id, m.name, m.url, m.permission_string, m.parent_id, m.order_num
            FROM
                users u
            JOIN
                user_role ur
            ON
                u.id = ur.user_id
            JOIN 
                role r 
            ON 
                ur.role_id = r.id
            JOIN 
                role_menu rm 
            ON 
                r.id = rm.role_id
            JOIN 
                menu m 
            ON 
                rm.menu_id = m.id
            WHERE 
                u.username = #{username}
            ORDER BY 
                m.order_num ASC
        """;
    }

}
