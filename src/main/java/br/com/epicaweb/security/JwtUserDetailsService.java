package br.com.epicaweb.security;

import org.springframework.stereotype.Component;

import br.com.epicaweb.domain.model.UserModel;

@Component
public class JwtUserDetailsService {

	
    public UserModel loadUserByUsername(String username) {
        UserModel u = new UserModel();
        u.setUsername(username);
        u.setId(1l);
        return u;
    }
    


    
    
}
