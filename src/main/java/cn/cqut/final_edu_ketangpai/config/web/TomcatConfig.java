package cn.cqut.final_edu_ketangpai.config.web;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @CLASSNAME:TomcatConfig
 * @description: 解决Invalid character found in the request target. The valid characters are defined in RFC 7230 and RFC 问题
 * @author: Nonameguy
 * @create: 2020-05-18 21:21
 */
@Configuration
public class TomcatConfig {
	@Bean
	public TomcatServletWebServerFactory webServerFactory() {
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
		factory.addConnectorCustomizers((Connector connector) -> {
			connector.setProperty("relaxedPathChars", "\"<>[\\]^`{|}");
			connector.setProperty("relaxedQueryChars", "\"<>[\\]^`{|}");
		});
		return factory;
	}
}
