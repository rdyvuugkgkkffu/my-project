package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.Student;
import com.example.demo.service.StudentService;
import com.example.demo.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 21006
* @description 针对表【tb_student】的数据库操作Service实现
* @createDate 2024-03-21 19:36:37
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{
    @Resource
    private StudentMapper studentMapper;


    @Override
    public Student selectStudentByUsernameAndPwd(String username, String password) {
        return studentMapper.selectOne(new LambdaQueryWrapper<Student>().eq(Student::getName,username).
                eq(Student::getPassword,password));
    }

    @Override
    public Student selectStudentById(Long userId) {
        return studentMapper.selectOne(new LambdaQueryWrapper<Student>().eq(Student::getId,userId));
    }
}




