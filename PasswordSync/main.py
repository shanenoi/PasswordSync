from support import data_storage
from flask import Flask
import logging
import os


app = Flask(__name__)
DATA_FILE = os.getcwd() + '/data/db'


'[===============INDEX PAGE===============]'
@app.route("/")
def hello_world():
    return (
        "[+] /field/[parameter]<br/>"
        "[+] /getNames/[parameter]/"
    )


'[===============INPUT FIELD FOR USER===============]'
@app.route(
    "/field/" +
        "<string:private_key>&" +
        "<string:method>&" +
        "<string:key>&" +
        "<string:value>"
)
def query(private_key, method, key, value):

    data = data_storage.touch_data(DATA_FILE, None, "get")

    if private_key in data.keys():
        stack = data[private_key]

        if method == "get":
            if key in stack.keys():
                return stack[key]
            else:
                return "wrong key!"

        elif method == "update":
            stack[key] = value
            data_storage.touch_data(DATA_FILE, data, "set")
            return "ok"

        elif method == "delete":
            if key in stack.keys():
                stack.pop(key)
                data_storage.touch_data(DATA_FILE, data, "set")
                return "ok"
            else:
                return "wrong key!"

        return "wrong method!"

    return "wrong private key!"


@app.route(
    "/getNames/<string:private_key>"
)
def return_all(private_key):

    data = data_storage.touch_data(DATA_FILE, None, "get")

    if private_key in data.keys():
        return '<br/>'.join(list(data[private_key].keys()))
    
    else:
        return "wrong private key!"



if __name__ == "__main__":
    app.run()
