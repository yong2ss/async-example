package dev.yong2ss.asyncexample.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncService {

    private final EmailService emailService;

    /*
    Async
    - Spring에서는 Bean을 proxy 객체로 wrapping
        - Bean을 사용해야 한다
    - 비동기로 동작할 수 있도록 SubThread에게 위임
        [asyncCall_1] :: http-nio-8080-exec-1
        [sendMail] ::defaultTaskExecutor-1
        [sendMail] ::messageTaskExecutor-1
     */
    public void asyncCall_1() {
        System.out.println("[asyncCall_1] :: " + Thread.currentThread().getName());
        emailService.sendMail();
        emailService.sendMailWithCustomThreadPool();
    }

    /*
    Async fail
        [asyncCall_2] :: http-nio-8080-exec-2
        [sendMail] ::http-nio-8080-exec-2
        [sendMail] ::http-nio-8080-exec-2
     */
    public void asyncCall_2() {
        System.out.println("[asyncCall_2] :: " + Thread.currentThread().getName());
        EmailService emailService = new EmailService();
        emailService.sendMail();
        emailService.sendMailWithCustomThreadPool();
    }

    /*
    Async fail
    - AsyncService Bean내부의 @async를 호출해도 Proxy로 래핑된 Bean이 아니라서 동작하지 않는다.
        [asyncCall_3] :: http-nio-8080-exec-3
        [sendMail] ::http-nio-8080-exec-3
     */
    public void asyncCall_3() {
        System.out.println("[asyncCall_3] :: " + Thread.currentThread().getName());
        sendMail();
    }

    @Async
    public void sendMail() {
        System.out.println("[sendMail] ::" + Thread.currentThread().getName());
    }
}
