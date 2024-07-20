package org.example.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.db.Cliente;
import org.example.repository.ClienteRepository;

import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class ClienteRest {
    @Inject
    ClienteRepository clienteRepository;

    @GET
    public List<Cliente> findAll() {
        System.out.println("findAll");
        return clienteRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        System.out.println("findById");
        var op = clienteRepository.findByIdOptional(id);
        if (op.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(op.get()).build();
    }

    @POST
    public Response create(Cliente cliente) {
        cliente.setId(null);
        clienteRepository.persist(cliente);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Cliente cliente) {
        Cliente obj = clienteRepository.findById(id);

        obj.setNombre(cliente.getNombre());
        obj.setApellido(cliente.getApellido());
        obj.setDireccion(cliente.getDireccion());

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        clienteRepository.deleteById(id);
        return Response.ok().build();
    }

}