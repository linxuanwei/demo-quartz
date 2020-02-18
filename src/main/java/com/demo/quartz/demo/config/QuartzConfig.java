package com.demo.quartz.demo.config;

import com.demo.quartz.demo.AutowiringSpringBeanJobFactory;
import com.demo.quartz.demo.job.HelloJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Objects;

/**
 * @author Seven.Lin
 * @date 2020/1/15 14:18
 */
@Configuration
public class QuartzConfig {

    private String group = "demo";

    // 创建job对象
    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setGroup(group);
        jobDetail.setName("HelloJob");
        jobDetail.setJobClass(HelloJob.class);
        return jobDetail;
    }

    // 创建trigger对象
    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setGroup(group);
        trigger.setName("HelloTrigger");
        trigger.setJobDetail(Objects.requireNonNull(jobDetailFactoryBean().getObject()));
        trigger.setCronExpression("0/2 * * * * ?"); // 每2s执行一次
        return trigger;
    }

    // 创建scheduler对象
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setSchedulerName(group);
        scheduler.setTriggers((cronTriggerFactoryBean().getObject()));
        scheduler.setJobFactory(jobFactory());
        return scheduler;
    }

    @Bean
    public AutowiringSpringBeanJobFactory jobFactory() {
        return new AutowiringSpringBeanJobFactory();
    }

}
