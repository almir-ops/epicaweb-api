package br.com.epicaweb.ws.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
    private String password;
    
    // need default constructor for JSON Parsing
    public JwtRequest() {

    }
    
    public JwtRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    
}
