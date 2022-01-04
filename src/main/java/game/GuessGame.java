package game;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GuessGame {

    private static final int PLAYER_NUM = 5;
    private static final int ROUND_NUM = 5;
    private List<Player> playerList = new ArrayList<>();
    private List<Round> roundList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void initPlayer(){
        for(int i=0; i<PLAYER_NUM; i++) {
            System.out.print("Please enter player name:");
            String name = scanner.nextLine();
            playerList.add(new Player(name));
        }
    }

    public void initRound(){
        for(int i=0; i<ROUND_NUM; i++) {
            System.out.println("Round " + (i+1));
            for(Player player: playerList) {
                System.out.print("Please enter your guess player " + player.getName() + ":");
                int num = this.getInputNum();
                player.getGuessNum().add(num);
            }
        }
    }

    /**
     * the number 1<=num<=10
     */
    private int getInputNum(){
        int num;
        while(true) {
            try{
                String numStr = scanner.nextLine();
                num = Integer.parseInt(numStr);
                if (num < 1 || num > 10) {
                    throw new RuntimeException("the number should be between 1 and 10");
                }
            }catch (Exception e){
                System.out.println("the number should be between 1 and 10, retry");
                continue;
            }
            break;
        }
        return num;
    }

    /**
     * 1 generate a random answer number 1<=num<=10
     * 2 calculate player score
     * 3 judge who win
     */
    public void guessNum(){
        SecureRandom rn = new SecureRandom();
        for(int i=0; i<ROUND_NUM; i++) {
            int ansNum = rn.nextInt(10) + 1;
            roundList.add(new Round(ansNum));

            System.out.println("Guess Number for round " + (i+1) + ":"+ansNum);
            this.calcPlayerScore(ansNum);

            //System.out.println(JSON.toJSONString(playerList));
            this.whoWin();
        }
    }

    private void whoWin(){
        int maxScore = 0;
        for(Player player: playerList) {
            if(player.getScore()>0 && player.getScore()>maxScore) {
                maxScore = player.getScore();
            }
        }

        if(maxScore==0){
            System.out.println("No winner");
            return;
        }

        System.out.println("Winner/s:");
        for(Player player : playerList) {
            if(player.getScore() == maxScore) {
                System.out.println("Player:" + player.getName());
                player.setWinTimes(player.getWinTimes()+1);
            }
        }
    }

    private void calcPlayerScore(int ansNum) {
        for(Player player : playerList) {
            player.setScore(0);
            List<Integer> numList = player.getGuessNum();
            for(Integer n : numList) {
                if(ansNum == n){
                    player.setScore(player.getScore()+1);
                }
            }
        }
    }

    public void printPlayerDetail() {
        for (Player player : playerList) {
            System.out.println("Player name:" + player.getName());
            int count=0;
            for(Integer num: player.getGuessNum()){
                String msg = String.format("Round %s guess value:%s Correct value:%s",
                    count+1, num, roundList.get(count).getAnswer());
                count++;
                System.out.println(msg);
            }
        }
    }

    public void whoWinOverAll(){
        int maxScore = 0;
        for(Player player: playerList) {
            if(player.getWinTimes()>0 && player.getWinTimes()>maxScore) {
                maxScore = player.getWinTimes();
            }
        }

        if(maxScore==0){
            System.out.println("No winner");
            return;
        }

        System.out.println("The Overall winner/s:");
        for(Player player : playerList) {
            if(player.getWinTimes() == maxScore) {
                System.out.println("Player:" + player.getName());
            }
        }
    }

}
