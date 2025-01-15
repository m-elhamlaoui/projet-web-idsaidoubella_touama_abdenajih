package com.example.demo.controller;

import com.example.demo.Service.CartService;
import com.example.demo.Service.ProductService;
import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductHTMLController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }

    @PostMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable Long id, Model model) {
        Optional<Product> optionalProduct = productService.getProductById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            cartService.addToCart(product, 1);
            return "redirect:/cart";
        } else {
            return "error/404";
        }
    }

    @GetMapping("/remove-from-cart/{id}")
    public String removeFromCart(@PathVariable Long id) {
    	cartService.removeProductFromCart(id); // Supprime le produit du panier
        return "redirect:/cart"; // Redirige vers la page du panier
    }

    @GetMapping("/product/{id}")
    public String getProductDetails(@PathVariable Long id, Model model) {
        Optional<Product> optionalProduct = productService.getProductById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            model.addAttribute("product", product);
            return "product-details";
        } else {
            return "redirect:/products";
        }
    }

    @GetMapping("/products")
    public String viewProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        List<CartItem> cartItems = cartService.getCartItems();
        double total = cartService.calculateTotal();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);
        return "cart";
    }
    @GetMapping("/confirm-order")
    public String confirmOrder(Model model) {
        // Récupérer les éléments du panier
        List<CartItem> cartItems = cartService.getCartItems();
        double total = cartService.calculateTotal();

        // Ajouter les éléments du panier et le total au modèle
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);

        // Vider le panier après la confirmation
        cartService.clearCart();

        // Retourner la vue de confirmation de commande
        return "order-confirmation";
    }
    @PostMapping("/confirm-order")
    public String confirmOrder(@RequestParam String name,@RequestParam String phone, @RequestParam String address, Model model) {
        // Récupérer les éléments du panier
        List<CartItem> cartItems = cartService.getCartItems();
        double total = cartService.calculateTotal();

        // Ajouter les éléments du panier et le total au modèle
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);

        // Vider le panier après la confirmation
        cartService.clearCart();

        // Rediriger vers la page de confirmation de commande
        return "order-confirmation";
    }

    @GetMapping("/increase-quantity/{id}")
    public String increaseQuantity(@PathVariable Long id) {
        cartService.increaseQuantity(id);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }

   
}