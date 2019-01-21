package com.ecommerce.stores.config;

import com.ecommerce.commons.security.filter.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@ComponentScan("com.ecommerce.commons.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthorizationFilter authorizationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers(SwaggerConfig.SWAGGER_RESOURCES).permitAll()
                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .and()
                    .authorizeRequests()
                    .antMatchers("/api/v1/**").authenticated()
                .and()
                    .addFilterBefore(authorizationFilter, BasicAuthenticationFilter.class)
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
