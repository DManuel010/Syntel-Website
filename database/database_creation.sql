-- ******* DATABASE CONFIGURATION *******

CONNECT SA AS SYSDBA;

CREATE TABLESPACE cholla_tablespace
DATAFILE 'C:\TEST\DATABASE31_FILE.dbf'
SIZE 20M AUTOEXTEND ON;

CREATE USER cholla
IDENTIFIED BY cholla
DEFAULT TABLESPACE cholla_tablespace
/

GRANT DBA TO cholla
/

CONNECT cholla/cholla