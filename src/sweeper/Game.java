package sweeper;


import static sweeper.Box.*;

public class Game {
    private Bomb bomb;
    private Flag flag;
    private GameState state;
    public GameState getState() {
        return state;
    }

    public Game (int calls, int rows, int bombs)
    {

        Ranges.setSize(new Coard(calls, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }
    public void start()
    {
        bomb.start();
        flag.start();
        state = GameState.PLAYED;
    }
    public Box getBox (Coard coard)
    {
        if (flag.get(coard) == OPENED)
            return bomb.get(coard);
        else
        return  flag.get(coard);
    }
    public void pressLeftButton(Coard coard)
    {
        if (gameOver()) return;
        openBox (coard);
        checkWinner();
    }
    private void checkWinner()
    {
        if (state == GameState.PLAYED)
            if (flag.getCountOfClosedBoxes() == bomb.getTotalBombs())
                state = GameState.WINNER;
    }
    private void openBox(Coard coard)
    {
        switch (flag.get(coard)) {
            case OPENED: setOpenedAroundNumber (coard);
                return;
            case FLAGED:
                return;
            case CLOSED:
                switch (bomb.get(coard))
                {
                    case ZERO: openBoxesAround(coard); return;
                    case BOMB: openBombs(coard); return;
                        default: flag.setOpenedToboBox(coard); return;
                }
        }
    }

    private void setOpenedAroundNumber(Coard coard) {
        if (bomb.get(coard) != BOMB)
            if(flag.getCountOfFlagedBoxesAround(coard) == bomb.get(coard).getNumber())
                for (Coard around : Ranges.getCoardsAround(coard))
                    if (flag.get(around) == Box.CLOSED)
                        openBox(around);
    }

    private void openBombs(Coard bombed) {
        state = GameState.BOMBED;
        flag.setBombedToBox(bombed);
        for (Coard coard : Ranges.getAllCoards())
            if (bomb.get(coard) == Box.BOMB)
                flag.setOpenedToCloserBombBox(coard);
            else flag.setNoBomb(coard);
    }

    private void openBoxesAround(Coard coard) {
        flag.setOpenedToboBox(coard);
        for (Coard around: Ranges.getCoardsAround(coard))
            openBox(around);
    }

    public void pressRightButton(Coard coard)
    {
        if (gameOver()) return;
        flag.toggleFlagedToBox(coard);
    }

    private boolean gameOver() {
        if (state == GameState.PLAYED)
            return false;
        start();
        return true;
    }
}