package com.gmail.juliarusakevich.classifier.validator;

import com.gmail.juliarusakevich.classifier.config.yml.CustomErrorMessage;
import com.gmail.juliarusakevich.classifier.config.yml.CustomPattern;
import com.gmail.juliarusakevich.classifier.dto.country.CountryCreateDTO;
import com.gmail.juliarusakevich.classifier.dto.errors.ErrorMessage;
import com.gmail.juliarusakevich.classifier.validator.api.IValidator;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class CountryValidator implements IValidator<CountryCreateDTO> {

    private final CustomErrorMessage errorMessage;
    private final CustomPattern pattern;

    public CountryValidator(CustomErrorMessage errorMessage,
                            CustomPattern pattern) {
        this.errorMessage = errorMessage;
        this.pattern = pattern;
    }


    @Override
    public ValidationResult isValid(CountryCreateDTO dto) {
        var validationResult = new ValidationResult();

        if (!isValidString(pattern.getTitleCountry(), dto.getTitle())) {
            validationResult.add(new ErrorMessage(
                    dto.getTitle(),
                    errorMessage.getTitleCountry()
            ));
        }

        if (!isValidString(pattern.getDescriptionCountry(), dto.getDescription())) {
            validationResult.add(new ErrorMessage(
                    dto.getDescription(),
                    errorMessage.getIncorrectSymbols()
            ));
        }


        return validationResult;
    }


    private boolean isValidString(String regex, String input) {
        return Pattern.matches(regex, input);
    }
}
