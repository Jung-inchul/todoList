## Todo List
Incheol's Todo List

## Installation
```
$ mvn install
```

## Running the Application
```
$ mvn spring-boot:run
```

- Example Url : http://localhost:29801
- Swagger Url : http://localhost:29801/swagger-ui.html

## How to use?
- Search<br/>
You can retrieve data by adding various conditions.(Task, Created Date, Updated Date)<br/>
![search](http://pomelowholesale.net/css/images/incheol/search.png)<br/><br/>

- Create/Insert<br/>
You can create or modify tasks.<br/>
![create/update](http://pomelowholesale.net/css/images/incheol/create.png)<br/><br/>

- Pagination<br/>
The list implements the paging function and has a **page size of 5**.<br/>
![pagination](http://pomelowholesale.net/css/images/incheol/paging.png)<br/><br/>

- Validation <br/>
If you create or modify a task without typing, a warning message appears. <br/>
To add ReferenceIds, type '{number},' in text box, or do not allow input values.<br/>
![validation](http://pomelowholesale.net/css/images/incheol/validation.png)<br/><br/>

## Strategies

### Environment
- BackEnd : jdk1.8, SpringBoot
- FrontEnd : html, JSGrid(Javascript)
- DB : h2 inmemory DB

### DB Schema
![db schema](http://pomelowholesale.net/css/images/incheol/erd.png)


### API Specification

**Url : localhost:29801/todos <br />**
Method : GET <br />
Description : Get Todos <br />
Request : 
```
{
	"pageSize": 10,
	"pageIndex": 1,
	"task": "study",
	"createdDate": "2018-05-45 12:00:00",
	"updatedDate": "2018-05-45 12:00:00"
}
```
Response :
```
{
	"success": true,
	"message": null,
	"data": {
		"totalCount : 50,
		"items" : [
			      {
				    "todoId": 26,
				    "task": "study java",
				    "createdDate": "2018-05-45 12:00:00",
				    "updatedDate": "2018-05-45 12:00:00",
				    "isDone": true,
				    "referenceIds": [1,2,3,4]
			      },
			      {
				    "todoId": 27,
				    "task": "study angular",
				    "createdDate": "2018-05-45 12:00:00",
				    "updatedDate": null,
				    "isDone": false,
				    "referenceIds": [1]
			      }
		    ]
	  }
}
```
<br />

**Url : localhost:29801/todos <br />**
Method : POST <br />
Description : Insert todo <br />
Request : 
```
{
	"todoId": null,
        "task": "study java",
        "createdDate": "2018-05-45 12:00:00",
        "updatedDate": "2018-05-45 12:00:00",
        "isDone": true,
    	"referenceIds": [1,2,3,4]
}
```
Response :
```
{
	"success": true,
	"message": "Success",
	"data": {
                "todoId": 1,
                "task": "study java",
                "createdDate": "2018-05-45 12:00:00",
                "updatedDate": "2018-05-45 12:00:00",
                "isDone": true,
                "referenceIds": [1,2,3,4]
            }
}
```
<br />

**Url : localhost:29801/todos/{todoId} <br />**
Method : PUT <br />
Description : Update todo <br />
Request : 
```
{
	"todoId": 26,
        "task": "study java",
        "createdDate": "2018-05-45 12:00:00",
        "updatedDate": "2018-05-45 12:00:00",
        "isDone": true,
    	"referenceIds": [1,2,3,4]
}
```
Response :
```
{
	"success": true,
	"message": "Success",
	"data": {
                "todoId": 26,
                "task": "study java",
                "createdDate": "2018-05-45 12:00:00",
                "updatedDate": "2018-05-45 12:00:00",
                "isDone": true,
                "referenceIds": [1,2,3,4]
            }
}
```
<br />

**Url : localhost:29801/todos/{todoId} <br />**
Method : DELETE <br />
Description : Delete todo <br />
Response :
```
{
	"success": true,
	"message": "Success",
	"data": null
}
```
