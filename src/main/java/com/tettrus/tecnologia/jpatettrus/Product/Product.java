package com.tettrus.tecnologia.jpatettrus.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Table(name="products")
@Entity
public class Product {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    int quantidade;

    Product(){}

    public Product(ProductDto entity) {
        this.nome = entity.nome();
        this.quantidade = entity.quantidade();
    }

    public void updateProduct(ProductDto productDto) {
        if(productDto.nome() != null) {
            this.nome = productDto.nome();
        }
        if(productDto.quantidade() != 0) {
            this.quantidade = productDto.quantidade();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
