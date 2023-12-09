-- create database
-- Database name 
-- p.s. you can change the db name to whatever you want but make sure to change it in the DB.java file
CREATE DATABASE "fridgeList" 
    WITH
    -- Database owner 
    -- p.s. you can change the owner to whatever you want but make sure to change it in the DB.java file
    OWNER = root 
    ENCODING = 'UTF8'
    LOCALE_PROVIDER = 'libc'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- create table
-- p.s. you can change the table name to whatever you want 
CREATE TABLE items (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    day INT NOT NULL,
    month VARCHAR(255) NOT NULL,
    year INT NOT NULL
);