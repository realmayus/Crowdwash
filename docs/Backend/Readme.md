# Crowdwash Backend docs

Account Management based on Web

## Abfragen

Standart GET Location: ```/```

Response:
```Json
{
  "error": true,
  "stacktrace": {
    "stacktrace": "Location '/' has no function defined",
    "type": "path"
  }
}
```

### Login

GET ```/login?user=USERNAME&pw=PASSWORD```

Response (fals login ausgeführt wurde):
```Json
{
  "error": false,
  "SessionID": "kipyYJFiEuIv",
  "name": "USERNAME"
}
```
Response (fals login fehlgeschlagen ist):
```Json
{
    "error": True,
    "stacktrace": {
        "type": "Account",
        "stacktrace": "Wrong Login Infos!"
    }
}
```

### Logout
GET ```/logout?session=kipyYJFiEuIv```

Response (fals logout ausgeführt wurde):
```Json
{
    "error": False,
    "result": "Log out complete"
}
```

Response (fals login fehlgeschlagen ist):
```Json
{
    "error": True,
    "stacktrace": {
        "type": "Session",
        "stacktrace": "Session is invalid"
    }
}
```