package com.sp.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		try {
			auth.inMemoryAuthentication().withUser(User.builder().username("user").password(passwordEncoder().encode("1234")).roles("USER"))
			.withUser(User.builder().username("admin").password(passwordEncoder().encode("1234")).roles("ADMIN")); // 임시로 사용할 유저
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		// .csrf().disable() //csrf 토큰 비활성화 (테스트 시 걸어두는 게 좋음)
		.authorizeRequests() // 인증절차에 대한 설정을 진행
		.antMatchers("/bbs/list").authenticated() // 해당 주소로 들어오면 인증이 필요함 
		.anyRequest().permitAll() //이 3개 주소가 아니라면 권한이 허용된다.
		.and()
		.formLogin()
		.defaultSuccessUrl("/bbs/list")
	    .permitAll();
	}
}
