package com.gmail.juliarusakevich.classifier.validator;

import com.gmail.juliarusakevich.classifier.config.yml.CustomErrorMessage;
import com.gmail.juliarusakevich.classifier.config.yml.CustomPattern;
import com.gmail.juliarusakevich.classifier.dto.category.ConcertCategoryCreateDTO;
import com.gmail.juliarusakevich.classifier.dto.errors.ErrorMessage;
import com.gmail.juliarusakevich.classifier.validator.api.IValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ConcertCategoryValidator implements IValidator<ConcertCategoryCreateDTO> {

    private final CustomPattern pattern2;
    private final CustomErrorMessage errorMessage2;

    public ConcertCategoryValidator(
            CustomPattern pattern2,
            CustomErrorMessage errorMessage2) {
        this.pattern2 = pattern2;
        this.errorMessage2 = errorMessage2;
    }

    @Override
    public ValidationResult isValid(ConcertCategoryCreateDTO dto) {
        var validationResult = new ValidationResult();
        if (!isValidString(pattern2.getTitleConcertCategory(), dto.getTitle())) {
            validationResult.add(new ErrorMessage(
                    dto.getTitle(),
                    errorMessage2.getIncorrectSymbols()
            ));
        }

        return validationResult;
    }

    private boolean isValidString(String regex, String input) {
        return Pattern.matches(regex, input);
    }
}
