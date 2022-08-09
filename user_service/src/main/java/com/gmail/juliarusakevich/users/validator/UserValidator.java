package com.gmail.juliarusakevich.users.validator;

import com.gmail.juliarusakevich.users.config.yml.CustomErrorMessage;
import com.gmail.juliarusakevich.users.config.yml.CustomPattern;
import com.gmail.juliarusakevich.users.dto.UserCreateUpdateDTO;
import com.gmail.juliarusakevich.users.dto.errors.ErrorMessage;
import com.gmail.juliarusakevich.users.validator.api.IValidator;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UserValidator implements IValidator<UserCreateUpdateDTO> {

    private final CustomErrorMessage errorMessage;
    private final CustomPattern pattern;


    public UserValidator(CustomErrorMessage errorMessage,
                         CustomPattern pattern) {
        this.errorMessage = errorMessage;
        this.pattern = pattern;
    }

    @Override
    public ValidationResult isValid(UserCreateUpdateDTO object) {
        var validationResult = new ValidationResult();


        if (!isValidString(pattern.getMailPattern(), object.getMail())) {
            validationResult.add(new ErrorMessage(
                    object.getMail(),
                    errorMessage.getIncorrectMail()
            ));
        }

        if (!isValidString(pattern.getNickPattern(), object.getNick())) {
            validationResult.add(new ErrorMessage(
                    object.getNick(),
                    errorMessage.getIncorrectNick()
            ));
        }

        if (!isValidString(pattern.getPasswordPattern(), object.getPassword())) {
            validationResult.add(new ErrorMessage(
                    object.getPassword(),
                    errorMessage.getIncorrectPassword()
            ));
        }

        return validationResult;
    }

    private boolean isValidString(String regex, String input) {
        return Pattern.matches(regex, input);
    }
}
