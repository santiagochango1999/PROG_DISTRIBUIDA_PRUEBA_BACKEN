package org.example.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.example.db.Orden_Compra;

@ApplicationScoped
@Transactional
public class Orden_Compra_Repository implements PanacheRepositoryBase<Orden_Compra, Integer> {
}
