package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.Clazz;
import com.example.demo.service.ClazzService;
import com.example.demo.utils.Result;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/sms/clazzController")
public class ClazzController {
    @Resource
    private ClazzService clazzService;

    @RequestMapping("/getClazzsByOpr/{pn}/{pageSize}")

    public Result<Object> getClazzs(
            @PathVariable("pn") Integer pn,
            @PathVariable("pageSize") Integer pageSize,
            Clazz clazz) {
        String gradeName = clazz.getGradeName();
        String name = clazz.getName();
        Page<Clazz> page = clazzService.page(new Page<>(pn, pageSize), new LambdaQueryWrapper<Clazz>().
                like(StrUtil.isNotBlank(gradeName), Clazz::getGradeName, gradeName).
                like(StrUtil.isNotBlank(name), Clazz::getName, name)
                .orderByDesc(Clazz::getId)
        );

        return Result.ok(page);
    }

    @PostMapping("/saveOrUpdateClazz")

    public Result<Object> saveOrUpdateClazz(@RequestBody Clazz clazz) {
        Integer id = clazz.getId();//判断请求体是否有id
        if (id != null) {
            clazzService.update(clazz, new LambdaQueryWrapper<Clazz>().eq(Clazz::getId, id));
        } else {
            clazzService.save(clazz);
        }
        return Result.ok();
    }


    @DeleteMapping("/deleteClazz")
    public Result<Object> deleteClazz(@RequestBody List<Integer> ids) {
        if (ids.size() == 1) {
            //单条删除
            clazzService.removeById(ids.get(0));
        } else {
            //批量删除
            clazzService.removeBatchByIds(ids);
        }
        return Result.ok();
    }
    
    @GetMapping("/getClazzs")
    public  Result<Object> getClazzs(){
     List<Clazz> clazzes =  clazzService.list(null);
     return  Result.ok(clazzes);
    }
    


}
