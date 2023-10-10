package ru.gb.interfaces;

public interface IServerView {
    void appendLog(String text);
    boolean connect ();
    void serverStop();
}