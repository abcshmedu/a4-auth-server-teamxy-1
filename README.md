## Auth server for Shareit

- Author: Sven Schatter

- [Heroku-App](https://shareit-teamxy.herokuapp.com/)

## API Usage

URI | Method | Action
--- | --- | ---
/users/authenticate | POST | Authenticate yourself by providing your email and password via JSON.
 |  |
/check/{token} | GET | Check the validity of a token.
 |  |
 |  |
 |  |
/users/create | POST | Create a new user.
/users | GET | Get all users.
/users/{id} | GET | Get information about an existing User.
/users/{id} | PUT | Update the information of an existing User.
## Examples

- /users/authenticate

```json
// Your Data
{
  "email": "your@email.com",
  "password": "yourPassword123"
}

// Response
{
  "token": "814adf48-7865-4b95-8b58-e55d26c539c5"
}
```

- /check/{your_token}

```json
// Invalid token
{
  "valid": false
}

// Valid token
{
  "valid": true,
  "user": "You",
  "email": "your@email.com",
  "userGroup": "NORMAL",
  "expirationDate": 1495973869
}
```

## Error Codes

These are possible responses upon your API calls.

Status | Code | Detail
--- | --- | ---
OK | 200 | OK.
ALREADY_EXISTS | 400 | The entity you wanted to create already exists.
INVALID_INFORMATION | 400 | The information you provided is invalid.
MISSING_INFORMATION | 400 | Some required information is missing. (e.g.: title, author, etc.)
NOT_FOUND | 404 | The requested resource could not be found.
ERROR | 500 | Internal Server Error.
