package com.astolfo.domain.rbac.model;

import com.astolfo.domain.rbac.model.valueobject.enumtype.AuthorityType;
import com.astolfo.domain.rbac.model.valueobject.enumtype.HttpMethod;
import com.astolfo.domain.rbac.model.valueobject.entity.Permission;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Setter(AccessLevel.PRIVATE)
@Getter
public class Menu {

    private Long id;

    private Permission permission;

    private String description;

    private String url;

    private HttpMethod httpMethod;

    private AuthorityType authorityType;

    private Integer orderNum;

    private Boolean enabled;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDeleted;


    public Menu(
            Permission permission,
            String url,
            HttpMethod httpMethod
    ) {
        setPermission(permission);

        setUrl(url);

        setHttpMethod(httpMethod);

        setAuthorityType(AuthorityType.MENU);

        setOrderNum(0);

        setEnabled(Boolean.TRUE);

        setCreateTime(LocalDateTime.now());

        setUpdateTime(LocalDateTime.now());

        setIsDeleted(Boolean.FALSE);
    }

    public void updateDetails(
            String description,
            AuthorityType authorityType,
            Integer orderNum
    ) {
        setDescription(description);

        setAuthorityType(authorityType);

        setOrderNum(orderNum);
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
