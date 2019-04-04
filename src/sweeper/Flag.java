package sweeper;

class Flag {
    private Matrix flagMap;
    private int countOfClosedBoxes;

    void start()
    {
        flagMap = new Matrix(Box.CLOSED);
   //     flagMap.set (new Coard(4, 4), Box.OPENED);
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }
    Box get (Coard coard)
    {
        return flagMap.get(coard);

    }
    public void setOpenedToboBox(Coard coard)
    {
        flagMap.set(coard, Box.OPENED);
        countOfClosedBoxes --;
    }
    void toggleFlagedToBox (Coard coard)
    {
        switch (flagMap.get(coard))
        {
            case FLAGED: setClosedToBox (coard); break;
            case CLOSED: setFlagedToBox (coard); break;
        }
    }
    private void setClosedToBox (Coard coard)
    {
        flagMap.set(coard, Box.CLOSED);
    }
    public void setFlagedToBox(Coard coard)
    {
        flagMap.set(coard, Box.FLAGED);
    }

    int getCountOfClosedBoxes() {
        return countOfClosedBoxes;
    }

    void setBombedToBox(Coard coard) {
        flagMap.set(coard, Box.BOMBED);
    }

    public void setOpenedToCloserBombBox(Coard coard) {
        if (flagMap.get(coard) == Box.CLOSED)
            flagMap.set(coard, Box.OPENED);
    }

    public void setNoBomb(Coard coard) {
        if (flagMap.get(coard) == Box.FLAGED)
            flagMap.set(coard, Box.NOBOMB);
    }
  //  void setOpenedAroundNumber(Coard coard){
   //     if (getCountOfFlagedBoxesAround(coard) == ;
  //  }

    int getCountOfFlagedBoxesAround(Coard coard) {
        int count = 0;
        for (Coard around : Ranges.getCoardsAround(coard))
            if (flagMap.get(around) == Box.FLAGED)
                count++;
            return count;
    }
}
