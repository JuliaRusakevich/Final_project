package com.gmail.juliarusakevich.user.service;

import com.gmail.juliarusakevich.user.repository.IUserRepository;
import com.gmail.juliarusakevich.user.repository.model.User;
import com.gmail.juliarusakevich.user.service.api.IUserCreateService;
import com.gmail.juliarusakevich.user.service.dto.UserCreateUpdateDTO;
import com.gmail.juliarusakevich.user.service.dto.UserReadDTO;
import com.gmail.juliarusakevich.user.service.dto.UserRegistration;
import com.gmail.juliarusakevich.user.service.mapper.UserMapper;
import com.gmail.juliarusakevich.user.validator.api.IValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserCreateService, UserDetailsService {

    private final IUserRepository repository;
    private final UserMapper userMapper;
    private final IValidator<UserCreateUpdateDTO> validator;

    public UserService(IUserRepository repository,
                       UserMapper userMapper,
                       IValidator<UserCreateUpdateDTO> validator) {
        this.repository = repository;
        this.userMapper = userMapper;
        this.validator = validator;
    }

    @Override
    public User addUser(UserCreateUpdateDTO dto) {
        validator.isValid(dto);
        var userCreate = userMapper.toEntity(dto);
        return this.repository.save(userCreate);
    }

    @Override
    //@PostFilter("filterObject.role.name().equals('ADMIN')") дополнительная фильтрация
    public Page<UserReadDTO> findAll(Pageable pageable) {
        return this.repository.findAll(pageable)
                .map(userMapper::toDTO);

    }

    @Override
    public Optional<UserReadDTO> findById(UUID uuid) {
        return this.repository.findById(uuid)
                .map(userMapper::toDTO);
    }

    @Override
    public User updateUserInfo(UUID uuid, LocalDateTime dtUpdate, UserCreateUpdateDTO dto) {
        if (uuid == null) {
            throw new IllegalArgumentException("Это поле не может быть null.");
        }

        var user = this.repository
                .findById(uuid)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("Пользователь не найден.");
                });
//ИСПРАВИТЬ НА !
        if (user.getDtUpdate().equals(dtUpdate)) {
            throw new OptimisticLockException("Данные уже были обновлены.");
        }

        user.setUsername(dto.getUsername());
        user.setNick(dto.getNick());
        user.setRole(dto.getRole());
        user.setStatus(dto.getStatus());
        user.setPassword(dto.getPassword());

        return this.repository.save(user);
    }

    @Override
    public User registerUser(UserRegistration dto) {
        var user = userMapper.toEntityRegister(dto);
        return this.repository.save(user);
    }

    @Override
    public UserDetails getUser() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var result = this.repository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));

        System.out.println("Username: " + result.getUsername());
        System.out.println("ROLE: " + result.getAuthorities());
        return result;
    }


}
