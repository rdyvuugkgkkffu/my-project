package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.Clazz;
import com.example.demo.pojo.Grade;
import com.example.demo.pojo.Teacher;
import com.example.demo.service.TeacherService;
import com.example.demo.utils.Result;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/sms/teacherController")
public class TeacherController {
    @Resource
    private TeacherService teacherService;
    
    @RequestMapping("/getTeachers/{pn}/{pageSize}")
    public Result<Object> getTeachers(
            @PathVariable("pn") Integer pn,
            @PathVariable("pageSize") Integer pageSize,
            Teacher teacher
    ){
       String clazzName  = teacher.getClazzName();
       String name = teacher.getName();
        Page<Teacher> page = teacherService.page(new Page<>(pn, pageSize), new LambdaQueryWrapper<Teacher>().
                like(StrUtil.isNotBlank(clazzName), Teacher::getClazzName, clazzName).
                like(StrUtil.isNotBlank(name), Teacher::getName, name)
                .orderByDesc(Teacher::getId));
        return Result.ok(page);
    } 
    
   @DeleteMapping("/deleteTeacher")
    
    public Result<Object> deleteTeacher(@RequestBody List<Integer> ids ){
        if(ids.size()==1){//单条删除
            teacherService.removeById(ids.get(0));
        }else {//整条删除
            teacherService.removeBatchByIds(ids);
        }
        return Result.ok();
   }
    
   
   
   @PostMapping("/saveOrUpdateTeacher")
    public Result<Object> saveOrUpdateTeacher(@RequestBody Teacher teacher){
        Integer id = teacher.getId();
        if(id !=null){
            teacherService.update(teacher,new LambdaQueryWrapper<Teacher>().eq(Teacher::getId,id));
        }else {
            teacherService.save(teacher);
        }
        return Result.ok();
   }
}
