# Business Codes — Memoriae

## Range Overview

| Range       | Domain                   |
|-------------|--------------------------|
| 1000 - 1999 | Authentication & Session |
| 2000 - 2999 | User                     |

### Sub-ranges (common to all domains)

| Sub-range   | Nature                                  |
|-------------|-----------------------------------------|
| x000 - x099 | Success                                 |
| x100 - x199 | CRUD errors (not found, conflict, etc.) |
| x200 - x299 | Validation errors                       |
| x300 - x399 | Permission / authorization errors       |

---

## 1000 — Authentication & Session

| Code | Meaning                           | Context                                                                           |
|:-----|:----------------------------------|:----------------------------------------------------------------------------------|
| 1000 | Authentication successful         | The user submits correct email + password, a JWT token is generated and returned  |
| 1001 | Logout successful                 | The user calls `/logout`, their token is invalidated server-side                  |
| 1002 | Token refreshed successfully      | The client sends a valid refresh token and receives a new access token            |
| 1100 | Invalid credentials               | The user enters a wrong password for an existing account                          |
| 1101 | Account not found                 | The email provided at login does not match any account in the database            |
| 1102 | Account disabled                  | The user attempts to log in but their account has been suspended or disabled      |
| 1103 | Invalid token                     | The JWT token provided in the header is malformed or its signature does not match |
| 1104 | Token expired                     | The token was valid but its expiration date has passed                            |
| 1105 | Missing token                     | The request reaches a protected route with no `Authorization` header              |
| 1106 | Invalid refresh token             | The refresh token sent is malformed or unknown to the server                      |
| 1107 | Refresh token expired             | The refresh token was valid but has exceeded its lifetime                         |
| 1200 | Missing email                     | The login request body does not contain the `email` field                         |
| 1201 | Missing password                  | The login request body does not contain the `password` field                      |
| 1300 | Access denied — not authenticated | An unauthenticated user attempts to access a protected resource                   |

---

## 2000 — User

| Code | Message                     | Context                                                                                    |
|:-----|:----------------------------|:-------------------------------------------------------------------------------------------|
| 2000 | User created successfully   | The registration form is submitted with valid data, the account is created in the database |
| 2001 | User retrieved successfully | The `GET /users/{id}` call correctly returns the user's profile                            |
| 2002 | User updated successfully   | The user modifies their username or email and the update is persisted                      |
| 2003 | User deleted successfully   | The user requests account deletion, they are removed from the database                     |
| 2100 | User not found              | A `GET /users/{id}` is made but no user with that id exists                                |
| 2101 | Email already in use        | At registration, the provided email is already associated with an existing account         |
| 2102 | Pseudo already taken        | At registration or update, the chosen username is already taken by another user            |
| 2103 | Deletion not possible       | Account deletion is blocked because it still has linked characters or notes                |
| 2200 | Validation errors           | An error had been detected during validation of resquest body                              |
| 2300 | Action not authorized       | A non-admin user attempts to access another user's profile                                 |
| 2301 | Update forbidden            | A user attempts to modify another user's profile                                           |
| 2302 | Deletion forbidden          | A user attempts to delete another user's account                                           |
