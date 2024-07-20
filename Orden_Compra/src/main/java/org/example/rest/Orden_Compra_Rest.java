package org.example.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.clients.ClienteRestClient;
import org.example.clients.ProductosRestClient;
import org.example.db.Orden_Compra;
import org.example.dto.OrdenCompraDto;
import org.example.repository.Orden_Compra_Repository;

import java.util.List;

@Path("/ordenCompras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class Orden_Compra_Rest {

    @Inject
    Orden_Compra_Repository ocompra;

    @Inject
    @RestClient
    ClienteRestClient clienteRestClient;

    @Inject
    @RestClient
    ProductosRestClient productosRestClient;

    @GET
    public List<OrdenCompraDto> findAll() {
        var ordenes = ocompra.listAll();

        return ordenes.stream().
                map(orden -> {
                    var cliente = clienteRestClient.findById(orden.getClienteId());
                    var producto = productosRestClient.findById(orden.getProductoId());

                    OrdenCompraDto dto = new OrdenCompraDto();
                    dto.setPrecio(orden.getPrecio());
                    dto.setClienteId(cliente.getId());
                    dto.setProductoId(producto.getId());
                    dto.setId(orden.getId());
                    return dto;
                }).toList();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {

        var op = ocompra.findByIdOptional(id);
        if (op.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(op.get()).build();
    }

    @POST
    public Response create(Orden_Compra ordenCompra) {
        ordenCompra.setId(null);
        ocompra.persist(ordenCompra);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(Orden_Compra ordenCompra, @PathParam("id") Integer id) {
        Orden_Compra obj = ocompra.findById(id);

        obj.setPrecio(ordenCompra.getPrecio());
        obj.setClienteId(ordenCompra.getClienteId());
        obj.setProductoId(ordenCompra.getProductoId());

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        ocompra.deleteById(id);
        return Response.ok().build();
    }

}

