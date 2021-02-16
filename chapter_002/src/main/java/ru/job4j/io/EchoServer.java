package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TO DO
 * Доработайте класса ru.job4j.io.EchoServer.
 * Если клиент отправлять запрос http://localhost:9000/?msg=Bye нужно завершить работу сервера.
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))
                ) {
                    String str;
                    boolean flag = false;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        if (str.contains("/?msg=Bye HTTP/")) {
                            flag = true;
                        }
                    }
                    out.write("HTTP/1.1 200 OK\r\n".getBytes());
                    if (flag) {
                        server.close();
                    }
                }
            }
        }
    }
}
