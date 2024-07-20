package org.example.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.example.db.Cliente;

@ApplicationScoped
@Transactional
public class ClienteRepository implements PanacheRepositoryBase<Cliente, Integer> {
}
