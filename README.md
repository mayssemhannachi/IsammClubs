# Club Portal Project

## Getting Started

Welcome to the Club Portal project developed using Visual Studio Code for Java. This guide will help you set up and start writing Java code in Visual Studio Code.

## Folder Structure

The project follows a standard folder structure:

- `src`: Contains the source code files.
- `lib`: Intended for managing dependencies.
- `bin`: The default folder for compiled output files.

If you wish to customize the folder structure, navigate to `.vscode/settings.json` and update the relevant settings there.

> Note: Ensure that the dependencies are correctly managed using the `JAVA PROJECTS` view. For more details, refer to [this link](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## Overview

This project is a Club Portal designed for [University Name] to manage various clubs and user interactions. The portal allows users to sign up, join clubs, and provides administrative tools for club administrators.

## Features

- **User Authentication:**
  - Users can log in as administrators or regular users.
  - Admins have access to an admin dashboard with filtering options.

- **Club Signup:**
  - Users can sign up for the portal, providing necessary details.
  - Users can join specific clubs and update their profiles.

- **Admin Dashboard:**
  - Admins can filter users based on club, grade, or speciality.
  - Results are displayed in a readable format.

- **Database Handling:**
  - Utilizes SQLite database for user and admin information.
  - DatabaseHandler class encapsulates database operations.

- **Graphical User Interface (GUI):**
  - Implemented using Java Swing for a user-friendly experience.

## Project Structure

- **src/ folder:**
  - Contains the Java source code files.

- **lib/ folder:**
  - Intended for managing external dependencies.

- **bin/ folder:**
  - The default folder for compiled output files.

- **/Users/macbookair/Documents/IsammClubs/PortalDB/portal.db:**
  - SQLite database file.

## Running the Project

1. **Dependencies:**
   - Ensure you have Java installed on your machine.

2. **Set Up the Workspace:**
   - Open the project in Visual Studio Code.
   - Verify the folder structure and update `.vscode/settings.json` if needed.

3. **Run the Application:**
   - Execute the main class: `useradmin.java`.

## Additional Notes

- **User Interface Design:**
  - The GUI is designed to be intuitive and user-friendly.

- **Code Structure:**
  - The project follows a modular structure for easy maintenance.

- **Database Connection:**
  - Database connection details are provided in the source code.

## Future Improvements

- **Enhanced User Profiles:**
  - Implement more user profile features.

- **Security Measures:**
  - Enhance user authentication and data security.

## Feedback and Contributions

Feel free to provide feedback or contribute to this project. Open to suggestions and improvements.




