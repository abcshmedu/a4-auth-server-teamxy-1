package edu.hm.schatter.auth.businesslayer;

import javax.ws.rs.core.Response;

/**
 * Contains information how a certain procedure went.
 */
public enum AuthServiceResult {
    OK(200, "OK.", Response.Status.OK),
    INCORRECT_INFORMATION(400, "The information you provided is incorrect.", Response.Status.BAD_REQUEST),
    ERROR(500, "Internal Server Error.", Response.Status.INTERNAL_SERVER_ERROR);

    private final int code;
    private final String message;
    private final Response.Status status;

    /**
     * Standard constructor.
     * @param code The HTTP error code.
     * @param message The message which is sent to the user.
     * @param status Corresponding HTTP Status.
     */
    AuthServiceResult(int code, String message, Response.Status status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    /**
     * Getter.
     * @return The HTTP error code.
     */
    public int getCode() {
        return code;
    }

    /**
     * Getter.
     * @return The HTTP message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Getter.
     * @return The HTTP Status.
     */
    public Response.Status getStatus() {
        return status;
    }

    /**
     * Getter for JSON message.
     * @return JSON message of the error code.
     */
    public String getJSON() {
        return "{\"code\": " + code + ", \"detail\": \"" + message + "\"}";
    }
}
