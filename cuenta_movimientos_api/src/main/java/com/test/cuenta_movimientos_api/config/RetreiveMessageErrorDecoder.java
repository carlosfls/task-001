package com.test.cuenta_movimientos_api.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.cuenta_movimientos_api.exceptions.AlreadyExistException;
import com.test.cuenta_movimientos_api.exceptions.ErrorDetails;
import com.test.cuenta_movimientos_api.exceptions.NotFoundException;
import com.test.cuenta_movimientos_api.exceptions.UnprocessableEntityException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class RetreiveMessageErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        Response.Body body = response.body();
        if (body == null)
            return null;

        try {
            Set<String> messages = new ObjectMapper()
                    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                    .readValue(Util.toString(body.asReader(StandardCharsets.UTF_8)), ErrorDetails.class)
                    .getDetails();
            String message = String.join(", ", messages);

            return switch (response.status()) {
                case 409 ->
                        new AlreadyExistException(ObjectUtils.isEmpty(messages) ? "Already exist" : message);
                case 404 -> new NotFoundException(ObjectUtils.isEmpty(messages) ? "Not found" : message);
                case 422 ->
                        new UnprocessableEntityException(ObjectUtils.isEmpty(messages) ? "Unprocessable Entity" : message);
                default -> errorDecoder.decode(methodKey, response);
            };
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }
    }
}
