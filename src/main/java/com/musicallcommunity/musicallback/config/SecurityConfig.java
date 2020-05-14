package com.musicallcommunity.musicallback.config;

import com.musicallcommunity.musicallback.config.AppProperties;
import com.musicallcommunity.musicallback.security.CustomAuthenticationProvider;
import com.musicallcommunity.musicallback.security.CustomUserDetailsService;
import com.musicallcommunity.musicallback.security.RestAuthenticationEntryPoint;
import com.musicallcommunity.musicallback.security.TokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Objects;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AppProperties appProperties;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private RestAuthenticationEntryPoint unauthorizedHandler;

    public SecurityConfig(AppProperties appProperties) {
        super();
        this.appProperties = appProperties;
    }

    @Bean
    public TokenAuthenticationFilter jwtFilter(CustomUserDetailsService customUserDetailsService) {
        return new TokenAuthenticationFilter(customUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        final CustomAuthenticationProvider authProvider = new CustomAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin();
        http.csrf().ignoringAntMatchers("/h2-console/**", appProperties.getH2().getPath()+"/**");
        http
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable()
                .formLogin()
                .disable()
                .httpBasic()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/",
                        "/error",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                .permitAll()
                .antMatchers("/api/auth/**", "/h2-console/**", appProperties.getH2().getPath()+"/**", "/webjars/**", "/api/password/**")
                .permitAll()
                //.antMatchers("/api/auth/signin").permitAll()
                //.antMatchers("/api/translate").permitAll()
                //.antMatchers("/api/admin/users/**").hasAuthority("WRITE_PRIVILEGE")
                //.antMatchers("/api/user/profile/**").hasAuthority("READ_PRIVILEGE")
                .anyRequest()
                .authenticated();

        // Add our custom Token based authentication filter
        http.addFilterBefore(jwtFilter(customUserDetailsService), UsernamePasswordAuthenticationFilter.class);
    }

}
