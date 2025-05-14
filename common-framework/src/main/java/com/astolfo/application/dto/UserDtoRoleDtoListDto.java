package com.astolfo.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDtoRoleDtoListDto {

    private UserDto userDto;

    private List<RoleDto> roleDtoList;

}
