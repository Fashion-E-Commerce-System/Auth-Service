# Repository 계층 (DAO)

이 패키지는 데이터베이스에 접근하는 역할을 담당합니다. Spring Data JPA의 `JpaRepository` 인터페이스를 상속받아 데이터베이스의 테이블에 대한 CRUD(Create, Read, Update, Delete) 작업을 간단하게 처리할 수 있도록 합니다.

이 계층은 전통적인 **DAO(Data Access Object)** 의 역할을 수행합니다.

### 핵심 코드 설명

- **`JpaRepository<T, ID>`**: Spring Data JPA가 제공하는 핵심 인터페이스입니다.
    - `T`: 리포지토리가 관리할 엔티티(Entity) 클래스 (예: `Product`)
    - `ID`: 해당 엔티티의 기본 키(Primary Key) 타입 (예: `Long`)
- **`ProductRepository`, `MemberRepository`, `OrderRepository`**: `JpaRepository`를 상속받는 것만으로도 `save()`, `findById()`, `findAll()`, `delete()` 등과 같은 기본적인 데이터 관리 메소드를 별도의 구현 없이 바로 사용할 수 있습니다.

### 시각화 (Mermaid)

![Repository Diagram](../../../../../../../docs/images/repository.svg)
