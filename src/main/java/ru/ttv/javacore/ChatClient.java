package ru.ttv.javacore;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {

    public void startClient(Scanner consoleScanner){
        ChatSocketClient chatSocketClient = new ChatSocketClient();
        Thread scanThread = null;
        try {
            chatSocketClient.clientStart();
            scanThread = chatSocketClient.getScanThread();
            while (true) {
                if (consoleScanner.hasNext()) {
                    String msg = consoleScanner.nextLine();
                    chatSocketClient.sendMessage(msg + "\n");
                    if(msg.equals("end")){
                        break;
                    }
                }

            }
            //chatSocketClient.sendMessage("Hello!\n");
            //chatSocketClient.sendMessage("How are you?\n");
            Thread.sleep(500);
            //Thread.sleep(500);


        } catch (UnknownHostException e){
            System.out.println("Unknown host...");
        }catch (IOException e){
            System.out.println("IO exception...");
        } catch (SecurityException e) {
            System.out.println("Security exception...");
            //e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception...");
            //e.printStackTrace();
        }finally {
            if(scanThread != null){
                scanThread.interrupt();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(chatSocketClient != null){
                chatSocketClient.sendMessage("end");
                chatSocketClient.close();
            }
        }
    }
}
