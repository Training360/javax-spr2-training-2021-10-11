package employees;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private DataSource dataSource;

    private UserService userService;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("johndoe")
//                .password("$2a$10$Lf4VZCtA4iQerEpP5EMqsOv0lXnZykWzKvd1q68J0FsZ0JnYy5Ymi")
//                .authorities("normal_user");

//        auth
//                .jdbcAuthentication()
//                .usersByUsernameQuery("select nev, jelszo, bekapcsolt from felhasznalok where nev = ?")
//                .authoritiesByUsernameQuery("select szerepkor from szerepkorok where nev = ?")
//                .dataSource(dataSource);

//        auth.userDetailsService(userService);
//
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/api/employees/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .and()
                .httpBasic()
                .and()
                .userDetailsService(userService)
//                .authorizeRequests()
//                .and()
                .csrf().disable()
        ;

    }
}
