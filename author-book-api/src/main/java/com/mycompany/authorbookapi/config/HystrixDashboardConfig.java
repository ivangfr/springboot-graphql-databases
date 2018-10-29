package com.mycompany.authorbookapi.config;

import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrixDashboard
public class HystrixDashboardConfig {
}
