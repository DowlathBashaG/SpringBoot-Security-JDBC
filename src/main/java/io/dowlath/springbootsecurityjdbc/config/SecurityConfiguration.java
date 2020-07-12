package io.dowlath.springbootsecurityjdbc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * @Author Dowlath
 * @create 7/12/2020 1:20 PM
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // Default schema (ie standard) provided
    // https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#user-schema

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.jdbcAuthentication()
           // Use Case : 2
           .dataSource(dataSource) ;// semicolon removed after override users and authorities
           // Use Case : 3
           // override users and authorities, it will any kind of scheman will work
           /*.usersByUsernameQuery("select username,password,enabled "
                   + "from users "
                   + "where username = ?")
           .authoritiesByUsernameQuery("select username,authority "
                   + "from authorities "
                   + "where username = ?"); */

         // Use Case : 1
         // with out database scripts and schema we used
         /*  .withDefaultSchema()
           .withUser(
                   User.withUsername("user")
                       .password("pass")
                       .roles("USER")
           )
           .withUser(
                   User.withUsername("admin")
                           .password("pass")
                           .roles("ADMIN")
           );*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER","ADMIN")
                .antMatchers("/").permitAll()
                .and().formLogin();

    }

    // always deal with hashed passwords !!!
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
