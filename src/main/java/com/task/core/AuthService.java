package com.task.core;

import com.task.api.request.SignUpDTO;
import com.task.core.model.Role;
import com.task.core.model.User;
import com.task.db.AuthRepository;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {

    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }


    public void createUser(SignUpDTO signUpDTO) {
        User user = new User();
        user.setEmail(signUpDTO.getEmail());
        String hashedPassword = BCrypt.hashpw(signUpDTO.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        user.setRole(Role.USER);

        authRepository.insertUser(user);
    }
}
