package cn.cqut.final_edu_ketangpai.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * @CLASSNAME:MybatisPlusConfig
 * @description:
 * @author: Nonameguy
 * @create: 2020-04-30 02:12
 */
@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig {

	@Bean
	public ISqlInjector sqlInjector() {
		return new LogicSqlInjector();
	}

	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

	/**
	 * 打印 sql
	 */
	@Bean
	public PerformanceInterceptor performanceInterceptor() {
		PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
		//格式化sql语句
		Properties properties = new Properties();
		properties.setProperty("format", "true");
		performanceInterceptor.setProperties(properties);
		return performanceInterceptor;
	}

}
