package sweeper;

class Bomb {
    private Matrix bombMap;
    private int totalBombs;
    Bomb (int totalBombs){
        this.totalBombs = totalBombs;
        fixBombsCount();
    }
    void start(){
        bombMap = new Matrix(Box.ZERO);
        for (int j=0; j<totalBombs; j++)
            placeBomb();
    }
    Box get (Coard coard){
        return bombMap.get(coard);
    }

    private void fixBombsCount()
    {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (totalBombs > maxBombs)
            totalBombs = maxBombs;
    }


    private void placeBomb(){
        while (true)
        {
            Coard coard = Ranges.getRandomCoard();
            if (Box.BOMB == bombMap.get(coard))
                continue;
            bombMap.set(coard, Box.BOMB);
            incNumbersAroundBomb(coard);
            break;
        }



    }
    private void incNumbersAroundBomb(Coard coard)
    {
        for (Coard around : Ranges.getCoardsAround(coard))
            if (Box.BOMB != bombMap.get(around))
            bombMap.set(around, bombMap.get(around).getNextNumberBox());
    }

    int getTotalBombs() {
        return totalBombs;
    }
}
