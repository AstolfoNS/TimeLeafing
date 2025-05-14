package com.astolfo.domain.domain.rbac.model;

import com.astolfo.domain.domain.rbac.model.valueobject.entity.Email;
import com.astolfo.domain.domain.rbac.model.valueobject.enumtype.Gender;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    // 唯一标识
    private Long id;

    // 基础属性
    private Email email;

    private String username;

    private String password;

    private String avatar;

    private Gender gender;

    private String introduction;

    private LocalDateTime lastLoginTime;

    // 生命周期属性
    private List<Long> roleIdList;

    private Boolean enabled;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDeleted;


    public void markAsLoggedIn() {
        setLastLoginTime(LocalDateTime.now());
    }

    public void disable() {
        setEnabled(false);
    }

    public void enable() {
        setEnabled(true);
    }

    public void softDelete() {
        setIsDeleted(true);
        disable();
    }

    public void restore() {
        setIsDeleted(false);
        enable();
    }
}