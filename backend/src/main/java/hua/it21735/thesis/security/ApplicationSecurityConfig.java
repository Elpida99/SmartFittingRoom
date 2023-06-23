//package hua.it21735.thesis.security;
//
//import hua.it21735.thesis.security.jwt.JwtConfig;
//import hua.it21735.thesis.security.jwt.JwtTokenVerifier;
//import hua.it21735.thesis.security.jwt.JwtUsernamePasswordAuthFilter;
//import hua.it21735.thesis.security.model.Permission;
//import hua.it21735.thesis.security.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import javax.crypto.SecretKey;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
//
//    private final PasswordEncoder passwordEncoder;
//    private final UserService userService;
//    private final JwtConfig jwtConfig;
//    private final SecretKey secretKey;
//
//    @Autowired
//    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, UserService userService, JwtConfig jwtConfig, SecretKey secretKey) {
//        this.passwordEncoder = passwordEncoder;
//        this.userService = userService;
//        this.jwtConfig = jwtConfig;
//        this.secretKey = secretKey;
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()//.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//           //     .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilter(new JwtUsernamePasswordAuthFilter(authenticationManager(), jwtConfig, secretKey))
//                .addFilterAfter(new JwtTokenVerifier(jwtConfig, secretKey), JwtUsernamePasswordAuthFilter.class)
//                .authorizeRequests()
//                .antMatchers("/sign-up").permitAll()
//                .antMatchers("/users/**").hasAuthority(Permission.DELETE.getPermission())
//                .anyRequest()
//                .authenticated();
//
//    }
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder);
//        provider.setUserDetailsService(userService);
//
//        return provider;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//}
//
