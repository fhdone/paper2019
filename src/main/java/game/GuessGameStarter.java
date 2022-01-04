package game;

public class GuessGameStarter {

    public static void main(String[] args) throws Exception {

        GuessGame guessDemo = new GuessGame();
        guessDemo.initPlayer();

        System.out.println("########################################");
        guessDemo.initRound();

        System.out.println("########################################");
        guessDemo.guessNum();

        System.out.println("########################################");
        guessDemo.printPlayerDetail();

        System.out.println("########################################");
        guessDemo.whoWinOverAll();

        System.out.println("########################################");
    }


}
