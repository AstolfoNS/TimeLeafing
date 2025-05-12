package com.astolfo.infrastructure.persistence.mapper.provider;

import org.apache.ibatis.annotations.Param;

public class RoleSqlProvider {

    public String findUserRoleEntityListById(@Param("id") Long id) {
        return """
            SELECT
                role.*
            FROM
                role
            JOIN
                user_role
            ON
                user_role.role_id = role.id
            WHERE
                user_role.user_id = #{id}
        """;
    }

    public String findUserRoleEntityListByUsername(@Param("username") String username) {
        return """
            SELECT
                role.*
            FROM
                user
            JOIN
                user_role
            ON
                user_role.user_id = user.id
            JOIN
                role
            ON
                user_role.role_id = role.id
            WHERE
                user.username = #{username}
        """;
    }

}
