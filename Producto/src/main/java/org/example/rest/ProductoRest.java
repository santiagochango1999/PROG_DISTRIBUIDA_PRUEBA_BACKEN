package org.example.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.db.Producto;
import org.example.repository.ProductoRepository;

import java.util.List;

@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class ProductoRest {
    @Inject
    ProductoRepository productoRepository;

    @GET
    public List<Producto> findAll() {
        System.out.println("findAll");
        return productoRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        System.out.println("findById");
        var op = productoRepository.findByIdOptional(id);
        if (op.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(op.get()).build();
    }

    @POST
    public Response create(Producto producto) {
        producto.setId(null);
        productoRepository.persist(producto);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Producto producto) {
        Producto obj = productoRepository.findById(id);

        obj.setNombre(producto.getNombre());
        obj.setPrecio(producto.getPrecio());


        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        productoRepository.deleteById(id);
        return Response.ok().build();
    }


}
