package ru.hogwarts.school.model;

public class Info {

    private String port;

    public Info(String port) {
        this.port = port;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Info{" +
                "port='" + port + '\'' +
                '}';
    }
}
