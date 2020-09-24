package com.musicallcommunity.musicallback;

import com.musicallcommunity.musicallback.config.AppProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2
@EnableConfigurationProperties(AppProperties.class)
public class MusicAllBackApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		return builder.sources(MusicAllBackApplication.class);
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder()
				.sources(MusicAllBackApplication.class)
				.run(args);
	}
}
