## Bank service

In this project used N-tier architecture with DB layer, DAO layer, Service layer and Controllers
layer. <br>
Project was developed according to SOLID principles with authorization and authentication. <br>
Implementation of an analogue of a banking application. <br>

##### Functional: <br>

- CRUD operations with users
- Creating a new account for the user
- Money transfer between accounts
- View information (balance) on the account
- View the history of payments on the account using pagination etc.

### Project Structure

The project has an N-tier structure and consists of the database layer, the DAO layer for
interaction with the database, the service layer which contains the business logic, and the
controllers layer.

#### UML diagram that describes the relationship between the entities

<img src="https://github.com/Vedroid/images-in-readme/blob/main/project_bankservice_uml.png" alt="project_bankservice_uml" width="400"/>

##### Users can perform the following actions:

- Authorization
- View a list of user accounts by user’s phone number
- Money transfer between account
- View information (balance) on the account
- View account payment history using pagination

##### Admins in their turn can:

- Create user
- Update user’s data
- See user’s info by user id
- Find user by phone number
- Remove the user
- Create new user’s account
- Block user account

### Technologies Used

- Java 15
- Spring Boot, Data JPA, Web MVC, Security
- H2Database
- Apache Maven
- Maven Checkstyle Plugin
- Travis
- Swagger
- Lombok
- External Api (To make a currency transfer, an external API is used to find out the current
  exchange rate.)

### To start the project you need:

1) Download and install the
   [JDK](https://www.oracle.com/java/technologies/javase-downloads.html, "Download JDK")
2) Clone this project into your local folder and open the project in an IDE
3) Run a project

### Author

[Vadim Okulov](https://github.com/Vedroid)
