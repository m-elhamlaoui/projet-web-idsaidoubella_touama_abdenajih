# **Projet : Gestion d’un Catalogue de Produits Cosmétiques**

## **Table des Matières**
1. [Introduction](#introduction)  
2. [Fonctionnalités principales](#fonctionnalités-principales)  
3. [Architecture et technologies utilisées](#architecture-et-technologies-utilisées)  
4. [Design Patterns intégrés](#design-patterns-intégrés)  
5. [Organisation des tâches et GitHub](#organisation-des-tâches-et-github)  

---

## **1. Introduction**

Ce projet vise à développer une application web pour la gestion d’un catalogue de produits cosmétiques.  
L’application permettra :  
- **Aux administrateurs** : Ajouter, modifier, ou supprimer des produits.  
- **Aux clients** : Parcourir le catalogue, filtrer les produits selon leurs préférences, et ajouter des articles à un panier virtuel.  

L’application sera :  
- Multilingue (Français et Anglais).  
- Compatible avec plusieurs bases de données relationnelles.  
- Développée en respectant les bonnes pratiques de programmation orientée objet.  

---

## **2. Fonctionnalités principales**

### **Pour les administrateurs**  
- Ajouter de nouveaux produits avec des attributs : nom, description, prix, catégorie, image.  
- Modifier ou supprimer les produits existants.  

### **Pour les clients**  
- Parcourir le catalogue de produits avec une interface intuitive.  
- Filtrer les produits selon des critères comme :  
  - **Catégorie** : soins, maquillage, accessoires, etc.  
  - **Prix** : plage de prix personnalisée.  
- Rechercher un produit par mot-clé.  
- Ajouter des produits à un panier virtuel.  

### **Multilingue**  
- Interface et contenu accessibles en Français et en Anglais.  
- Messages d’interface et descriptions des produits adaptés dynamiquement selon la langue choisie.

---

## **3. Architecture et technologies utilisées**

### **Technologies principales**  
1. **Backend** :  
   - **Java avec Spring Boot** :  
     - Gestion des requêtes via REST APIs.  
     - Sécurisation avec Spring Security (hachage des mots de passe).  

2. **Frontend** :  
   - HTML/CSS (Bootstrap pour un design moderne et responsive).  
   - Optionnel : React.js pour les interactions avancées côté client.  

3. **Base de données** :  
   - PostgreSQL ou MySQL (choix flexible grâce à Hibernate/JPA).  

### **Outils**  
- **Maven** : Système de build pour gérer les dépendances et les scripts.  
- **Git/GitHub** : Suivi de version et collaboration d’équipe.  

---

## **4. Design Patterns Intégrés**

### **Singleton**  
- Utilisé pour gérer une connexion unique à la base de données via une classe `DatabaseConnection`.

### **DAO (Data Access Object)**  
- Permet d’interagir proprement avec la base de données :  
  - Classe `ProductDAO` pour gérer les produits.  
  - Classe `UserDAO` pour gérer les utilisateurs.  

### **Strategy**  
- Implémenté pour les filtres dynamiques sur les produits :  
  - Classe `FilterStrategy` avec des implémentations comme `CategoryFilter` et `PriceFilter`.  

### **MVC (Model-View-Controller)**  
- Architecture adoptée pour séparer les responsabilités :  
  - **Model** : Définition des entités (e.g., `Product`, `User`).  
  - **View** : Interfaces utilisateur en HTML/CSS.  
  - **Controller** : Gestion des requêtes et des réponses.  

---

## **5. Organisation des tâches et GitHub**

### **Structure de gestion des tâches**
- Les fonctionnalités sont divisées en **issues GitHub** :  
  - `feature`: Pour chaque nouvelle fonctionnalité (e.g., "Ajouter un produit").  
  - `bug`: Pour corriger les problèmes.  
  - `documentation`: Mise à jour du README et autres fichiers.  

### **Branches Git**  
- Une branche par fonctionnalité ou correctif (e.g., `feature/add-product`).  
- Les branches sont fusionnées dans la branche principale (`main`) après revue.  

### **Étapes initiales**  
1. Créer les issues pour chaque tâche.  
2. Configurer le dépôt avec Maven et Spring Boot.  
3. Développer les fonctionnalités en suivant les bonnes pratiques SOLID et Clean Code.  
