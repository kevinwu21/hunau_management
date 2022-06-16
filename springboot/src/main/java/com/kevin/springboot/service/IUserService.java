package com.kevin.springboot.service;

import com.kevin.springboot.controller.dto.UserDTO;
import com.kevin.springboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**数据服务接口层
 * 服务类
 * @author Kevin
 * @since 2022-06-14
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);
}
