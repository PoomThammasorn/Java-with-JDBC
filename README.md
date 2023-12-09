# Refrigerator Expiry Tracker

## Overview

The Refrigerator Expiry Tracker is a JavaFX application designed to help you manage and track the expiration dates of items in your refrigerator. The program allows you to add, update, and delete items, while also providing a visual representation of the time left before an item expires.

## Features

- **Add, Update, and Delete**: Easily add new items to your refrigerator, update existing ones, and remove items when needed.

- **Clear and Refresh**: Clear all input fields or refresh the displayed data with a click of a button.

- **Time Left Indicator**: The program calculates and displays the time left for each item to expire, providing a clear indication of its freshness.

- **Export Data**: Export the contents of the refrigerator to a file for easy backup or sharing.

- **JavaFX UI**: The program utilizes JavaFX for a user-friendly and interactive graphical user interface.

- **Correctness, Robustness, Efficiency**: The code is designed to be correct, robust to handle various scenarios, and efficient in its execution.

## How to Use

1. **Add an Item**: Enter the name, expiration date, and other details in the input fields. Click the "Add" button to add the item to the refrigerator.

2. **Update an Item**: Select a row in the table, modify the desired fields, and click the "Update" button to save the changes.

3. **Delete an Item**: Select a row in the table and click the "Delete" button to remove the item from the refrigerator.

4. **Clear Input Fields**: Click the "Clear" button to reset all input fields.

5. **Refresh Data**: Click the "Refresh" button to reload and display the latest data in the table.

6. **Export Data**: Click the "Export" button to save the current refrigerator contents to a file.

7. **Check Expiry Status**: The "Time Left" column indicates how much time is left before an item expires.

## Code Structure and Concepts

- **Control Structures**: The program uses various control structures, such as `if-else` statements and loops, to manage the flow of execution based on different conditions.

- **Subroutines**: Common operations are encapsulated in methods like `updateDayDropdown()`, `deleteSelectedRow(table)`, and `loadDataIntoTable(table)` for better code organization and reusability.

- **Objects and Classes**: The `Material` class represents items stored in the refrigerator, and the program uses objects of this class to manage data.

- **JavaFX**: The graphical user interface is implemented using JavaFX, providing an interactive and visually appealing experience.

- **Correctness, Robustness, Efficiency**: The code is designed to be correct in its functionality, robust to handle unexpected inputs or errors, and efficient in terms of data processing.

- **Input/Output Streams, Files, and Networking**: The program demonstrates file input/output operations, specifically writing and reading refrigerator contents to/from a text file.

- **JDBC and CRUD Operations**: The application connects to a PostgreSQL database using JDBC, performing CRUD operations (Create, Read, Update, and Delete) with the relational database.

Feel free to explore and modify the code to suit your needs!

## JDBC and CRUD Operations

The program utilizes JDBC (Java Database Connectivity) to interact with a PostgreSQL database for managing refrigerator contents. The CRUD operations are explained below:

- **Create (Insert)**: The `addBtn` event handler inserts a new item into the database when the "Add" button is clicked.

- **Read (Select)**: The `loadDataIntoTable(table)` method reads data from the database and populates the table on application startup or when the "Refresh" button is clicked.

- **Update (Update)**: The `updateBtn` event handler updates the selected item's details in the database when the "Update" button is clicked.

- **Delete (Delete)**: The `deleteSelectedRow(table)` method deletes the selected item from both the table and the database when the "Delete" button is clicked.

## Relational Database Schema

The database schema consists of a single table named `materials` with the following columns:

- `id` (Serial): Unique identifier for each item.
- `name` (VARCHAR): Name of the item.
- `day` (INT): Day of the expiration date.
- `month` (VARCHAR): Month of the expiration date.
- `year` (INT): Year of the expiration date.


Happy tracking your refrigerator contents!
