# Stackoverflow TW Project

### Setup & Run:

- Create a database in psql shell / pgadmin / etc..
  - `psql -U username`
  - `CREATE DATABASE databasename;`L
- Set up the database as data source in Intellij
- Run `setup.sql` on the data source
- Copy `config-env.txt` and rename to `config.env`
  - Make sure it is ignored in `.gitignore`!
- Modify the port and database values
- Run main

### Frontend setup

- run 'npm i' in 'sof-frontend directory'
- rename env.txt according to instructions
- start backend (run StackoverflowTwApplication.java class)
- run npm start

### Tasks:

- [x] Main page listing all questions with details, date and answer count
- [ ] Feature to sort questions on Main page by alphabet, date or answer count
- [x] Detail page of a question shows who asked it and lists all answers with date and users
- [x] Feature to add new question (redirects to question detail page after save)
- [x] Feature to add new answer (location: question detail page)
- [x] Feature to delete question (later extend so only the owner can delete it)
- [x] Feature to delete answer (location: question delete page) (later extend so only the owner can
  delete it)
- [x] Feature to Register new user
- [x] Feature to Login with user and store session (who is currently logged in is always visible on
  frontend)
- [x] Feature to Logout user
- [ ] Users list page: shows username, registration date, number of questions, number of answers
- [ ] Feature to mark the accepted answer for the user's own question
- [ ] Write tests for the service layer

#### Extra:

- [ ] OPTIONAL: Admin user type can delete any question or answer
- [ ] OPTIONAL: Filter by question titles  (location: main page)
- [ ] OPTIONAL: Feature to tag questions
- [x] OPTIONAL: Feature to edit questions (limited to the ones created by the user)
- [x] OPTIONAL: Feature to edit answers (limited to the ones created by the user)
- [ ] OPTIONAL: Feature to calculate reputation of the user and display them differently if they are
  very active on the site (display reputation on user list page too)

## General requirements:

- Create an SQL database to store your data
- Stick to Model View Controller layers (Frontend doesn't do calculations, only displays the data,
  SQL handles the data manipulation)
- Do the base data features first and add user management later, extend already existing ones if
  necessary
- Aim for the code to keep SOLID, OOP and Clean code principles
- Focus on the Java & SQL parts and do minimal frontend as it is not the goal of this project
- Vanilla Javascript and HTML is suggested for frontend, nothing more complicated (like frameworks)
  is necessary
- If you need new API endpoints ask mentors to create them for you
