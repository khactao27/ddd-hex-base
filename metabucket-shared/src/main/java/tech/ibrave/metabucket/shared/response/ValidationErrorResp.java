package tech.ibrave.metabucket.shared.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import tech.ibrave.metabucket.shared.message.Message;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationErrorResp {
    private String id;
    private String errorCode;
    private String message;
    private String messageCode;
    private Set<FieldDetailError> fieldErrors = new HashSet<>(3);

    public ValidationErrorResp(String errorCode, Message message) {
        this.id = RandomStringUtils.randomAlphabetic(6);
        this.errorCode = errorCode;
        this.messageCode = message.messageCode();
        this.message = message.content();
    }

    public void addFieldError(FieldDetailError fieldError) {
        fieldErrors.add(fieldError);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FieldDetailError {
        private String field;
        private String message;

        public static FieldDetailError of(String field, String message) {
            return new FieldDetailError(field, message);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FieldDetailError that = (FieldDetailError) o;
            return Objects.equals(field, that.field);
        }

        @Override
        public int hashCode() {
            return Objects.hash(field);
        }
    }
}
