package com.xiaobao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

/**
 * @author 小暴
 */
@SpringBootApplication
@MapperScan(basePackages = "com.xiaobao.dao")
@ServletComponentScan(basePackages="com.xiaobao.Filter.*")
public class BackstageApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackstageApplication.class, args);
    }
    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory (){
        // 修改内置的 tomcat 容器配置
        TomcatServletWebServerFactory tomcatServlet = new TomcatServletWebServerFactory();
        tomcatServlet .addConnectorCustomizers(
                (TomcatConnectorCustomizer) connector -> connector.setProperty("relaxedQueryChars", "[]{}")
        );
        return tomcatServlet ;
    }
}
