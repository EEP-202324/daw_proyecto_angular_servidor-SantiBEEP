package example.universidad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
class SecurityConfig {
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(request -> request
			.requestMatchers("/universidad/**")
			.hasRole("UNI-OWNER"))
			.httpBasic(Customizer.withDefaults())
			.csrf(csrf -> csrf.disable());
		return http.build();
    }
	
	@Bean
	UserDetailsService testOnlyUsers(PasswordEncoder passwordEncoder) {
		User.UserBuilder users = User.builder();
		UserDetails santi = users
				.username("santi")
				.password(passwordEncoder.encode("abc123"))
				.roles("UNI-OWNER")
				.build();
		UserDetails otro = users
			    .username("otro")
			    .password(passwordEncoder.encode("123abc"))
			    .roles("NON-OWNER")
			    .build();
		return new InMemoryUserDetailsManager(santi, otro);
	}
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
