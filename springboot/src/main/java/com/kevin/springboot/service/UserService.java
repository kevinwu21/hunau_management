package com.kevin.springboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kevin.springboot.controller.dto.UserDTO;
import com.kevin.springboot.entity.User;
import com.kevin.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: KevinWu
 * 2022/6/9 11:41 AM
 */
@Service
public class UserService extends ServiceImpl<UserMapper,User> {
    public boolean saveUser(User admin) {
//        第一种方法
//        if (admin.getId() == null){
//            return save(admin);
//        } else {
//            return updateById(admin);
//        }

//        第二种方法（简单）
        return saveOrUpdate(admin);
    }

//    @Autowired
//    private UserMapper userMapper;

//    public int save(User admin){
//        if(admin.getId() == null){  //user没有id，则表示是新增
//            return userMapper.insert(admin);
//        }else{  //否则为更新
//            return userMapper.update(admin);
//        }
//    }
}
