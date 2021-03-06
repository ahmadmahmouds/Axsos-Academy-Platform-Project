package com.axsosplatform.axsosplatform.services;

import com.axsosplatform.axsosplatform.models.User;
import com.axsosplatform.axsosplatform.repository.UserRepository;
import com.axsosplatform.axsosplatform.repository.RoleRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private  UserRepository userRepository;
    private RoleRepository roleRepository;
    private  BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SendEmailService sendEmailService;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // register user and hash their password
    public User registerUser(User user) {
        user.setPassword(user.genaratePassword());
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }

    // find user by email
    public User findByEmail(String email) {

        return userRepository.findByUsername(email);
    }

    public List<User> findAllUser(){
        return userRepository.findAll();
    }



    public void saveWithUserRole(User user) {
        System.out.println("save user in database ");
        String password =user.genaratePassword();
        System.out.println("the password is : "+password);
        sendEmailService.sendEmail(user.getUsername(),"Your email is: "+user.getUsername()+"Your password is:  "+password , "Password");
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRoles(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }

    // 2
    public void saveUserWithAdminRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
        userRepository.save(user);
    }

    // 3
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
