package dev.ducnguyen.social_network_project.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Stream;

public class GenderValidator implements ConstraintValidator<GenderValid, CharSequence> {

    private List acceptedValues;

    @Override
    public void initialize(GenderValid genderValid) {
        acceptedValues = Stream.of(genderValid.enumClass().getEnumConstants())
                .map(Enum::name)
                .toList();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return acceptedValues.contains(value.toString().toUpperCase());
    }
}
