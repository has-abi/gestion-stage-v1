package com.gestion.stage.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
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
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v3/api-docs**");
		web.ignoring().antMatchers(HttpMethod.PUT, "/gestion-stage-api/user/image/**");
		web.ignoring().antMatchers(HttpMethod.GET, "/gestion-stage-api/etudiant/confirm/**");
		web.ignoring().antMatchers(HttpMethod.GET, "/gestion-stage-api/mail/username/**");
		web.ignoring().antMatchers(HttpMethod.GET, "/gestion-stage-api/user/confirm/code/**");
		web.ignoring().antMatchers(HttpMethod.POST, "/gestion-stage-api/user/newUser/**");
		web.ignoring().antMatchers(HttpMethod.POST, "/gestion-stage-api/user/checkCode");
		web.ignoring().antMatchers(HttpMethod.POST, "/gestion-stage-api/user/email");
		web.ignoring().antMatchers(HttpMethod.POST, "/gestion-stage-api/user/checkSecurityQuestion");
		web.ignoring().antMatchers(HttpMethod.POST, "/gestion-stage-api/user/updatePassword");
		web.ignoring().antMatchers(HttpMethod.POST, "/gestion-stage-api/mail/send");
		web.ignoring().antMatchers(HttpMethod.GET, "/files/**");
		web.ignoring().antMatchers(HttpMethod.GET, "/file/display/**");
		web.ignoring().antMatchers(HttpMethod.GET, "/gestion-stage-api/stage/coordinateur/**");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests().antMatchers(HttpMethod.PUT).permitAll()
				.antMatchers(HttpMethod.GET, "/gestion-stage-api/stage/**", "/gestion-stage-api/sujetForum/**",
						"/gestion-stage-api/commentaire/**", "/gestion-stage-api/organismeAccueil/**",
						"/gestion-stage-api/rapport/**", "/gestion-stage-api/user/**")
				.permitAll()
				.antMatchers(HttpMethod.POST, "/gestion-stage-api/coordinateur/**", "/gestion-stage-api/etudiant/**")
				.hasAnyAuthority("ADMIN_ROLE").antMatchers(HttpMethod.GET, "/pv/coordinateur/**")
				.hasAnyAuthority("COORDINATEUR_ROLE")
				.antMatchers(HttpMethod.POST, "/gestion-stage-api/user/checkPassword", "/login/**").permitAll()
				.antMatchers(HttpMethod.POST, "/gestion-stage-api/user/**", "/gestion-stage-api/etablissement/**",
						"/gestion-stage-api/ville/**", "/gestion-stage-api/pays/**", "/gestion-stage-api/filiere/**",
						"/gestion-stage-api/departement/**")
				.hasAuthority("ADMIN_ROLE")
				.antMatchers(HttpMethod.GET, "/gestion-stage-api/departement/**", "/gestion-stage-api/coordnateur/**")
				.hasAuthority("ADMIN_ROLE")
				.antMatchers(HttpMethod.GET, "/gestion-stage-api/user/email/**", "/gestion-stage-api/user/image/**",
						"/gestion-stage-api/stage/**", "/gestion-stage-api/etablissement/**")
				.permitAll().antMatchers("/gestion-stage-api/user/**", "/gestion-stage-api/coordnateur/**")
				.hasAuthority("ADMIN_ROLE").antMatchers(HttpMethod.POST, "/gestion-stage-api/rapport/**")
				.hasAnyAuthority("ADMIN_ROLE", "ETUDIANT_ROLE", "COORDINATEUR_ROLE").anyRequest().authenticated().and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
