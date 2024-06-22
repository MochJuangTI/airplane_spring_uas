## Register Customer
Endpoint : PUT /customer/register

Request Header : none

Request Body :
```json
{
  "fullname" :  "Moch Juang",
  "email" : "mochjuangpp@nusaputra.ac.id",
  "password" :  "JIda09",
  "hobby" : "Watching Film",
}
```
Response Body (Success) :
```json
{
  "data" : {
    "fullname" :  "Moch Juang",
    "email" : "mochjuangpp@nusaputra.ac.id",
    "password" :  "JIda09",
    "hobby" : "Watching Film",
    "credit" : {
      "balance" : 200000
    }
  }
}
```

Response Body (Failed) :
```json
{
  "error" :  "Email format involid, Email already exist, ..",
}
```

## Login Customer
Endpoint : POST /customer/register

Request Header : none

Request Body :
```json
{
  "email" : "mochjuangpp@nusaputra.ac.id",
  "password" :  "JIda09",
}
```
Response Body (Success) :
```json
{
  "data" : {
    "fullname" :  "Moch Juang",
    "email" : "mochjuangpp@nusaputra.ac.id",
    "password" :  "JIda09",
    "hobby" : "Watching Film",
    "credit" : {
      "balance" : 200000
    }
  }
}
```

Response Body (Failed) :
```json
{
  "error" :  "customer not found",
}
```
<hr>  


## Create Destination
Endpoint : PUT /destination

Request Header :
- Authorization : Bearer Token (Mandatory)

Request Body :
```json
{
  "name" :  "",
  "description" : "",
  "price" :  "",
  "country_id" : "",
  "photos" :  "",
  "insurance" : true,
  "refundable" : true,
  "vat" : 2.4
}
```
Response Body (Success) :
```json
{
  "data" : {
    "name" :  "",
    "description" : "",
    "price" :  "",
    "country_id" : "",
    "photos" :  "",
    "insurance" : true,
    "refundable" : true,
    "vat" : 2.4
  }
}
```

Response Body (Failed) :
```json
{
  "error" :  "data required"
}
```



## Get List Destination
Endpoint : GET /destination

Request Header :
- Authorization : Bearer Token (Mandatory)

Request Body : none


Response Body (Success) :
```json
{
  "data" : [
    {
      "name" :  "",
      "description" : "",
      "price" :  "",
      "country_id" : "",
      "photos" :  "",
      "insurance" : true,
      "refundable" : true,
      "vat" : 2.4
    }, ...
  ]
}
```

Response Body (Failed) : none

## Get Detail Destination
Endpoint : GET /destination/:id

Request Header :
- Authorization : Bearer Token (Mandatory)

Request Body : none

Response Body (Success) :
```json
{
  "data" : {
    "name" :  "",
    "description" : "",
    "price" :  "",
    "country_id" : "",
    "photos" :  "",
    "insurance" : true,
    "refundable" : true,
    "vat" : 2.4
  }
}
```

Response Body (Failed) :
```json
{
  "error" :  "destination not found"
}
```

## Update Destination
Endpoint : PATCH /destination/:id

Request Header :
- Authorization : Bearer Token (Mandatory)

Request Body :
```json
{
  "name" :  "",
  "description" : "",
  "price" :  "",
  "country_id" : "",
  "photos" :  "",
  "insurance" : true,
  "refundable" : true,
  "vat" : 2.4
}
```
Response Body (Success) :
```json
{
  "data" : {
    "name" :  "",
    "description" : "",
    "price" :  "",
    "country_id" : "",
    "photos" :  "",
    "insurance" : true,
    "refundable" : true,
    "vat" : 2.4
  }
}
```

Response Body (Failed) :
```json
{
  "error" :  "data required"
}
```


## Delete Destination
Endpoint : DELETE /destination/:id

Request Header :
- Authorization : Bearer Token (Mandatory)

Request Body : none


Response Body (Success) :
```json
{
  "data" : {
    "name" :  "",
    "description" : "",
    "price" :  "",
    "country_id" : "",
    "photos" :  "",
    "insurance" : true,
    "refundable" : true,
    "vat" : 2.4
  }
}
```

Response Body (Failed) :
```json
{
  "error" :  "data required"
}
```


## Checking Available Seat
Endpoint : POST /booking/seat

Request Header :
- Authorization : Bearer Token (Mandatory)

Request Body :
```json
{
  "destination_id" : 1,
  "date" : "2022-10-15"
}
```
Response Body (Success) :
```json
{
  "data" : {
    "date" : "2022-10-15",
    "destination": {
      "name": "",
      "description": "",
      "price": "",
      "country_id": "",
      "photos": "",
      "insurance": true,
      "refundable": true,
      "vat": 10.0
    },
    "seat": [
      {
        "number" : 1,
        "available" : true
      },
      {
        "number" : 2,
        "available" : false
      }
    ]
  }
}
```

Response Body (Failed) :
```json
{
  "error" :  "field required, destination not found"
}
```

<hr>  

## Create Booking
Endpoint : PUT /booking

Request Header :
- Authorization : Bearer Token (Mandatory)

Request Body :
```json
{
  "destination_id" : 1,
  "customer_id" : 1,
  "seat" :  [1, 2],
  "date" : ""
}
```
Response Body (Success) :
```json
{
  "data" : {
    "date" : "2022-10-10",
    "destination" : {
      "name" :  "",
      "description" : "",
      "price" :  "",
      "country_id" : "",
      "photos" :  "",
      "insurance" : true,
      "refundable" : true,
      "vat" : 10.0
    },
    "customer" : {
      "fullname" :  "Moch Juang",
      "email" : "mochjuangpp@nusaputra.ac.id",
      "password" :  "JIda09",
      "hobby" : "Watching Film",
      "credit" : {
        "balance" : 200000
      }
    },
    "booking_items" : [
      {
        "seat" : 1,
        "total_price" : 2000
      },
      {
        "seat" : 2,
        "total_price" : 2000
      }
    ],
    "invoice" : {
      "total_payments" : 4000,
      "isPaid" :  false,
      "total_vat" : 400
    }
  }
}
```

Response Body (Failed) :
```json
{
  "error" :  "field required, destination not found, customer not found, Your credit balance is not enough, ..."
}
```



## Pay Booking
Endpoint : POST /booking/:id/pay

Request Header :
- Authorization : Bearer Token (Mandatory)

Request Body : none

Response Body (Success) :
```json
{
  "data" : {
    "date" : "2022-10-10",
    "destination" : {
      "name" :  "",
      "description" : "",
      "price" :  "",
      "country_id" : "",
      "photos" :  "",
      "insurance" : true,
      "refundable" : true,
      "vat" : 10.0
    },
    "customer" : {
      "fullname" :  "Moch Juang",
      "email" : "mochjuangpp@nusaputra.ac.id",
      "password" :  "JIda09",
      "hobby" : "Watching Film",
      "credit" : {
        "balance" : 200000
      }
    },
    "booking_items" : [
      {
        "seat" : 1,
        "total_price" : 2000
      },
      {
        "seat" : 2,
        "total_price" : 2000
      }
    ],
    "invoice" : {
      "total_payments" : 4000,
      "isPaid" :  true,
      "total_vat" : 400
    }
  }
}
```

Response Body (Failed) :
```json
{
  "error" :  "field required"
}
```




