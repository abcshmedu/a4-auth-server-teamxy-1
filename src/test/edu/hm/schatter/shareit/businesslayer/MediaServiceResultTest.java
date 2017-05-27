package edu.hm.schatter.shareit.businesslayer;

import org.junit.Test;

import static org.junit.Assert.*;

public class MediaServiceResultTest {

    @Test
    public void correctJSONforOK() {
        final String json = MediaServiceResult.OK.getJSON();
        final String expectedJSON = "{\"code\": 200, \"detail\": \"OK.\"}";

        assertEquals(expectedJSON, json);
    }

    @Test
    public void correctJSONforALREADY_EXISTS() {
        final String json = MediaServiceResult.ALREADY_EXISTS.getJSON();
        final String expectedJSON = "{\"code\": 400, \"detail\": \"The entity you wanted to create already exists.\"}";

        assertEquals(expectedJSON, json);
    }

    @Test
    public void correctJSONforINVALID_INFORMATION() {
        final String json = MediaServiceResult.INVALID_INFORMATION.getJSON();
        final String expectedJSON = "{\"code\": 400, \"detail\": \"The information you provided is invalid.\"}";

        assertEquals(expectedJSON, json);
    }

    @Test
    public void correctJSONforMISSING_INFORMATION() {
        final String json = MediaServiceResult.MISSING_INFORMATION.getJSON();
        final String expectedJSON = "{\"code\": 400, \"detail\": \"Some required information is missing. (e.g.: title, author, etc.)\"}";

        assertEquals(expectedJSON, json);
    }

    @Test
    public void correctJSONforNOT_FOUND() {
        final String json = MediaServiceResult.NOT_FOUND.getJSON();
        final String expectedJSON = "{\"code\": 404, \"detail\": \"The requested resource could not be found.\"}";

        assertEquals(expectedJSON, json);
    }

    @Test
    public void correctJSONforERROR() {
        final String json = MediaServiceResult.ERROR.getJSON();
        final String expectedJSON = "{\"code\": 500, \"detail\": \"Internal Server Error.\"}";

        assertEquals(expectedJSON, json);
    }
}