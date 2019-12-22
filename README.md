# Parking Lot

## Simple parking solution application

#### Prerequisites
1. Java 8
2. Maven
3. Docker

#### Run (from the root directory)
First, setup the batabase
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
Finally, Run the application
```
bin/parking_lot
```

###### For manual database setup
1. Username: jek
2. Password: 123456
3. Databas Name: parking_lot
** change the port in connectionUrl in DatabaseProvider **
