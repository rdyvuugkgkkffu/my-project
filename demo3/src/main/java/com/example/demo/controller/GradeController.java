package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.Grade;
import com.example.demo.service.GradeService;
import com.example.demo.utils.Result;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 带条件的分页查询
 * pn:当前页码
 * pigeSize 每页显示的数据数
 * gradeName 模糊查询的条件
 */

@RestController
@RequestMapping("/sms/gradeController")
public class GradeController {
    @Resource

    private GradeService gradeService;


    @RequestMapping("/getGrades/{pn}/{pageSize}")
    public Result<Object> getGrades(
            @PathVariable("pn") Integer pn,
            @PathVariable("pageSize") Integer pageSize,
            String gardeName) {

        Page<Grade> page = gradeService.page(new Page<>(pn, pageSize), new LambdaQueryWrapper<Grade>().
                like(StrUtil.isNotBlank(gardeName), Grade::getName, gardeName).orderByDesc(Grade::getId));

        return Result.ok(page);
    }


    //删除功能
    @DeleteMapping("/deleteGrade")
    public Result<Object> deleteGrade(@RequestBody List<Integer> ids) {
        if (ids.size() == 1) {
            //单条删除
            gradeService.removeById(ids.get(0));
        } else {
            //批量删除
            gradeService.removeBatchByIds(ids);
        }
        return Result.ok();
    }

    /**
     * 根据判断请求体重是否有id进行添加或者修改功能
     *
     * @param grade 封装请求体中的JSON数据到实体类Grade中
     * @return 返回成功的数据
     */
    @PostMapping("saveOrUpdateGrade")
    public Result<Object> saveOrUpdateGrade(@RequestBody Grade grade) {
        //判断请求体是否有id，有则修改，否则为添加
        Integer id = grade.getId();
        if (id != null) {
            gradeService.update(grade, new LambdaQueryWrapper<Grade>().eq(Grade::getId, id));
        } else {

            gradeService.save(grade);
        }
        return Result.ok();

    }

    @GetMapping("/getGrades")
    public Result<Object> getGrades() {
        List<Grade> grades = gradeService.list(null);
        return Result.ok(grades);
    }


}
