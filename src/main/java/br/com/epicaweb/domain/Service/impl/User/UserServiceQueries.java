package br.com.epicaweb.domain.Service.impl.User;

import br.com.epicaweb.domain.Service.UserService;
import br.com.epicaweb.domain.model.UserModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public abstract class UserServiceQueries  implements UserService {

    public UserModel loadUserByUsername(String name) {
        return null;
    }
}
