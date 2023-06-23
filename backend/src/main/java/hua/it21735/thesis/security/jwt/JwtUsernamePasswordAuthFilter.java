//package hua.it21735.thesis.security.jwt;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import hua.it21735.thesis.security.model.User;
//import io.jsonwebtoken.Jwts;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.crypto.SecretKey;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.Date;
//import java.time.LocalDate;
//
//public class JwtUsernamePasswordAuthFilter extends UsernamePasswordAuthenticationFilter {
//
//
//    private static final Logger logger = LoggerFactory.getLogger(JwtUsernamePasswordAuthFilter.class);
//
//    private final AuthenticationManager authenticationManager;
//    private final JwtConfig jwtConfig;
//    private final SecretKey secretKey;
//
//    @Autowired
//    public JwtUsernamePasswordAuthFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig, SecretKey secretKey) {
//        this.authenticationManager = authenticationManager;
//        this.jwtConfig = jwtConfig;
//        this.secretKey = secretKey;
//    }
//
//    @Override
//    //gets credentials and validates them
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        try {
//
//            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
//
//            Authentication authentication = new UsernamePasswordAuthenticationToken(
//                    user.getUsername(),
//                    user.getPassword()
//            );
//            logger.debug("USER IS = " + user);
//            Authentication auth = authenticationManager.authenticate(authentication);
//            logger.debug("return: " + auth);
//            return auth;
//
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    //creates JWT token and sends it to client
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication
//    authResult) throws IOException, ServletException {
//
//        String token = Jwts.builder()
//                .setSubject(authResult.getName())
//                .claim("authorities", authResult.getAuthorities())
//                .setIssuedAt(Date.valueOf(LocalDate.now()))
//                .setExpiration(Date.valueOf(LocalDate.now().plusWeeks(2)))
//                .signWith(secretKey)
//                .compact();
//
//        response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token);
//        logger.debug("RESPONSE " + response.getHeader("Authorization"));
//
//    }
//}
