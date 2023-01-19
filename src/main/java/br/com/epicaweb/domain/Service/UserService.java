package br.com.epicaweb.domain.Service;

import br.com.epicaweb.domain.model.UserModel;
import br.com.epicaweb.ws.request.JwtRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public JwtRequest decode(String req);

    public UserModel loadUserByUsername(String name);


}
