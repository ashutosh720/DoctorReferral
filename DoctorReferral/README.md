# Suggesting Doctor Based On Symptoms :-

## Frameworks and language used:
-     Java
-     Spring Boot
-     SQL database
-     Postman

## Data Flow


*  **Model** :
  There are Four Models class in this application.
    * Doctor Model Class.
    * Patient Model Class.
    * Speciality Model Class.
    * Symptoms Model Class.


* **Controller** :

  There are Five Controller classse in this application.

    * DoctorController Class.
    * PatientController Class.
    * SpecialityController Class.
    * SymptomsController class.
    * StatusController class.





* **Service** :

  There are Frou Service class in this application.

    * DoctorService Class.
    * PatientService Class.
    * SpecialityService Class.
    * SymptomsService Class
    * StatusService Class



* **Repository** :

  There are Four Repository Interfaces in this application.

    * DoctorRepository Interface.
    * PatientRepository Interface.
    * StatusRepository Interface.
    * SymptomsRepository Interface


* **Utility/Validation** :
    * In this application I'm using annotations based validation with manual validations for more safety and to keep continuity in data .



* **GlobalExceptionHandler class** :
    #### In this class I have handled the exceptions that generates in project inbuild as well as custom.
 
    * AlreadyExistsException
    * NotFoundException
    * NotValid



* **Database**

    * I have used SQL Database to store the data.
   

## Project Summary

The project aims to create a user-friendly web app for patients to find suitable doctors based on their symptoms and location. Patients can input their symptoms, and the system provides relevant doctor recommendations. To maintain data accuracy, the system has built-in validations. If no specialists are available locally, it suggests doctors in nearby cities. Ultimately, the app improves healthcare access and outcomes for patients.
  













  
