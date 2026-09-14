package io.bootify.my_app.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 6485045508217235030L;

	public NotFoundException() {
        super();
    }

    public NotFoundException(final String message) {
        super(message);
    }

}
