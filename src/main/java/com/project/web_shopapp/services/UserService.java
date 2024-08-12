package com.project.web_shopapp.services;

import com.project.web_shopapp.dtos.UserDTO;
import com.project.web_shopapp.exceptions.DataNotFoundException;
import com.project.web_shopapp.models.Role;
import com.project.web_shopapp.models.User;
import com.project.web_shopapp.repositories.RoleRepository;
import com.project.web_shopapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Override
    public User createUser(UserDTO userDTO) throws DataNotFoundException {
        String phoneNumber = userDTO.getPhoneNumber();
        // kiem tra xem sdt da ton tai hay chx
        if(userRepository.existsByPhoneNumber(phoneNumber)){
            throw new DataIntegrityViolationException("Phone number already exists");
        }
        // convert from userDTO => user
        User newUser = User.builder()
                .fullName(userDTO.getFullName())
                .password(userDTO.getPassword())
                .phoneNumber(userDTO.getPhoneNumber())
                .address(userDTO.getAddress())
                .dateOfBirth(userDTO.getDateOfBirth())
                .faceBookAccountId(userDTO.getFacebookAccountId())
                .googleAccountId(userDTO.getGoogleAccountId())
                .build();
        Role role = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(() -> new DataNotFoundException("Role not found"));
        newUser.setRole(role);
        // kiem tra neu co accountId, khong yeu cau password
        if (userDTO.getFacebookAccountId() == 0 && userDTO.getGoogleAccountId() == 0){
            String password = userDTO.getPassword();
            //String encodedPassword = passwordEncoder.encode(password);
            // ở phần spring security
           // newUser.setPassword(encodedPassword);
        }
        return userRepository.save(newUser);
    }

    @Override
    public String login(String phoneNumber, String password) {
        // đoạn này liên quan đến spring  security, sẽ làm ở phần liên quan đên security
        return null;
    }
}
