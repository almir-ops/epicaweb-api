package br.com.epicaweb.domain.Service.impl.User;

import br.com.epicaweb.domain.Service.UserService;
import br.com.epicaweb.domain.model.UserModel;
import br.com.epicaweb.ws.request.JwtRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    public JwtRequest decode(String req) {
        try {

            byte[] decode = Base64.getDecoder().decode(req);
            String decodeStr = new String(decode);
            String[] split = decodeStr.toString().split(":");
            String contrato = split[0];
            String cpfCnpj =  split[1];

            JwtRequest jwtRequest = new JwtRequest( contrato , cpfCnpj);
            return jwtRequest;

        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    // we can omit this part
    public UserModel loadUserByUsername(String name) {
        UserModel u = new UserModel();
        u.setId(1l);
        u.setUsername(name);
        return u;

    }


}
