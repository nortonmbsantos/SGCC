	package br.edu.utfpr.sgcc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import br.edu.utfpr.sgcc.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        		.antMatchers("/login").permitAll()
        		.antMatchers("/register").permitAll()
                .antMatchers("/admin", "/admin/**").hasRole("ADMIN")
                .antMatchers("/user", "/user/**").hasRole("USER")
                .antMatchers("/resident", "/resident/**").hasRole("RESIDENT")
                .and().formLogin().loginPage("/login")
                .successHandler(myAuthenticationSuccessHandler())
                .failureUrl("/?error=true").permitAll()
                .and().logout().permitAll()
                .and().csrf().disable()
                .logout().logoutSuccessUrl("/login");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
    	return new BCryptPasswordEncoder(); 
    }
    
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }
}