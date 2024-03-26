package com.example.demo.mapper;

import com.example.demo.pojo.Grade;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 21006
* @description 针对表【tb_grade】的数据库操作Mapper
* @createDate 2024-03-21 19:36:33
* @Entity com.example.demo.pojo.Grade
*/
@Mapper
public interface GradeMapper extends BaseMapper<Grade> {

}




