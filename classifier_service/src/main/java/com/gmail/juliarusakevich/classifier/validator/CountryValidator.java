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

    private final CustomPattern pattern1;
    private final CustomErrorMessage errorMessage1;

    public CountryValidator(CustomPattern pattern1, CustomErrorMessage errorMessage1) {
        this.pattern1 = pattern1;
        this.errorMessage1 = errorMessage1;
    }

    @Override
    public ValidationResult isValid(CountryCreateDTO dto) {
        var validationResult = new ValidationResult();

        if (!isValidString(pattern1.getTitleCountry(), dto.getTitle())) {
            validationResult.add(new ErrorMessage(
                    dto.getTitle(),
                    errorMessage1.getTitleCountry()
            ));
        }

        if (!isValidString(pattern1.getDescriptionCountry(), dto.getDescription())) {
            validationResult.add(new ErrorMessage(
                    dto.getDescription(),
                    errorMessage1.getIncorrectSymbols()
            ));
        }

        return validationResult;
    }


    private boolean isValidString(String regex, String input) {
        return Pattern.matches(regex, input);
    }
}
