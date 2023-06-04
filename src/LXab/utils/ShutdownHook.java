package src.LXab.utils;

public class ShutdownHook extends Thread {
    @Override
    public void run() {
        ConnectionPool.closeConnection();
    }
}
