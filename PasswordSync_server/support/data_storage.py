def touch_data(name, data, method):
    if method == "get":
        with open(name) as f:
            return eval(f.read())
    elif method == "set":
        with open(name, "w") as f:
            f.write(str(data))
