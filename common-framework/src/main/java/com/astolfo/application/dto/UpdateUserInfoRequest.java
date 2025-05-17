package com.astolfo.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateUserInfoRequest {

    String nickname;

    String gender;

    String introduction;

}
