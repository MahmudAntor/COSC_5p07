import socket
import threading
from tracerSetup import tracer
from opentelemetry import trace

HOST = socket.gethostname()
PORT = 5000

def clientHandler(conn, address):
    isConnected = True

    with conn:
        while isConnected == True:
            data = conn.recv(1024).decode('utf-8')
            if not data:
                isConnected = False
            else:
                with open("Output.txt", "a") as localFile:
                    localFile.write(data)
                    conn.send(b'200')

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server_socket:
    server_socket.bind((HOST, PORT))
    server_socket.listen(10)
    clientCount = 1

    with tracer.start_as_current_span("Server"):
        while True:
            with tracer.start_as_current_span(f"Receiving Request from Client: {clientCount}"):
                clientCount += 1
                conn, address = server_socket.accept()
                print(address)
                clientHandlerThread = threading.Thread(target=clientHandler, args=(conn, address))
                clientHandlerThread.start()
                clientHandlerThread.join()
            if(clientCount > 10):
                break


