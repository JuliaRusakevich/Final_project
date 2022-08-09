package com.gmail.juliarusakevich.event.validator;

import com.gmail.juliarusakevich.event.config.yml.CustomErrorMessage;
import com.gmail.juliarusakevich.event.config.yml.CustomPattern;
import com.gmail.juliarusakevich.event.config.yml.ProjectDetailsUrl;
import com.gmail.juliarusakevich.event.dto.film.FilmCreateAndUpdateDto;
import com.gmail.juliarusakevich.event.validator.api.IValidator;
import com.gmail.juliarusakevich.event.dto.errors.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.regex.Pattern;

@Component
public class FilmValidator implements IValidator<FilmCreateAndUpdateDto> {

    private final RestTemplate template;
    private final CustomErrorMessage errorMessage;
    private final ProjectDetailsUrl url;
    private final CustomPattern pattern;


    public FilmValidator(RestTemplate template, CustomErrorMessage errorMessage, ProjectDetailsUrl url, CustomPattern pattern) {
        this.template = template;
        this.errorMessage = errorMessage;
        this.url = url;
        this.pattern = pattern;
    }

    @Override
    public ValidationResult isValid(FilmCreateAndUpdateDto object) {
        var validationResult = new ValidationResult();

        if (!validCountry(object.getCountry())) {
            validationResult.add(new ErrorMessage(
                    object.getCountry().toString(),
                    errorMessage.getCountryNotFound()
            ));
        }

        if (!isValidString(pattern.getTitle(), object.getTitle())) {
            validationResult.add(new ErrorMessage(
                    object.getTitle(),
                    errorMessage.getIncorrectSymbols()
            ));
        }

        if (!isValidString(pattern.getDescription(), object.getDescription())) {
            validationResult.add(new ErrorMessage(
                    object.getDescription(),
                    errorMessage.getIncorrectSymbols()
            ));
        }

        var currentDate = LocalDateTime.now();

        var dtEvent = object.getDtEvent();
        if (dtEvent.isBefore(currentDate)) {
            validationResult.add(new ErrorMessage(
                    dtEvent.toString(),
                    errorMessage.getIncorrectDate()
            ));
        }

        var dtEndOfSale = object.getDtEndOfSale();
        if (dtEndOfSale.isBefore(currentDate)) {
            validationResult.add(new ErrorMessage(
                    dtEvent.toString(),
                    errorMessage.getIncorrectDate()
            ));
        }

        return validationResult;
    }

    private boolean validCountry(UUID uuid) {

        HttpStatus statusCode;

        try {
            String urlCountry = url.getCountry() + uuid;
            ResponseEntity<String> res = template.getForEntity(urlCountry, String.class);
            statusCode = res.getStatusCode();
        } catch (ResourceAccessException e) {
            throw new RuntimeException(errorMessage.getConnectionFail());
        }
        if (!statusCode.is2xxSuccessful()) {
            throw new EntityNotFoundException(errorMessage.getCountryNotFound());
        }
        return true;

    }

    private boolean isValidString(String regex, String input) {
        return Pattern.matches(regex, input);
    }
}
