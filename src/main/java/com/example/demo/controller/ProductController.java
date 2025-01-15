package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Controller // Pour supporter les vues Thymeleaf
@RequestMapping("/api/products") // Pour les routes web
public class ProductController {

    @Autowired
    private ProductService productService;

    // === Routes pour les vues Thymeleaf ===

    // Affiche la liste des produits
    @GetMapping("/products")
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products); // Injecte la liste des produits dans la vue
        return "products"; // Retourne le fichier products.html
    }
    public String getProducts(@RequestParam(required = false) String query,
            @RequestParam(required = false) String category,
            Model model) {
List<Product> products;

if (query != null && !query.isEmpty()) {
// Rechercher des produits par nom
products = productService.searchProducts(query);
} else if (category != null && !category.isEmpty()) {
// Filtrer les produits par catégorie
products = productService.filterProductsByCategory(category);
} else {
// Afficher tous les produits
products = productService.getAllProducts();
}

// Ajouter les produits au modèle
model.addAttribute("products", products);

// Retourner le nom de la vue Thymeleaf
return "products"; // Assurez-vous que "products" correspond au nom de votre fichier HTML
}

    // Affiche les détails d'un produit
    @GetMapping("/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get()); // Injecte le produit dans la vue
            return "product-details"; // Retourne le fichier product-details.html
        } else {
            return "error/404"; // Retourne une page d'erreur 404
        }
    }

    // === Routes pour l'API REST (utilisables avec Postman) ===

    // Récupérer tous les produits (API)
    @GetMapping("/api")
    @ResponseBody // Indique que la réponse est au format JSON
    public List<Product> getAllProductsApi() {
        return productService.getAllProducts();
    }

    // Ajouter un nouveau produit (API)
    @PostMapping("/api")
    @ResponseBody // Indique que la réponse est au format JSON
    public Product addProductApi(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    // Ajouter plusieurs produits en une seule requête (API)
    @PostMapping("/api/batch")
    @ResponseBody // Indique que la réponse est au format JSON
    public List<Product> addProductsApi(@RequestBody List<Product> products) {
        return productService.saveProducts(products);
    }

    // Récupérer un produit par son ID (API)
    @GetMapping("/api/{id}")
    @ResponseBody // Indique que la réponse est au format JSON
    public Optional<Product> getProductByIdApi(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // Mettre à jour un produit (API)
    @PutMapping("/api/{id}")
    @ResponseBody // Indique que la réponse est au format JSON
    public Product updateProductApi(@PathVariable Long id, @RequestBody Product productDetails) {
        Optional<Product> existingProduct = productService.getProductById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(productDetails.getName());
            product.setDescription(productDetails.getDescription());
            product.setPrice(productDetails.getPrice());
            product.setCategory(productDetails.getCategory());
            product.setImageUrl(productDetails.getImageUrl());
            return productService.saveProduct(product);
        }
        return null;
    }

    // Supprimer un produit (API)
    @DeleteMapping("/api/{id}")
    @ResponseBody // Indique que la réponse est au format JSON
    public void deleteProductApi(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}