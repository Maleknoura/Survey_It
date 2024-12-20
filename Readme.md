# API REST Survey IT

## **Description**
Survey IT est une application de sondages dans le domaine de l'informatique, développée en utilisant **Spring Boot**. Elle permet de gérer des surveys structurés en chapitres, sous-chapitres, et questions avec des types de réponses variés. L'application offre des fonctionnalités pour participer aux surveys et consulter les résultats sous forme de statistiques.

---

## **Contexte**
Survey IT a été conçue pour répondre aux besoins de l'application ITLens :
1. **Création et gestion des surveys** avec leur structure hiérarchique (chapitres, sous-chapitres, questions).
2. **Participation aux surveys** avec enregistrement des réponses des utilisateurs.
3. **Consultation des résultats** sous forme de statistiques et pourcentages.

---

## **Fonctionnalités principales**

### **CRUD par entité**
Chaque entité dispose d'un ensemble complet d'opérations CRUD.

#### **Survey**
- **POST** `/surveys` : Créer un nouveau survey.
- **GET** `/surveys` : Lister tous les surveys.
- **GET** `/surveys/{id}` : Obtenir les détails d'un survey.
- **PUT** `/surveys/{id}` : Mettre à jour un survey.
- **DELETE** `/surveys/{id}` : Supprimer un survey.

#### **SurveyEdition**
- **POST** `/survey-editions` : Créer une nouvelle édition d’un survey.
- **GET** `/survey-editions` : Lister toutes les éditions.
- **GET** `/survey-editions/{id}` : Obtenir les détails d'une édition.
- **PUT** `/survey-editions/{id}` : Mettre à jour une édition.
- **DELETE** `/survey-editions/{id}` : Supprimer une édition.

#### **Chapter**
- **POST** `/chapters` : Ajouter un chapitre à un survey.
- **GET** `/chapters` : Lister tous les chapitres.
- **GET** `/chapters/{id}` : Obtenir les détails d'un chapitre.
- **PUT** `/chapters/{id}` : Mettre à jour un chapitre.
- **DELETE** `/chapters/{id}` : Supprimer un chapitre.

#### **SubChapter**
- **POST** `/subchapters` : Ajouter un sous-chapitre à un chapitre.
- **GET** `/subchapters` : Lister tous les sous-chapitres.
- **GET** `/subchapters/{id}` : Obtenir les détails d'un sous-chapitre.
- **PUT** `/subchapters/{id}` : Mettre à jour un sous-chapitre.
- **DELETE** `/subchapters/{id}` : Supprimer un sous-chapitre.

#### **Question**
- **POST** `/questions` : Ajouter une question à un sous-chapitre.
- **GET** `/questions` : Lister toutes les questions.
- **GET** `/questions/{id}` : Obtenir les détails d'une question.
- **PUT** `/questions/{id}` : Mettre à jour une question.
- **DELETE** `/questions/{id}` : Supprimer une question.

#### **Answer**
- **POST** `/answers` : Ajouter une réponse à une question.
- **GET** `/answers` : Lister toutes les réponses.
- **GET** `/answers/{id}` : Obtenir les détails d'une réponse.
- **PUT** `/answers/{id}` : Mettre à jour une réponse.
- **DELETE** `/answers/{id}` : Supprimer une réponse.

---

## **Gestion des entités**
Chaque entité dispose de sa propre gestion organisée avec un contrôleur, un service, et un repository.

### **Exemple : Gestion d’un Survey**
1. **Controller (`SurveyController`)** :
    - Contient les endpoints REST exposés à l'utilisateur.
    - Utilise des DTO pour les échanges de données.

2. **Service (`SurveyService`)** :
    - Implémente la logique métier (création, mise à jour, suppression).
    - Vérifie les règles de gestion.

3. **Repository (`SurveyRepository`)** :
    - Utilise Spring Data JPA pour interagir avec la base de données.

### **Exemple : Gestion d’un Chapter**
1. **Controller (`ChapterController`)** :
    - Expose les endpoints liés aux chapitres.
    - Valide les données reçues.
2. **Service (`ChapterService`)** :
    - Responsable de la création et de la modification des chapitres.
3. **Repository (`ChapterRepository`)** :
    - Gestion de la persistance des chapitres.

*Les autres entités suivent une structure similaire.*

---

## **Endpoints de l'API**
Exemples pour une entité spécifique (Question) :
- **POST** `/questions` : Ajouter une nouvelle question.
- **GET** `/questions` : Récupérer toutes les questions.
- **GET** `/questions/{id}` : Obtenir les détails d'une question.
- **PUT** `/questions/{id}` : Mettre à jour une question existante.
- **DELETE** `/questions/{id}` : Supprimer une question.

---

## **Technologies utilisées**
- **Backend** : Spring Boot
    - **Spring Web** : Exposition de l'API REST.
    - **Spring Data JPA** : Gestion de la persistance.
    - **Lombok** : Simplification du code.
    - **MapStruct** : Mappage entre DTO et entités.
    - **JUnit 5 & Mockito** : Tests unitaires et tests de services.
    - **@RestControllerAdvice** : Gestion centralisée des exceptions.
    - **Swagger** : Documentation automatique.
- **Base de données** : PostgreSQL.

---

## **Installation et utilisation**
1. Clonez le dépôt GitHub :
   ```bash
   git clone https://github.com/Maleknoura/Survey_It.git
   cd Survey_It
