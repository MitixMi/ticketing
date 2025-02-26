package avion.com.controller;

import avion.com.annotation.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Validator {
    public static Map<String, String> validate(Object obj) {
        Map<String, String> errors = new HashMap<>();
        
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            
            try {
                Object value = field.get(obj);
                
                // Check @NotNull
                if (field.isAnnotationPresent(NotNull.class) && value == null) {
                    NotNull notNull = field.getAnnotation(NotNull.class);
                    errors.put(field.getName(), notNull.message());
                }
                
                // Check @MinLength
                if (field.isAnnotationPresent(MinLength.class) && value != null) {
                    MinLength minLength = field.getAnnotation(MinLength.class);
                    if (value.toString().length() < minLength.value()) {
                        errors.put(field.getName(), minLength.message());
                    }
                }
                
                // Check @MaxLength
                if (field.isAnnotationPresent(MaxLength.class) && value != null) {
                    MaxLength maxLength = field.getAnnotation(MaxLength.class);
                    if (value.toString().length() > maxLength.value()) {
                        errors.put(field.getName(), maxLength.message());
                    }
                }
                
                // Check @Numeric
                if (field.isAnnotationPresent(Numeric.class) && value != null) {
                    if (!value.toString().matches("\\d+")) {
                        Numeric numeric = field.getAnnotation(Numeric.class);
                        errors.put(field.getName(), numeric.message());
                    }
                }

                // Check @Email
                if (field.isAnnotationPresent(Email.class) && value != null) {
                    String emailPattern = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
                    if (!value.toString().matches(emailPattern)) {
                        Email email = field.getAnnotation(Email.class);
                        errors.put(field.getName(), email.message());
                    }
                }

                // Check @Min
                if (field.isAnnotationPresent(Min.class) && value != null) {
                    Min min = field.getAnnotation(Min.class);
                    if (Integer.parseInt(value.toString()) < min.value()) {
                        errors.put(field.getName(), min.message());
                    }
                }

                // Check @Pattern
                if (field.isAnnotationPresent(Pattern.class) && value != null) {
                    Pattern pattern = field.getAnnotation(Pattern.class);
                    if (!value.toString().matches(pattern.regex())) {
                        errors.put(field.getName(), pattern.message());
                    }
                }

            } catch (IllegalAccessException e) {
                errors.put(field.getName(), "Error accessing field: " + field.getName());
            }
        }
        
        return errors;
    }
}
