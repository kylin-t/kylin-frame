//package com.kylin.modules.app.controller;
//
//import com.kylin.com.kylin.common.base.controller.BaseController;
//import com.kylin.com.kylin.core.utils.R;
//import com.kylin.modules.app.service.MobileService;
//import com.kylin.modules.app.utils.FileUtils;
//
//import com.kylin.modules.system.service.SysDeptService;
//import com.kylin.modules.system.service.SysUserService;
//import org.apache.commons.codec.binary.Base64;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.*;
//
//@RestController
//@RequestMapping("/app")
//public class PDAController extends BaseController {
//    @Autowired
//    private MobileService mobileService;
//    @Autowired
//    private SysDeptService sysDeptService;
//    @Autowired
//    private SysUserService sysUserService;
//    @Autowired
//    private GoodsTypeService goodsTypeService;
//    @Autowired
//    private SysCodeValueServiece sysCodeValueService;
//    @Autowired
//    private SysPassRecordService sysPassRecordService;
//    @Autowired
//    private SysPassRecordAttrService sysPassRecordAttrService;
//    @Autowired
//    private VehicleGradeService vehicleGradeService;
//
//    /**
//     * 移动端注册设备
//     * @param mobileNum
//     * @param deptNum
//     * @param mac
//     * @param versionNo
//     * @return
//     */
//    @RequestMapping(value = "registerMobile",method = RequestMethod.POST)
//    public R registerMobile(@RequestParam String mobileNum, @RequestParam String deptNum, @RequestParam String mac, @RequestParam String versionNo){
//        SysDept dept = sysDeptService.queryObject(deptNum);
//        if(dept.getChannels() == null){
//            return R.error("收费站信息不全，请联系管理员");
//        }
//        if(StringUtils.isNullOrEmpty(mobileNum)
//                || StringUtils.isNullOrEmpty(deptNum)
//                || StringUtils.isNullOrEmpty(mac)) {
//            return R.error("提交注册信息不完整");
//        }
//        MobileEntity m = mobileService.queryObject(mobileNum);
//        if(m == null || !deptNum.equals(m.getStationNum())) {
//            return R.error("提交注册信息不正确");
//        }
//        String mobileMac = m.getMobileMac();
//        if(!StringUtils.isNullOrEmpty(mobileMac)){
//            if (mobileMac.equals(mac)){
//                return R.ok();
//            }
//            if (!mobileMac.equals(mac)){
//                return R.error("该注册码已被使用");
//            }
//        }
//        try {
//            m.setMobileMac(mac.toUpperCase());
//            m.setVersionNo(Double.valueOf(versionNo));
//            mobileService.update(m);
//        } catch (Exception e) {
//            return R.error("绑定移动端失败");
//        }
//        return R.ok();
//    }
//
//    /**
//     * 根据收费站编码得到所在集团公司
//     * @param deptNum
//     * @return
//     */
//    @RequestMapping(value = "getGroupByDeptNum",method = RequestMethod.POST)
//    public R getGroupByDeptNum(@RequestParam String mobileNum, @RequestParam String mac, @RequestParam String deptNum){
//        if(!verifyMobile(mobileNum, deptNum, mac)) {
//            return R.error("验证移动端失败，不能同步数据");
//        }
//        SysDept currDept = sysDeptService.queryObject(deptNum);
//        SysDept sysDept = sysDeptService.getHeaderDept(deptNum);
//        return R.ok().put("group", getDeptMap(sysDept)).put("station", getDeptMap(currDept));
//    }
//
//    /**
//     * 根据收费站编码得到所在集团公司收费站列表
//     * @param deptNum
//     * @return
//     */
//    @RequestMapping(value = "getStations",method = RequestMethod.POST)
//    public R getStations(@RequestParam String mobileNum, @RequestParam String mac, @RequestParam String deptNum){
//        if(!verifyMobile(mobileNum, deptNum, mac)) {
//            return R.error("验证移动端失败，不能同步数据");
//        }
//        List<Map<String,String>> rList = new ArrayList<Map<String,String>>();
//        List<SysDept> list = sysDeptService.getStationsByDeptNum(deptNum);
//        for(SysDept dept: list) {
//            rList.add(getDeptMap(dept));
//        }
//        return R.ok().put("stations", rList);
//    }
//
//    /**
//     * 根据收费站编码得到收费站用户列表
//     * @param deptNum
//     * @return
//     */
//    @RequestMapping(value = "getPDAUsers",method = RequestMethod.POST)
//    public R getUsers(@RequestParam String mobileNum, @RequestParam String mac, @RequestParam String deptNum){
//        if(!verifyMobile(mobileNum, deptNum, mac)) {
//            return R.error("验证移动端失败，不能同步数据");
//        }
//        List<SysUserEntity> list = sysUserService.getPDAUsersByDeptNum(deptNum);
//        List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
//        for(SysUserEntity u: list) {
//            Map<String, Object> m = new HashMap<String,Object>();
//            m.put("username", u.getUsername());
//            m.put("password", u.getPassword());
//            m.put("salt", u.getSalt());
//            m.put("cname", u.getCname());
//            m.put("classNum", u.getClassNum());
//            m.put("roleNums", u.getRoleNum());
//            m.put("roleNames", u.getRoleName());
//            dataList.add(m);
//        }
//        return R.ok().put("users", dataList);
//    }
//
//    /**
//     * 根据收费站编码得到货物类型列表
//     * @param deptNum
//     * @return
//     */
//    @RequestMapping(value = "getGoodsType",method = RequestMethod.POST)
//    public R getGoodsType(@RequestParam String mobileNum, @RequestParam String mac, @RequestParam String deptNum){
//        if(!verifyMobile(mobileNum, deptNum, mac)) {
//            return R.error("验证移动端失败，不能同步数据");
//        }
//        List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
//        List<GoodsTypeEntity> list = goodsTypeService.queryListByDeptNum(deptNum);
//        for(GoodsTypeEntity e: list) {
//            Map<String,Object> m = new HashMap<String,Object>();
//            m.put("value", e.getType3());
//            m.put("text", e.getType3Name());
//            m.put("qCode", e.getType3QCode());
//            m.put("gValue", e.getType2());
//            m.put("gText", e.getType2Name());
//            m.put("gQCode", e.getType2QCode());
//            dataList.add(m);
//        }
//        return R.ok().put("goodsType", dataList);
//    }
//
//    /**
//     * 得到其他数据字典
//     * @return
//     */
//    @RequestMapping(value = "getOthers",method = RequestMethod.POST)
//    public R getOthers(@RequestParam String mobileNum, @RequestParam String deptNum, @RequestParam String mac){
//        if(!verifyMobile(mobileNum, deptNum, mac)) {
//           return R.error("验证移动端失败，不能同步数据");
//        }
//        Map<String,Object> map = new HashMap<String,Object>();
//        List<SysCodeValueEntity> datas = sysCodeValueService.queryListByCatalogNums(new Integer[]{12,20});
//        int old = -999;
//        List<Map<String,Object>> scvList = null;
//        for (SysCodeValueEntity scv: datas) {
//            int catalogNum = scv.getCatalogNum();
//            if(old == -999 || old != catalogNum) {
//                if(old != -999) {
//                    map.put("Cata" + String.valueOf(old), scvList);
//                }
//                scvList = new ArrayList<Map<String,Object>>();
//                old = catalogNum;
//            }
//            scvList.add(getValueMap(scv));
//        }
//        if(scvList != null && scvList.size() > 0) {
//            map.put("Cata" + String.valueOf(old), scvList);
//        }
//        return R.ok(map);
//    }
//
//    /**
//     * 传输绿通过车记录
//     * @param pr
//     * @return
//     */
//    @RequestMapping(value = "addPassRecord",method = RequestMethod.POST)
//    public R addPassRecord(SysPassRecordEntity pr){
//        String isGreen = pr.getIsGreen();
//        if("1".equals(isGreen)){
//            pr.setPassType(1);
//        }else{
//            pr.setPassType(0);
//        }
//        pr.setCreator(pr.getOpUsername());
//        pr.setMode(19001);
//        pr.setCreateTime(new Date());
//
//        sysPassRecordService.save(pr);
//
//        MultipartFile pic1 = pr.getPic1();
//        MultipartFile pic2 = pr.getPic2();
//        MultipartFile pic3 = pr.getPic3();
//        MultipartFile pic4 = pr.getPic4();
//
//        try {
//            String base64Photo1 = Base64Helper.getBase64("data:image/jpeg;base64,"+ Base64.encodeBase64String(pic1.getBytes()) );
//            String base64Photo2 = Base64Helper.getBase64("data:image/jpeg;base64,"+ Base64.encodeBase64String(pic2.getBytes()) );
//            String base64Photo3 = Base64Helper.getBase64("data:image/jpeg;base64,"+ Base64.encodeBase64String(pic3.getBytes()) );
//            String base64Photo4 = Base64Helper.getBase64("data:image/jpeg;base64,"+ Base64.encodeBase64String(pic4.getBytes()) );
//
//            SysPassRecordAttrEntity pra = new SysPassRecordAttrEntity();
//            pra.setPhoto1(Base64.decodeBase64(base64Photo1));
//            pra.setPhoto2(Base64.decodeBase64(base64Photo2));
//            pra.setPhoto3(Base64.decodeBase64(base64Photo3));
//            pra.setPhoto4(Base64.decodeBase64(base64Photo4));
//            pra.setRecordId(pr.getRecordId());
//            pra.setExitStation(pr.getExitStation());
//
//            sysPassRecordAttrService.save(pra);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        return R.ok();
//    }
//
//    private boolean verifyMobile(String mobileNum, String deptNum, String mac) {
//        if(StringUtils.isNullOrEmpty(mobileNum)
//                || StringUtils.isNullOrEmpty(deptNum)
//                || StringUtils.isNullOrEmpty(mac)) {
//            return false;
//        }
//        MobileEntity m = mobileService.queryObject(mobileNum);
//        if(m == null || !deptNum.equals(m.getStationNum()) || !(mac.toUpperCase()).equals(m.getMobileMac().toUpperCase())) {
//            return false;
//        }
//        return true;
//    }
//
//    private List<Map<String,String>> getStationsByDeptNum(String deptNum) {
//        List<Map<String,String>> rList = new ArrayList<Map<String,String>>();
//        List<SysDept> list = sysDeptService.getStationsByDeptNum(deptNum);
//        for(SysDept dept: list) {
//            rList.add(getDeptMap(dept));
//        }
//        return rList;
//    }
//
//    private Map<String,String> getDeptMap(SysDept dept) {
//        Map<String,String> map = new HashMap<String,String>();
//        map.put("deptNum", dept.getDeptNum());
//        map.put("deptName", dept.getName());
//        map.put("queryCode",dept.getQueryCode());
//        map.put("channels", dept.getChannels());
//        return map;
//    }
//
//    private Map<String, Object> getValueMap(SysCodeValueEntity e) {
//        Map<String, Object> map = new HashMap<String,Object>();
//        map.put("valueNum", e.getValueNum());
//        map.put("valueName", e.getValueName());
//        map.put("queryCode", e.getQueryCode());
//        return map;
//    }
//
//    /**
//     * 获取同步状态
//     * @param mobileNum
//     * @param deptNum
//     * @param mac
//     * @return
//     */
//    @RequestMapping(value = "/syncState-query",method = RequestMethod.POST)
//    public R querySyncState(@RequestParam String mobileNum, @RequestParam String deptNum, @RequestParam String mac){
//        if(!verifyMobile(mobileNum,deptNum,mac)){
//            return R.error("验证移动端失败，不能同步数据");
//        }
//        MobileEntity mobileEntity = mobileService.querySyncState(mobileNum,deptNum);
//        Map<String,Object> map = new HashMap<>();
//        map.put("stationSync",mobileEntity.getStationSync());
//        map.put("usersSync",mobileEntity.getUsersSync());
//        map.put("goodsSync",mobileEntity.getGoodsSync());
//        map.put("catalogSync",mobileEntity.getCatalogSync());
//        return R.ok().put("syncState",map);
//    }
//
//    /**
//     * 修改同步状态
//     * @param map
//     * @return
//     */
//    @RequestMapping(value = "/syncState-update",method = RequestMethod.POST)
//    public R updateSyncState(@RequestParam Map<String,Object> map){
//        mobileService.updateSyncState(map);
//        return R.ok();
//    }
//
//    /**
//     * 获取车辆评级信息
//     * @param vehicleNum 车牌号
//     * @return
//     */
//    @RequestMapping(value = "/vcGradeInfo",method = RequestMethod.POST)
//    public R queryVehicleGrade(@RequestParam String vehicleNum){
//        VehicleGradeEntity vehicleGrade = vehicleGradeService.queryVehicleGrade(vehicleNum);
//        return R.ok().put("vcGradeInfo",vehicleGrade);
//    }
//
//    /**
//     * 获取过车照片
//     * @param recordId
//     * @return
//     */
//    @RequestMapping(value = "/passImgs",method = RequestMethod.POST)
//    public R queryPassImgs(@RequestParam Long recordId, HttpServletResponse response){
//        SysPassRecordAttrEntity passAttr = vehicleGradeService.queryPassImgs(recordId);
//        byte[] img1 = Base64.decodeBase64(Base64Helper.getFromBase64(Base64.encodeBase64String(passAttr.getPhoto1())));
//
//        try {
//            this.getImg(response,img1);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return R.error(e.getMessage());
//        }
//
//        return R.ok();
//    }
//    private void getImg (HttpServletResponse response, byte[] imgData) throws IOException {
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("image/*");
//
//        InputStream is = new ByteArrayInputStream(imgData);
//        OutputStream os = response.getOutputStream();
//        int count = 0;
//        byte[] buffer = new byte[1024*8];
//        while ((count = is.read(buffer))!= -1){
//            os.write(buffer,0,count);
//            os.flush();
//        }
//        os.close();
//    }
//
//    /**
//     * 查询app版本
//     * @param map
//     * @return
//     */
//    @RequestMapping(value = "/version",method = RequestMethod.POST)
//    public R version(@RequestParam Map<String,String> map){
//        if(!verifyMobile((String)map.get("mobileNum"),(String)map.get("deptNum"),(String)map.get("mac"))){
//            return R.error("验证移动端失败");
//        }
//        MobileEntity mobileEntity = mobileService.queryVersion((String)map.get("mobileNum"),(String)map.get("deptNum"));
//        Double newVersionNo = mobileEntity.getVersionNo();
//        if(Double.parseDouble(map.get("versionNo"))<newVersionNo){
//            return R.ok(newVersionNo.toString()).put("remark",mobileEntity.getRemark());
//        }
//        return R.ok();
//    }
//
//    /**
//     * 更新app（下载安装包）
//     * @param versionNo
//     * @param deptNum
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = "/version-download/{deptNum}/{versionNo}/",method = RequestMethod.GET)
//    public R updateVersion(@PathVariable String deptNum, @PathVariable String versionNo, HttpServletResponse response){
//        String versionNo1 = versionNo;
//        SysDept sysDept = sysDeptService.getHeaderDept(deptNum);
//        String appRoute = mobileService.getAppRoute(versionNo1,sysDept.getDeptNum());
//        FileUtils fileUtils = new FileUtils();
//        try {
//            fileUtils.download(appRoute,response);
//        }catch (RuntimeException e){
//            e.printStackTrace();
//            return R.error();
//        }
//        return R.ok();
//    }
//}
