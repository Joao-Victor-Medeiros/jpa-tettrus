package com.tettrus.tecnologia.jpatettrus.Product;

public record ProductDto(Long id, String nome, int quantidade) {
    public ProductDto(Product product) {
        this(product.getId(), product.getNome(), product.getQuantidade());
    }
}
