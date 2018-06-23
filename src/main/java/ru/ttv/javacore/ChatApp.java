package ru.ttv.javacore;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class ChatApp
{
    public static void main( String[] args )
    {
        ChatClient chatClient = new ChatClient();
        chatClient.startClient(new Scanner(System.in));
    }
}
