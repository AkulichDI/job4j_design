package ru.job4j.io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        boolean running = true;

        try (ServerSocket server = new ServerSocket(9000)) {
            while (running) {
                try (
                        Socket socket = server.accept();
                        BufferedReader input = new BufferedReader(
                                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)
                        );
                        OutputStream output = socket.getOutputStream()
                ) {
                    String requestLine = input.readLine();
                    if (requestLine == null || requestLine.isEmpty()) {
                        continue;
                    }

                    System.out.println(requestLine);

                    for (String line = input.readLine(); line != null && !line.isEmpty(); line = input.readLine()) {
                        System.out.println(line);
                    }

                    String body = "What";
                    String msg = extractMessage(requestLine);

                    if ("Hello".equals(msg)) {
                        body = "Hello";
                    } else if ("Bye".equals(msg)) {
                        body = "Bye";
                        running = false;
                    }

                    byte[] bodyBytes = body.getBytes(StandardCharsets.UTF_8);
                    String response =
                            "HTTP/1.1 200 OK\r\n" +
                                    "Content-Type: text/plain; charset=UTF-8\r\n" +
                                    "Content-Length: " + bodyBytes.length + "\r\n" +
                                    "\r\n" +
                                    body;

                    output.write(response.getBytes(StandardCharsets.UTF_8));
                    output.flush();
                }
            }
        }
    }

    private static String extractMessage(String requestLine) {
        int start = requestLine.indexOf("msg=");
        if (start == -1) {
            return "";
        }

        start += 4;
        int end = requestLine.indexOf(' ', start);
        if (end == -1) {
            end = requestLine.length();
        }

        String rawValue = requestLine.substring(start, end);
        return URLDecoder.decode(rawValue, StandardCharsets.UTF_8);
    }
}