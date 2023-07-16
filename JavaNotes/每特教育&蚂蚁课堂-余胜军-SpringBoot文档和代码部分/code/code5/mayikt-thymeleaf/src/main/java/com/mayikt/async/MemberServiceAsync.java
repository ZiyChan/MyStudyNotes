package com.mayikt.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @ClassName MemberServiceAsync
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
@Component
@Slf4j
public class MemberServiceAsync {
    @Async("taskExecutor")
    public String sms() {
        log.info(">02<");
        try {
            log.info(">正在发送短信..<");
            Thread.sleep(3000);
        } catch (Exception e) {

        }
        log.info(">03<");
        return "短信发送完成!";
    }
    /**
     * 线程池
     */
}
