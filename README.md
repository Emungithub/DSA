# Student Management System

A Java-based Student Management System that handles student records, courses, programmes, and tutorial groups.

## Project Structure

The project follows a layered architecture:

- `src/`
  - `boundary/` - User interface layer
  - `control/` - Business logic layer
  - `entity/` - Data models
  - `dao/` - Data Access Objects
  - `utility/` - Utility classes
  - `adt/` - Abstract Data Types

## Data Files

The system uses several data files for persistence:
- `students.dat` - Student records
- `courses.dat` - Course information
- `programmes.dat` - Programme details
- `tutorialGroups.dat` - Tutorial group assignments
- `assignment.dat` - Assignment data

## Building and Running

### Prerequisites
- Java Development Kit (JDK)
- Apache Ant (for building)

### Build Instructions
1. Ensure you have JDK and Ant installed
2. Open terminal in project root directory
3. Run the following command to build:
   ```
   ant build
   ```

### Running the Application
After building, you can run the application using:
```
ant run
```

## Project Features
- Student record management
- Course management
- Programme management
- Tutorial group assignments
- Assignment tracking

## Development
This project is built using Java and follows object-oriented programming principles with a layered architecture for better maintainability and separation of concerns.

## License
This project is for educational purposes. 