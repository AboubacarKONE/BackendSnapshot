package com.example.backend.ErrorsHandler;

import lombok.Data;

@Data
public class ParticipantNotFoundError extends Exception{
    public ParticipantNotFoundError(String message) {
        super(message);
    }

    public ParticipantNotFoundError(String message, Throwable cause) {
        super(message, cause);
    }

    public ParticipantNotFoundError(Throwable cause) {
        super(cause);
    }

    protected ParticipantNotFoundError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
