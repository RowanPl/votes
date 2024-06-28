package org.example.votes.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService authManager(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager man = new InMemoryUserDetailsManager();


        UserDetails u1 = User.withUsername("Karel")
                .password(passwordEncoder
                        .encode("Appel"))
                .roles("USER")
                .build();
        man.createUser(u1);

        UserDetails u2 = User.withUsername("Freek")
                .password(passwordEncoder
                        .encode("Appel"))
                .roles("USER")
                .build();
        man.createUser(u2);

        UserDetails u3 = User.withUsername("Ans")
                .password(passwordEncoder
                        .encode("Peer"))
                .roles("ADMIN")
                .build();
        man.createUser(u3);






        return man;
    }


    @Bean
    protected SecurityFilterChain filter(HttpSecurity http) throws Exception {

        http
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(HttpMethod.GET, "/votes").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/votes").authenticated()
                                .requestMatchers(HttpMethod.GET, "/votes/{username}").authenticated()
                                .anyRequest().denyAll()

                ).sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();

    }


}
