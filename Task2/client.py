import socket
import threading
from tracerSetup import tracer

HOST = socket.gethostname()
PORT = 5000

def fileHandler(fileNumber):
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as client_socket:
        client_socket.connect((HOST, PORT))
        with open(f"res/{fileNumber}.txt", "rb") as file:
            while True:
                bytes_read = file.read(1024)
                if not bytes_read:
                    break

                client_socket.sendall(bytes_read)
                data = client_socket.recv(1024).decode()

                print('Received from server: ' + data)
    

with tracer.start_as_current_span("Client"):
    threadList = []    
    for i in range(10):
        fileHandlerThread = threading.Thread(target=fileHandler, args=(i,))
        threadList.append(fileHandlerThread)
        with tracer.start_as_current_span(f"Sending File {i}"):
            fileHandlerThread.start()
            fileHandlerThread.join()
