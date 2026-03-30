package br.wutzke;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/unipds")
public class UniPDSResource {

    public int number = 0;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getNumber() {
        return "Number: " + number;
    }

    @POST
    public void increment() {
        number++;
    }

    @DELETE
    public void decrement() {
        number--;
    }

    @PUT
    public void setNumber(int number) {
        this.number = number;
    }
}
