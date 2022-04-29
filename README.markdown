
## API Reference

#### Add consent
Takes ids of patient and research project and creates new consent

```http
  POST /addConsent
```

| Parameter | Type     | 
| :-------- | :------- | 
| `patientId` | `long` |  
| `patientId` | `string`|

#### Get item

```http
  GET /withdrawConsent
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |

#### add(num1, num2)

Takes two numbers and returns the sum.

