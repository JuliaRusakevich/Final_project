package com.gmail.juliarusakevich.users.service;

import com.gmail.juliarusakevich.users.config.yml.CustomErrorMessage;
import com.gmail.juliarusakevich.users.dto.CustomUserDetails;
import com.gmail.juliarusakevich.users.dto.UserCreateUpdateDTO;
import com.gmail.juliarusakevich.users.dto.UserReadDTO;
import com.gmail.juliarusakevich.users.dto.UserStatusDto;
import com.gmail.juliarusakevich.users.entity.User;
import com.gmail.juliarusakevich.users.mapper.UserMapper;
import com.gmail.juliarusakevich.users.repository.UserEntityRepository;
import com.gmail.juliarusakevich.users.service.api.IUserService;
import com.gmail.juliarusakevich.users.validator.ValidationException;
import com.gmail.juliarusakevich.users.validator.api.IValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OptimisticLockException;
import java.util.Optional;
import java.util.UUID;
/*
Если не указываем явно транзакции, значит они будут по умолчанию из UserRepository,
а это не правильно, так как в mapper можем получать доступ к Lazy инициализированным сущностям
-> LazyInitializationException или доп. запросы в БД
-> делаем @Transactional над классом -> над каждым методом открывается/закрывается транзакция
readOnly = true - оптимизация на уровне БД и application
в readOnly не изменяем сущности!
 */

@Component
@Transactional(readOnly = true)
public class UsersService implements IUserService, UserDetailsService {

    private final UserEntityRepository repository;
    private final UserMapper mapper;
    private final CustomErrorMessage errorMessage;
    private final IValidator<UserCreateUpdateDTO> validator;

    public UsersService(UserEntityRepository userRepository,
                        UserMapper mapper,
                        CustomErrorMessage errorMessage,
                        IValidator<UserCreateUpdateDTO> validator) {
        this.repository = userRepository;
        this.mapper = mapper;
        this.errorMessage = errorMessage;
        this.validator = validator;
    }

    /*
     Если не смогли сохранить - исключительная ситуация
     Optional.of(dto) - принцип fail fast
     saveAndFlush - использовать, если на момент сохранения произойдет exception
      */
    @Transactional
    @Override
    public UserReadDTO add(UserCreateUpdateDTO dto) {
        var validationResult = this.validator.isValid(dto);
        if (validationResult.isValid()) {
            return Optional.of(dto)
                    .map(mapper::toEntity)
                    .map(this.repository::save)
                    .map(mapper::toDTO)
                    .orElseThrow();//на контроллере словим
        } else {
            throw new ValidationException(validationResult.getErrors());
        }
    }

    @Override
    public Page<UserReadDTO> findAll(Pageable pageable) {
        return this.repository.findAll(pageable)
                .map(mapper::toDTO);
    }

    /*
    можем не найти по id ->
    вернется null ->
    null нежелательно ->
    вместо null используем Optional
    */
    @Override
    public Optional<UserReadDTO> findById(UUID uuid) {
        return this.repository.findById(uuid)
                .map(mapper::toDTO);
    }

    @Transactional
    @Override
    public Optional<UserReadDTO> update(UUID uuid, Integer version, UserCreateUpdateDTO dto) {
        var toUser = this.mapper.toEntity(dto);
        User fromUser = getUserFromDB(uuid, version);
        this.mapper.map(fromUser, toUser);
        /*
        без Flush не произойдет запрос в бд и можем отловить exception на другом уровне или после commit
        поэтому, чтобы понимать сразу, где беда -> saveAndFlush
         */
        this.repository.saveAndFlush(fromUser);
        return Optional.ofNullable(this.mapper.toDTO(fromUser));
    }


    @Override
    public User findByMail(String mail) {
        return this.repository.findByMail(mail)
                .map(userEntity -> new User(
                        userEntity.getMail(),
                        userEntity.getPassword(),
                        userEntity.getRole()
                ))
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                this.errorMessage.getUserNotFound()
                        ));
    }

    /*
    (String mail,
                    String password,
                    Set<UserRole> role,
                    UserStatus status,
                    UUID uuid)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = this.repository.findByMail(username)
                .map(userEntity -> new User(
                        userEntity.getMail(),
                        userEntity.getPassword(),
                        userEntity.getRole(),
                        userEntity.getStatus(),
                        userEntity.getUuid()
                ))
                .orElseThrow(() -> new UsernameNotFoundException
                        (this.errorMessage.getUserNotFound()));
        return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
    }

    @Transactional
    @Override
    public void updateStatus(UUID uuid, Integer version, UserStatusDto status) {
      var userFrom = getUserFromDB(uuid, version);
        userFrom.setStatus(status.getStatus());
        this.repository.saveAndFlush(userFrom);
    }

    private User getUserFromDB(UUID uuid, Integer version) {

        var fromUser = this.repository.findById(uuid)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException(
                            this.errorMessage.getUserNotFound()
                    );
                });
        if (!fromUser.getVersion().equals(version)) {
            throw new OptimisticLockException(
                    this.errorMessage.getUpdatedInfo()
            );
        }
        return fromUser;
    }


}




