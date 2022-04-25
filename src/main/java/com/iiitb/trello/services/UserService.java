package com.iiitb.trello.services;

import com.iiitb.trello.model.entities.UserEntity;
import com.iiitb.trello.repo.UserRepository;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> addNewUser(UserEntity userEntity) {

        String password = userEntity.getPassword();

        try {
            String hashed_password = getHashedPassword(helper(password));

            userEntity.setPassword(hashed_password);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e);
        }
        userEntity.setActive(true);
        userEntity.setCreatedOn(Timestamp.from(Instant.now()));
        userRepository.save(userEntity);
        return userRepository.findById(userEntity.getId());
    }

    public static byte[] helper(String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(s.getBytes(StandardCharsets.UTF_8));
    }

    public static String getHashedPassword(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);

        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
}
