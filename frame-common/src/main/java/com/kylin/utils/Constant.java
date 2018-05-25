package com.kylin.utils;

/**
 * @Author kylin
 * Description  常量
 * @Date Created in 2018/05/17 15:42
 */
public class Constant {

    public static final int SUPER_ADMIN = 1;

    /**
     * 是否类型
     */
    public enum STATUS{
        /**
         * 正常
         */
        NORMAL(1),
        /**
         * 禁用
         */
        DISABLED(0);

        private Integer value;

        private STATUS(Integer value){
            this.value=value;
        }
        public Integer getValue(){
            return value;
        }
    }
}
