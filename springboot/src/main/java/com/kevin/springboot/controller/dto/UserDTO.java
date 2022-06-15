package com.kevin.springboot.controller.dto;


import lombok.Data;

/** 接收前端登录请求的参数
 * @author: KevinWu
 * 2022/6/14 3:07 PM
 */
@Data
public class UserDTO {
    private String username;
    private String password;
    private String nickname;
    private String avatarUrl;
    private String token;
}
