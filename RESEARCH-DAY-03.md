# RESEARCH-DAY-03.md

## Q1. What is JPA? What is Hibernate? How are they related?

JPA (Java Persistence API) is a Java specification that defines how Java objects are mapped to relational databases. Hibernate is a framework that implements the JPA specification. In simple terms, JPA provides the rules, while Hibernate provides the actual implementation.

## Q2. What is the difference between @Entity and @Table?

`@Entity` marks a Java class as a JPA entity that can be stored in a database table. `@Table` specifies the name and details of the database table. If `@Table` is not provided, JPA uses the class name as the table name by default.

## Q3. What is a foreign key? What is @ManyToOne? Give 2 real-world examples.

A foreign key is a column that links one table to another and maintains referential integrity.

`@ManyToOne` represents a relationship where many records belong to one parent record.

Examples:

1. Many menu items belong to one category.
2. Many employees belong to one department.

## Q4. What does @JoinColumn(name = "category_id") do?

It specifies the foreign key column used to connect two tables. In the Menu entity, `category_id` stores the ID of the related Category.

## Q5. Why store price as BigDecimal and not double?

`BigDecimal` provides precise decimal calculations and avoids rounding errors. `double` can introduce small precision errors, which is unacceptable when working with money.

## Q6. What does FetchType.LAZY vs EAGER mean? What is the default for @ManyToOne?

* LAZY: Data is loaded only when needed.
* EAGER: Data is loaded immediately with the parent entity.

The default fetch type for `@ManyToOne` is `FetchType.EAGER`.

## Q7. What is the N+1 query problem?

The N+1 query problem occurs when one query loads a list of entities and then additional queries are executed for each entity to load related data. This results in many unnecessary database queries and poor performance.

## Q8. What is dependency injection? Constructor injection vs field injection — which is preferred and why?

Dependency Injection (DI) is a design pattern where dependencies are provided to a class instead of being created inside the class.

Constructor injection is preferred because:

* Dependencies are required at object creation.
* Easier to test.
* Supports immutable fields using `final`.
* Makes dependencies explicit.

## Q9. What does @RequiredArgsConstructor (Lombok) do?

`@RequiredArgsConstructor` automatically generates a constructor for all `final` fields and fields marked with `@NonNull`. It reduces boilerplate code and supports constructor injection.

## Q10. What is the role of the SERVICE layer? Why must it be separate from the controller?

The Service layer contains business logic and application rules. Controllers should only handle HTTP requests and responses. Separating them improves maintainability, testing, and code organization.

## Q11. Why MUST you validate that categoryId exists before saving a menu?

To prevent creating menu items that reference categories that do not exist. This maintains data integrity and avoids foreign key constraint violations.

## Q12. Difference between save() and saveAndFlush()?

* `save()` stores the entity and waits until the persistence context is flushed.
* `saveAndFlush()` immediately writes changes to the database.

`saveAndFlush()` is useful when immediate database synchronization is required.

## Q13. Why write private mapper methods (entity <-> dto)?

Mapper methods separate conversion logic from business logic, improve code reuse, reduce duplication, and make the service easier to maintain and test.

# SELF-QUIZ

## Q1. Why didn't we add @OneToMany on Category for menus?

We only needed a unidirectional relationship from Menu to Category. Adding `@OneToMany` would make the relationship bidirectional, increasing complexity and potentially causing unnecessary data loading. Since the application only needs to access the Category from a Menu, `@ManyToOne` is sufficient.

## Q2. What would ddl-auto = create-drop do? When would you use it?

`ddl-auto=create-drop` tells Hibernate to create all database tables when the application starts and drop them when the application stops. It is useful during development and testing because it provides a clean database each time the application runs. It should not be used in production because it deletes all data.

## Q3. If you delete a Category that has menus, what happens by default?

By default, the database will prevent the deletion if there are Menu records referencing that Category through a foreign key. The delete operation fails because it would violate referential integrity.

## Q4. Why is BigDecimal better than double for storing money values?

`BigDecimal` provides exact decimal precision and avoids rounding errors. `double` uses floating-point arithmetic, which can introduce small inaccuracies. Since financial calculations require precision, `BigDecimal` is the preferred choice for storing money values.
