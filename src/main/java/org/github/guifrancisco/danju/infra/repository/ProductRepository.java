package org.github.guifrancisco.danju.infra.repository;

import org.github.guifrancisco.danju.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, String> {

}
