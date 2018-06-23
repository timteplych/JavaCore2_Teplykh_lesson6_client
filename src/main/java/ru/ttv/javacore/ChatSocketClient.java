package ru.ttv.javacore;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatSocketClient {
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;
    private Thread scanThread;
    private Socket socket;
    private Scanner scanner;
    private PrintWriter printWriter;

    public void clientStart() throws UnknownHostException, IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        scanner = new Scanner(socket.getInputStream());
        printWriter = new PrintWriter(socket.getOutputStream());

        //listenining server thread
        scanThread = new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        if (Thread.interrupted()) {
                            break;
                        }
                        if (scanner.hasNext()) {
                            String msg = scanner.nextLine();

                            System.out.println(msg);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        scanThread.start();
    }

    //Sending message to server
    public void sendMessage(String message) {
        printWriter.append(message);
        printWriter.flush();
    }

    public Thread getScanThread(){
        return scanThread;
    }

    public void close(){
        try {
            socket.close();
            scanner.close();
            printWriter.close();
        }catch (IOException e){
            System.out.println("Error while closing IO objects");
        }
    }
}
