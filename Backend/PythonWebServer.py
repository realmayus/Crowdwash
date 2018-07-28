# Flask imports
from flask import Flask
from flask import request
from flask import Response

# import Package 'Json'
import json

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

# Start Object
if __name__ == "__main__":
    # Starts the Webserver
    app.run()
