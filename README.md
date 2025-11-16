Une API REST complète pour la gestion de tâches (Todo List) construite avec Spring Boot, JPA, MySQL et documentation Swagger/OpenAPI.

📋 Table des Matières

Fonctionnalités
Technologies Utilisées
Prérequis
Installation
Configuration
Lancement
Endpoints API
Tests
Architecture
Documentation


✨ Fonctionnalités
Opérations CRUD

✅ Créer une nouvelle tâche
✅ Récupérer toutes les tâches
✅ Récupérer une tâche par ID
✅ Mettre à jour une tâche
✅ Supprimer une tâche
✅ Basculer le statut d'une tâche (complété/non complété)

Fonctionnalités Avancées

🔍 Rechercher des tâches par mot-clé
📊 Filtrer par statut (complété/non complété)
🎯 Filtrer par priorité (LOW, MEDIUM, HIGH, URGENT)
⏰ Récupérer les tâches en retard
📅 Gestion des dates d'échéance

Fonctionnalités Techniques

🛡️ Validation des données avec Bean Validation
🚨 Gestion globale des exceptions
📖 Documentation Swagger/OpenAPI automatique
🗄️ Persistance MySQL avec JPA/Hibernate
🧪 Tests unitaires complets
🔄 Mapping automatique avec MapStruct


🛠 Technologies Utilisées
TechnologieVersionDescriptionJava17Langage de programmationSpring Boot3.4.0Framework applicationSpring Data JPA3.4.0Couche de persistanceMySQL8.0+Base de donnéesLombok1.18.30Réduction du boilerplateMapStruct1.5.5Mapping objet-objetSpringDoc OpenAPI2.3.0Documentation APIJUnit 55.10.0Tests unitairesMockito5.3.0Mocking pour testsMaven3.8+Gestionnaire de dépendances

📦 Prérequis
Avant de commencer, assurez-vous d'avoir installé :

☕ Java 17 ou supérieur

bash  java -version

📦 Maven 3.8+

bash  mvn -version

🗄️ MySQL 8.0+

bash  mysql --version

💻 Un IDE (IntelliJ IDEA, Eclipse, VS Code)


🚀 Installation
1. Cloner le projet
   bashgit clone https://github.com/wozeWilfried/todolist-api.git
   cd todolist-api
2. Créer la base de données
   Connectez-vous à MySQL et créez la base de données :
   sqlCREATE DATABASE todolist_db;
   Ou utilisez la création automatique configurée dans application.properties.
3. Configurer l'application
   Modifiez src/main/resources/application.properties :
   propertiesspring.datasource.url=jdbc:mysql://localhost:3306/todolist_db
   spring.datasource.username=VOTRE_USERNAME
   spring.datasource.password=VOTRE_PASSWORD
4. Installer les dépendances
   bashmvn clean install

⚙️ Configuration
Configuration MySQL
Option 1: Configuration manuelle
propertiesspring.datasource.url=jdbc:mysql://localhost:3306/todolist_db
spring.datasource.username=root
spring.datasource.password=password
Option 2: Variables d'environnement
bashexport DB_URL=jdbc:mysql://localhost:3306/todolist_db
export DB_USERNAME=root
export DB_PASSWORD=password
Configuration JPA
properties# Stratégie de mise à jour du schéma
spring.jpa.hibernate.ddl-auto=update

# Afficher les requêtes SQL
spring.jpa.show-sql=true

# Dialecte MySQL
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
Configuration du Port
properties# Port par défaut: 8080
server.port=8080

🎯 Lancement
Méthode 1: Avec Maven
bashmvn spring-boot:run
Méthode 2: Avec le JAR
bash# Générer le JAR
mvn clean package

# Exécuter le JAR
java -jar target/Todo-List-0.0.1-SNAPSHOT.jar
Méthode 3: Depuis l'IDE
Exécutez la classe TodoListApplication.java

🌐 Endpoints API
Base URL
http://localhost:8080/api/v1/todos
Endpoints Disponibles
MéthodeEndpointDescriptionPOST/api/v1/todosCréer une tâcheGET/api/v1/todosRécupérer toutes les tâchesGET/api/v1/todos/{id}Récupérer une tâche par IDPUT/api/v1/todos/{id}Mettre à jour une tâcheDELETE/api/v1/todos/{id}Supprimer une tâchePATCH/api/v1/todos/{id}/toggleBasculer le statutGET/api/v1/todos/status/{completed}Filtrer par statutGET/api/v1/todos/priority/{priority}Filtrer par prioritéGET/api/v1/todos/overdueTâches en retardGET/api/v1/todos/search?keyword={keyword}Rechercher
Exemples de Requêtes
Créer une tâche
bashcurl -X POST http://localhost:8080/api/v1/todos \
-H "Content-Type: application/json" \
-d '{
"title": "Acheter du pain",
"description": "Aller à la boulangerie",
"priority": "HIGH",
"dueDate": "2025-12-31T23:59:59"
}'
Récupérer toutes les tâches
bashcurl http://localhost:8080/api/v1/todos
Mettre à jour une tâche
bashcurl -X PUT http://localhost:8080/api/v1/todos/1 \
-H "Content-Type: application/json" \
-d '{
"title": "Acheter du pain et du lait",
"completed": true,
"priority": "MEDIUM"
}'
Supprimer une tâche
bashcurl -X DELETE http://localhost:8080/api/v1/todos/1

🧪 Tests
Exécuter tous les tests
bashmvn test
Exécuter un test spécifique
bashmvn test -Dtest=TodoServiceTest
Rapport de couverture
bashmvn clean test jacoco:report
Le rapport sera disponible dans target/site/jacoco/index.html
Structure des Tests
src/test/java/com/cwa/Todo_List/
├── services/
│   └── TodoServiceTest.java        # Tests unitaires du service
├── controllers/
│   └── TodoControllerTest.java     # Tests du contrôleur
└── repository/
└── TodoRepositoryTest.java     # Tests du repository

🏗 Architecture
Architecture en Couches
┌─────────────────────────────────────┐
│         Controller Layer            │  ← Endpoints REST
├─────────────────────────────────────┤
│          Service Layer              │  ← Logique métier
├─────────────────────────────────────┤
│        Repository Layer             │  ← Accès aux données
├─────────────────────────────────────┤
│         Database (MySQL)            │  ← Persistance
└─────────────────────────────────────┘
Structure des Packages
com.cwa.Todo_List/
├── controllers/        # Contrôleurs REST
├── services/          # Services métier
├── repository/        # Repositories JPA
├── entities/          # Entités JPA
├── dto/              # Data Transfer Objects
├── mapper/           # Mappers MapStruct
└── exception/        # Gestion des exceptions
Modèle de Données
javaTodo {
Long id
String title
String description
Boolean completed
Priority priority      // LOW, MEDIUM, HIGH, URGENT
LocalDateTime dueDate
LocalDateTime createdAt
LocalDateTime updatedAt
}