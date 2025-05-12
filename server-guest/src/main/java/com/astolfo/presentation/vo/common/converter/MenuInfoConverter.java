package com.astolfo.presentation.vo.common.converter;

import com.astolfo.domain.rbac.model.Menu;
import com.astolfo.presentation.vo.MenuInfo;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MenuInfoConverter {

    public MenuInfo toVo(Menu menu) {
        if (menu == null) {
            return null;
        } else {
            return new MenuInfo(menu.getPermission().getPermissionName(), menu.getDescription());
        }
    }

    public List<MenuInfo> toVo(List<Menu> menuList) {
        if (menuList == null) {
            return null;
        } else {
            return menuList
                    .stream()
                    .map(this::toVo)
                    .collect(Collectors.toList());
        }
    }

}
