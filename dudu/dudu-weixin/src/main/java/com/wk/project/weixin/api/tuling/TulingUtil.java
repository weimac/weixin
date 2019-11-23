package com.wk.project.weixin.api.tuling;

import com.wk.project.weixin.api.tuling.bean.InputText;
import com.wk.project.weixin.api.tuling.bean.Perception;
import com.wk.project.weixin.api.tuling.bean.TulingBean;
import com.wk.project.weixin.api.tuling.bean.UserInfo;
import com.wk.project.weixin.util.WeixinUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:图灵机器人
 * @Company:qianfeng
 * @Auther:weiMac
 * @Date:2019/11/21
 * @Time:11:48
 */
@Service
public class TulingUtil {

    private  static  final String TULING_API_URL="http://openapi.tuling123.com/openapi/api/v2";
    //private  static String[] strs={"d5986fee224f44d2bbd79ce822aa1678","fefd00854aa8420a90d0e901d421dcf2","cbb6dc788c1a47cda4f44bf4e7035c3f"};

    /**
     *#############TODO 发送消息 响应消息
     * @Inputparam msg用户发送的文本
     * @Outputparam  result 用户相应的文本
     * @return
     */
    public String sendMessage(String msg,String api){

//        int i=0;
        //生成入参的JSON对象
        JSONObject jsonObject=sendJSONObject(msg,api);
        //第二步 对指定的API地址发送post请求(传入入参的JSON对象)
        JSONObject jsonObject1=WeixinUtil.httpRequest( TULING_API_URL,"POST",jsonObject.toString());

        JSONArray array= (JSONArray) jsonObject1.get("results");

        JSONObject object= (JSONObject) array.get(0);

        JSONObject object2= (JSONObject) object.get("values");

        String result= (String) object2.get("text");

//        if(result.equals("请求次数超限制!")){
//            i=i+1;
//            System.out.println("正在执行的机器人"+strs[i]);
//            return sendMessage(msg);
//        }else{
            return result;
//        }

    }


//    public String send(String msg,String api){
//
//        //生成入参的JSON对象
//        JSONObject jsonObject2=sendJSONObject(msg,api);
//        //第二步 对指定的API地址发送post请求(传入入参的JSON对象)
//        JSONObject jsonObject3=WeixinUtil.httpRequest( TULING_API_URL,"POST",jsonObject2.toString());
//
//        JSONArray array1= (JSONArray) jsonObject3.get("results");
//
//        JSONObject object3= (JSONObject) array1.get(0);
//
//        JSONObject object4= (JSONObject) object3.get("values");
//
//        String result2= (String) object4.get("text");
//
//        return  result2;
//
//    }






    /**#############TODO 生成入参的json对象
     *按规则生成入参的json对象
     * @param msg 发送的文本
     * @param apiKey 机器人api接口
     * @return
     */
    public JSONObject sendJSONObject(String msg,String apiKey){

        //JSON格式数据(入参)
        InputText inputText=new InputText();
        inputText.setText(msg);

        Perception perception=new Perception();
        perception.setInputText(inputText);
        UserInfo userInfo=new UserInfo();
        //图灵机器人的Api接口
        userInfo.setApiKey(apiKey);
        userInfo.setUserId("user152403121");

        TulingBean tulingBean=new TulingBean();
        tulingBean.setPerception(perception);
        tulingBean.setUserInfo(userInfo);

        //javaBean转换成json对象
        JSONObject jsonObject=JSONObject.fromObject(tulingBean);
        //System.out.println("请求数据:"+jsonObject.toString());
        return jsonObject;

    }



//     public static void main(String[] args) {
//
//         String api="http://openapi.tuling123.com/openapi/api/v2";
//
//         //用户输入的文本
//         String msg="哈哈哈";
//         //JSON格式数据(入参)
//         InputText inputText=new InputText();
//         inputText.setText(msg);
//
//         Perception perception=new Perception();
//         perception.setInputText(inputText);
//         UserInfo userInfo=new UserInfo();
//         //图灵机器人的Api接口
//         userInfo.setApiKey("d5986fee224f44d2bbd79ce822aa1678");
//         userInfo.setUserId("user152403121");
//
//         TulingBean tulingBean=new TulingBean();
//         tulingBean.setPerception(perception);
//         tulingBean.setUserInfo(userInfo);
//
//        //javaBean转换成json对象
//         JSONObject jsonObject=JSONObject.fromObject(tulingBean);
//         System.out.println("请求数据:"+jsonObject.toString());
//
//         for(int i=0;i<100;i++) {
//             //第二步 对指定的API地址发送post请求(传入入参的JSON对象)
//             JSONObject jsonObject1 = WeixinUtil.httpRequest(api, "POST", jsonObject.toString());
//
//             System.out.println("返回数据:" + jsonObject1.toString());
//
//             JSONArray array = (JSONArray) jsonObject1.get("results");
//
//             JSONObject object = (JSONObject) array.get(0);
//
//             JSONObject object2 = (JSONObject) object.get("values");
//             String result = (String) object2.get("text");
//             System.out.println("返回的结果:" + result);
//         }
//
//
//    }
}
