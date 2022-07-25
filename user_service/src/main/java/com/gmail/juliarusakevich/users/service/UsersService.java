package com.gmail.juliarusakevich.users.service;

import com.gmail.juliarusakevich.users.dto.CustomUserDetails;
import com.gmail.juliarusakevich.users.dto.UserReadDTO;
import com.gmail.juliarusakevich.users.entity.User;

import com.gmail.juliarusakevich.users.mapper.UserMapper;
import com.gmail.juliarusakevich.users.repository.UserEntityRepository;

import com.gmail.juliarusakevich.users.service.api.IUserService;
import com.gmail.juliarusakevich.users.validator.api.IValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
public class UsersService implements IUserService, UserDetailsService {

    private final UserEntityRepository repository;
    private final UserMapper mapper;
    private final IValidator<User> validator;

    public UsersService(UserEntityRepository userRepository,
                        UserMapper mapper,
                        IValidator<User> validator) {
        this.repository = userRepository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public void register(User object) {
        this.repository.save(object);
    }

    @Override
    public User findByMail(String mail) {
        return this.repository.findByMail(mail)
                .map(userEntity -> new User(
                        userEntity.getMail(),
                        userEntity.getPassword()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }

    @Override
    public User add(User object) {
        return this.repository.save(object);
    }

    @Override
    public Page<UserReadDTO> findAll(Pageable pageable) {
        return this.repository.findAll(pageable)
                .map(mapper::toDTO);
    }

    @Override
    public Optional<UserReadDTO> findById(UUID uuid) {
        return this.repository.findById(uuid)
                .map(mapper::toDTO);
    }

    @Override
    public void updateInfo(UUID uuid, LocalDateTime dtUpdate, User object) {
        if (uuid == null) {
            throw new IllegalArgumentException("The field is empty");
        }
        var user = this.repository
                .findById(uuid)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("User Not Found");
                });
        //ИСПРАВИТЬ НА !
        if (user.getModifiedAt().equals(dtUpdate)) {
            throw new OptimisticLockException("The data has already been updated");
        }
        this.repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = this.repository.findByMail(username)
                .map(userEntity -> new User(
                        userEntity.getMail(),
                        userEntity.getPassword(),
                        userEntity.getRole()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
    }


}




