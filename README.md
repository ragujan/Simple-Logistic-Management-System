
#  Simple Logistic Management System


This application is to enhance the knowledge on Java EE development, that includes EJB, Transaction Management, JPA, JTA, JDBC security. 
This is very simple and minimalistic Logistic management system. There's only two characters use this application, Admin and Merchant. Admin can register routes, add Freights. Merchant can register products, and make orders. Both parties will be able to track the order/freight progress. 

### Detailed explanation

There are mainly two Freight Retrieval beans; to get the freight status, Freight data is stored in “freight” table, while the live updates of a moving freight will be available on “freight_tracking” table. It stores freight coordinates, freight’s route progress will be stored in percentage, would also be able to track the delay time very accurately.  

Freight Tracking attributes  

Id, coordinates, route_progress, expected_delay, freight_id. 

To track the order that is shipped in the freight, there is a table called “freight_has_orders”, which will record the freight’s id and order’s id, which can be useful to track the orders.  
 
Relationships ( -- represents a one to one,  -* represents a many relationship) 

freight -- -* freight_tracking 

freight -- -* freight_has_orders  

orders -- -* freight_has_orders 

Once the freight started to move, the order status will be changed to “has_started” 

A Destination will have multiple routes, route will be added to the freight table. Example of a destination would look like this  

London - Paris - Rome - Cairo – Dubai 

Routes of a destination would look like this  

    London 

    Paris 

    Rome 

    Cairo 

    Dubai 

Each route will be added to a freight separately, in the client side, in this case the merchant 

A Merchant will be seeing the whole destination when making an order, instead of separate routes, which will make more sense. These destinations are filtered from the only available freight.  

If an available freight’s first route hasn’t started the journey yet, That destination will be available for the merchant to choose. If there are more than destinations are in the freight  
and one started the journey and other freight hasn’t, that destination will be still available. 

Available destination will be retrieved based on the first route’s status; it must be not marked as "started" in the freight list. 

### To run the application

You must have to have glassfish 7.0.12

Glassfish server configuration 
For JDBC Resource 

* Make JDBC Connection Pool
Add a Name, 	
Pool Name:  webapp

Resource Type - javax.sql.DataSource
Datasource Classname - com.mysql.cj.jdbc.MysqlDataSource

Add DB Connection values such as Password, root, host, db name.
![alt text](https://github.com/ragujan/Simple-Logistic-Management-System/blob/main/src/main/resources/server_config_images/JDBC%20Connection%20Pool.JPG?raw=true)
* Make JDBC Resource
Add a Name, JNDI Name 	
"jdbc/logistic_system" 

Select the JDBC Connection Pool name which was created earlier.
![alt text](https://github.com/ragujan/Simple-Logistic-Management-System/blob/main/src/main/resources/server_config_images/JDBC%20Resource.JPG?raw=true)
* Security Configuration

To configure, you have to go to the configurations -> default config -> security -> Realms -> file  
In the file you can manage users. 

Select Manage users-> then New user

add admin type user 
* User ID as "admin", Group List: "admin".
add merchant type user 
* User ID as "merchant", Group List: "merchant"
![alt text](https://github.com/ragujan/Simple-Logistic-Management-System/blob/main/src/main/resources/server_config_images/file_real_security.JPG?raw=true)





