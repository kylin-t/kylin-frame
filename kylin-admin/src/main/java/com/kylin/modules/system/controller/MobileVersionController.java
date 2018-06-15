package com.kylin.modules.system.controller;

import com.kylin.core.annotation.SysLog;
import com.kylin.core.utils.PageUtils;
import com.kylin.core.utils.QueryTest;
import com.kylin.core.utils.R;
import com.kylin.modules.system.entity.MobileVersion;
import com.kylin.modules.system.service.MobileVersionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;


/**
 * 移动端APP版本
 * @Author hy
 * @Date 2018/1/11 18:12
 */
@RestController
@RequestMapping("/sys/mobileVersion")
public class MobileVersionController {
    @Autowired
    private MobileVersionService mobileVersionService;

    /**
     * 移动端版本查询
     * @param params
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:mobileVersion:list")
    public R list(@RequestParam Map<String,Object> params){
        QueryTest queryTest = new QueryTest(params);
        List<MobileVersion> mobile = mobileVersionService.queryList(queryTest);
        int total = mobileVersionService.queryTotal(queryTest);
        PageUtils pageUtil = new PageUtils(mobile, total, queryTest.getLimit(), queryTest.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 移动端新增
     * @return
     */
    @SysLog("新增移动端")
    @RequestMapping("/save")
    public R save(@RequestBody MobileVersion params){
        mobileVersionService.save(params);
        return R.ok();
    }

    /**
     * 移动端删除
     * @return
     */
    @SysLog("删除移动端数据")
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] mobileNum){
        mobileVersionService.deleteBatch(mobileNum);
        return R.ok();
    }

    /**
     * 修改移动端数据
     */
    @RequestMapping("/update")
    public R update(@RequestBody MobileVersion params){
        mobileVersionService.update(params);
        return R.ok();
    }

    /**
     * 移动端信息
     * @param versionId
     * @return
     */
    @RequestMapping("/info/{versionId}")
    public R info(@PathVariable("versionId") Long versionId){
        MobileVersion mobileVersionEntity = mobileVersionService.queryObject(versionId);
        return R.ok().put("mobileVersionEntity", mobileVersionEntity);
    }

    /**
     * 移动端信息查询
     * @param params
     * @return
     */
    @RequestMapping("/select")
    public R select(@RequestParam Map<String, Object> params){
        List<MobileVersion> list = mobileVersionService.queryList(params);
        return R.ok().put("list", list);
    }

    /**
     * 更新说明
     * @param params
     * @return
     */
    @RequestMapping("/select-ramarks")
    public R selectRemark(@RequestParam Map<String, Object> params){
        List<MobileVersion> list = mobileVersionService.queryReamrkByVersionId(params);
        return R.ok().put("list", list);
    }

    /**
     * 集团公司查询
     * @param params
     * @return
     */
    @RequestMapping("/select-parentId")
    public List<MobileVersion> selectParentId(@RequestParam Map<String, Object> params){
        List<MobileVersion> list = mobileVersionService.queryListParentID(params);
        return list;
    }

    /**
     * 文件上传
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public R fileUpload(HttpServletRequest request)throws Exception{
        if (request instanceof MultipartHttpServletRequest) {
            //获得表单提交的file  getFile("file")参数为form表单 input中的name名称
            MultipartFile file =  ((MultipartHttpServletRequest) request).getFile("file");
            if(file!=null){
                //上传文件的新路径
//                String appPath = "F:/upload/app";
                //原始文件名
                String orignName = file.getOriginalFilename();
                //创建新文件  （路径+名称）
                File appFile = new File(request.getServletContext().getRealPath("/"+orignName));
                //判断目录是否存在  不存在则创建
                File fileParent = appFile.getParentFile();
                if(!fileParent.exists()){
                    fileParent.mkdirs();
                }
                //开始上传
                file.transferTo(appFile);
                return R.ok().put("filePath",appFile);
            }
        }
        return R.error("上传失败");
    }
}
