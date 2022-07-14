package com.gmail.juliarusakevich.classifier.validator;

import com.gmail.juliarusakevich.classifier.dto.category.ConcertCategoryCreateDTO;
import com.gmail.juliarusakevich.classifier.validator.api.IValidator;
import com.gmail.juliarusakevich.classifier.validator.errors.ErrorMessage;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class ConcertCategoryValidator implements IValidator<ConcertCategoryCreateDTO> {

    private final List<ErrorMessage> errorMessages = new ArrayList<>();
    private final Environment environment; //read application.properties

    public ConcertCategoryValidator(Environment environment) {
        this.environment = environment;
    }

    @Override
    public boolean isValid(ConcertCategoryCreateDTO dto) {

        if (!errorMessages.isEmpty()) {
            throw new ValidationException(errorMessages);
        }
        return true;
    }

    private boolean isValidString(String regex, String input) {
        return !Pattern.matches(regex, input);
    }
}
