package com.kevin.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kevin.springboot.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

// @Mapper
public interface UserMapper extends BaseMapper<User> {
//    //查
//    @Select("select * from sys_user")
//    List<User> findAll();
//
//    //增
//    @Insert("INSERT into sys_user (username, password,nickname,email,phone,address) " +
//            "VALUES (#{username},#{password},#{nickname},#{email},#{phone},#{address})")
//    int insert(User admin);
//
//    //改
//    int update(User admin);
//
//    //删
//    @Delete("delete from sys_user where id=#{id}")
//    Integer deleteByID(@Param("id") Integer id);
//
//    @Select("select * from sys_user where username like concat('%',#{username},'%') and email like concat('%',#{email},'%') and address like concat('%',#{address},'%') limit #{pageNum},#{pageSize}")
//    List<User> selectPage(Integer pageNum, Integer pageSize,String username,String email,String address);
//    @Select("select count(*) from sys_user where username like concat('%',#{username},'%') and email like concat('%',#{email},'%') and address like concat('%',#{address},'%')")
//    Integer selectTotal(String username,String email,String address);
}
