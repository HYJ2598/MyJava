package yjs.tyust.edu.cn.jiewei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("yjs.tyust.edu.cn.jiewei.mapper")
public class JieweiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JieweiApplication.class, args);
    }

}
