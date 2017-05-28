## 2. Pratkikumsaufgabe Software-Architektur Sommer 2017

- Author: Sven Schatter

- [Heroku-App](https://shareit-teamxy.herokuapp.com/)

## API Usage

URI | Method | Wirkung
--- | --- | ---
/users/create | POST | Create a new user.
/users/authenticate | POST | Authenticate yourself, username and password are expected in JSON.
/users | GET | Get all users.
/users/{id} | GET | Get information about an existing User.
/users/{id} | PUT | Update the information of an existing User.
 |  |
/check/{token} | GET | Check the validity of a token.

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
