# Course-Registration
This is a course registration system built using java,hibernate,jersey

This project was developed with the help f intellij,to run this setup enviorment on intellij (Maven project,java11 and other dependencies are metioned in pom.xml,to deploy use tomcat server)

src contains all the backend and front end code

Backend code is modular and easy to understand

beans- contains all the JPA code i.e schema creation

DAO- data access operations,inside Dao folder  -> these are interfaces for data acess which are implemented in impl(Find their implementation in impl folder)

Services- Contoller -> Services -> DAO so services sits between controller and Dao

Controller -> is all the REST part i.e handling different request(diff url patterns)
