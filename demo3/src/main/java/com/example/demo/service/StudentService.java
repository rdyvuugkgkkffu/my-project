package com.example.demo.service;

import com.example.demo.pojo.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 21006
* @description 针对表【tb_student】的数据库操作Service
* @createDate 2024-03-21 19:36:37
*/
public interface StudentService extends IService<Student> {

    Student selectStudentByUsernameAndPwd(String username, String password);

    Student selectStudentById(Long userId);
    //Student select


}
