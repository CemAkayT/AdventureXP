package com.example.adventure.controller;

import com.example.adventure.model.Product;
import com.example.adventure.service.ProductJPA;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private ProductJPA productJPA;

    public ProductController(ProductJPA productJPA){
        this.productJPA = productJPA;
    }

@GetMapping
    public ResponseEntity<Set<Product>>getProduct(){
        return new ResponseEntity<>(productJPA.findAll(), HttpStatus.OK);
    }

@GetMapping({"/{id}"})
public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id){
    Optional<Product> product = productJPA.findById(id);
    return new ResponseEntity<>(product, HttpStatus.OK);
}


@PostMapping
    public ResponseEntity<Set<Product>>addProduct(Product name){
        productJPA.save(name);
        return new ResponseEntity<>(productJPA.findAll(), HttpStatus.OK);
}
@DeleteMapping("/{id}")
    public ResponseEntity<Set<Product>>deleteProductById(@PathVariable Product id){
        productJPA.delete(id);
        return new ResponseEntity<>(productJPA.findAll(), HttpStatus.OK);
}
@PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Product product){
        Optional<Product> productTemp = productJPA.findById(product.getId());
        if (productTemp.isPresent()) {
            if (product.getName() == null || product.getName().equals("")) {
                product.setName(productTemp.get().getName());
            }
            if (product.getPrice() == 0) {
                product.setPrice(productTemp.get().getPrice());
            }

            productJPA.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }else{return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);}
    }
}
