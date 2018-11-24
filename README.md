# hibernate-with-mysql--example-
A simple hibernate project using mysql and swing library.
## Prerequisites
* Eclipse (use the version that fit to your needs) 
* JDK 1.6 or above
* JRE 1.6 or above
* MySQL 8.0 installed
* Maven

## Configurations
Please, use the configurations below before run anything in the project:
1. Run the two SQL scripts **create_tables.sql** and **insert_data.sql**. This files create the database, tables, stored procedure and data needed for the application can run.
If you don't know how to do it, you can find it [here](https://stackoverflow.com/questions/8940230/how-to-run-sql-script-in-mysql).
2. In the **hibernate.cfg.xml**, edit the lines below:

```
..........
     <property name = "hibernate.connection.username">
         root <-- your DB username
      </property>
      
     <property name = "hibernate.connection.password">
         david123456 <-- your DB password
      </property>
...........
```
**The files I mentioned before located in src/main/resources**

**Default username: user1 and password: user1**

## What the application can do?
1. Create a computer with serial number, description and assembly date (this parameter is a int) with its peaces like a motherboard, mouse, etc.
2. Delete a computer throught its serial number.
3. Update a computer parameters (Not its pieces).
4. Get a list of computers costs.

## Observations 
1. As you can see, the database name, tables name, stored procedure and GUI labels are in spanish. I don't want to create a new one so I used a database I had on hand.
2. The table **detalle_piezas** is junction table but if you noticed, there are not any JPA annotations in the entities saying that. That is because I used the stored procedure from above
to fill that table. You can use hibernate to do it as shown [here](https://www.baeldung.com/hibernate-many-to-many).
