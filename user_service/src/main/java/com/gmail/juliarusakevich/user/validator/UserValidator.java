package com.gmail.juliarusakevich.user.validator;

import com.gmail.juliarusakevich.user.repository.model.enums.UserRole;
import com.gmail.juliarusakevich.user.validator.api.IValidator;
import com.gmail.juliarusakevich.user.validator.errors.ErrorMessage;
import com.gmail.juliarusakevich.user.service.dto.UserCreateUpdateDTO;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.gmail.juliarusakevich.user.validator.util.Constants.*;

@Component
public class UserValidator implements IValidator<UserCreateUpdateDTO> {

    private final List<ErrorMessage> errorMessages = new ArrayList<>();
    private final Environment environment; //read application.properties

    public UserValidator(Environment environment) {
        this.environment = environment;
    }

    @Override
    public boolean isValid(UserCreateUpdateDTO dto) {

        if (dto.getMail() == null || isValidString(environment.getProperty(MAIL_PATTERN), dto.getMail())) {
            errorMessages.add(new ErrorMessage(
                    FIELD_MAIL + dto.getMail(),
                    ERROR_MESSAGE_FOR_MAIL
            ));
        }

        if (dto.getNick() == null || isValidString(environment.getProperty(NICK_PATTERN), dto.getNick())) {
            errorMessages.add(new ErrorMessage(
                    FIELD_NICK,
                    ERROR_MESSAGE_FOR_NICK
            ));
        }

        if (dto.getRole() == null || !dto.getRole().equals(UserRole.USER) || !dto.getRole().equals(UserRole.ADMIN)){
            errorMessages.add(new ErrorMessage(
                    FIELD_ROLE,
                    ERROR_MESSAGE_FOR_ROLE
            ));
        }

            if (!errorMessages.isEmpty()) {
                throw new ValidationException(errorMessages);
            }


        return true;
    }

    private boolean isValidString(String regex, String input) {
        return !Pattern.matches(regex, input);
    }
}
