# Evolent Health - Programming Problem
#### Project Description
Design and implement a production ready application for maintaining contact information. Please choose the frameworks, packages and/or technologies that best suit the requirements.
###### Minimum expected functionality:
    - List contacts
    - Add a contact
    - Edit contact
    - Delete/Inactivate a contact

###### Minimum Contact model fields:
    - First Name
    - Last Name
    - Email
    - Phone Number
    - Status (Possible values: Active/Inactive)
#### Project Import Steps
  - This project is develeped in Eclipse, Please IMPORT this project in Eclipse IDE. [Eclipse EE]
  - To RUN this web application, you will require Tomcat with Enterprise Library. I used TomEE Plume for this. [TomEE Plume]
  - For Database, I used Oracle 11g.

#### Database Tables/Sequence/Triggers
```sql
CREATE TABLE CONTACT_USERS 
(
  USERID INTEGER PRIMARY KEY
, FIRST_NAME VARCHAR2(50) NOT NULL 
, LAST_NAME VARCHAR2(50) 
, STATUS INTEGER DEFAULT 1 NOT NULL 
);

CREATE TABLE CONTACT_DETAILS 
(
  CID INTEGER PRIMARY KEY
, USERID INTEGER NOT NULL
, TYPE VARCHAR2(20) NOT NULL 
, VALUE VARCHAR2(50) NOT NULL 
, FOREIGN KEY (USERID) REFERENCES CONTACT_USERS(USERID)
);

CREATE SEQUENCE CONTACT_USER_ID;
CREATE SEQUENCE CONTACT_DETAIL_ID;

CREATE TRIGGER CONTACT_USER_ID_TRIGGER
BEFORE INSERT ON CONTACT_USERS
for each row
begin
  select CONTACT_USER_ID.nextval
  into :new.USERID
  from dual;
end;

CREATE TRIGGER CONTACT_DETAIL_ID_TRIGGER
BEFORE INSERT ON CONTACT_DETAILS
for each row
begin
  select CONTACT_DETAIL_ID.nextval
  into :new.CID
  from dual;
end;
```

#### REST Service Libraries
For REST, I have used JAX-RS in JAVA to create the project. 

#### URLs Being Used in This Project
I have created 3 JSP pages for the requirements.
```
http://<host>:<port>/EvolentContactManager/index.jsp
http://<host>:<port>/EvolentContactManager/contact.jsp
http://<host>:<port>/EvolentContactManager/addOrUpdate.jsp
```
All the REST end points are defined in the below java class:
```
com.priya.rest.RestEndpoints;
```



   [Eclipse EE]: <https://www.eclipse.org/downloads/packages/release/2020-06/r/eclipse-ide-enterprise-java-developers>
   [TomEE Plume]: <https://tomee.apache.org/download-ng.html>
