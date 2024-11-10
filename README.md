# DeviceManagement

 Device management API using Spring Boot.


Step 1:
        Download the project from github repository into local and unzip.
               
        
Step 2 : Build the project.
          
     Go to the project home location(devicemanagement) where the pom.xml is located.
     Execute the "mvn clean install" command.

Step 3 : Run the application.

             Execute the "./mvnw spring-boot:run"  command.

Step 4 : Open the browser and type the below specified url for Swagger UI.

             http://localhost:8080/swagger-ui
             
Step 4 : Swagger UI is having all the endpoints((POST,GET,PUT,PATCH,DELETE..), Please gothrough.

               create the device by using POST endpoint
               delete the device by using DELETE endpoint
               update the device by using PUT endpoint
               update the specific field by using PATCH endpoint & etc
               
               ![image](https://github.com/user-attachments/assets/b36c59b1-500d-4457-bbde-c3174dbcb7c7)


Step 5 : H2 database is using to store the data. To View the data  go to browser and type below specidied url and provide the jdbc url , username  & 
        password is as is empty and click on Connect button.Here 

                  http://localhost:8080/h2-console/

                  JDBC URL:  jdbc:h2:mem:devices
                  User Name: sa

         ![A10AA801-5B9D-466B-AEC6-51941B64A5B6](https://github.com/user-attachments/assets/dc9af69d-4619-4789-903f-0b9af9b6d35e)

              
