package br.wutzke;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("starwars")
public class StarWarsResource {

    @RestClient
    StarWarsService starwarsService;

    @GET
    @Path("starships")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStarships() {
        return starwarsService.getStarships();
    }
}
