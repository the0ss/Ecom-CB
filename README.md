
# Ecomm Backend

Implemented CRUD APIs for ecommerce using Java and Spring Boot.
And also added unit tests.

## APIs

- Create with Post
    ```
    http://localhost:8080/product
    ```
    ```
    {
    "name":"Colgate",
    "description":"used to clean teeth",
    "price":100.00,
    "quantityAvailable":10
    }
    ```
- Retrieve with Get(pId)
    ```
    http://localhost:8080/product/1
    ```
- Update with Put
    ```
    http://localhost:8080/product/1
    ```
    ```
    {
    "name":"Pepsodent",
    "description":"used to clean teeth and toungue",
    "price":100.0,
    "quantityAvailable":10
    }
    ```
- Delete product with Delete(pId)
    ```
    http://localhost:8080/product/1
    ```
- Apply Tax and Discount with Post
    ```
    http://localhost:8080/product/1/apply-discount-tax?discountOrTax=2&applicableValue=10
    ```

