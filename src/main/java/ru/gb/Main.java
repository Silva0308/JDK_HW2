package ru.gb;

import ru.gb.client.Client;
import ru.gb.client.ClientGUI;
import ru.gb.server.Log;
import ru.gb.server.Server;
import ru.gb.server.ServerGUI;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(new ServerGUI(), new Log());
        new Client(new ClientGUI(server), server);
        new Client(new ClientGUI(server), server);
    }
    }
