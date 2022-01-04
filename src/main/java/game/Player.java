package game;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;

    //recode each round score
    private Integer score = 0;

    //win times
    private Integer winTimes = 0;

    //guess numbers
    private List<Integer> guessNum = new ArrayList<>();



    public Player(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getWinTimes() {
        return winTimes;
    }

    public void setWinTimes(Integer winTimes) {
        this.winTimes = winTimes;
    }

    public List<Integer> getGuessNum() {
        return guessNum;
    }

    public void setGuessNum(List<Integer> guessNum) {
        this.guessNum = guessNum;
    }
}
