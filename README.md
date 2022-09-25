# rms-backend
## Uh
Requires a postgres db, edit credentials in application.properties

# Usage
## Register a new user
POST
```/api/public/user/register```

- Request body:
```
{
  "username": #YOUR_USERNAME, (*)
  "password": #YOUR_PASSWORD, (*)
  "email": #YOUR_EMAIL,
  "phone_number": #YOUR_PHONENUMBER
}
```
(*) fields are required

- Response: query result

## Get all user records (not meant for production)
GET
```/api/public/user/all```

- Response:
```
[
    {
        "id": 1,
        "username": "example",
        "password": "example",
        "email": "example",
        "phone_number": "42069",
        "role": "example",
        "work_for_store": 1
    }
]
```

## Authenticate with a registered account
GET
```/api/public/user/all```

- Request body:
```
{
  "username": #YOUR_USERNAME, (*)
  "password": #YOUR_PASSWORD (*)
}
```
(*) fields are required

- Response: the jwt if correct

## Test jwt on routes that require auth
GET
```/api/public/user/test```

- Authorization:
  - Bearer token: ${jwt}
  - or
  - Header, authorization: "Bearer " + ${jwt}
  
- Response: "monkaHmm"

# Otters are cool (huh?)
