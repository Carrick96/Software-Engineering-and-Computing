package cn.cqut.final_edu_ketangpai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
@MapperScan("cn.cqut.final_edu_ketangpai.dao")
public class FinalEduKetangpaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalEduKetangpaiApplication.class, args);
	}

}
