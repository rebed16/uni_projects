#include <winsock2.h>
#include <iostream>
#include <string>
#include <thread>

using namespace std;

// Funcție pentru a primi mesaje de la server
void receive_messages(SOCKET client_socket) {
    char buffer[1024];

    while (true) {
        int bytes_received = recv(client_socket, buffer, sizeof(buffer) - 1, 0);
        if (bytes_received <= 0) {
            cout << "Server disconnected" << endl;
            exit(0);
        }
        buffer[bytes_received] = '\0';
        cout << "\nMessage from server: " << buffer << endl;

    }
}

int main(int argc, char** argv) {
    if (argc != 3) {
        cerr << "Usage: " << argv[0] << " <server IP> <server port>" << endl;
        exit(1);
    }

    string server_ip = argv[1];
    int port = atoi(argv[2]);

    WSADATA wsaData;
    WSAStartup(MAKEWORD(2, 2), &wsaData);

    SOCKET client_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (client_socket == INVALID_SOCKET) {
        cerr << "Socket creation failed, error: " << WSAGetLastError() << endl;
        WSACleanup();
        return 1;
    }

    sockaddr_in server_addr;
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(port);
    server_addr.sin_addr.s_addr = inet_addr(server_ip.c_str());

    if (connect(client_socket, (sockaddr*)&server_addr, sizeof(server_addr)) < 0) {
        cerr << "Connection failed, error: " << WSAGetLastError() << endl;
        closesocket(client_socket);
        WSACleanup();
        return 1;
    }

    cout << "Connected to server " << server_ip << " on port " << port << endl;

    // Pornim un thread pentru a asculta mesajele de la server
    thread recv_thread(receive_messages, client_socket);
    recv_thread.detach(); // Rulează independent

    string message;
    while (true) {
        cout << "Enter message (type 'exit' to disconnect): ";
        getline(cin, message);

        if (message == "exit") {
            cout << "Closing connection..." << endl;
            break;
        }

        send(client_socket, message.c_str(), message.length(), 0);
    }

    closesocket(client_socket);
    WSACleanup();
    return 0;
}
