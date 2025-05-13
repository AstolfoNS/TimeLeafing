package com.astolfo.webinterface.vo.converter;

import com.astolfo.domain.rbac.model.Permission;
import com.astolfo.webinterface.vo.MenuInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用于将domain层中的model -> vo
 *
 *
 */
@Component
public class MenuInfoConverter {

    public MenuInfo toVo(Permission menu) {
        if (menu == null) {
            return null;
        } else {
            return new MenuInfo(menu.getPermission().getPermissionName(), menu.getDescription());
        }
    }

    public List<MenuInfo> toVo(List<Permission> menuList) {
        if (menuList == null) {
            return null;
        } else {
            return menuList
                    .stream()
                    .map(menu -> Optional.of(toVo(menu)).orElseThrow(() -> new NoSuchElementException("Can not convert null menu")))
                    .collect(Collectors.toList());
        }
    }

}
