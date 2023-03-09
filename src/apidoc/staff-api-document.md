# Staff Manage API Interface specification

## Table of contents

1. [Get All Staff API](#get-all-staff-api)
2. [Get Staff Details By Id API](#get-staff-details-by-id-api)
3. [Update Staff API](#update-staff-api)
4. [Delete Staff API](#delete-staff-api)
5. [Insert Staff API](#insert-staff-api)

## General parameters

- Character encoding
    - UTF-8
- Communication protocol
    - HTTPS

## Get All Staff API

### Request

- URI
    - /staff/getAll
- Method
    - GET

#### Header

- Content-type
    - application/json

#### Path parameters

|Required|Parameter name|Value (example)|Type|Description|
|:----|:----|:----|:----|:----|
|○|id|1|string|Staff id|
|○|name|quynh|string|Staff name|
|○|page|1|int|Page number|
|○|itemByPage|10|int|Item by page|

#### Sample request URL

```
/staff/getAll
```

### Response
- Content-type
    - application/json
- HTTP Status:
    - 200 OK
    - 400 Bad Request

#### Body

- Trả về toàn bộ thông tin nhân viên tìm kiếm, kèm phân trang

#### Staff Information

|Key|Value (example)|Type|Description|
|:----|:----|:----|:----|
|itemCount|12|int|Number of record|
|id|1|string|Staff id|
|name|quynh|string|Staff's name|
|address|HCM|string|Staff's address|
|phoneNumber|0123456789|string|Staff's phone number|
|dateOfBirth|2000|string|Staff's birthdate|
|createUser|1|string|Creator id|
|createDateTime|2023-02-28 14:41:51|string|Created date|
|updateUser|1|string|Updater id|
|updateDateTime|2023-03-09 15:01:37|string|Updated date|

#### Sample response

```json
{
    "itemCount": 2,
    "staffs": [
        {
            "id": 1,
            "name": "Quynh",
            "address": "CT",
            "phoneNumber": "012233",
            "dateOfBirth": "2000",
            "createUser": 1,
            "createDateTime": "2023-02-28 14:41:51",
            "updateUser": 1,
            "updateDateTime": "2023-03-09 15:01:37"
        },
        {
            "id": 10,
            "name": "fjnf",
            "address": "da",
            "phoneNumber": "Ada",
            "dateOfBirth": "aadd",
            "createUser": null,
            "createDateTime": null,
            "updateUser": null,
            "updateDateTime": null
        }
    ]
}
```

#### Error Response

##### Parameter error

|HTTP Status|Error code|Error message|Description|
|:----|:----|:----|:----|
|400|cannot_enter_string_in_integer_field|Can not enter String in integer field.|The requested Staff ID is contains Non-Nummeric characters.|
|400|cannot_enter_string_in_integer_field|Can not enter String in integer field.|The requested Page or Item by page is contains Non-Nummeric characters.|

##### Sample Error Response

```json
{
    "code": "cannot_enter_string_in_integer_field",
    "message": "Can not enter String in integer field."
}
```

## Get Staff Details By Id API

### Request

- URI
    - /staff/getStaffDetailsById/{id}
- Method
    - GET

#### Header

- Content-type
    - application/json

#### Path parameters

|Required|Parameter name|Value (example)|Type|Description|
|:----|:----|:----|:----|:----|
|○|id|1|string|Staff id|

#### Sample request URL

```
/staff/getStaffDetailsById/1
```

### Response

#### Header

- Content-type
    - application/json
- HTTP status
    - 200 OK
    - 404 Not Found

#### Body

- Trả về thông tin nhân viên theo chỉ định

##### Staff information

|Key|Value (example)|Type|Description|
|:----|:----|:----|:----|
|id|1|string|Staff id|
|name|quynh|string|Staff's name|
|address|HCM|string|Staff's address|
|phoneNumber|0123456789|string|Staff's phone number|
|dateOfBirth|2000|string|Staff's birthdate|
|createUser|1|string|Creator id|
|createDateTime|2023-02-28 14:41:51|string|Created date|
|updateUser|1|string|Updater id|
|updateDateTime|2023-03-09 15:01:37|string|Updated date|

##### Sample response

```json
{
    "id": 1,
    "name": "Quynh",
    "address": "CT",
    "phoneNumber": "012233",
    "dateOfBirth": "2000",
    "createUser": 1,
    "createDateTime": "2023-02-28 14:41:51",
    "updateUser": 1,
    "updateDateTime": "2023-03-09 15:01:37"
}
```

#### Error repsonse

##### Parameter error

|HTTP Status|Error code|Error message|Description|
|:----|:----|:----|:----|
|404|id_not_found|Id is not exists.|The requested Staff ID is not exist.|


##### Sample Error Response

```json
{
  "code": "id_not_found ",
  "message": "Id is not exists."
}
```

## Update Staff API

### Request

- URI
    - /staff/update
- METHOD
    - PUT

#### Header

- Content-type
    - application/json

#### Body

- Cập nhật thông tin nhân viên được chỉ định.

#### Path parameters

|Required|Parameter name|Value (example)|Type|Description|
|:----|:----|:----|:----|:----|
|○|id|1|string|Staff's id|
|○|name|quynh|string|Staff's name|
|○|address|HCM|String|Staff's address|
|○|phoneNumber|0123456789|String|Staff's phone number|
|○|dateOfBirth|2002|String|Staff's birthdate|

#### Sample request URL

```
/staff/update
```

##### Request Body

|Required|Parameter name|Value (example)|Type|Description|
|:----|:----|:----|:----|:----|
|○|id|38|string|Staff's id|
|○|name|hello|string|Staff's name|
|○|address|HCM|String|Staff's address|
|○|phoneNumber|0147258369|String|Staff's phone number|
|○|dateOfBirth|2005|String|Staff's birthdate|

##### Body

```json 
{
    "id": 38,
    "name": "hello",
    "address": "Hcm",
    "phoneNumber": "0147258369",
    "dateOfBirth": "2005"
}
```

### Response

#### Header

- Content-type
    - application/json
- HTTP status
    - 201 OK
    - 400 Bad Request

#### Body

#### Error response

##### Parameter error

|HTTP Status|Error code|Error message|Description|
|:----|:----|:----|:----|
|400|cannot_enter_string_in_integer_field|Can not enter String in integer field.|The requested Staff's id contains non-nummeric characters.|
|400|lenght_not_enough|length must be between 2 and 50|The requested staff's name is less than 2 characters.|

##### Sample Error Response

```json
{
    "code": "cannot_enter_string_in_integer_field",
    "message": "Can not enter String in integer field."
}
```

## Delete Staff API

### Request

- URI
    - /staff/delete/{id}
- METHOD
    - DELETE

#### Header

- Content-type
    - application/json

#### Body

- Xóa nhân viên được chỉ định.

#### Sample request URL

```
/staff/delete/1
```

### Response

#### Header

- Content-type
    - application/json
- HTTP status
    - 200 OK
    - 404 Not Found

#### Error response

##### Parameter error

|HTTP Status|Error code|Error message|Description|
|:----|:----|:----|:----|
|404|id_not_found|Id is not exists.|The requested Staff ID is not exist.|

##### Sample Error Response


```json
{
    "code": "id_not_found",
    "message": "Id is not exists."
}
```

## Insert Staff API

### Request

- URI
    - /staff/insert
- METHOD
    - POST

#### Header

- Content-type
    - application/json

#### Body

- Thêm nhân viên mới.

#### Sample request URL

```
/staff/insert
```

#### Request Body

|Key|Value (example)|Type|Description|
|:----|:----|:----|:----|
|id|40|string|Staff id|
|name|test|string|Staff's name|
|address|HCM|string|Staff's address|
|phoneNumber|0123456789|string|Staff's phone number|
|dateOfBirth|2000|string|Staff's birthdate|

##### Body

```json
{
    "id": 40,
    "name": "test",
    "address": "Hcm",
    "phoneNumber": "0123456789",
    "dateOfBirth": "2000"
}
```

### Response

#### Header

- Content-type
    - application/json
- HTTP status
    - 201 OK
    - 400 Bad Request
    - 409 Conflict

#### Body

#### Error response

##### Parameter error

|HTTP Status|Error code|Error message|Description|
|:----|:----|:----|:----|
|409|id_already_exist|Id is already exists.|The requested staff's is already exists.|
|400|lenght_not_enough|length must be between 2 and 50|The requested staff's name is less than 2 characters.|

##### Sample Error Response

- When insert an id has already exists.

```json
{
    "code": "id_already_exist",
    "message": "Id already exist."
}
```

- When name field is not enough characters.

```json
{
    "code": "lenght_not_enough",
    "message": "length must be between 2 and 50"
}
```