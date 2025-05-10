package com.astolfo.domain.rbac.model;

import com.astolfo.domain.rbac.model.valueobject.Email;
import com.astolfo.domain.rbac.model.valueobject.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Setter
    private Long id;

    private String username;

    private String password;

    private Email email;

    @Setter
    private String avatar;

    @Setter
    private String introduction;

    @Setter
    private Gender gender;

    private LocalDateTime lastLoginTime;

    private Boolean enabled;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDeleted;


    public User(
            String username,
            String password,
            Email email
    ) {
        this.id = null;

        this.username = username;

        this.password = password;

        this.email = email;

        this.avatar = null;

        this.introduction = null;

        this.gender = Gender.UNKNOWN;

        this.lastLoginTime = null;
    }

    public void updateProfile(
            String avatar,
            Gender gender,
            String introduction
    ) {
        setAvatar(avatar);

        setGender(gender);

        setIntroduction(introduction);
    }

    public void markAsLoggedIn() {
        this.lastLoginTime = LocalDateTime.now();
    }

    public void disable() {
        this.enabled = false;
    }

    public void enable() {
        this.enabled = true;
    }

    public void softDelete() {
        this.isDeleted = true;
        disable();
    }

    public void restore() {
        this.isDeleted = false;
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

        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return id != null ? Objects.hash(id) : 0;
    }

}
