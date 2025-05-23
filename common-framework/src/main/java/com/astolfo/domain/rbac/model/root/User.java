package com.astolfo.domain.rbac.model.root;

import com.astolfo.domain.rbac.model.valueobject.Gender;
import com.astolfo.domain.rbac.model.valueobject.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter(AccessLevel.PRIVATE)
@Getter
public class User {

    // 唯一标识
    private UserId id;

    // 基础属性
    private Email email;

    private Username username;

    private Nickname nickname;

    private PasswordHash passwordHash;

    private Avatar avatar;

    private Gender gender;

    private String introduction;

    private LocalDateTime lastLoginTime;

    // 生命周期属性
    private List<RoleId> roleIdList;

    private Boolean enabled;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDeleted;

    @Data
    public static class Details {

        // 唯一标识
        private UserId id;

        // 基础属性
        private Email email;

        private Username username;

        private Nickname nickname;

        private PasswordHash passwordHash;

        private Avatar avatar;

        private Gender gender;

        private String introduction;

        private LocalDateTime lastLoginTime;

        // 生命周期属性
        private List<RoleId> roleIdList;

        private Boolean enabled;

        private LocalDateTime createTime;

        private LocalDateTime updateTime;

        private Boolean isDeleted;
    }

    private User(Details details) {
        setId(details.getId());

        setEmail(details.getEmail());

        setUsername(details.getUsername());

        setNickname(details.getNickname());

        setPasswordHash(details.getPasswordHash());

        setAvatar(details.getAvatar());

        setGender(details.getGender());

        setIntroduction(details.getIntroduction());

        setLastLoginTime(details.getLastLoginTime());

        setRoleIdList(details.getRoleIdList());

        setEnabled(details.getEnabled());

        setCreateTime(details.getCreateTime());

        setUpdateTime(details.getUpdateTime());

        setIsDeleted(details.getIsDeleted());
    }

    public static User of(Details details) {
        return new User(details);
    }

    public void recordLogin() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}