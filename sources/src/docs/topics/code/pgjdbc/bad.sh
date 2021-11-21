#!/bin/bash
CLASS_PATH=target/classes/:/home/hom/Library/postgresql/postgresql-42.2.2.jar
java -cp  ${CLASS_PATH} pgjdbcdemo.SQLInjectionDemo ";'drop table president cascade; commit;--"
