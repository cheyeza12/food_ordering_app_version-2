# RESEARCH-DAY-02.md

## Q1. What is a Java generic type? Why is <T> useful?
A generic type allows a class or method to work with any data type. `<T>` is a placeholder that gets replaced with a real type when used. It's useful because we can write one `Response<T>` class that works for `CategoryDto`, `UserDto`, or any other type without rewriting code.

## Q2. What does Lombok @Builder generate?
`@Builder` generates a builder class with a method for each field, plus a `build()` method that creates the object. Instead of `new Response(200, "OK", data, time)`, you write `Response.builder().statusCode(200).message("OK").data(data).build()`.

## Q3. What is the Builder design pattern?
Builder is a creational pattern for constructing complex objects step by step. Use it when an object has many optional fields — instead of many constructors, you chain method calls and call `build()` at the end.

## Q4. What is LocalDateTime? How is it different from Date?
`LocalDateTime` is a modern Java 8+ class representing date and time without a timezone. `Date` is the old class that includes timezone complexity and is harder to use. `LocalDateTime` is cleaner and easier to format.

## Q5. Why does a consistent response format matter to frontend developers?
Frontend developers can write one piece of code to handle all API responses. They always know where to find the data, the status code, and the message — instead of guessing the shape of each response.

## Q6. What does @JsonInclude(JsonInclude.Include.NON_NULL) do?
It tells Jackson not to include fields that are null in the JSON output. So if `data` is null on an error response, it won't appear in the JSON at all.

## Q7. What is a static factory method?
A static factory method is a static method that creates and returns an instance of a class. `Response.success(...)` is cleaner than `new Response<>(...)` because it has a meaningful name and hides the construction details.

---

## Self-Quiz

### Q1. Why use <T> instead of Object for data field?
With `Object` you lose type safety — the compiler can't check what's inside. With `<T>` the compiler knows the exact type and can catch errors at compile time.

### Q2. Difference between Response<T> and ResponseEntity<T>?
`Response<T>` is our custom wrapper that holds statusCode, message, data, timestamp inside the JSON body. `ResponseEntity<T>` is Spring's wrapper that controls the HTTP status code and headers. You can have both: `ResponseEntity<Response<CategoryDto>>`.

### Q3. If a request fails, what statusCode does Response hold?
The HTTP error code — for example 404 for not found, 400 for bad request.

### Q4. Why add a timestamp?
So the client knows exactly when the response was generated. Useful for debugging, logging, and caching.