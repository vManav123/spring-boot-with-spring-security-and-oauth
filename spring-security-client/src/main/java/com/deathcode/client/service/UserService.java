package com.deathcode.client.service;

import com.deathcode.client.data.entity.User;
import com.deathcode.client.data.entity.VerificationToken;
import com.deathcode.client.data.rest.UserModel;
import com.deathcode.client.repository.UserRepository;
import com.deathcode.client.repository.VerificationTokenRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.deathcode.client.helper.AppUtility.parseUserModel;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;



    public User insert(final User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public VerificationToken saveToken(User user,String token)
    {
        VerificationToken verificationToken = new VerificationToken(user,token);
        return verificationTokenRepository.save(verificationToken);
    }

    @SneakyThrows
    public UserModel get(final long id)
    {
        User user = userRepository.findById(id).orElse(null);
        if(user==null)
            throw new Exception("Invalid User ID : "+id);
        user.setPassword("*********");
        return parseUserModel(user);
    }

    @SneakyThrows
    public List<User> getAll()
    {
        return userRepository.findAll();
    }
}

