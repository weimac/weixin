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
public class TulingBean {

    private int reqType=0; //输入类型:0-文本(默认)、1-图片、2-音频

    private Perception perception;

    private UserInfo userInfo;
}
