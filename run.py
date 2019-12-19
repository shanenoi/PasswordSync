import threading
import os

def client():
    os.system('sleep 1s;cd PasswordSync/; java -jar client.jar')

def server():
    os.system('cd PasswordSync/; python3 main.py >> log')

def main():
    threading.Thread(target=client).start()
    threading.Thread(target=server).start()

if __name__ == "__main__":
    main()
