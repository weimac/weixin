package com.wk.project.weixin.api.tuling.bean;

import lombok.Data;

/**
 * @Description:
 * @Company:qianfeng
 * @Auther:weiMac
 * @Date:2019/11/21
 * @Time:11:31
 */
@Data
public class UserInfo {
    /**机器人标识*/
    private String apiKey;
    /** 用户唯一标识*/
    private String userId;
}
