package com.astolfo.domain.rbac.model.root;

import com.astolfo.domain.rbac.model.valueobject.Gender;
import com.astolfo.domain.rbac.model.valueobject.*;
import lombok.*;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

        private List<RoleId> roleIdList = List.of();

        // 生命周期属性
        private Boolean enabled;

        private LocalDateTime createTime;

        private LocalDateTime updateTime;

        private Boolean isDeleted;
    }

    @Data
    public static class NewUser {
        private Email email;

        private Username username;

        private Nickname nickname;

        private PasswordHash passwordHash;

        private Gender gender;
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

    private User(NewUser newUser) {
        setEmail(newUser.getEmail());

        setUsername(newUser.getUsername());

        setNickname(newUser.getNickname());

        setPasswordHash(newUser.getPasswordHash());

        setGender(newUser.getGender());
    }

    public Long getIdLong() {
        if (Objects.isNull(id)) {
            return null;
        } else {
            return id.getUserId();
        }
    }

    public String getEmailString() {
        if (Objects.isNull(email)) {
            return null;
        } else {
            return email.getEmail();
        }
    }

    public String getUsernameString() {
        if (Objects.isNull(username)) {
            return null;
        } else {
            return username.getUsername();
        }
    }

    public String getNicknameString() {
        if (Objects.isNull(nickname)) {
            return null;
        } else {
            return nickname.getNickname();
        }
    }

    public String getPasswordHashString() {
        if (Objects.isNull(passwordHash)) {
            return null;
        } else {
            return passwordHash.getPasswordHash();
        }
    }

    public String getAvatarString() {
        if (Objects.isNull(avatar)) {
            return null;
        } else {
            return avatar.getAvatar();
        }
    }

    public String getGenderString() {
        if (Objects.isNull(gender)) {
            return null;
        } else {
            return gender.getGender();
        }
    }

    public static User of(Details details) {
        return new User(details);
    }

    public static User of(NewUser newUser) {
        return new User(newUser);
    }

    public void updateNickname(Nickname nickname) {
        setNickname(nickname);
    }

    public void updateAvatar(Avatar avatar) {
        // TODO: 修改头像逻辑（使用minIO）

        setAvatar(avatar);
    }

    public void updateGender(Gender gender) {
        setGender(gender);
    }

    public void updateIntroduction(String introduction) {
        setIntroduction(introduction);
    }

    public void updatePasswordHash(PasswordHash passwordHash) {
        // TODO: 修改密码逻辑

        setPasswordHash(passwordHash);
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