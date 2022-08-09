package com.gmail.juliarusakevich.event.service;

import com.gmail.juliarusakevich.event.config.yml.CustomErrorMessage;
import com.gmail.juliarusakevich.event.dto.EventStatusUpdateDto;
import com.gmail.juliarusakevich.event.mapper.film.FilmMapper;
import com.gmail.juliarusakevich.event.service.api.IFilmService;
import com.gmail.juliarusakevich.event.dto.film.FilmCreateAndUpdateDto;
import com.gmail.juliarusakevich.event.dto.film.FilmReadDto;
import com.gmail.juliarusakevich.event.repository.IFilmRepository;
import com.gmail.juliarusakevich.event.repository.entity.EventFilm;
import com.gmail.juliarusakevich.event.validator.ValidationException;
import com.gmail.juliarusakevich.event.validator.api.IValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class FilmService implements IFilmService {

    private final IFilmRepository repository;
    private final FilmMapper filmMapper;
    private final IValidator<FilmCreateAndUpdateDto> validator;
    private final UserHolder userHolder;
    private final CustomErrorMessage errorMessage;

    public FilmService(IFilmRepository repository, FilmMapper filmMapper, IValidator<FilmCreateAndUpdateDto> validator, UserHolder userHolder, CustomErrorMessage errorMessage) {
        this.repository = repository;
        this.filmMapper = filmMapper;
        this.validator = validator;
        this.userHolder = userHolder;
        this.errorMessage = errorMessage;
    }

    @Transactional
    @Override
    public EventFilm addEvent(FilmCreateAndUpdateDto object) {
        var validationResult = this.validator.isValid(object);

        if (!this.userHolder.isEnableAccount()) {
            throw new IllegalArgumentException(errorMessage.getAccountNotActivated());
        }
        if (validationResult.isValid()) {
            return Optional.of(object)
                    .map(filmMapper::toEntity)
                    .map(repository::save)
                    .orElseThrow();
        } else {
            throw new ValidationException(validationResult.getErrors());
        }
    }

    @Override
    public Page<FilmReadDto> findAll(Pageable pageable) {
        return this.repository.findAll(pageable)
                .map(filmMapper::toDTO);
    }

    @Override
    public Optional<FilmReadDto> findByUuid(UUID uuid) {
        return this.repository.findById(uuid)
                .map(filmMapper::toDTO);
    }

    @Transactional
    @Override
    public void update(UUID uuid, Integer version, FilmCreateAndUpdateDto dto) {
        var filmFromDB = getEventFilmFromDB(uuid, version);
        map(dto, filmFromDB);
        this.repository.saveAndFlush(filmFromDB);
    }

    @Transactional
    @Override
    public void updateStatus(UUID uuid, Integer version, EventStatusUpdateDto status) {
        var film = getEventFilmFromDB(uuid, version);
        film.setStatus(status.getStatus());
        this.repository.saveAndFlush(film);
    }

    private void map(FilmCreateAndUpdateDto dto, EventFilm filmFromDB) {
        filmFromDB.setTitle(dto.getTitle());
        filmFromDB.setDescription(dto.getDescription());
        filmFromDB.setDtEvent(LocalDateTime.now());
        filmFromDB.setDtEndOfSale(LocalDateTime.now());
        filmFromDB.setType(dto.getType());
        filmFromDB.setStatus(dto.getStatus());
        filmFromDB.setCountry(dto.getCountry());
        filmFromDB.setReleaseYear(dto.getReleaseYear());
        filmFromDB.setReleaseDate(dto.getReleaseDate());
        filmFromDB.setDuration(dto.getDuration());
    }

    private EventFilm getEventFilmFromDB(UUID uuid, Integer version) {
        var fromFilm = this.repository.findById(uuid)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException(
                            this.errorMessage.getConcertNotFound()
                    );
                });
        if (!fromFilm.getVersion().equals(version)) {
            throw new OptimisticLockException(
                    this.errorMessage.getUpdatedInfo()
            );
        }
        return fromFilm;
    }
}
