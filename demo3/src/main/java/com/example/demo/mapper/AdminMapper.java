package com.example.demo.mapper;

import com.example.demo.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 21006
* @description 针对表【tb_admin】的数据库操作Mapper
* @createDate 2024-03-21 19:36:12
* @Entity com.example.demo.pojo.Admin
*/
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}




