package com.marcio.helpdesk.repositories;

import com.marcio.helpdesk.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Cliente, Integer> {
}
