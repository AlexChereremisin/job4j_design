package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))
                ) {
                    String str;
                    boolean isHello = false;
                    boolean isExit = false;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        if (str.contains("/?msg=Hello")) {
                            isHello = true;
                        }
                        if (str.contains("/?msg=Exit")) {
                            isExit = true;
                        }
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (!(isExit || isHello)) {
                        out.write("What".getBytes());
                    }
                    if (isHello) {
                        out.write("Hello".getBytes());
                    }
                    if (isExit) {
                        server.close();
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("IO Exception", e);
        }
    }
}
