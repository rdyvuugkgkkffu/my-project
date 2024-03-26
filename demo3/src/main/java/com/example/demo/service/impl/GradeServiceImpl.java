package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.pojo.Grade;
import com.example.demo.service.GradeService;
import com.example.demo.mapper.GradeMapper;
import org.springframework.stereotype.Service;

/**
* @author 21006
* @description 针对表【tb_grade】的数据库操作Service实现
* @createDate 2024-03-21 19:36:33
*/
@Service
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade>
    implements GradeService{

}




