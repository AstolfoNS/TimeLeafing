package com.astolfo.model.vo;

import com.astolfo.common.enums.Gender;
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

    private Gender gender;

    private String introduction;

}
