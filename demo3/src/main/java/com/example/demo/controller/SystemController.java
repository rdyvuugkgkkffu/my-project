package com.example.demo.controller;

import com.example.demo.pojo.Admin;
import com.example.demo.pojo.LoginForm;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.Teacher;
import com.example.demo.service.AdminService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeacherService;
import com.example.demo.utils.*;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/sms/system")
public class SystemController {
    @Resource
    private AdminService adminService;

    @Resource
    private StudentService studentService;

    @Resource
    private TeacherService teacherService;

    /**
     * 将验证码图片返回给浏览器
     *
     * @param session
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getVerifiCodeImage")
    public void getVerifiCodeImage(HttpSession session, HttpServletResponse response) throws IOException {
        //获取验证码图片
        BufferedImage verifiCodeImage = CreateVerifiCodeImage.getVerifiCodeImage();
        //获取一下验证码中的值
        String code = new String(CreateVerifiCodeImage.getVerifiCode());
        //将验证码中的值保存到session中
        session.setAttribute("code", code);
        //将验证码图片返货给浏览器
        ImageIO.write(verifiCodeImage, "JPG", response.getOutputStream());

    }

    @PostMapping("/login")
    public Result<Object> login(@RequestBody LoginForm loginForm, HttpSession session) {
        //先校验验证码 判断验证码是否正确
        String code = (String) session.getAttribute("code");

        //先判断是否失效
        if (code == null || "".equals(code)) {
            return Result.fail().message("验证码失效");
        }

        //获取用户输入的验证码
        String userInputCode = loginForm.getVerifiCode();
        if (!userInputCode.equalsIgnoreCase(code)) {

            return Result.fail().message("验证码输入有误");
        }
        //销毁session中的验证码的值
        session.removeAttribute("code");
        //获取用户类型
        Integer userType = loginForm.getUserType();
        String username = loginForm.getUsername();
        String password = MD5.encrypt(loginForm.getPassword());
        Map<String, Object> map = new LinkedHashMap<>();

        if (userType == 1) {
            Admin admin = adminService.selectAdminByUsernameAndPwd(username, password);
            if (admin != null) {
                //登录成功后需要根据用户id和用户类型生成token
                String token = JwtHelper.createToken((admin.getId()).longValue(), userType);
                map.put("token", token);
                return Result.ok(map);
            }
            return Result.fail().message("用户名或者密码错误");
        } else if (userType == 2) {
            Student student = studentService.selectStudentByUsernameAndPwd(username, password);
            if (student != null) {
                //登录成功后需要根据用户id和用户类型生成token
                String token = JwtHelper.createToken((student.getId()).longValue(), userType);
                map.put("token", token);
                return Result.ok(map);
            }
            return Result.fail().message("用户名或者密码错误");

        } else {
            Teacher teacher = teacherService.selectTeacherByUsernameAndPwd(username, password);
            if (teacher != null) {
                //登录成功后需要根据用户id和用户类型生成token
                String token = JwtHelper.createToken((teacher.getId()).longValue(), userType);
                map.put("token", token);
                return Result.ok(map);
            }
            return Result.fail().message("用户名或者密码错误");
        }

    }

    /**
     * 接受来自浏览器的数据并进行判断
     * @param token
     * @return
     */
    @GetMapping("/getInfo")
    public Result<Object> getinfo(@RequestHeader("token") String token) {
        //先判断token是否有效
        if (JwtHelper.isExpiration(token)) {

            return Result.build(null, ResultCodeEnum.TOKEN_ERROR);//token失效，牢记这个方法
        }
        //解析token，获取用户id和用户类型，将用户类型返回给浏览器
        Long userId = JwtHelper.getUserId(token);
        Integer userType = JwtHelper.getUserType(token);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("userType", userType);

        if (userType == 1) {
            Admin admin = adminService.selectAdminById(userId);
            map.put("user", admin);


        } else if (userType == 2) {
            Student student = studentService.selectStudentById(userId);
            map.put("user", student);

        } else {
            Teacher teacher = teacherService.selectTeacherById(userId);
            map.put("user", teacher);
        }
        return Result.ok(map);
    }

    @PostMapping("/headerImgUpload")
    public Result<Object> headerImgUpload( @RequestPart("multipartFile") MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        assert originalFilename != null;
        String photoName = UUID.randomUUID().toString().replace("-", "").toLowerCase().
                concat(originalFilename.substring(originalFilename.lastIndexOf(".")));
        String savePath = "C:/Users/21006/IdeaProjects/src/main/resources/static/upload".concat(photoName);
        //保存图片
        multipartFile.transferTo(new File(savePath));
        return Result.ok("upload/".concat(photoName));
    }

}
