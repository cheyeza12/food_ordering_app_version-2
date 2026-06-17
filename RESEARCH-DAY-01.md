RESEARCH-DAY-01.md

1.What does CRUD stand for?

CRUD stands for Create, Read, Update, and Delete. These are the four basic operations performed on data in an application. Create adds new data, Read retrieves data, Update modifies existing data, and Delete removes data.

2.Difference between HTTP methods POST, PUT, PATCH, and DELETE?
POST is used to create.

PUT is used to update.

PATCH is used to update only specific fields.

DELETE is used to remove.

Example:

POST /api/categories → Create a category.

PUT /api/categories/1 → Replace the category data.

PATCH /api/categories/1 → Update only the name field.

DELETE /api/categories/1 → Delete category 1.


# 3.Give the correct HTTP status code for each:

a. A new category was created

201 Created

b. A category was deleted successfully

204 No Content

c. The id requested does not exist

404 Not Found

d. The request body is missing a required field

400 Bad Request

e. The user is logged in but not allowed

403 Forbidden.


4.Difference between @RequestBody, @RequestParam, and @PathVariable
@RequestBody

**Used to receive data from the request body.**

@PostMapping("/categories")
public CategoryDto createCategory(@RequestBody CategoryDto dto) {
return dto;
}
@RequestParam

**Used to get values from query parameters.**

@GetMapping("/categories")
public String getCategory(@RequestParam String name) {
return name;
}

URL Example:

/api/categories?name=Food
@PathVariable

**Used to get values directly from the URL path.**

@GetMapping("/categories/{id}")
public Long getCategory(@PathVariable Long id) {
return id;
}

URL Example:

/api/categories/1


# 5.What is Jakarta Bean Validation? Explain @Valid, @NotBlank, and @Size.

Jakarta Bean Validation is a framework that validates user input before it is processed by the application. It helps ensure data is correct and follows defined rules.

@Valid Triggers validation on an object.

public ResponseEntity<CategoryDto> create(
@RequestBody @Valid CategoryDto dto) {
}
@NotBlank

Ensures a field is not null, empty, or only spaces.

@NotBlank(message = "Category name is required")
private String name;
@Size

Restricts the minimum and maximum length of a field.

@Size(min = 2, max = 50)
private String name;

6.Why return a DTO and not the entity itself? Give 2 reasons.
* Security

A DTO allows us to hide sensitive fields that should not be exposed to clients.

* Better API Design

A DTO lets us control exactly what data is sent to the client without exposing the internal structure of the database entity.

7.What is Optional<T>? Why does findById return Optional?

Optional<T> is a container object that may contain a value or may be empty.

findById() returns an Optional because the requested record might not exist in the database. Using Optional helps avoid NullPointerException and forces developers to handle the case where no data is found.

Example:

Optional<Category> category = categoryRepository.findById(id);

if (category.isPresent()) {
return category.get();
}

# SELF-QUIZ

Q1. Why ResponseEntity instead of returning the object?

ResponseEntity gives full control over the HTTP response, including the status code, headers, and response body. For example, a successful create operation can return 201 Created instead of the default 200 OK.

Q2. What status should a successful DELETE return? Why?

A successful DELETE should return 204 No Content because the resource was removed successfully and there is no response body to return.

Q3. Update only one field  PUT or PATCH? Defend your answer.
Q3. Update only one field  PUT or PATCH? Defend your answer.

PATCH is more appropriate because it is designed for partial updates. If only one field needs to change, PATCH updates that field without requiring the entire resource to be sent.

Q4. What happens if you forget @Valid on the controller?

Validation annotations such as @NotBlank and @Size will not be executed. Invalid data may be accepted and saved to the database.

Q5. Why must update/delete have {id} in the URL but create does not?

Update and delete operations need to know exactly which existing resource to modify or remove, so an ID is required. Create does not need an ID because the system generates a new resource and assigns its ID automatically.
