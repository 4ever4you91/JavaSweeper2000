package sweeper;

class Matrix
{
    private Box [] [] matrix;
    Matrix (Box defaultBox)
    {
        matrix = new Box[Ranges.getSize().x][Ranges.getSize().y];
        for (Coard coard : Ranges.getAllCoards())
            matrix [coard.x] [coard.y] = defaultBox;
    }
    Box get (Coard coard)
    {
        if (Ranges.inRange (coard))
            return matrix [coard.x] [coard.y];
        return null;
    }
    void set (Coard coard, Box box)
    {
        if (Ranges.inRange (coard))
            matrix [coard.x] [coard.y] = box;
    }
}
