package com.astolfo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserVO {

    private Long id;

    private String username;

    private String avatar;

    private String introduction;

}
