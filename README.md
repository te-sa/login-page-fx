# login-page-fx

Trying to learn more about JavaFX by building a simple login page.

## Ideas:

- [X] add signup functionality
    - [X] check for valid passwords (at least 8 chars, with uppercase, lowercase, number & special char)
    - [X] check that no username is assigned twice
- [ ] make a clear visual distinction between login and signup
- [ ] add warning if capslock is on while typing password?
- [ ] give option to unmask password?
- [ ] how many tries before locked out of account for some time? 3? Give warning
- [ ] add warning before closing out where appropriate (page, not login screen)
- [X] connect with SQL database (SQLite)
- [ ] allow users to change their passwords (and usernames?)
- [X] add user counter and display message: You are the nth user! on page (only when they first sign in?)
- [ ] autogenerate valid usernames to choose from?
- [ ] add security questions? On page as security feature when changing username/password/trying to recall either
- [ ] replace print statements with pop-ups/text in GUI
- [ ] add way to recover password
- [ ] give visual representation of password strength + show valid password specifications somewhere

## Fix:

- [X] set times logged in does not work for new users!
  - issue was that no new record had been inserted into login_info 
