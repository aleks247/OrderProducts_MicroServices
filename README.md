Assignment: Design and implement a microservices-based system using Feign
client as a communication mechanism between services. The system should
consist of two services: a "Product Service" and an "Order Service." The Product
Service is responsible for managing products, while the Order Service handles
order processing.
Tasks:
1. Set up a new Spring Boot project for each service (Product Service and
Order Service).
2. Create a RESTful API for the Product Service that includes endpoints for
creating, retrieving, updating, and deleting products.
3. Implement the necessary functionality in the Product Service to handle
the API endpoints.
4. Use Feign client in the Order Service to consume the Product Service API.
Define Feign client interfaces in the Order Service for accessing the
Product Service endpoints.
5. Create RESTful API endpoints in the Order Service for placing new orders.
These endpoints should interact with the Product Service through the
Feign client.
6. Implement the necessary functionality in the Order Service to handle the
API endpoints for order placement, including making requests to the
Product Service.
7. Test the system by starting both services and using a tool like Postman or
cURL to interact with the API endpoints.