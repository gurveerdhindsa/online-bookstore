# online-bookstore
[![Build Status](https://travis-ci.com/gurveerdhindsa/online-bookstore.svg?token=Q7Wj8LGyEKmLYx5gvdog&branch=master)](https://travis-ci.com/gurveerdhindsa/online-bookstore)

# https://amazin-online-bookstore.herokuapp.com/

<p align="center">
  <img src="screenshots/web/milestone-2-web-application.png">
</p>

>Bookstore Owner can upload and edit Book information (ISBN, picture, description, author, publisher,...) and inventory. User can search for, and browse through, the books in the Bookstore, sort/filter them based on the above information. User can then decide to purchase one or many books by putting them in the Shopping Cart and proceeding to Checkout. The purchase itself will obviously be simulated, but purchases cannot exceed the inventory. User can also view Book Recommendations based on past purchases. This is done by looking for users whose purchases are most similar (using Jaccard distance: Google it!), and then recommending books purchased by those similar users but that the current User hasn't yet purchased.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
For building and running the application you need:
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

### Installing
First, clone this repository to your local machine using `https://github.com/gurveerdhindsa/online-bookstore.git`

### Running the application locally
Option 1:
- Execute the main method in src/main/java/Repository/Launcher.java

Option 2:
```
mvn spring-boot:run
```

## Running the tests
```
mvn test
```

## Built With
* [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management

## Kanban
#### Current state
##### Milestone 1
The application is integrated with Travis CI and running a production build on Heroku. The application displays a collection of books which is fetched from a NoSQL database. Currently, there is enough functionality to get a feel for how the system will work. On the frontend, there is a search engine, and filters the displayed books. There are author and genre filters that are populated based on all books in the bookstore. On the backend, there is an endpoint in place to return every book and its attributes.
##### Milestone 2
Currently, the user view of the application is fully functional. The user can search, and filter through the various books in the bookstore. The search engine takes in the title of a book and displays every book that matches the criteria. In addition, there are genre and author filters in place. On the backend, there /title endpoint was upgraded to accomodate the other filters. The inventory of each book is verified prior to adding the item to the shopping cart and the checkout phase is fully functional. User and transactions are added in the Users database table.

As for the feedback from Milestone 1, we have created a UML diagram and removed UI components that do not have functionality. Also, we pass database credentials via environment variables on Heroku/Travis CI.

#### Milestone 3
In the final milestone, the admin view is fully developed. This view allows an admin to modify attributes of each book in the bookstore. The recommendations for a specific user is calculated and displayed accordingly in the web application.

## Database Schema
#### Books database schema
![Books database schema](screenshots/database/DB_schema.PNG)

#### User database schema
![User database schema](screenshots/database/user_tableSchema.PNG)


#### UML Diagram 
![Uml Diagram](screenshots/database/UML.jpg)

## Authors
* **Abubakar Abdulsalam**
* **Gurveer Dhindsa**
* **Rohan Katkar**

## Acknowledgments
* README inspiration from [PurpleBooth](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2)
