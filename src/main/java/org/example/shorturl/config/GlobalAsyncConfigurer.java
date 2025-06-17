package org.example.shorturl.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Slf4j
@Configuration
public class GlobalAsyncConfigurer implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        taskExecutor.setCorePoolSize(10);                  // minimal ishlovchi threadlar soni
        taskExecutor.setMaxPoolSize(60);                   // maksimal threadlar soni
        taskExecutor.setKeepAliveSeconds(30);              // bo‘sh thread qanchaga yashasin
        taskExecutor.setQueueCapacity(100);                // queue'ga nechta task sig‘adi
        taskExecutor.setThreadNamePrefix("my_pool-");      // thread nomlari logda ko‘rinadi

        taskExecutor.initialize();                         // executorni ishga tushurish
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (e, method, params) -> {
            log.error("Error : On Method : {}, Input Parameters : {}", method.getName(), params);
            e.printStackTrace();
        };
    }
}