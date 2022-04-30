
###Installation and running
You must have Maven installed on your computer.
In terminal you mast navigate to directory in which project is stored 
and run command "mvn install".
Then navigate to directory "target" and there run commend "java -jar MedicalCenter-0.0.1-SNAPSHOT.jar"

###Remote database details 
Username: JlMBElsPeP

Database name: JlMBElsPeP

Password: PWxiYwW6u7

Server: remotemysql.com

Port: 3306


####Register new patient
Takes patient's personal data and creates new patient in database

```http
  POST /registerPatient
```

BODY(RAW, JSON) example:

```json
{
    "name": "Adam",
    "lastname": "Sito",
    "email": "a.sito@gmail.com",
    "pesel": 96374182545,
    "phoneNumber": 654789321,
    "dateOfBirth": "1996-08-03",
    "address": "Domowa 5"
}
```

####Update patient's personal data 
Takes patients id and new personal data and updates it in database

```http
  POST /updatePatientPersonalData
```

PARAMS example:

| KEY | VALUE     | 
| :-------- | :------- | 
| `id`      | `1` |

BODY(RAW, JSON) example:

```json
{
    "id": 7,
    "name": "Nadia",
    "lastname": "Tulipan",
    "email": "n.lipa@gmail.com",
    "pesel": 32198745685,
    "phoneNumber": 753951842,
    "dateOfBirth": "1998-06-21",
    "address": "Konwaliowa 8"
}
```

####Bind patient with a project
Takes patient's id and research project's id and binds them if there is a consent for it

```http
  POST /bindPatientWithResearchProject
```

PARAMS example:

| KEY | VALUE     | 
| :-------- | :------- | 
| `patientId`      | `2` |
| `researchProjectId`      | `1` |

###Unbind patient with a project
Takes patient's id and research project's id and unbinds them

```http
  POST /unbindPatientWithResearchProject
```

PARAMS example:

| KEY | VALUE     | 
| :-------- | :------- | 
| `patientId`      | `2` |
| `researchProjectId`      | `3` | 


###Delete patient
Takes patient's id and deletes patient and their personal data

```http
  POST /deletePatient
```

PARAMS example:

| KEY | VALUE     | 
| :-------- | :------- | 
| `id`      | `4` |


###Create research project
Takes research project's info and saves it in database

```http
  POST /createResearchProject
```

BODY(RAW, JSON) example:

```json
{
    "description": "Researching sleep walking",
    "start": "2022-08-15"
}
```

####Update research project's date of beginning
Takes research project's id and new date of beginning and updates it in database

```http
  POST /updateResearchProjectDate
```

PARAMS example:

| KEY | VALUE     | 
| :-------- | :------- | 
| `id`      | `1` |

BODY(RAW, JSON) example:

```json
"2022-06-30"
```

####Update research project's description
Takes research project's id and new description and updates it in database

```http
  POST /updateResearchProjectDescription
```

PARAMS example:

| KEY | VALUE     | 
| :-------- | :------- | 
| `id`      | `1` |

BODY(RAW, TEXT) example:

```text
Researching insomnia among women.
```

####Delete research project
Takes research project's id and deletes research project

```http
  POST /deleteResearchProject
```

PARAMS example:

| KEY | VALUE     | 
| :-------- | :------- | 
| `id`      | `4` | 

#### Add consent
Takes ids of patient and research project and creates new consent

```http
  POST /addConsent
```
Params example:

| KEY | VALUE     | 
| :-------- | :------- | 
| `patientId` | `3` |  
| `researchProjectId` | `2`|

#### Withdraw consent
Takes consent id and removes consent from database. Patient is no longer apart of research project.

```http
  POST /withdrawConsent
```
PARAMS:

| KEY | VALUE     | 
| :-------- | :------- | 
| `id`      | `1` | 

####Upload consent image 

```http
  POST /uploadImage
```
PARAMS example:

| KEY | VALUE     | 
| :-------- | :------- | 
| `consentId`      | `1` | 

BODY(FORM-DATA) example:

| KEY | VALUE     |  
| :-------- | :------- | 
| multipartFile (File)   | (select file) | 
|fileName|name|

####Delete consent image
Takes consent id and deletes its image

```http
  POST /deleteImage
```

PARAMS example:

| KEY | VALUE     | 
| :-------- | :------- | 
| `consentId`      | `1` | 

####Order laboratory test
Takes patient id, research project id and date and creates an order for a laboratory test 

```http
  POST /orderLaboratoryTest
```

PARAMS example:

| KEY | VALUE     | 
| :-------- | :------- | 
| `patientId`      | `1` |
| `researchProjectId`      | `1` |

BODY(RAW, JSON) example:

```json
"2022-06-15T12:30:00"
```

####Add test result 
     Takes patient's id, laboratory test's id and result's description and saves it in database
     
```http
POST /addTestResult
```
     
PARAMS example:
     
| KEY | VALUE     | 
| :-------- | :------- | 
| `patientId`      | `2` | 
| `laboratoryTestId`     | `1` | 
     
BODY(RAW, TEXT) example:
     
```text
The result is negative
```


####Update test result 
Takes test result's id and new result description and updates it in database
     
```http
POST /updateTestResult
```
     
PARAMS example:
     
| KEY | VALUE     | 
| :-------- | :------- | 
| `testResultId`      | `1` | 
     
BODY(RAW, TEXT) example:
     
```text
Test results are positive
```

####Delete test result
Takes test result's id and deletes test result

```http
  POST /deleteTestResult
```

PARAMS example:

| KEY | VALUE     | 
| :-------- | :------- | 
| `id`      | `1` | 
