package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.Teacher;
import com.example.demo.service.TeacherService;
import com.example.demo.mapper.TeacherMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 21006
* @description 针对表【tb_teacher】的数据库操作Service实现
* @createDate 2024-03-21 19:36:40
*/
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService{
    @Resource
    private  TeacherMapper teacherMapper;


    @Override
    public Teacher selectTeacherByUsernameAndPwd(String username, String password) {
        return teacherMapper.selectOne(new LambdaQueryWrapper<Teacher>().eq(Teacher::getName,username).
                eq(Teacher::getPassword,password));
    }

    @Override
    public Teacher selectTeacherById(Long userId) {
        return teacherMapper.selectOne(new LambdaQueryWrapper<Teacher>().eq(Teacher::getId,userId));
    }
}




