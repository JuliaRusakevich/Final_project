package com.gmail.juliarusakevich.users.validator;

import com.gmail.juliarusakevich.users.config.ProjectDetails;
import com.gmail.juliarusakevich.users.dto.UserRegistration;
import com.gmail.juliarusakevich.users.dto.errors.ErrorMessage;
import com.gmail.juliarusakevich.users.validator.api.IValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class UserRegistrationValidator implements IValidator<UserRegistration> {

    private final List<ErrorMessage> errorMessages = new ArrayList<>();

    private final ProjectDetails projectDetails;

    public UserRegistrationValidator(ProjectDetails projectDetails) {
        this.projectDetails = projectDetails;
    }

    @Override
    public boolean isValid(UserRegistration object) {
        //ПРОВЕРКА MAIL
        if (!isValidString(projectDetails.mailPattern, object.getMail())) {
            errorMessages.add(new ErrorMessage(
                    object.getMail(),
                    projectDetails.mailErrorMessage
            ));

        }
        //ПРОВЕРКА NICK
        if (!isValidString(projectDetails.nickPattern, object.getNick())) {
            errorMessages.add(new ErrorMessage(
                    object.getNick(),
                    projectDetails.nickErrorMessage
            ));
        }

        //ПРОВЕРКА PASSWORD
        if (!isValidString(projectDetails.passwordPattern, object.getPassword())) {
            errorMessages.add(new ErrorMessage(
                    object.getPassword(),
                    projectDetails.passwordErrorMessage
            ));
        }

        if (!errorMessages.isEmpty()) {
            throw new ValidationException(errorMessages);
        }

        return true;
    }

    private boolean isValidString(String regex, String input) {
        return Pattern.matches(regex, input);
    }
}
