package org.github.guifrancisco.danju.service;

import lombok.extern.slf4j.Slf4j;
import org.github.guifrancisco.danju.domain.dto.DataProduct;
import org.github.guifrancisco.danju.domain.dto.DataRegisterProduct;
import org.github.guifrancisco.danju.domain.dto.DataUpdateProduct;
import org.github.guifrancisco.danju.domain.entity.Product;
import org.github.guifrancisco.danju.repository.ProductRepository;
import org.github.guifrancisco.danju.service.mapper.ProductMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;


    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public void registerProduct(DataRegisterProduct dataRegisterProduct){
        log.info("[ProductService.registerProduct] - [Service]");
        Product product = productMapper.toEntity(dataRegisterProduct);
        productRepository.save(product);
    }

    public void updateProduct(String id,DataUpdateProduct dataUpdateProduct){
        log.info("[ProductService.updateProduct] - [Service]");
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with id " + id + " not found"));
        productMapper.updateEntityFromDto(product, dataUpdateProduct);
        productRepository.save(product);
    }

    public void deleteProduct(String id){
        log.info("[ProductService.deleteProduct] - [Service]");
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with id " + id + " not found"));
        productRepository.delete(product);
    }

    public Page<DataProduct> findAllProducts(Pageable pageable){
        log.info("[ProductService.findAllProducts] - [Service]");
        Page<Product> products = productRepository.findAll(pageable);
        return  products.map(productMapper::toDto);
    }


}
