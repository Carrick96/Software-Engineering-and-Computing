package cn.cqut.final_edu_ketangpai.config.web;

import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @CLASSNAME:MvcConfig
 * @description: 开启Mvc,自动注入spring容器。 WebMvcConfigurerAdapter：配置视图解析器
 *  当一个类实现了这个接口（ApplicationContextAware）之后，这个类就可以方便获得ApplicationContext中的所有bean
 * @author: Nonameguy
 * @create: 2020-05-02 13:24
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
public class MvcConfig implements WebMvcConfigurer, ApplicationContextAware {
	// Spring容器
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 定义默认的请求处理器
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	/**
	 * 创建viewResolver
	 *
	 * @return
	 */
	@Bean(name = "viewResolver")
	public ViewResolver createViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		// 设置Spring 容器
		viewResolver.setApplicationContext(this.applicationContext);
		// 取消缓存
		viewResolver.setCache(false);
		// 设置解析的前缀
		viewResolver.setPrefix("/WEB-INF/html/");
		// 设置试图解析的后缀
		viewResolver.setSuffix(".html");
		return viewResolver;
	}
	@Value("${kaptcha.border}")
	private String border;

	@Value("${kaptcha.textproducer.font.color}")
	private String fcolor;

	@Value("${kaptcha.image.width}")
	private String width;

	@Value("${kaptcha.textproducer.char.string}")
	private String cString;

	@Value("${kaptcha.image.height}")
	private String height;

	@Value("${kaptcha.textproducer.font.size}")
	private String fsize;

	@Value("${kaptcha.noise.color}")
	private String nColor;

	@Value("${kaptcha.textproducer.char.length}")
	private String clength;

	@Value("${kaptcha.textproducer.font.names}")
	private String fnames;
	/**
	 * 文件上传解析器
	 *
	 * @return
	 */
	/*@Bean(name = "multipartResolver")
	public CommonsMultipartResolver createMultipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("utf-8");
		// 1024 * 1024 * 20 = 20M
		multipartResolver.setMaxUploadSize(20971520);
		multipartResolver.setMaxInMemorySize(20971520);
		return multipartResolver;
	}*/
	@Bean
	public ServletRegistrationBean<KaptchaServlet> servletRegistrationBean() {
		ServletRegistrationBean<KaptchaServlet> servlet =
				new ServletRegistrationBean<>
				(new KaptchaServlet(), "/Kaptcha");
		// 无边框
		servlet.addInitParameter("kaptcha.border", border);
		// 字体颜色
		servlet.addInitParameter("kaptcha.textproducer.font.color", fcolor);
		// 图片宽度
		servlet.addInitParameter("kaptcha.image.width", width);
		// 使用哪些字符生成验证码
		servlet.addInitParameter("kaptcha.textproducer.char.string", cString);
		// 图片高度
		servlet.addInitParameter("kaptcha.image.height", height);
		// 字体大小
		servlet.addInitParameter("kaptcha.textproducer.font.size", fsize);
		// 干扰线的颜色
		servlet.addInitParameter("kaptcha.noise.color", nColor);
		// 字符个数
		servlet.addInitParameter("kaptcha.textproducer.char.length", clength);
		// 字体
		servlet.addInitParameter("kaptcha.textproducer.font.names", fnames);
		return servlet;
	}
}
