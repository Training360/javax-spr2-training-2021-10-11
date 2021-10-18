//package employees;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
////    @Autowired
////    private DataSource dataSource;
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth
////                .inMemoryAuthentication()
////                .withUser("johndoe")
////                .password("$2a$10$Lf4VZCtA4iQerEpP5EMqsOv0lXnZykWzKvd1q68J0FsZ0JnYy5Ymi")
////                .authorities("normal_user");
//
////        auth
////                .jdbcAuthentication()
////                .usersByUsernameQuery("select nev, jelszo, bekapcsolt from felhasznalok where nev = ?")
////                .authoritiesByUsernameQuery("select szerepkor from szerepkorok where nev = ?")
////                .dataSource(dataSource);
//
//        auth.userDetailsService(userService);
//
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}
