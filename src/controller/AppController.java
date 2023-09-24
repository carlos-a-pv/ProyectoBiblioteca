package controller;

import model.Biblioteca;

public enum AppController {

    INSTANCE;
    private final Biblioteca model;

    AppController() {
        model = new Biblioteca("UQ BIBLIOTECA");
    }

    public Biblioteca getModel() {
        return model;
    }
}
