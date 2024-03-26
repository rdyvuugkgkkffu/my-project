package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName tb_admin
 */
@TableName(value ="tb_admin")
@Data
public class Admin implements Serializable {
    private Integer id;

    private String name;

    private String gender;

    private String password;

    private String email;

    private String telephone;

    private String address;

    private String portraitPath;

    private static final long serialVersionUID = 1L;
}