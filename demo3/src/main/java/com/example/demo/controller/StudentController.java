package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.Student;
import com.example.demo.service.StudentService;
import com.example.demo.utils.MD5;
import com.example.demo.utils.Result;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/sms/studentController")
public class StudentController {
    @Resource
    private StudentService studentService;

    @GetMapping("/getStudentByOpr/{pn}/{pageSize}")
    public Result<Object> getStudentByOpr(@PathVariable("pn") Integer pn,
                                          @PathVariable("pageSize") Integer pageSize,
                                          String name, String clazzName) {

        Page<Student> page = studentService.page(new Page<>(pn, pageSize),
                new LambdaQueryWrapper<Student>().like(StrUtil.isNotBlank(name), Student::getName, name)
                        .like(StrUtil.isNotBlank(clazzName), Student::getClazzName, clazzName)
                        .orderByDesc(Student::getId));

        return Result.ok(page);
    }


    @DeleteMapping("/delStudentById")
    public Result<Object> delStudentById(@RequestBody List<Integer> ids) {
        if (ids.size() == 1) {
            studentService.removeById(ids.get(0));
        } else {
            studentService.removeBatchByIds(ids);
        }
        return Result.ok();
    }

    @PostMapping("/addOrUpdateStudent")
    public Result<Object> addOrUpdateStudent(@RequestBody Student student){
        Integer id = student.getId();
        if(id == null){
            //将获取到的密码进行加密 存储到数据库中
            student.setPassword(MD5.encrypt(student.getPassword()));
            //添加学生
            studentService.save(student);
        }else {
            studentService.update(student,new LambdaQueryWrapper<Student>().eq(Student::getId,id));
        }
        return Result.ok();
    }

}
