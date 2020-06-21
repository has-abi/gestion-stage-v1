package com.gestion.stage.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("passe by config");
		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				// http.formLogin();
				.and().authorizeRequests().antMatchers(HttpMethod.PUT, "/gestion-stage-api/user/").permitAll()
				.antMatchers(HttpMethod.GET, "/gestion-stage-api/stage/**", "/gestion-stage-api/sujetForum/**",
						"/gestion-stage-api/commentaire/**", "/gestion-stage-api/organismeAccueil/**",
						"/gestion-stage-api/rapport/**")
				.permitAll()
				.antMatchers(HttpMethod.POST, "/gestion-stage-api/user/**", "/gestion-stage-api/etablissement/**",
						"/gestion-stage-api/ville/**", "/gestion-stage-api/pays/**", "/gestion-stage-api/filiere/**",
						"/gestion-stage-api/departement/**")
				.hasAuthority("ADMIN_ROLE").antMatchers(HttpMethod.PUT, "/gestion-stage-api/stage/**")
				.hasAnyAuthority("ADMIN_ROLE", "ETUDIANT_ROLE", "COORDINATEUR_ROLE")
				.antMatchers(HttpMethod.GET, "/gestion-stage-api/etablissement/**", "/gestion-stage-api/departement/**",
						"/gestion-stage-api/coordnateur/**")
				.hasAuthority("ADMIN_ROLE")
				.antMatchers(HttpMethod.GET, "/gestion-stage-api/user/email/**", "/gestion-stage-api/user/image/**",
						"/gestion-stage-api/stage/**")
				.permitAll().antMatchers("/gestion-stage-api/user/**", "/gestion-stage-api/coordnateur/**")
				.hasAuthority("ADMIN_ROLE").antMatchers(HttpMethod.POST, "/gestion-stage-api/rapport/**")
				.hasAnyAuthority("ADMIN_ROLE", "ETUDIANT_ROle", "COORDINATEUR_ROLE").anyRequest().authenticated().and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
