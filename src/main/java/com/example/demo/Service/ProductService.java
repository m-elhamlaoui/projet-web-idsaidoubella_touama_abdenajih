package com.example.demo.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
 // Liste statique de produits (utilisée temporairement si vous n'utilisez pas encore la base de données)
    private final List<Product> products = List.of(
        new Product(1l ,"crème", "Éclat naturel avec notre sérum hydratant à la vitamine C.", 25.99, "cos2.jpg"),
        new Product(2l, "Serum", "Révélez votre beauté intérieure avec notre fond de teint longue tenue.", 39.99, "cos3.jpg"),
        new Product(3l, "Oil Naturel", "Lèvres sublimes grâce à notre rouge à lèvres mat haut de gamme.", 39.99, "cos4.jpg")
    );

    // Récupérer tous les produits
    public List<Product> getAllProducts() {
        return products; // Retourne la liste statique de produits
    }

    // Récupérer un produit par son ID
    public Optional<Product> getProductById(Long id) {
        return products.stream()
            .filter(product -> product.getId().equals(id)) // Filtre les produits par ID
            .findFirst(); // Retourne un Optional<Product>
    }
 

    // Méthodes pour interagir avec la base de données (si vous utilisez ProductRepository)
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    public List<Product> searchProducts(String query) {
        return productRepository.findByNameContainingIgnoreCase(query);
    }
    public List<Product> filterProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    // Enregistrer plusieurs produits
    public List<Product> saveProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }
}