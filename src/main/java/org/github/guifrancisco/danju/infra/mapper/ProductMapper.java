package org.github.guifrancisco.danju.infra.mapper;

import org.github.guifrancisco.danju.domain.dto.DataProduct;
import org.github.guifrancisco.danju.domain.dto.DataRegisterProduct;
import org.github.guifrancisco.danju.domain.dto.DataUpdateProduct;
import org.github.guifrancisco.danju.domain.entity.Customer;
import org.github.guifrancisco.danju.domain.entity.Product;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class ProductMapper {

    public Product toEntity(DataRegisterProduct dataRegisterProduct){
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setName(dataRegisterProduct.name());
        product.setDescription(dataRegisterProduct.description());
        product.setPrice(dataRegisterProduct.price());
        return product;
    }

    public DataProduct toDto(Product product){
        return new DataProduct(product);
    }

    public void updateEntityFromDto(Product product, DataUpdateProduct dataUpdateProduct){
        if(dataUpdateProduct.name() != null){
            product.setName(dataUpdateProduct.name());
        }
        if (dataUpdateProduct.description() != null){
            product.setDescription(dataUpdateProduct.description());
        }
    }
}
