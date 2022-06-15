package com.kevin.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kevin.springboot.controller.dto.UserDTO;
import com.kevin.springboot.entity.User;
import com.kevin.springboot.mapper.UserMapper;
import com.kevin.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户操作
 * @author: KevinWu
 * 2022/6/7 10:28 PM
 */
@RestController
@RequestMapping("/user")
public class UserController {

//    @Autowired  //引入userMapper
//    private UserMapper userMapper;
    @Autowired  //引入userService
    private UserService userService;

    // 增和改
    @PostMapping
    public boolean saveUser(@RequestBody User admin){
        return userService.saveUser(admin);
    }

    // 删
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
//        return userMapper.deleteByID(id);
        return userService.removeById(id);
    }

    // 批量删
    @PostMapping("/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return userService.removeBatchByIds(ids);
    }

    // 查
    @GetMapping
    public List<User> findAll(){

//        List<User> all = userMapper.findAll();
//        return all;
        return userService.list();
    }

    // 分页查询(自己实现)
    // 接口路径：/user/page?pageNum=1&pageSize=5
    // @RequestParam接收
    // limit 第一个参数 =（pageNum - 1）* pageSize
//    @GetMapping("/page")
//    public Map<String, Object> findPage(@RequestParam Integer pageNum,
//                                        @RequestParam Integer pageSize,
//                                        @RequestParam String username,
//                                        @RequestParam String email,
//                                        @RequestParam String address){
//        pageNum = (pageNum - 1) * pageSize;
//        List<User> data = userMapper.selectPage(pageNum,pageSize,username,email,address);
//        Integer total = userMapper.selectTotal(username,email,address);
//        Map<String, Object> res = new HashMap<>();
//        res.put("data",data);
//        res.put("total",total);
//        return res;
//    }

    // 分页查询（ Mybatis_Plus方式 分页助手）
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String username,
                                @RequestParam(defaultValue = "") String email,
                                @RequestParam(defaultValue = "") String address){
        IPage<User> page = new Page<>(pageNum, pageSize);
        //QueryWrapper实现 查询功能
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!"".equals(username)){
            queryWrapper.like("username",username);
        }
        if (!"".equals(email)){
            queryWrapper.like("email",email);
        }
        if (!"".equals(address)){
            queryWrapper.like("address",address);
        }

        return userService.page(page,queryWrapper);
    }

    /**
     * excel 导出接口
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception{
        // 从数据库查询出所有数据
        List<User> list = userService.list();
        // 通过工具类创建 writer 写出到磁盘路径 （指定存储路径的方法）
//        ExcelWriter writer= ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器，通过浏览器下载到默认下载位置
        ExcelWriter writer = ExcelUtil.getWriter(true);
        // 自定义标题别名
        writer.addHeaderAlias("username","用户名");
        writer.addHeaderAlias("password","密码");
        writer.addHeaderAlias("nickname","昵称");
        writer.addHeaderAlias("email","邮箱");
        writer.addHeaderAlias("phone","电话");
        writer.addHeaderAlias("address","地址");
        writer.addHeaderAlias("createTime","创建时间");
        writer.addHeaderAlias("avatarUrl","头像");

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list,true);
        // 设置浏览器响应格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        // 通过输出流反应到浏览器
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
    }


    /**
     * excel 导入接口
     */
    @PostMapping("/import")
    public Boolean imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 方式1：通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
//        List<User> list = reader.readAll(User.class);

        // 方式2：忽略表头的中文，直接读取表的内容
        List<List<Object>> list = reader.read(1);
        List<User> users = CollUtil.newArrayList();
        for (List<Object> row : list) {
            User user = new User();
            user.setUsername(row.get(0).toString());
            user.setPassword(row.get(1).toString());
            user.setNickname(row.get(2).toString());
            user.setEmail(row.get(3).toString());
            user.setPhone(row.get(4).toString());
            user.setAddress(row.get(5).toString());
            user.setAvatarUrl(row.get(6).toString());
            users.add(user);
        }

        userService.saveBatch(users);
        return true;
    }


//    @PostMapping
//    public boolean login(@RequestBody UserDTO userDTO){
//        return userService.login(userDTO);
//    }


}
