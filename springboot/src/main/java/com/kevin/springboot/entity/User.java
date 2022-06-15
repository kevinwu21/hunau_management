package com.kevin.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: KevinWu
 * 2022/6/7 9:49 PM
 */
@Data
@TableName(value = "sys_user")
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String username;
    @JsonIgnore
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String address;
    private LocalDateTime createTime;
    private String avatarUrl;

}
