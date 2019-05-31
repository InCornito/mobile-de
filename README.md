Spring Boot application that implements a REST service for manipulating ads and customers. 

1. The classes in this artifact are all in one package called "de.mobile". Create a proper package structure and move the classes to where they belong.
* Proper package structure created
2. Make the domain objects "MobileAd" and "MobileCustomer" persist, either on a local mysqld or a local mongodb.
* Persisted in MongoDB
3. Currently the application only handles read use cases for ads. Extend the application so that ads can also be created.
* Please use "ads -> Add ad" in postman. 
4. There is a MobileCustomer class in the artifact. Extend the application so that it is also possible to create and delete customers.
* Please use "customers -> Register customer" and "customers -> Delete customer" calls in postman. 
5. Make sure that the Ad related use cases become customer-aware (meaning that an ad needs a customer in every case).
* All /ads endpoints are protected, only authorized users can use them. All ads contains customer email(unique id).  
6. Add validation to the calls that create new entities. The following rules should apply:
   - An ad needs a customer id, a make name, a model name and a category.
   - A customer needs a needs a formally valid email address, a firstname and a lastname composed of alphanumeric characters.
* Validation added
7. Configure logging properly so that log messages are logged to a file.
* Logs are located in /spring.log 
8. Implement a proper error handling for 404 and 500, with different error messages. Please describe how to reproduce both error statuses.
* Use "ads -> Get ad by id" call with not existing id in order to get 404 error.
* For sake of test purpose 500 error can be reproduced if use the same "customers -> Register customer" call twice(DuplicateKeyException), because unique email validation is not performed.
9. Change the project so that it builds an executable jar.
* DONE
10. Create a simple HTML/Javascript application that talks to the above REST service. It should be able to list all ads. Making it look pretty would be a plus.
* NOT DONE
11. If the dealer data and the customer data were not accessible from the database but from other RESTful services, what approach would you use to integrate these services?
* I would split functionality to user microservice and ad microservice, add gateway, auth microservices, store data in separate DBs, use k8s for deployment, scaling, and management of containerized applications.
12. If you decide not to write tests for your work for reasons of timeboxing, please spend a few minutes to describe what parts of the application you would write tests for, and what aspects these tests would cover, and what role they play in the development process.
* Common tests are included