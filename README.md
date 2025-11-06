# E-commerce System Backend

This is the backend for a simple e-commerce system, built with Spring Boot.

## Project Structure

The project follows a layered architecture, organized by feature function into the following packages:

-   `src/main/java/com/e_commerce_system/backend/`
    -   `controller`: Handles incoming HTTP requests, validates input, and calls the appropriate service methods. It's the entry point for the API.
        -   `OrderController.java`: Manages order creation.
        -   `ProductController.java`: Manages fetching products.
    -   `service`: Contains the core business logic of the application. It orchestrates calls to repositories and other services.
        -   `OrderService.java`: Business logic for creating orders and managing inventory.
        -   `ProductService.java`: Business logic for retrieving product information.
    -   `repository`: This layer is responsible for data access. These interfaces extend Spring Data JPA's `JpaRepository`, which provides standard database operations (CRUD - Create, Read, Update, Delete) out of the box. This layer acts as the **DAO (Data Access Object)**.
        -   `MemberRepository.java`: Data access for `Member` entities.
        -   `OrderRepository.java`: Data access for `Order` entities.
        -   `ProductRepository.java`: Data access for `Product` entities.
    -   `domain`: Contains the JPA entity classes, which are plain Java objects that map to database tables.
        -   `Member.java`: Represents a user.
        -   `Order.java`: Represents a customer order.
        -   `Product.java`: Represents a product for sale.
    -   `dto` (Data Transfer Object): Objects used to transfer data between the client and the server.
        -   `OrderRequest.java`: Carries the data needed to create a new order.

## UML Class Diagram

아래 다이어그램은 이 프로젝트의 주요 클래스와 그 관계를 시각적으로 보여줍니다. 각 패키지(controller, service, repository, domain, dto)의 클래스들이 어떻게 서로 의존하고 상호작용하는지 나타냅니다.

![UML Class Diagram](docs/images/main_diagram.svg)

## Load Testing with k6

This project uses [k6](https://k6.io/) for load testing.

### Prerequisites

- [k6](https://k6.io/docs/getting-started/installation/) installed on your machine.

### Running the Tests

1.  Make sure the backend application is running.
2.  Open a terminal and navigate to the `k6` directory.
3.  Run the following command:

    ```bash
    k6 run scripts/test.js
    ```