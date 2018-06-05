package com.kylin.sys.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.kylin.Result.Result;
import com.kylin.utils.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author kylin
 * Description
 * @Date Created in 2018/05/31 9:36
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    private Producer producer;

    @RequestMapping("/sys/captcha")
    public void captcha(HttpServletResponse response){
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        try {
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(image, "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录
     */
    @ResponseBody
    @RequestMapping(value = "/sys/login", method = RequestMethod.POST)
    public Result login(String username, String password, String captcha){
        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if(!captcha.equalsIgnoreCase(kaptcha)){
            return Result.failure("验证码不正确");
        }
        try{
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        }catch (UnknownAccountException e) {
            return Result.failure(e.getMessage());
        }catch (IncorrectCredentialsException e) {
            return Result.failure("账号或密码不正确");
        }catch (LockedAccountException e) {
            return Result.failure("账号已被锁定,请联系管理员");
        }catch (AuthenticationException e) {
            return Result.failure("账户验证失败");
        }


        return Result.success();
    }
}
