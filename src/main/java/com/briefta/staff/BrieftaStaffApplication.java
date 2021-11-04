package com.briefta.staff;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.briefta.staff.configuration.FileStorageProperties;

@SpringBootApplication
@EnableJpaRepositories("com.briefta.staff.repository")
@EnableAutoConfiguration
@Configuration

public class BrieftaStaffApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrieftaStaffApplication.class, args);
	}

	@Bean
	public FileStorageProperties getFileStorage() {
		return new FileStorageProperties();
	}

	@Bean
	public AtomicLong messageCounter() {
		return new AtomicLong(0);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
}
