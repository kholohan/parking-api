package org.kylecorp.util.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ParkingRuntimeException extends RuntimeException implements
        ExceptionMapper<ParkingRuntimeException> {
    public ParkingRuntimeException() {
    }

    public ParkingRuntimeException(final String message) {
        super(message);
    }

    public ParkingRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ParkingRuntimeException(final Throwable cause) {
        super(cause);
    }

    public ParkingRuntimeException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Response toResponse(ParkingRuntimeException exception) {
        return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
    }
}
