# Parking Lot

## Simple parking solution application

#### Prerequisites

Setup the environment
1. Java 8
2. Maven
3. Docker

#### Run (from the root directory)
First, setup the database

Assume that docker cmd will run without permission. Otherwise add `sudo` at the beginning
```
docker run --name jekparking -p 5723:3306 -e MYSQL_ROOT_PASSWORD=123456 -d mysql/mysql-server:5.7
docker exec -it jekparking bash
mysql -h localhost -u root -p 
CREATE USER 'jek' IDENTIFIED BY 'jek';
grant all on *.* to 'jek'@'%' identified by '123456';
FLUSH PRIVILEGES;
CREATE DATABASE parking_lot CHARACTER SET utf8 COLLATE utf8_general_ci;
exit;
exit
```
Second, setup the application
```
bin/setup
```
Finally, run the application
```
bin/parking_lot
```
You can run the application in two mode.

1. passing a .txt file as the argument ex. `bin/parking_lot parking.txt`
   - execute the file command
2. interacting with the console(no argument is needed) ex. `bin/parking_lot`
   - input different type of commands

###### Sample accepted command
1. create_parking_lot 6
2. park KA-01-HH-1234 White
3. leave 4
4. status
5. registration_numbers_for_cars_with_colour White
6. slot_numbers_for_cars_with_colour White
7. slot_number_for_registration_number KA-01-HH-3141

###### For manual database setup
1. Username: jek
2. Password: 123456
3. Databas Name: parking_lot

**change the port in connectionUrl in DatabaseProvider**

Change according to your choice
