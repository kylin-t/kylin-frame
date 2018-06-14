package com.kylin.modules.system.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.kylin.common.Result.Result;
import com.kylin.common.base.controller.BaseController;
import com.kylin.common.utils.ShiroUtils;
import com.kylin.modules.system.service.SysUserTokenService;
import com.kylin.modules.system.entity.SysUser;
import com.kylin.modules.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Description: 登陆相关
 * @author: kylin
 * @create: 2018-01-30 17:42
 **/
@RestController
@Api(tags ={"登陆相关"})
public class SysLoginController extends BaseController{
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;
    @Autowired
    private Producer producer;
    /**
     * 验证码
     */
    @RequestMapping("captcha.jpg")
    public void captcha(HttpServletResponse response)throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }
    /**
     * 登录
     */
    @ApiOperation(value = "登陆")
    @RequestMapping(value = "/sys/login", method = RequestMethod.POST)
    public Result login(String username, String password, String captcha)throws IOException {
        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if(!captcha.equalsIgnoreCase(kaptcha)){
            return Result.failure("验证码不正确");
        }
        //用户信息
        SysUser user = sysUserService.queryByUserName(username);

        //账号不存在、密码错误
        if(user == null || !user.getPassword().equals(new Sha256Hash(password, user.getSalt()).toHex())) {
            return Result.failure("账号或密码不正确");
        }

        //账号锁定
        if(user.getStatus() == 0){
            return Result.failure("账号已被锁定,请联系管理员");
        }

        //生成token，并保存到数据库
        Result result = sysUserTokenService.createToken(user.getUserId());
        return result;
    }


    /**
     * 退出
     */
    @ApiOperation(value = "退出")
    @RequestMapping(value = "/sys/logout", method = RequestMethod.POST)
    public Result logout() {
        sysUserTokenService.logout(getUserId());
        return Result.success();
    }
}
