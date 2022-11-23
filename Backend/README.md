Install python and pip3

Install virtualenv
pip3 install virtualenv

Create a Virtual Env
virtualenv env

Activate the env
source env/Scripts/activate

Inside the env
pip3 install flask flask-sqlalchemy


To create a db

inside env
python 
(opens the python shell)
from app import db
db.create_all()
(that will create a db)
exit()