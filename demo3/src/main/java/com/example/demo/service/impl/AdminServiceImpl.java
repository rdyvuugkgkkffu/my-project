package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.pojo.Admin;
import com.example.demo.service.AdminService;
import com.example.demo.mapper.AdminMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 21006
* @description 针对表【tb_admin】的数据库操作Service实现
* @createDate 2024-03-21 19:36:12
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{
@Resource
private AdminMapper adminMapper;



    @Override
    public Admin selectAdminByUsernameAndPwd(String username, String password) {
        return adminMapper.selectOne(new LambdaQueryWrapper<Admin>().eq(Admin::getName,username).
                eq(Admin::getPassword,password));
    }

    @Override
    public Admin selectAdminById(Long userId) {
        return adminMapper.selectOne(new LambdaQueryWrapper<Admin>().eq(Admin::getId,userId));
    }
}




