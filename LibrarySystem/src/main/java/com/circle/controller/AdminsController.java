package com.circle.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.circle.pojo.Admins;
import com.circle.service.AdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author circle
 * @since 2022-06-28
 */
@RestController
@RequestMapping("/admins")
public class AdminsController {
    @Autowired
    AdminsService adminsService;

    @RequestMapping("/allAdmins")
    public List<Admins> allAdmin(){
        List<Admins> list = adminsService.list();
        return list;
    }

    @PostMapping("/login")
    public String login(@RequestBody Admins admins){
        QueryWrapper<Admins> wrapper = new QueryWrapper<>();
        wrapper.eq("username",admins.getUsername());
        List<Admins> list = adminsService.list(wrapper);
        if(list.size() < 1){
            return "用户不存在";
        }else if(list.get(0).getPassword().equals(admins.getPassword())){
            return "ok";
        }else{
            return "密码错误";
        }
    }

}
