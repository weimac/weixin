package com.wk.project.weixin.api.accessToken;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @Description:
 * @Company:qianfeng
 * @Auther:weiMac
 * @Date:2019/11/22
 * @Time:17:44
 */
@WebListener
public class AssessTokenListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("....项目启动了....");
        //new AccessTokenThread().start();
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("项目关闭了....");
    }

}
