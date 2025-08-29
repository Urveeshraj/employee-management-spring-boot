package com.urveeshproject.employee.component;

import com.urveeshproject.employee.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupWebhookRunner implements ApplicationRunner {

    @Autowired
    private WebhookService webhookService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Application started - initiating webhook flow...");

        // Add a small delay to ensure the application is fully started
        Thread.sleep(2000);

        // Start the webhook flow
        webhookService.processWebhookFlow();
    }
}
