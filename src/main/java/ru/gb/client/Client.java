package ru.gb.client;

import ru.gb.interfaces.IClientView;
import ru.gb.server.Server;

public class Client {
    private String name;
    private final IClientView IClientView;
    private final Server server;

    public Client(IClientView IClientView, Server server) {
        this.IClientView = IClientView;
        this.server = server;
    }

    public boolean connectToServer(String name){
        this.name = name;
        if (server.connectUser(this)){
            printText("Вы на связи!\n");
            boolean connected = true;
            String log = server.getHistory();
            if (log != null){
                printText(log);
            }
            return true;
        } else {
            printText("Не удалось подключиться к серверу");
            return false;
        }
    }


    public void sendMessage(String message){
        if (server.serverWork()) {
            if (!message.isEmpty()) {
                server.sendMessage(name + ": " + message);
            }
        } else {
            printText("Нет подключения к серверу");
        }
    }
//
//    public void serverAnswer(String answer){
//        printText(answer);
//    }

    public void disconnect(){
        server.disconnectUser(this);
    }

    public String getName() {
        return name;
    }

    public void printText(String text){
        IClientView.showMessage(text);
    }
}