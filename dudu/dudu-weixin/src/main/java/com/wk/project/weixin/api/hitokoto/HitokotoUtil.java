package com.wk.project.weixin.api.hitokoto;

import com.thoughtworks.xstream.mapper.Mapper;
import com.wk.project.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Company:qianfeng
 * @Auther:weiMac
 * @Date:2019/11/22
 * @Time:1:40
 */
@Service
public class HitokotoUtil {

    private final String  HITOKOTO_API_URL="https://v1.hitokoto.cn/";

    public  String sendMessage(){
        JSONObject jsonObject= WeixinUtil.httpRequest(HITOKOTO_API_URL,"GET", null);
        String result = (String) jsonObject.get("hitokoto");

        return result;


    }
}
