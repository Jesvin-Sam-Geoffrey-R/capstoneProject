package com.wecp.medicalequipmentandtrackingsystem.config;

import com.wecp.medicalequipmentandtrackingsystem.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final JwtRequestFilter jwtRequestFilter;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService,
                          JwtRequestFilter jwtRequestFilter,
                          PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/user/register", "/api/user/login","/api/user/users").permitAll()
                .antMatchers(HttpMethod.POST, "/api/hospital/create").hasAuthority("HOSPITAL")
                .antMatchers(HttpMethod.POST, "/api/hospital/equipment").hasAuthority("HOSPITAL")
                .antMatchers(HttpMethod.POST, "/api/hospital/maintenance/schedule").hasAuthority("HOSPITAL")
                .antMatchers(HttpMethod.POST, "/api/hospital/order").hasAnyAuthority("HOSPITAL") // Repeat for accounts
                .antMatchers(HttpMethod.GET, "/api/hospitals").hasAuthority("HOSPITAL")
                .antMatchers(HttpMethod.GET, "/api/hospital/equipment/{hospitalId}").hasAuthority("HOSPITAL")
                .antMatchers(HttpMethod.GET, "/api/technician/maintenance").hasAuthority("TECHNICIAN")
                .antMatchers(HttpMethod.GET, "/api/supplier/orders").hasAuthority("SUPPLIER")
                .antMatchers(HttpMethod.PUT, "/api/technician/maintenance/update/{maintenanceId}").hasAuthority("TECHNICIAN")
                .antMatchers(HttpMethod.PUT, "/api/supplier/order/update/{orderId}").hasAuthority("SUPPLIER")
                .antMatchers(HttpMethod.DELETE, "/api/maintainence/{id}").hasAuthority("TECHNICIAN")
                .antMatchers(HttpMethod.DELETE, "/api/orders/{id}").hasAuthority("SUPPLIER")

                

                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

