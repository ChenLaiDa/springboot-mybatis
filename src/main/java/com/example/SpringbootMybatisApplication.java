package com.example;

import com.example.config.MyConfig;
import com.example.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@MapperScan("com.example.mapper")
@Slf4j
public class SpringbootMybatisApplication {

    public static void main(String[] args) throws UnknownHostException {
        //返回ioc容器
        ConfigurableApplicationContext run = SpringApplication.run(SpringbootMybatisApplication.class, args);
        Environment env = run.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = getString(env.getProperty("server.servlet.context-path"),"");
        log.info("\n----------------------------------------------------------\n\t" +
                "Application Jeecg-Boot is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
                "Swagger文档: \thttp://" + ip + ":" + port + path + "/doc.html\n" +
                "----------------------------------------------------------");



        //查看容器里面的组件
//        String[] names = run.getBeanDefinitionNames();
//        for (String name : names) {
//            System.out.println(name);
//        }


        //从容器中获取组件
//        Student chenchong01 = run.getBean("chenchong", Student.class);
//        Student chenchong02 = run.getBean("chenchong", Student.class);
//        System.out.println("组件: "+(chenchong01 == chenchong02));//true  默认单例的
//
//        MyConfig my = run.getBean(MyConfig.class);
//        System.out.println(my);
//
//        Student chenchong = my.chenchong();
//        Student chenchong1 = my.chenchong();
//        System.out.println(chenchong == chenchong1);

    }

    public static String getString(String s, String defval) {
        if (StringUtils.isEmpty(s)) {
            return (defval);
        }
        return (s.trim());
    }


}
