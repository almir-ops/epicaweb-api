package br.com.epicaweb.config;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.epicaweb.security.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.event.LoggerListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.epicaweb.security.repository.ContratoRepository;
import br.com.epicaweb.security.JwtAuthenticationEntryPoint;
import br.com.epicaweb.security.filter.JwtRequestFilter;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = { ContratoRepository.class, PermissaoRepository.class}, enableDefaultTransactions = false)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private OpenLdapAuthenticationProvider openLdapAuthenticationProvider;
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private JwtRequestFilter jwtRequestFilter;

	@Value("${server.domain}")
	private String domain;

	@Value("${server.url}")
	private String url;

	
	WebSecurityConfig(OpenLdapAuthenticationProvider openLdapAuthenticationProvider, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtRequestFilter jwtRequestFilter) {
		this.openLdapAuthenticationProvider = openLdapAuthenticationProvider;
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.jwtRequestFilter = jwtRequestFilter;
	}


	@Bean
	public ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
		ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider(domain, url);
		provider.setConvertSubErrorCodesToExceptions(true);
		provider.setUseAuthenticationRequestCredentials(true);
		return provider;
	}

	@Bean
	public LoggerListener loggerListener() {
		return new LoggerListener();
	}

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(activeDirectoryLdapAuthenticationProvider());
//		auth.authenticationProvider(openLdapAuthenticationProvider);

        
        if (auth.isConfigured()) {        	
		    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();			
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		//UserDetails userDetails = new User(authentication.getName()  ,  authentication.getCredentials().toString()  ,  grantedAuthorities);						
		//Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, authentication.getCredentials().toString(), grantedAuthorities);
        }
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * auth.authenticationProvider(activeDirectoryLdapAuthenticationProvider()).
	 * userDetailsService(userDetailsService()); }
	 * 
	 * 
	 * @Override public void configure(WebSecurity web) throws Exception {// Allow
	 * Login API to be accessed without authentication
	 * web.ignoring().antMatchers("/login").antMatchers(HttpMethod.OPTIONS, "/**");
	 * // Request type options should be allowed. }
	 */

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and().csrf().disable()
				.headers().frameOptions().deny().and()
				.authorizeRequests()
				.antMatchers("/api/auth").permitAll()
				.anyRequest().authenticated().and()

				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		/*
		 * .antMatchers(HttpMethod.POST, "/").permitAll() .antMatchers(HttpMethod.PUT,
		 * "/").permitAll() .antMatchers(HttpMethod.OPTIONS, "/").permitAll()
		 * .antMatchers(HttpMethod.OPTIONS, "/").permitAll()
		 * .antMatchers(HttpMethod.GET, "/").permitAll()
		 */
//				.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "http://localhost:4200")).and()
		/*
		 * .and()
		 * 
		 * // filtra requisições de login .addFilterBefore(new JWTLoginFilter("/login",
		 * authenticationManager()), UsernamePasswordAuthenticationFilter.class)
		 * 
		 * // filtra outras requisições para verificar a presença do JWT no header
		 * .addFilterBefore(new JWTAuthenticationFilter(),
		 * UsernamePasswordAuthenticationFilter.class);
		 * 
		 */
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**",
				"/swagger-resources/**");
	}	

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(
				Arrays.asList("https://localhost:4200", "http://localhost:4200", "http://localhost:8080", "*"));
		configuration.setAllowedMethods(
				Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
