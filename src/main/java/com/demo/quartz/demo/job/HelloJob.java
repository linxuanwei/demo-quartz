package com.demo.quartz.demo.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author Seven.Lin
 * @date 2020/1/15 11:49
 */
@Slf4j
//@DisallowConcurrentExecution // 禁止并发执行 // @PersistJobDataAfterExecution 表示当正常执行完Job后, JobDataMap中的数据应该被改动, 以被下一次调用时用。 两个注解搭配使用
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("****Hello quartz {} ****", jobExecutionContext.getJobDetail().getKey());
    }
}