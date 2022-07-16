package com.jm;

import com.bstek.ureport.console.UReportServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication(scanBasePackages={"com.jm"})
@ImportResource("classpath*:ureport-console-context.xml")
@MapperScan("com.jm.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletRegistrationBean<UReportServlet> buildServlet(){
        return new ServletRegistrationBean<>(new UReportServlet(), "/ureport/*");
    }

}
