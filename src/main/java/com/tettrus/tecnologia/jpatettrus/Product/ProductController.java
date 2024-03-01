package com.tettrus.tecnologia.jpatettrus.Product;

import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;


import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/apiv1.0.0/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepo;

    @PostMapping
    @Transactional
    public ResponseEntity<Product> save(@RequestBody ProductDto entity) {
        var prod = new Product(entity);
        System.out.println(entity.nome());
        System.out.println(prod.quantidade);
        productRepo.save(prod);
        
        return ResponseEntity.ok(prod);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDto>> listProducts(@PageableDefault(sort = {"nome"}) Pageable pageable){
        var page = productRepo.findAll(pageable).map(ProductDto::new);
        return ResponseEntity.ok(page);
    }
    

    @PutMapping()
    @Transactional
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto ataulizaProductDto) {
        Product product = productRepo.getReferenceById(ataulizaProductDto.id());
        product.updateProduct(ataulizaProductDto);

        return ResponseEntity.ok(new ProductDto(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable("id") Long id) throws Exception {
        if(productRepo.existsById(id)){
            this.productRepo.deleteById(id);
        }else {
            throw new Exception("id do produto não encontrado");
        }
        
        return ResponseEntity.noContent().build();
    }
}
