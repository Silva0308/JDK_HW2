package ru.gb.server;

import ru.gb.client.Client;
import ru.gb.interfaces.IRepository;
import ru.gb.interfaces.IServerView;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private final IRepository IRepository;
    private final IServerView IServerView;
    List<Client> clients;

    public Server(IServerView IServerView, IRepository IRepository) {
        this.IServerView = IServerView;
        this.IRepository = IRepository;
        clients = new ArrayList<>();
    }

    public boolean connectUser(Client client){
        if (!IServerView.connect()){
            return false;
        }
        clients.add(client);
        return true;
    }

    public String getHistory() {
        return IRepository.readLog(this);
    }

    public void sendMessage(String text){
        if (!IServerView.connect()){
            return;
        }
        IServerView.appendLog(text);
        answerAll(text);
        IRepository.saveInLog(text, this);
    }

    private void answerAll(String text){
        for (Client clients: clients){
            clients.printText(text);
        }
    }

    public void disconnectUser(Client client){
        if (client != null){
            IServerView.appendLog(client.getName() + " " + "отключился");
        }
        answerAll(client.getName() + " " + "покинул чат");
        clients.remove(client);
        ServerStopped(clients);
    }

    public boolean serverWork(){
        return IServerView.connect();
    }

    public void ServerStopped (List<Client> clients){
        if(clients.size() == 0){
            IServerView.appendLog("Нет подключений. Сервер остановлен");
            IServerView.serverStop();
        }
    }
}