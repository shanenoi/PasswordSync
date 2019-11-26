from support import data_storage
from flask import Flask
import time
import os


app = Flask(__name__)
DATA_FILE = os.getcwd() + '/data/db'
time_key = "t|^^3_|-|@$|-|"


'[===============INDEX PAGE===============]'
@app.route("/")
def hello_world():
    return (
        "[+] /field/[parameter]<br/>"
        "[+] /check/[parameter]"

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
            stack[time_key] = str(hash(time.time()))
            data_storage.touch_data(DATA_FILE, data, "set")
            return stack[time_key]

        elif method == "delete":
            if key in stack.keys():
                stack.pop(key)
                stack[time_key] = str(hash(time.time()))
                data_storage.touch_data(DATA_FILE, data, "set")
                return stack[time_key]
            else:
                return "wrong key!"

        return "wrong method!"

    return "wrong private key!"


'[===============CHECK CHANGED===============]'
@app.route(
    "/check/" +
        "<string:private_key>&" +
        "<string:hashed_time>"
)
def check_changed(private_key, hashed_time):
    data = data_storage.touch_data(DATA_FILE, None, "get")
    if private_key in data:
        if data[private_key][time_key] == hashed_time:
            return '1'
        else:
            return '0'
    else:
        return "wrong private key!"


if __name__ == "__main__":
    app.run()
