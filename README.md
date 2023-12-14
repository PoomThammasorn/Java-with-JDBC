# Refrigerator Expiry Tracker

This Java application serves as a Refrigerator Expiry Tracker, allowing users to manage and track the expiration dates of items stored in their refrigerator.

<img src="https://github.com/PoomThammasorn/Java_with_JDBC/assets/111583306/56614439-8930-4ef1-a176-9b9410f8b306" alt="image" width="500"/>

## Table of Contents

- [Getting Started](#getting-started)
- [Control Structures](#control-structures)
- [Subroutines](#subroutines)
- [Objects and Classes](#objects-and-classes)
- [JavaFX](#javafx)
- [Correctness, Robustness, Efficiency](#correctness-robustness-efficiency)
- [Input/Output Streams, Files, and Networking](#inputoutput-streams-files-and-networking)
- [JDBC and CRUD Operations](#jdbc-and-crud-operations)

## Getting Started

To use the Refrigerator Expiry Tracker, follow these steps:

1. Clone the repository to your local machine.
2. Open the project in your preferred Java development environment.
3. Run the `App.java` file to start the application.
4. The application window will appear, providing options to add, update, delete, and view refrigerator items.

## Control Structures

The program utilizes various control structures such as `if` statements, `for` loops, and event handling with JavaFX. These structures control the flow of the program based on user interactions.

## Subroutines

Subroutines, or methods, are used to modularize the code and handle specific functionalities. Methods like `start`, `createRoot`, `setupTitle`, and others encapsulate distinct parts of the program.

## Objects and Classes

The `Material` class represents objects in the application. Instances of this class are created to manage and display data related to refrigerator items.

## JavaFX

The Refrigerator Expiry Tracker is built using JavaFX, a framework for creating graphical user interfaces in Java. It employs JavaFX components like `VBox`, `Text`, `GridPane`, `TableView`, and `Button` to create a user-friendly interface.

## Correctness, Robustness, Efficiency

The code aims for correctness by handling basic CRUD operations on refrigerator items. Error handling is present for parsing integers, and the program is intended to be robust to user input.

Efficiency considerations may include potential enhancements for scalability, especially in database operations.

## Input/Output Streams, Files, and Networking

The application demonstrates basic Input/Output (I/O) operations with file handling. The `exportData` method writes data to a file, and file reading is showcased. Networking features are commented out but could be expanded based on project requirements.

<img src="https://github.com/PoomThammasorn/Java_with_JDBC/assets/111583306/fab726a5-cdd1-4de4-a29d-a2b8c2a2653c" alt="image" width="400"/>


## JDBC and CRUD Operations

The program interacts with PostgreSQL database using JDBC. CRUD operations (`Create`, `Read`, `Update`, `Delete`) are performed through methods like `DB.insertData`, `DB.readData`, `DB.updateData`, and `DB.deleteData`.

<img width="382" alt="image" src="https://github.com/PoomThammasorn/Java_with_JDBC/assets/111583306/efb80a88-0e34-45d0-8818-a019f2ed7981" width="300">





Feel free to explore and contribute to the development of this Refrigerator Expiry Tracker!
