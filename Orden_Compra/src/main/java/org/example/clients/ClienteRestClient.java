package org.example.clients;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.dto.ClienteDto;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

@RegisterRestClient(baseUri="stork://cli-service")
public interface ClienteRestClient {

    @GET
    @Path("/{id}")
    ClienteDto findById(@PathParam("id") Integer id);

}
