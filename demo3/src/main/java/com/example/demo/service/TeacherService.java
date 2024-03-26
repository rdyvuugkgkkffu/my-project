package com.example.demo.service;

import com.example.demo.pojo.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 21006
* @description 针对表【tb_teacher】的数据库操作Service
* @createDate 2024-03-21 19:36:40
*/
public interface TeacherService extends IService<Teacher> {


    Teacher selectTeacherByUsernameAndPwd(String username, String password);

    Teacher selectTeacherById(Long userId);
}
