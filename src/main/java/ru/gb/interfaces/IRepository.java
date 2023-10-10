package ru.gb.interfaces;

import ru.gb.server.Server;

public interface IRepository {
    public void saveInLog(String text, Server server);
    public String readLog(Server server);
}