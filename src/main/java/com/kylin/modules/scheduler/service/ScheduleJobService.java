package com.kylin.modules.scheduler.service;

import com.kylin.modules.scheduler.entity.ScheduleJob;

import java.util.List;
import java.util.Map;

/**
 * @Description: 定时任务业务接口
 * @author: kylin
 * @create: 2018-02-06 16:30
 **/
public interface ScheduleJobService {
    /**
     * 根据id查询定时任务
     * @param jobId
     * @return
     */
    ScheduleJob queryObject(Long jobId);

    /**
     * 查询定时任务列表
     * @param map
     * @return
     */
    List<ScheduleJob> queryList(Map<String,Object> map);

    /**
     * 查询列表总数
     * @param map
     * @return
     */
    int queryTotal(Map<String,Object> map);

    /**
     * 保存定时任务
     * @param scheduleJob
     */
    void save(ScheduleJob scheduleJob);

    /**
     * 更新定时任务
     * @param scheduleJob
     */
    void update(ScheduleJob scheduleJob);

    /**
     * 批量删除
     * @param jobIds
     */
    void deleteBatch(Long[] jobIds);

    /**
     * 批量更新任务状态
     * @param jobIds
     * @param status
     */
    void updateBactch(Long[] jobIds,int status);

    /**
     * 立即执行任务
     * @param jobIds
     */
    void run(Long[] jobIds);

    /**
     * 暂停任务
     * @param jobIds
     */
    void pause(Long[] jobIds);

    /**
     * 恢复任务
     * @param jobIds
     */
    void resume(Long[] jobIds);

}
