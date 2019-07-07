package innerclass;

public class Games {
    public static void play(GameFactory factory) {
        Game game = factory.getGame();
        while(game.move())
            ;
    }

    public static void main(String[] args) {
        play(Checkers.factory);
        play(Chess.factory);
    }
}
interface Game {
    boolean move();
}
interface GameFactory {
    Game getGame();
}
class Checkers implements Game {
    private int move;
    private static final int MOVE = 3;
    private Checkers() {}
    @Override
    public boolean move() {
        System.err.println("checkers move " + move);
        return ++move != MOVE;
    }
    public static GameFactory factory = new GameFactory() {
        @Override
        public Game getGame() {
            return new Checkers();
        }
    };
}
class Chess implements Game {
    private int move;
    private static final int MOVE = 4;
    private Chess() {}
    @Override
    public boolean move() {
        System.err.println("checkers move " + move);
        return ++move != MOVE;
    }
    public static GameFactory factory = new GameFactory() {
        @Override
        public Game getGame() {
            return new Chess();
        }
    };
}