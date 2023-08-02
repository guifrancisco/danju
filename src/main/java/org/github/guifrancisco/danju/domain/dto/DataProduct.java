package org.github.guifrancisco.danju.domain.dto;

import org.github.guifrancisco.danju.domain.entity.Product;

public record DataProduct(
        String id,

        String name,

        String description,

        double price
) {
    public DataProduct(Product product){
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }
}
