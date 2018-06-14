package com.kylin.common.utils;

/**
 * @Description: 常量
 * @author: kylin
 * @create: 2018-01-30 14:16
 **/
public class Constant {
    /** 超级管理员ID */
    public static final int SUPER_ADMIN = 1;
    /**
     * 菜单类型
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 定时状态
     */
    public enum ScheduleStatus {
        //正常
        NORMAL(0),
        //暂停
        PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    /**
     * 工作流状态
     */
    public enum WorkFlow{

        UN_SUBMIT("1"),//未提交
        IN_APPROVAL("2"),//审批中
        PASS("3"),//审批通过
        REJECT("4");//审批未通过

        private String value;

        WorkFlow(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static final String FINANCE_PROCESS_KEY = "financeProcess";

    public static final String FINANCE_NODE_ID1 = "usertask1";
    public static final String FINANCE_NODE_ID2 = "usertask2";
    public static final String FINANCE_NODE_ID3 = "usertask3";
    public static final String FINANCE_NODE_ID4 = "usertask4";

    public static final String KunBo_Flow_KEY = "KunBoFlow";


}
