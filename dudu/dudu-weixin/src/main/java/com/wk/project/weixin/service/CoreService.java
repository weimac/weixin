package com.wk.project.weixin.service;

import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wk.project.weixin.api.accessToken.AccessTokenRedis;
import com.wk.project.weixin.api.accessToken.AccessTokenThread;
import com.wk.project.weixin.api.hitokoto.HitokotoUtil;
import com.wk.project.weixin.api.tuling.TulingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wk.project.weixin.main.MenuManager;
import com.wk.project.weixin.pojo.AccessToken;
import com.wk.project.weixin.util.MessageUtil;
import com.wk.project.weixin.util.WeixinUtil;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import net.sf.json.JSONObject;


import com.wk.project.weixin.bean.resp.Article;
import com.wk.project.weixin.bean.resp.MusicMessage;
import com.wk.project.weixin.bean.resp.NewsMessage;
import com.wk.project.weixin.bean.resp.TextMessage;

@Service
public class CoreService {


	private  static String[] strs={"0d44699bde544431acecdfc79881a028","d5986fee224f44d2bbd79ce822aa1678",
									"8b176435af884e6ea93b795ac6a55d26","564b786b714a488fb5122fc1b1f71ac8",
									"fefd00854aa8420a90d0e901d421dcf2","716fb79c7c43457f94d4b6655b6dbf57",
									"82b89880a73a4e22b4cfeb72b5379edd","cbb6dc788c1a47cda4f44bf4e7035c3f"};

	@Autowired
	private TulingUtil tulingUtil;

	@Autowired
	private HitokotoUtil hitokotoUtil;

	@Autowired
	private AccessTokenRedis accessTokenRedis;

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析 调用消息工具类MessageUtil解析微信发来的xml格式的消息，解析的结果放在HashMap里；
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id） 下面三行代码是： 从HashMap中取出消息中的字段；
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息 组装要返回的文本消息对象；
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			// 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义
			// textMessage.setContent("欢迎访问<a
			// href=\"http://www.baidu.com/index.php?tn=site888_pg\">百度</a>!");
			// 将文本消息对象转换成xml字符串
			respMessage = MessageUtil.textMessageToXml(textMessage);
			/**
			 * 演示了如何接收微信发送的各类型的消息，根据MsgType判断属于哪种类型的消息；
			 */

			// 接收用户发送的文本消息内容
			String content = requestMap.get("Content");

			// 创建图文消息
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setToUserName(fromUserName);
			newsMessage.setFromUserName(toUserName);
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			newsMessage.setFuncFlag(0);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {

//				int i=0;
//				String result=tulingUtil.sendMessage(content,strs[i]);
//				if(result.equals("请求次数超限制!")) {
//					i =i+1;
//					System.out.println("正在执行的机器人" + strs[i]);
//					String result2=tulingUtil.sendMessage(content,strs[i]);
//					if(result2.equals("请求次数超限制!")){
//						i=i+1;
//						System.out.println("正在执行的机器人" + strs[i]);
//						String result3=tulingUtil.sendMessage(content,strs[i]);
//						if(result3.equals("请求次数超限制!")){
//							i=i+1;
//							System.out.println("正在执行的机器人" + strs[i]);
//							String result4=tulingUtil.sendMessage(content,strs[i]);
//							if(result4.equals("请求次数超限制!")){
////								i=4;
////								System.out.println("正在执行的机器人" + strs[i]);
////								String result5=tulingUtil.sendMessage(content,strs[i]);
////								if(result5.equals("请求次数超限制!")){
//									i=0;
//									System.out.println("正在执行的机器人" + strs[i]);
//									String result6=tulingUtil.sendMessage(content,strs[i]);
//									respContent = result6;
////								}else{
////									respContent = result5;
////								}
//							}else{
//								respContent = result4;
//							}
//
//						}else{
//							respContent = result3;
//						}
//					}else{
//						respContent = result2;
//					}
//
//				}else{
//					respContent = result;
//				}
				//System.out.println("accessToken得到的值为:"+ AccessTokenThread.access_token_val);
				//System.out.println("accessToken得到的值为:"+ accessTokenRedis.getAccessTokenVal());

				int i=0;
				String result=tulingUtil.sendMessage(content,strs[0]);
				if (result.equals("请求次数超限制!")){
					for ( int j=i; j < strs.length; j++) {
						result=tulingUtil.sendMessage(content,strs[j]);
						if(!result.equals("请求次数超限制!")) {
							System.out.println(strs[j]);
							respContent=result;
							break;
						}
					}
				}else {
					respContent=result;
				}



				//respContent = "您发送的是文本消息！";
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					
					respContent = "欢迎关注微信公众号";
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");

					if (eventKey.equals("11")) {
						respContent = "抢单项被点击！";

					}else if(eventKey.equals("31")){
						respContent = "联系我们被点击！";
					}else if(eventKey.equals("34")){

						respContent=hitokotoUtil.sendMessage();
						//respContent = "每日一言被点击！";
					}
					else if (eventKey.equals("70")) {

						List<Article> articleList = new ArrayList<Article>();
						
						// 单图文消息
						Article article = new Article();
						article.setTitle("标题");
						article.setDescription("描述内容");
						article.setPicUrl(
								"图片");
						article.setUrl("跳转连接");

						
						articleList.add(article);						
						// 设置图文消息个数
						newsMessage.setArticleCount(articleList.size());
						// 设置图文消息
						newsMessage.setArticles(articleList);
						// 将图文消息对象转换为XML字符串
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
						return respMessage;
					}
					
				}

			}

			// 组装要返回的文本消息对象；
			textMessage.setContent(respContent);
			// 调用消息工具类MessageUtil将要返回的文本消息对象TextMessage转化成xml格式的字符串；
			respMessage = MessageUtil.textMessageToXml(textMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}

}
