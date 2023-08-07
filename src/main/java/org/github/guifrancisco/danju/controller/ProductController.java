package org.github.guifrancisco.danju.controller;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.github.guifrancisco.danju.domain.dto.DataProduct;
import org.github.guifrancisco.danju.domain.dto.DataRegisterProduct;
import org.github.guifrancisco.danju.domain.dto.DataUpdateProduct;
import org.github.guifrancisco.danju.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("v1/product")
@RestController
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> registerProduct(@RequestBody DataRegisterProduct dataRegisterProduct){
        log.info("[ProductController.registerProduct] - [Controller]");
        productService.registerProduct(dataRegisterProduct);
        return new ResponseEntity<>("Product created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable String id, @RequestBody DataUpdateProduct dataUpdateProduct){
        log.info("[ProductController.updateProduct] - [Controller]");
        productService.updateProduct(id,dataUpdateProduct);
        return ResponseEntity.ok("Product updated successfully");
    }
    @GetMapping
    public ResponseEntity<Page<DataProduct>> findAllProducts(@PageableDefault(size = 10) Pageable pageable){
        log.info("[ProductController.findAllProducts] - [Controller]");
        Page<DataProduct> products = productService.findAllProducts(pageable);
        return ResponseEntity.ok().body(products);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id){
        log.info("[ProductController.deleteProduct] - [Controller]");
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
