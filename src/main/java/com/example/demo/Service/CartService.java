package com.example.demo.Service;

import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private List<CartItem> cartItems = new ArrayList<>();

    // Ajouter un produit au panier
    public void addToCart(Product product, int quantity) {
        // Vérifier si le produit est déjà dans le panier
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(product.getId())) {
                // Si le produit existe déjà, mettre à jour la quantité
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        // Si le produit n'est pas dans le panier, l'ajouter
        cartItems.add(new CartItem(product, quantity));
    }
    public void increaseQuantity(Long productId) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(item.getQuantity() + 1); // Augmente la quantité de 1
                break;
            }
        }
    }
 
    // Récupérer les éléments du panier
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    // Calculer le total du panier
    public double calculateTotal() {
        return cartItems.stream()
            .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
            .sum();
    }
    public void removeProductFromCart(Long productId) {
        cartItems.removeIf(item -> item.getProduct().getId().equals(productId));
    }
    public void clearCart() {
        cartItems.clear(); // Supprime tous les éléments du panier
    }

    // Supprimer un produit du panier (optionnel)
    public void removeFromCart(Long productId) {
        cartItems.removeIf(item -> item.getProduct().getId().equals(productId));
    }
}