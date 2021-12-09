package com.example.faculty.services.implementations;

import com.example.faculty.database.dto.user.UserCreateDto;
import com.example.faculty.database.dto.user.UserResponseDto;
import com.example.faculty.database.dto.user.UserUpdateDto;
import com.example.faculty.database.entity.User;
import com.example.faculty.database.mapstruct.mappers.IUserMapper;
import com.example.faculty.database.repository.UserRepository;
import com.example.faculty.services.interfaces.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserResponseDto> getAll() {
        return repository.findAll().stream().map(IUserMapper.MAPPER::userToResponseDto).collect(Collectors.toList());
    }

    @Override
    public Optional<UserResponseDto> get(UUID id) {
        return repository.findById(id).map(IUserMapper.MAPPER::userToResponseDto);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public UserResponseDto create(UserCreateDto dto) {
        User user = IUserMapper.MAPPER.createToUser(dto);
        return IUserMapper.MAPPER.userToResponseDto(repository.save(user));
    }

    @Override
    public UserResponseDto update(UserUpdateDto dto) {
        User user = IUserMapper.MAPPER.updateToUser(dto);
        return IUserMapper.MAPPER.userToResponseDto(repository.save(user));
    }

    @Override
    public User findByEmail(String email) {
        return repository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found: " + email));
    }

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }
}
