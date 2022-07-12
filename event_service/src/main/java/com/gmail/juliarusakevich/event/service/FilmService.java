package com.gmail.juliarusakevich.event.service;

import com.gmail.juliarusakevich.event.service.dto.film.FilmReadDto;
import com.gmail.juliarusakevich.event.service.dto.film.FilmCreateAndUpdateDto;
import com.gmail.juliarusakevich.event.service.mapper.film.AFilmReadMapper;
import com.gmail.juliarusakevich.event.service.mapper.api.IMapper;
import com.gmail.juliarusakevich.event.repository.model.EventFilm;
import com.gmail.juliarusakevich.event.repository.IFilmRepository;
import com.gmail.juliarusakevich.event.service.api.IFilmService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class FilmService implements IFilmService {

    private final IFilmRepository repository;
    private final IMapper<FilmCreateAndUpdateDto, EventFilm> filmCreateMapper;
    private final AFilmReadMapper filmMapper;


    public FilmService(IFilmRepository repository,
                       IMapper<FilmCreateAndUpdateDto, EventFilm> filmCreateMapper,
                       AFilmReadMapper filmMapper) {
        this.repository = repository;
        this.filmCreateMapper = filmCreateMapper;
        this.filmMapper = filmMapper;
    }

    @Override
    public EventFilm addEvent(FilmCreateAndUpdateDto object) {
        var result = filmCreateMapper.map(object);
        return this.repository.save(result);
    }

    @Override
    public Page<FilmReadDto> findAll(Pageable pageable) {
        return this.repository.findAll(pageable)
                .map(filmMapper::toDto);
    }

    @Override
    public Optional<FilmReadDto> findByUuid(UUID uuid) {
        return this.repository.findById(uuid)
                .map(filmMapper::toDto);
    }

    @Override
    public void update(UUID uuid,
                       LocalDateTime dtUpdate,
                       FilmCreateAndUpdateDto dto) {
        if (uuid == null) {
            throw new IllegalArgumentException("Это поле не может быть null.");
        }

        var film = this.repository
                .findById(uuid)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("Фильм не найден.");
                });

        if (!film.getDtUpdate().equals(dtUpdate)) {
            throw new OptimisticLockException("Данные уже были обновлены.");
        }

        film.setTitle(dto.getTitle());
        film.setDescription(dto.getDescription());
        film.setDtEvent(LocalDateTime.now());//ИСПРАВИТЬ НА НОРМАЛЬНУЮ ДАТУ
        film.setDtEndOfSale(LocalDateTime.now()); //ИСПРАВИТЬ НА НОРМАЛЬНУЮ ДАТУ
        film.setType(dto.getType());
        film.setStatus(dto.getStatus());
        film.setCountry(dto.getCountry()); //НАПИСАТЬ ПРОВЕРКУ НА СУЩЕСТВОВАНИЯ ТАКОЙ СТРАНЫ
        film.setReleaseYear(dto.getReleaseYear());
        film.setReleaseDate(dto.getReleaseDate());
        film.setDuration(dto.getDuration());

        this.repository.save(film);
    }
}
