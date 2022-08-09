package com.gmail.juliarusakevich.event.validator;

import com.gmail.juliarusakevich.event.config.yml.CustomErrorMessage;
import com.gmail.juliarusakevich.event.config.yml.CustomPattern;
import com.gmail.juliarusakevich.event.config.yml.ProjectDetailsUrl;
import com.gmail.juliarusakevich.event.dto.concert.ConcertCreateAndUpdateDto;
import com.gmail.juliarusakevich.event.dto.errors.ErrorMessage;
import com.gmail.juliarusakevich.event.validator.api.IValidator;
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
public class ConcertValidator implements IValidator<ConcertCreateAndUpdateDto> {

    private final RestTemplate template;
    private final CustomErrorMessage errorMessage;
    private final CustomPattern pattern;
    private final ProjectDetailsUrl url;

    public ConcertValidator(RestTemplate template, CustomErrorMessage errorMessage, CustomPattern pattern, ProjectDetailsUrl url) {
        this.template = template;
        this.errorMessage = errorMessage;
        this.pattern = pattern;
        this.url = url;
    }

    @Override
    public ValidationResult isValid(ConcertCreateAndUpdateDto object) {

        var validationResult = new ValidationResult();

        if (!validConcert(object.getCategory())) {
            validationResult.add(new ErrorMessage(
                    object.getCategory().toString(),
                    errorMessage.getCategoryNotFound()
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

    private boolean validConcert(UUID uuid) {

        HttpStatus statusCode;

        try {
            String urlConcert = url.getConcertCategory() + uuid;
            ResponseEntity<String> res = template.getForEntity(urlConcert, String.class);
            statusCode = res.getStatusCode();
        } catch (ResourceAccessException e) {
            throw new RuntimeException(errorMessage.getConnectionFail());
        }
        if (!statusCode.is2xxSuccessful()) {
            throw new EntityNotFoundException(errorMessage.getConcertNotFound());
        }
        return true;
    }

    private boolean isValidString(String regex, String input) {
        return Pattern.matches(regex, input);
    }
}
