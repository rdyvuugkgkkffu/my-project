package com.example.demo.service;

import com.example.demo.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 21006
* @description 针对表【tb_admin】的数据库操作Service
* @createDate 2024-03-21 19:36:12
*/
public interface AdminService extends IService<Admin> {

    Admin selectAdminByUsernameAndPwd(String username, String password);
    Admin selectAdminById(Long userId);
}
