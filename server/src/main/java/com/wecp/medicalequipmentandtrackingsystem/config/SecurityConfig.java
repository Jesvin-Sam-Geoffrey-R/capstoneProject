
package com.wecp.medicalequipmentandtrackingsystem.config;
 
import com.wecp.medicalequipmentandtrackingsystem.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig{
// extends WebSecurityConfigurerAdapter {
    // @Autowired
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
 
 
    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    // }
 
    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     http.cors().and().csrf().disable()
    //             .authorizeRequests()
    //             .antMatchers("/api/user/register", "/api/user/login").permitAll()
    //             .antMatchers(HttpMethod.POST, "/api/hospital/create").hasAuthority("HOSPITAL")
    //             .antMatchers(HttpMethod.POST, "/api/hospital/equipment").hasAuthority("HOSPITAL")
    //             .antMatchers(HttpMethod.POST, "/api/hospital/maintenance/schedule").hasAuthority("HOSPITAL")
    //             .antMatchers(HttpMethod.POST, "/api/hospital/order").hasAnyAuthority("HOSPITAL") // Repeat for accounts
    //             .antMatchers(HttpMethod.GET, "/api/hospitals").hasAuthority("HOSPITAL")
    //             .antMatchers(HttpMethod.GET, "/api/hospital/equipment/{hospitalId}").hasAuthority("HOSPITAL")
    //             .antMatchers(HttpMethod.GET, "/api/technician/maintenance").hasAuthority("TECHNICIAN")
    //             .antMatchers(HttpMethod.GET, "/api/supplier/orders").hasAuthority("SUPPLIER")
    //             .antMatchers(HttpMethod.PUT, "/api/technician/maintenance/update/{maintenanceId}").hasAuthority("TECHNICIAN")
    //             .antMatchers(HttpMethod.PUT, "/api/supplier/order/update/{orderId}").hasAuthority("SUPPLIER")
    //             .anyRequest().authenticated()
    //             .and()
    //             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
 
    //     http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    // }
 
    // @Bean
    // @Override
    // public AuthenticationManager authenticationManagerBean() throws Exception {
    //     return super.authenticationManagerBean();
    // }
 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
              return http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/user/register", "/api/user/login","/api/user/users").permitAll()
                .antMatchers(HttpMethod.POST, "/api/hospital/create").hasAuthority("HOSPITAL")
                .antMatchers(HttpMethod.POST, "/api/hospital/equipment").hasAuthority("HOSPITAL")
                .antMatchers(HttpMethod.POST, "/api/hospital/maintenance/schedule").hasAuthority("HOSPITAL")
                .antMatchers(HttpMethod.POST, "/api/hospital/order").hasAnyAuthority("HOSPITAL") // Repeat for accounts
                .antMatchers(HttpMethod.GET, "/api/hospitals").hasAuthority("HOSPITAL")
                .antMatchers(HttpMethod.GET, "/api/hospital/equipment/{hospitalId}").hasAuthority("HOSPITAL")
                .antMatchers(HttpMethod.GET, "/api/technician/maintenance").hasAnyAuthority("TECHNICIAN", "HOSPITAL")
                .antMatchers(HttpMethod.GET, "/api/supplier/orders").hasAnyAuthority("SUPPLIER", "HOSPITAL")
                .antMatchers(HttpMethod.PUT, "/api/technician/maintenance/update/{maintenanceId}").hasAnyAuthority("TECHNICIAN", "HOSPITAL")
                .antMatchers(HttpMethod.PUT, "/api/supplier/order/update/{orderId}").hasAnyAuthority("SUPPLIER", "HOSPITAL")
                .antMatchers(HttpMethod.DELETE, "/api/maintainence/{id}").hasAnyAuthority("TECHNICIAN", "HOSPITAL")
                .antMatchers(HttpMethod.DELETE, "/api/orders/{id}").hasAnyAuthority("SUPPLIER", "HOSPITAL")
                // .antMatchers(HttpMethod.DELETE, "/api/orders/{id}").hasAnyAuthority("HOSPITAL", "HOSPITAL")


                

                .anyRequest().authenticated()
                .and()
                    .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class).build();
    }
 
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setPasswordEncoder(passwordEncoder);
        dao.setUserDetailsService(userDetailsService);
        return dao;
    }
 
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class).
        userDetailsService(userDetailsService).passwordEncoder(passwordEncoder).
        and().build();
    }
}