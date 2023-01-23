package dev.yong2ss.asyncexample.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    @Async("defaultTaskExecutor") //@Async는 private(X)
    public void sendMail() {
        System.out.println("[sendMail] ::" + Thread.currentThread().getName());
    }

    @Async("messageTaskExecutor")
    public void sendMailWithCustomThreadPool() {
        System.out.println("[sendMail] ::" + Thread.currentThread().getName());
    }
}
