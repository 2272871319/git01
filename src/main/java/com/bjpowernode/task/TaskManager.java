package com.bjpowernode.task;

import com.bjpowernode.api.service.IncomeService;
import com.bjpowernode.common.util.HttpClientUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * ClassName:TaskManager
 * Package:com.bjpowernode.task
 * Description:
 * author:王
 */
@Component("taskManager")
public class TaskManager {

    @DubboReference(interfaceClass = IncomeService.class,version = "1.0")
    private IncomeService incomeService;

    /**
     * 定时任务的方法
     * 1.public方法
     * 2.没有参数
     * 3.无返回值
     */

    //@Scheduled(cron = "* * * * * ?")
    public void testCron(){
        int i=1;
        i=i++;
        System.out.println("i的值="+i);
    }

    //每天两点执行生成收益计划  四干掉的
    @Scheduled(cron = "0 0 2 * * ?")
    public void invokeGenerateIncomePlan(){
        incomeService.generatrIncomePlan();
    }

    //每天3点执行收益返还
    @Scheduled(cron = "0 0 3 * * ?")
    public void generateIncomeBack(){
        incomeService.generateIncomeBack();
    }

    //每20分钟调用一次查询接口   查询redis中有无未处理的订单    url: http://localhost:9000/pay/kq/query
    @Value("${KqQuery.url}")
    String url;
    @Scheduled(cron = "0 0/20 * * * ?")
    public void invokeMicrPayKuqiQianQuery(){
        try {
            HttpClientUtils.doGet(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
