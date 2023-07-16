package com.mayikt.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName ScheduledTasks
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
@Component
@Slf4j
public class ScheduledTasks {

    /**
     * 每隔3s时间执行到taskService
     */
//    @Scheduled(fixedRate = 3000)
    @Scheduled(cron = "1/2 * * * * ? ")
    public void taskService() {
        log.info("<<定时任务执行>>" + System.currentTimeMillis());
    }
}
