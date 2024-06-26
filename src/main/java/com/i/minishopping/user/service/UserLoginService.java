package com.i.minishopping.user.service;

import com.i.minishopping.user.bean.UserDTO;
import com.i.minishopping.user.dao.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // Lombok을 사용하여 생성자 주입을 간결하게 처리
public class UserLoginService implements LoginService {

    private final UserDAO userDAO;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean login(String userEmail, String password) {
        UserDTO user = userDAO.getUserByEmail(userEmail);

        if (user != null && passwordEncoder.matches(password, user.getUserPassword())) {
            System.out.println("로그인 성공");
            return true;
        } else {
            System.out.println("로그인 실패");
            return false;
        }
    }
}
