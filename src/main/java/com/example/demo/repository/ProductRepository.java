package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Rechercher des produits par nom (insensible à la casse).
     *
     * @param name Le nom ou une partie du nom du produit à rechercher.
     * @return Une liste de produits correspondant au nom recherché.
     */
    List<Product> findByNameContainingIgnoreCase(String name);

    /**
     * Filtrer les produits par catégorie.
     *
     * @param category La catégorie des produits à filtrer.
     * @return Une liste de produits appartenant à la catégorie spécifiée.
     */
    List<Product> findByCategory(String category);
}