This is the development version of AddressBook.

Libraries:
- JGoodies Looks, Forms
- Log4j
- SqliteJDBC

Build tool:
- Apache Ant(optional)
- Eclipse IDE(optional)

SCM:
- Git(optional)

To make it work we need to create de database, if it does not exists, follow the next instructions:

- Install SQLite3 in your machine.
- Start a console and locate into 'database' folder.
- Execute the next steps: 
 # sqlite3 addressBook.db
 # > .read database.sql
 # > .q
 
