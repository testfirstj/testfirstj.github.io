#!/bin/bash
dropdb --if-exists presidentDB
createdb -O exam presidentDB
cat presidents_until_43.sql | psql -U exam -X presidentDB 
