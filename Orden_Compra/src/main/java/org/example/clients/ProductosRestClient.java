package org.example.clients;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.dto.ClienteDto;
import org.example.dto.ProductoDto;

@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

@RegisterRestClient(baseUri="stork://pro-service")
public interface ProductosRestClient {

    @GET
    @Path("/{id}")
    ProductoDto findById(@PathParam("id") Integer id);

}
