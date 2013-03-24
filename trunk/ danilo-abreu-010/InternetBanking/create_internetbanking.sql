DROP DATABASE internetbanking;

CREATE DATABASE internetbanking;

CREATE TABLE `client` 
  ( 
     `idclient`  INT(11) NOT NULL auto_increment, 
     `cpf/cpnpj` VARCHAR(14) DEFAULT NULL, 
     `fist_name` VARCHAR(45) DEFAULT NULL, 
     `last_name` VARCHAR(45) DEFAULT NULL, 
     PRIMARY KEY (`idclient`) 
  ); 


CREATE TABLE `account_type` 
  ( 
     `idaccount_type` INT(11) NOT NULL auto_increment, 
     `code`           VARCHAR(3) NOT NULL, 
     `description`    VARCHAR(45) NOT NULL, 
     PRIMARY KEY (`idaccount_type`) 
  ); 


CREATE  TABLE `transaction_type` (  `idtransaction_type` INT NOT NULL AUTO_INCREMENT ,  `description` VARCHAR(45) NOT NULL ,  PRIMARY KEY (`idtransaction_type`) );


CREATE TABLE `account` ( 
     `idaccount`      INT(11) NOT NULL auto_increment, 
     `idclient`       INT(11) DEFAULT NULL, 
     `idaccount_type` INT(11) DEFAULT NULL, 
     `number`         VARCHAR(10) DEFAULT NULL, 
     `password`       VARCHAR(10) NOT NULL, 
     PRIMARY KEY (`idaccount`), 
     UNIQUE KEY `number_unique` (`number`), 
     KEY `idaccount_type_idx` (`idaccount_type`), 
     KEY `idclient_idx` (`idclient`), 
     CONSTRAINT `idaccount_type` FOREIGN KEY (`idaccount_type`) REFERENCES 
     `account_type` (`idaccount_type`) ON DELETE no action ON UPDATE no action, 
     CONSTRAINT `idclient` FOREIGN KEY (`idclient`) REFERENCES `client` ( 
     `idclient`) ON DELETE no action ON UPDATE no action 
  ); 



CREATE TABLE `transactions` 
  ( 
     `idtransactions`      INT(11) NOT NULL auto_increment, 
     `idaccount`           INT(11) NOT NULL, 
     `id_transaction_type` INT(11) NOT NULL, 
     `schedule_date`       DATETIME DEFAULT NULL, 
     `exec_date`           DATETIME DEFAULT NULL, 
     `amount`              FLOAT DEFAULT NULL, 
     PRIMARY KEY (`idtransactions`), 
     KEY `idaccount_idx` (`idaccount`), 
     KEY `idtransaction_type_idx` (`id_transaction_type`), 
     CONSTRAINT `idaccount` FOREIGN KEY (`idaccount`) REFERENCES `account` ( 
     `idaccount`) ON DELETE no action ON UPDATE no action, 
     CONSTRAINT `idtransaction_type` FOREIGN KEY (`id_transaction_type`) 
     REFERENCES `transaction_type` (`idtransaction_type`) ON DELETE no action ON 
     UPDATE no action 
  ); 

CREATE TABLE `advantage_account` 
  ( 
     `idadvantage_account`      INT(11) NOT NULL, 
     `id_account`               INT(11) DEFAULT NULL, 
     `advantage_account_number` VARCHAR(10) DEFAULT NULL, 
     PRIMARY KEY (`idadvantage_account`), 
     KEY `idaccoutn_idx` (`id_account`), 
     CONSTRAINT `idaccount_owner` FOREIGN KEY (`id_account`) REFERENCES 
     `account` (`idaccount`) ON DELETE no action ON UPDATE no action 
  );


CREATE TABLE `transfer` 
  ( 
     `idtransfer`          INT(11) NOT NULL, 
     `idtransaction`       INT(11) DEFAULT NULL, 
     `idadvantage_account` INT(11) DEFAULT NULL, 
     PRIMARY KEY (`idtransfer`), 
     KEY `idtransactoin_idx` (`idtransaction`), 
     KEY `idadvantage_account_idx` (`idadvantage_account`), 
     CONSTRAINT `idadvantage_account` FOREIGN KEY (`idadvantage_account`) 
     REFERENCES `advantage_account` (`idadvantage_account`) ON DELETE no action 
     ON UPDATE no action, 
     CONSTRAINT `idtransactoin` FOREIGN KEY (`idtransaction`) REFERENCES 
     `transactions` (`idtransactions`) ON DELETE no action ON UPDATE no action 
  ) ;

