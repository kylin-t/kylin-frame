package com.kylin.modules.system.entity;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author hy
 * @Date 2018/1/11 17:33
 */
public class MobileVersion {

    private Long versionId;

    private String deptNum;

    private Double versionNo;

    private String remark;

    private String filePath;

    private MultipartFile file;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public String getDeptNum() {
        return deptNum;
    }

    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    public Double getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(Double versionNo) {
        this.versionNo = versionNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
