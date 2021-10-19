package oit.is.team13.work04.dbsample.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class Sample3AuthConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.inMemoryAuthentication().withUser("team13").password(passwordEncoder().encode("p@ss")).roles("USER");

    auth.inMemoryAuthentication().withUser("dev")
        .password("$2y$10$3e7Hs2QZ/p91yJVgP5y/1OC7AC8jfc6YDYDzMGK1XieDlNR2nBGDe").roles("ADMIN");
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.formLogin();
    http.authorizeRequests().antMatchers("/sample4/**").authenticated();
    http.logout().logoutSuccessUrl("/");
    http.csrf().disable();
    http.headers().frameOptions().disable();
  }
}
