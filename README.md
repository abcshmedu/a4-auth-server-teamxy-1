## 2. Pratkikumsaufgabe Software-Architektur Sommer 2017

- Author: Sven Schatter

- [Heroku-App](https://shareit-teamxy.herokuapp.com/)

## API Usage

URI | Method | Wirkung
--- | --- | ---
/shareit/media/books | POST | Create a new book.
/shareit/media/books/{isbn} | GET | Get a JSON reprentation of a certain book.
/shareit/media/books | GET | List all books in a JSON array.
/shareit/media/books/{isbn} | PUT | Update an existing book. (JSON only contains the data that is new, as well as the isbn)
 |  |
/shareit/media/discs | POST | Create a new disc.
/shareit/media/discs/{barcode} | GET | Get a JSON reprentation of a certain disc.
/shareit/media/discs | GET | List all discs in a JSON array.
/shareit/media/discs/{barcode} | PUT | Update an existing disc. (JSON only contains the data that is new, as well as the barcode)

## JSON Examples

- Book

```json
{
  "title":"Title",
  "author":"Author",
  "isbn":"123456"
}
```

- Disc

```json
{
  "title":"Title",
  "barcode":"Author",
  "director":"Director",
  "fsk":0
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
