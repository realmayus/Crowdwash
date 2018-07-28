from flask import Flask
from flask import request
from flask import Response
import json
app = Flask(__name__)

@app.route("/", methods=["GET", "POST"])
def main():
    out = json.dumps({
        "error": True,
        "stacktrace": {
            "type": "path",
            "stacktrace": "Location '/' has no function defined"
        }
    })
    return Response(out, mimetype="application/json")

if __name__ == "__main__":
    app.run()
