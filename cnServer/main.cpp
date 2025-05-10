#include <winsock2.h>
#include <iostream>
#include <thread>
#include <vector>
#include <algorithm>
#include <mutex>
#include <sstream>

#pragma comment(lib, "ws2_32.lib") // Link cu Winsock

using namespace std;

vector<SOCKET> clients; // Lista clienților conectați
mutex clients_mutex;    // Protejează accesul la listă

void handle_client(SOCKET client_socket, sockaddr_in client_addr) {
    char buffer[1024];

    // Adăugăm clientul în listă
    {
        lock_guard<mutex> lock(clients_mutex);
        clients.push_back(client_socket);
    }

    cout << "Client connected from " << inet_ntoa(client_addr.sin_addr) << ":" << ntohs(client_addr.sin_port) << " to server: 127.0.0.1 on port: 8080"<<endl;

    while (true) {
        int bytes_received = recv(client_socket, buffer, sizeof(buffer) - 1, 0);
        if (bytes_received <= 0) {
            cout << "Client disconnected: " << inet_ntoa(client_addr.sin_addr) << ":" << ntohs(client_addr.sin_port) << endl;
            break;
        }

        buffer[bytes_received] = '\0'; // Terminăm șirul de caractere
        cout << "Received from client: " << buffer
     << " IP: " << inet_ntoa(client_addr.sin_addr)  // Convertim IP-ul la string
     << " Port: " << ntohs(client_addr.sin_port)    // Convertim portul în format citibil
     << endl;




        // Trimitem mesajul la toți clienții conectați
        {
            lock_guard<mutex> lock(clients_mutex);
            for (SOCKET sock : clients) {
                if (sock != client_socket) { // Nu trimitem mesajul înapoi la clientul inițial
                    send(sock, buffer, bytes_received, 0);
                }
            }
        }
    }

    // Scoatem clientul din lista de clienți la deconectare
    {
        lock_guard<mutex> lock(clients_mutex);
        clients.erase(remove(clients.begin(), clients.end(), client_socket), clients.end());
    }

    closesocket(client_socket);
}

int main() {
    WSADATA wsaData;
    WSAStartup(MAKEWORD(2, 2), &wsaData);

    SOCKET server_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (server_socket == INVALID_SOCKET) {
        cerr << "Socket creation failed, error: " << WSAGetLastError() << endl;
        WSACleanup();
        return 1;
    }

    sockaddr_in server_addr;
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(8080);
    server_addr.sin_addr.s_addr = INADDR_ANY;

    if (bind(server_socket, (sockaddr*)&server_addr, sizeof(server_addr)) < 0) {
        cerr << "Bind failed, error: " << WSAGetLastError() << endl;
        closesocket(server_socket);
        WSACleanup();
        return 1;
    }

    cout << "Server listening on port 8080..." << endl;
    listen(server_socket, SOMAXCONN);

    vector<thread> threads; // Stocăm thread-urile clienților

    while (true) {
        sockaddr_in client_addr;
        int addr_len = sizeof(client_addr);
        SOCKET client_socket = accept(server_socket, (sockaddr*)&client_addr, &addr_len);

        if (client_socket == INVALID_SOCKET) {
            cerr << "Accept failed, error: " << WSAGetLastError() << endl;
            continue;
        }

        // Creăm un thread pentru fiecare client nou
        threads.emplace_back(thread(handle_client, client_socket, client_addr));
        threads.back().detach(); // Rulăm thread-ul independent
    }

    closesocket(server_socket);
    WSACleanup();
    return 0;
}
