# Flask imports
from flask import Flask
from flask import request
from flask import Response

# import Package 'Json'
import json
# import Package mysql.connector for Database connection
import mysql.connector
# import Libs for hashing
import bcrypt

import string
import random


def getRandomSessionID():
    min_char = 8
    max_char = 12
    allchar = string.ascii_letters
    password = "".join(random.choice(allchar) for x in range(random.randint(min_char, max_char)))
    return password


#Password Hashing
def get_hashed_password(plain_text_password):
    # Hash a password for the first time
    plain_text_password = plain_text_password.encode("utf-8")
    salt = bcrypt.gensalt()
    return [bcrypt.hashpw(plain_text_password, salt), salt]

def check_password(plain_text_password, hashed_password):
    # Check hased password. Useing bcrypt, the salt is saved into the hash itself
    plain_text_password = plain_text_password.encode("utf-8")
    hashed_password = hashed_password.encode("utf-8")
    return bcrypt.checkpw(plain_text_password, hashed_password)

# Gets Database login
raw = open("database.json")
raw = raw.read()
sql = json.loads(raw)

#Variable app als Flask initialisieren
app = Flask(__name__)

# weist der Klasse main() die location 'ip:5000/' Zu und gibt an das GET und POST requests empfangen werden
@app.route("/", methods=["GET", "POST"])
def main():
    # Generiert Json code (Stracktrace(Error))
    out = json.dumps({
        "error": True,
        "stacktrace": {
            "type": "path",
            "stacktrace": "Location '/' has no function defined"
        }
    })
    # Gibt den Generierten Json zurück, sodass er ausgegeben wird (Hier mit dem Argument das der mimetype="application/json" ist).
    return Response(out, mimetype="application/json")


@app.route("/login", methods=["GET", "POST"])
def login():

    cnx = mysql.connector.connect(user=sql['user'], password=sql['pw'],
                              host=sql['host'],
                              database=sql['db'])
    cursor = cnx.cursor(buffered=True)

    user = request.args.get('user')
    pwRaw = request.args.get('pw')
    temp = get_hashed_password(pwRaw)
    hashed = temp[0]
    salt = temp[1]
    query = ("SELECT * From User WHERE Name='") + str(user) + ("'")
    #queryData = (user)


    cursor.execute(query)
    l = False
    for (Name, pw, salt) in cursor:
        l = True
        if check_password(pwRaw, pw):
            query = ("INSERT INTO Sessions (SessionID, Name) VALUES (%s, %s)")
            session = getRandomSessionID()
            queryData = (session, user)
            cursor.execute(query, queryData)
            out = json.dumps({
                "error": False,
                "name": Name,
                "SessionID": session
            })
        else:
            out = json.dumps({
                "error": True,
                "stacktrace": {
                    "type": "Account",
                    "stacktrace": "Wrong Login Infos!"
                }
            })
    if not l:
        out = json.dumps({
        "error": True,
        "stacktrace": {
            "type": "Account",
            "stacktrace": "Wrong Login Infos!"
        }
        })

    
    cnx.commit()
    cursor.close()
    cnx.close()

    return Response(out, mimetype="application/json")


@app.route("/logout", methods=["GET", "POST"])
def logout():
    sessionID = request.args.get('session')
    cnx = mysql.connector.connect(user=sql['user'], password=sql['pw'],
                              host=sql['host'],
                              database=sql['db'])
    cursor = cnx.cursor()
    query = ("DELETE From Sessions WHERE SessionID='") + str(sessionID) + ("'")

    try:
        cursor.execute(query)
        cnx.commit()
        cursor.close()
        cnx.close()
        out = json.dumps({
            "error": False,
            "result": "Log out complete"
        })
    except mysql.connector.Error:
        out = json.dumps({
            "error": True,
            "stacktrace": {
                "type": "Session",
                "stacktrace": "Session is invalid"
            }
        })


    return Response(out, mimetype="application/json")


#    if not cursor.execute(query) == None:
#        out = json.dumps({
#            "error": False,
#            "result": "Log out complete"
#        })
#    else:
#        out = json.dumps({
#            "error": True,
#            "stacktrace": {
#                "type": "Session",
#                "stacktrace": "Session is invalid"
#            }
#        })
#    

# Start Object
if __name__ == "__main__":
    # Starts the Webserver
    app.run(debug=True)
