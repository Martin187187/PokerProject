package main;

import com.stemmer.pokergame.model.Game;

public class GameSingleton {
    private static GameSingleton instance;
    private Game game;

    private GameSingleton() {
        // Initialize the Game instance here if needed
        // For simplicity, we'll assume you initialize it elsewhere
    }

    public static synchronized GameSingleton getInstance() {
        if (instance == null) {
            instance = new GameSingleton();
        }
        return instance;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
