package com.hatefulbug.onlineshop.service.impl;

import com.hatefulbug.onlineshop.dto.UserDto;
import com.hatefulbug.onlineshop.exception.AlreadyExistsException;
import com.hatefulbug.onlineshop.exception.ResourceNotFoundException;
import com.hatefulbug.onlineshop.model.Role;
import com.hatefulbug.onlineshop.model.User;
import com.hatefulbug.onlineshop.repository.RoleRepository;
import com.hatefulbug.onlineshop.repository.UserRepository;
import com.hatefulbug.onlineshop.request.CreateUserRequest;
import com.hatefulbug.onlineshop.request.UserUpdateRequest;
import com.hatefulbug.onlineshop.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User createUser(CreateUserRequest request) {
        Optional<User> findUser = userRepository.findByEmail(request.getEmail());
        if (findUser.isPresent()) {
            throw new AlreadyExistsException("User already exists");
        }
        Role role = roleRepository.findById(request.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .address(request.getAddress())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserUpdateRequest request, int userId) {
        return userRepository.findById(userId).map(existingUser -> {
            existingUser.setName(request.getName());
            existingUser.setEmail(request.getEmail());
            existingUser.setAddress(request.getAddress());
            existingUser.setPhone(request.getPhone());
            existingUser.setPassword(passwordEncoder.encode(request.getPassword()));
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.findById(userId).ifPresentOrElse(userRepository::delete, () -> {
            throw new ResourceNotFoundException("User not found!");
        });
    }

    @Override
    public UserDto convertUserToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
