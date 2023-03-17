# **Custom Annotation Tag - Spring Boot**

This code implements a custom annotation tag that verifies when a path request is containing specific values 
to perfom an action of some sort within Spring Boot framework. 

# [Install Instructions](#instructions)

     git clone git@github.com:josepico01/customannotation.git
  * cd customannotation
  * Compile and Run main
  * Open Postman
  * Hit a Post request http://localhost:8080/banana/{{type}}/eat and you should get a 200 OK response with a string.
  * For this implementation, the {{type}} variable expected is either 'peeled' or 'unpeled'
