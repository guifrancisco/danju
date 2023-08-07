package org.github.guifrancisco.danju.infra.repository;

import org.github.guifrancisco.danju.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}
