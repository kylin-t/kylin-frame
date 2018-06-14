package com.kylin.modules.system.controller;

import com.kylin.common.annotation.SysLog;
import com.kylin.common.base.controller.BaseController;
import com.kylin.common.utils.PageUtils;
import com.kylin.common.utils.QueryTest;
import com.kylin.common.utils.R;
import com.kylin.modules.system.entity.Mobile;
import com.kylin.modules.system.service.MobileService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author hy
 * @Date 2018/1/4 16:30
 */
@RestController
@RequestMapping("/sys/mobile")
public class MobileController extends BaseController {

    @Autowired
    private MobileService mobileService;

    /**
     * 移动端查询
     * @param params
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:mobile:list")
    public R list(@RequestParam Map<String,Object> params){

        QueryTest queryTest = new QueryTest(params);
        List<Mobile> mobile = mobileService.queryList(params);
        int total = mobileService.queryTotal(queryTest);
        PageUtils pageUtil = new PageUtils(mobile, total, queryTest.getLimit(), queryTest.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 移动端新增
     * @return
     */
    @SysLog("新增移动端")
    @RequestMapping("/save")
    public R save(@RequestBody Mobile params){
        try {
            String stationNum = params.getStationNum();
            String mobileNum = params.getMobileNum();
            int i = mobileService.queryMobileNum(mobileNum);
            if (i == 1) {
                return R.error();
            }
            mobileService.save(mobileNum, stationNum);
            return R.ok();
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }

    /**
     * 移动端删除
     * @return
     */
    @SysLog("删除移动端数据")
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] mobileNum){
        mobileService.deleteBatch(mobileNum);
        return R.ok();
    }

    /**
     * 修改移动端数据
     */
    @RequestMapping("/update")
    public R update(@RequestBody Mobile params){
        try {
            mobileService.update(params);
            return R.ok();
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }

    /**
     * 移动端信息
     * @param mobileNum
     * @return
     */
    @RequestMapping("/info/{mobileNum}")
    public R info(@PathVariable("mobileNum") String mobileNum){
        Mobile mobileEntity = mobileService.queryObject(mobileNum);
        return R.ok().put("mobileEntity", mobileEntity);
    }

    /**
     * 移动端信息查询
     * @param params
     * @return
     */
    @RequestMapping("/select")
    public R select(@RequestParam Map<String, Object> params){
        List<Mobile> list = mobileService.queryList(params);
        return R.ok().put("list", list);
    }

    /**
     * 移动端版本查询
     * @param params
     * @return
     */
    @RequestMapping("/select-versionNo")
    public List<Mobile> selectVersionNo(@RequestParam Map<String, Object> params){
        List<Mobile> list = mobileService.queryVersionNo(params);
        return list;
    }

}
