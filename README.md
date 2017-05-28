## Auth server for Shareit

- Author: Sven Schatter

- [Heroku-App](https://auth-teamxy.herokuapp.com/)

## API Usage

URI | Method | Action
--- | --- | ---
/users/authenticate | POST | Authenticate yourself by providing your email and password via JSON.
/check/{token} | GET | Check the validity of a token.
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