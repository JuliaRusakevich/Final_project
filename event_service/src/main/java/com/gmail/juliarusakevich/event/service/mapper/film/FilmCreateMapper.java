package com.gmail.juliarusakevich.event.service.mapper.film;

import com.gmail.juliarusakevich.event.service.dto.film.FilmCreateAndUpdateDto;
import com.gmail.juliarusakevich.event.service.mapper.api.IMapper;
import com.gmail.juliarusakevich.event.repository.model.EventFilm;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FilmCreateMapper implements IMapper<FilmCreateAndUpdateDto, EventFilm> {

    /*
            private UUID uuid;
            private LocalDateTime dtCreate;
            private LocalDateTime dtUpdate;
            private String title;
            private String description;
            private LocalDateTime dtEvent;
            private LocalDateTime dtEndOfSale;
            private EventType eventType;
            private EventStatus eventStatus;
            private String uuidCountry;
            private Integer releaseYear;
            private LocalDate releaseDate;
            private Integer duration;
     */
    @Override
    public EventFilm map(FilmCreateAndUpdateDto dto) {
        return EventFilm.builder()
                .dtCreate(LocalDateTime.now())
               // .dtUpdate(LocalDateTime.now())
                .title(dto.getTitle()) //  "title": "string",
                .description(dto.getDescription())// "description": "string",
                .dtEvent(LocalDateTime.now())//"dt_event": 0, ИСПРАВИТЬ НА НОРМАЛЬНУЮ ДАТУ ВРЕМЯ
                .dtEndOfSale(LocalDateTime.now())//"dt_end_of_sale": 0, ИСПРАВИТЬ НА НОРМАЛЬНУЮ ДАТУ ВРЕМЯ
                .eventType(dto.getType())//"type": "FILMS",
                .eventStatus(dto.getStatus())//"status": "DRAFT",
                .country(dto.getCountry())//"country": "uuid county",
                .releaseYear(dto.getReleaseYear())//"release_year": 2022,
                .releaseDate(dto.getReleaseDate())
                .duration(dto.getDuration())//"duration": 100
                .build();
    }
}
