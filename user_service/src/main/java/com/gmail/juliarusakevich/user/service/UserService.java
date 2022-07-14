package com.gmail.juliarusakevich.user.service;

import com.gmail.juliarusakevich.user.validator.api.IValidator;
import com.gmail.juliarusakevich.user.repository.IUserRepository;
import com.gmail.juliarusakevich.user.repository.model.UserCreate;
import com.gmail.juliarusakevich.user.service.api.IUserCreateService;
import com.gmail.juliarusakevich.user.service.dto.UserCreateUpdateDTO;
import com.gmail.juliarusakevich.user.service.dto.UserReadDTO;
import com.gmail.juliarusakevich.user.service.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserCreateService {

    private final IUserRepository repository;
    private final UserMapper mapper;
    private final IValidator <UserCreateUpdateDTO> validator;

    public UserService(IUserRepository repository,
                       UserMapper mapper,
                       IValidator<UserCreateUpdateDTO> validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public UserCreate addUser(UserCreateUpdateDTO dto) {
        var validDto = validator.isValid(dto);
        var userCreate = mapper.toEntity(dto);
        return this.repository.save(userCreate);
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
    public UserCreate updateUserInfo(UUID uuid, LocalDateTime dtUpdate, UserCreateUpdateDTO dto) {
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

        user.setMail(dto.getMail());
        user.setNick(dto.getNick());
        user.setRole(dto.getRole());
        user.setStatus(dto.getStatus());
        user.setPassword(dto.getPassword());

        return this.repository.save(user);
    }
/*


    @Override
    public Boolean checkNick(String nick) {
        return this.repository.findByNick(nick) != null;
    }

    @Override
    public Boolean checkMail(String mail) {
        return this.repository.findByMail(mail) != null;
    }
     */

}
