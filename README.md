# spring-rest-jpa-tutorial
Gradle Spring Rest JPA tutorial

Quick Crud operations using Gradle Spring Rest JPA and HSQL

# specification should be tighten to our Integration Test
ApplicationResourcesTest.java

# end points

GET /healthcheck
Expect to have a 200 ok status


DELETE /departments
Delete all
Response Status 200

GET /departments
List departments
Response Body json array of department objects
Response Status 200

POST /department
create department
Request Body department json object
Response Body newly created department json object with id
Response Status 200

GET /department/{id}
get department
Request param department id
Response Body department json object
Response Status 200

PUT /department
Request Body department json object
Response Body newly updated department json object
Response Status 200


DELETE /employees
Delete all employees
Response Status 200

GET /employees
List employees
Response Body json array of employee objects
Response Status 200

POST /employee
create employee
Request Body employee json object
Response Body newly created employee json object with id
Response Status 200

GET /employee/{id}
get employee
Request param employee id
Response Body employee json object
Response Status 200

PUT /employee
Request Body employee json object
Response Body newly updated employee json object
Response Status 200

#Todo
Generic Error hanler
Cors filters


#Done for a test, please feel free add changes


