package com.bjpowernode;

import com.bjpowernode.task.TaskManager;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableDubbo
@EnableScheduling//启动定时任务
@SpringBootApplication
public class MicrTaskApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(MicrTaskApplication.class, args);

		// TaskManager taskManager = (TaskManager) ctx.getBean("taskManager");
		// taskManager.generateIncomeBack();
	}

}
