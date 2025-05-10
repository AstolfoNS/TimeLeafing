package com.astolfo.infrastructure.persistence.entity;

<<<<<<< HEAD
public class RoleEntity {
=======
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("role")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleEntity {

    private Long id;

    private String name;

    private String description;

    private Boolean enabled;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDeleted;

>>>>>>> main
}
