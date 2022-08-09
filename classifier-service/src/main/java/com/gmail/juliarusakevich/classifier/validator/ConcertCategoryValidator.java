package com.gmail.juliarusakevich.classifier.validator;

import com.gmail.juliarusakevich.classifier.config.yml.CustomErrorMessage;
import com.gmail.juliarusakevich.classifier.config.yml.CustomPattern;
import com.gmail.juliarusakevich.classifier.dto.category.ConcertCategoryCreateDTO;
import com.gmail.juliarusakevich.classifier.dto.errors.ErrorMessage;
import com.gmail.juliarusakevich.classifier.validator.api.IValidator;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ConcertCategoryValidator implements IValidator<ConcertCategoryCreateDTO> {

    private final CustomPattern pattern;
    private final CustomErrorMessage errorMessage;

    public ConcertCategoryValidator(CustomPattern pattern, CustomErrorMessage errorMessage) {
        this.pattern = pattern;
        this.errorMessage = errorMessage;
    }

    @Override
    public ValidationResult isValid(ConcertCategoryCreateDTO dto) {
        var validationResult = new ValidationResult();
        if (!isValidString(pattern.getTitleConcertCategory(), dto.getTitle())) {
            validationResult.add(new ErrorMessage(
                    dto.getTitle(),
                    errorMessage.getIncorrectSymbols()
            ));
        }

        return validationResult;
    }

    private boolean isValidString(String regex, String input) {
        return Pattern.matches(regex, input);
    }
}
