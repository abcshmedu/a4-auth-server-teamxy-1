package edu.hm.schatter.shareit.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.schatter.shareit.businesslayer.MediaService;
import edu.hm.schatter.shareit.businesslayer.MediaServiceImpl;
import edu.hm.schatter.shareit.businesslayer.MediaServiceResult;
import edu.hm.schatter.shareit.models.Book;
import edu.hm.schatter.shareit.models.Disc;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Provides the API for the share it service.
 */
@Path("/media")
public class MediaRessource {

    private final MediaService mediaService = new MediaServiceImpl();

    /**
     * Provides the API call for creating a book via a POST parameter.
     * @param book The book that is created. Built from JSON.
     * @return Response containing further information on the success of the procedure.
     */
    @POST
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBook(Book book) {

        MediaServiceResult result = mediaService.addBook(book);

        return Response
                .status(result.getStatus())
                .entity(result.getJSON())
                .build();
    }

    /**
     * Provides the API call for updating a book via a PUT parameter.
     * @param book The book containing the updated information. Built from JSON.
     * @param isbn The isbn of the book to be updated.
     * @return Response containing further information on the success of the procedure.
     */
    @PUT
    @Path("/books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book, @PathParam("isbn")String isbn) {

        MediaServiceResult result = mediaService.updateBook(isbn, book);

        return Response
                .status(result.getStatus())
                .entity(result.getJSON())
                .build();
    }

    /**
     * Provides the API call for listing all the books currently available in the system.
     * @return Response containing further information on the success of the procedure
     * including a JSON array of all books available in the system.
     */
    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {

        final Book[] books = mediaService.getBooks();
        MediaServiceResult result = MediaServiceResult.OK;
        String json = "";

        try {
            json = convertToJSON(books);
        } catch (JsonProcessingException e) {
            result = MediaServiceResult.ERROR;
        }

        return Response
                .status(result.getStatus())
                .entity(result == MediaServiceResult.OK ? json : result.getJSON())
                .build();
    }

    /**
     * Provides the API call for getting the JSON of a specific book.
     * @param isbn The isbn identifying the book.
     * @return Response containing further information on the success of the procedure
     * including the data of the book in JSON format.
     */
    @GET
    @Path("/books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn")String isbn) {

        final Book book = mediaService.getBookByISBN(isbn);

        MediaServiceResult result = book == null ? MediaServiceResult.NOT_FOUND : MediaServiceResult.OK;
        String json = "";

        if (result == MediaServiceResult.OK) {
            try {
                json = convertToJSON(book);
            } catch (JsonProcessingException e) {
                result = MediaServiceResult.ERROR;
            }
        }

        return Response
                .status(result.getStatus())
                .entity(json)
                .build();
    }

    /**
     * Provides the API call for getting the JSON of a specific disc.
     * @param barcode The barcode identifying the disc.
     * @return Response containing further information on the success of the procedure
     * including the data of the disc in JSON format.
     */
    @GET
    @Path("/discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs(@PathParam("barcode") String barcode) {

        final Disc disc = mediaService.getDiscByBarcode(barcode);

        MediaServiceResult result = disc == null ? MediaServiceResult.NOT_FOUND : MediaServiceResult.OK;
        String json = "";

        if (result == MediaServiceResult.OK) {
            try {
                json = convertToJSON(disc);
            } catch (JsonProcessingException e) {
                result = MediaServiceResult.ERROR;
            }
        }

        return Response
                .status(result.getStatus())
                .entity(json)
                .build();
    }

    /**
     * Provides the API call for listing all the discs currently available in the system.
     * @return Response containing further information on the success of the procedure
     * including a JSON array of all discs available in the system.
     */
    @GET
    @Path("/discs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs() {

        final Disc[] discs = mediaService.getDiscs();

        MediaServiceResult result = MediaServiceResult.OK;
        String json = "";

        try {
            json = convertToJSON(discs);
        } catch (JsonProcessingException e) {
            result = MediaServiceResult.ERROR;
        }

        return Response
                .status(result.getStatus())
                .entity(result == MediaServiceResult.OK ? json : result.getJSON())
                .build();
    }

    /**
     * Provides the API call for creating a disc via a POST parameter.
     * @param disc The disc that is created. Built from JSON.
     * @return Response containing further information on the success of the procedure.
     */
    @POST
    @Path("/discs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDisc(Disc disc) {
        MediaServiceResult result = mediaService.addDisc(disc);

        return Response
                .status(result.getStatus())
                .entity(result.getJSON())
                .build();
    }

    /**
     * Provides the API call for updating a disc via a PUT parameter.
     * @param disc The disc containing the updated information. Built from JSON.
     * @param barcode The barcode of the book to be updated.
     * @return Response containing further information on the success of the procedure.
     */
    @PUT
    @Path("/discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDisc(Disc disc, @PathParam("barcode")String barcode) {
        MediaServiceResult result = mediaService.updateDisc(barcode, disc);

        return Response
                .status(result.getStatus())
                .entity(result.getJSON())
                .build();
    }

    /**
     * Converts objects to json.
     * @param o object to be converted.
     * @return json.
     * @throws JsonProcessingException object could not be converted to json.
     */
    private String convertToJSON(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }
}
