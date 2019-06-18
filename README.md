# Spring Link Shortener
spring link shortener is like to bit.ly service but very simplest . convert long URL to short link with visitor counter statistics and custom link generation option .

Tech :

Spring ( Boot , MVC , Data Repository , JPA Repository , Bead Validation )<br>
Mysql as DB (you could to change it to any other )<br>
JSP<br>
Jquery(used for ajax request / response )<br>

Missing :

CSS<br>
Deque Cache<br>
and more featurs as you need

DB hint :

just creat new schema (i used 'short_link') by your self then in the schema.sql file there are lines of table creation , uncomment and use them to make tables
then go to application.properties file and change your custom schema name and your DB ip:port <br><br>
note that this project is deployed on mysql and if you wants to have another one you must be change dependencies in pom file

be lucky , have fun :)
