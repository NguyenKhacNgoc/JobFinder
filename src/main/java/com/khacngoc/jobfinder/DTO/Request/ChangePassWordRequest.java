package com.khacngoc.jobfinder.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChangePassWordRequest {
    private String email;
    private String passWord;
    private String newPassWord;
}
