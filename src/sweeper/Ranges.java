package sweeper;

import java.util.ArrayList;
import java.util.Random;

public class Ranges {
    private static Coard size;
    private static ArrayList<Coard> allCoards;
    private static Random random = new Random();
    public static void setSize (Coard _size)
    {
        size = _size;
        allCoards = new ArrayList<Coard>();
        for (int y=0; y<size.y; y++)
            for (int x=0; x<size.x; x++)
                allCoards.add(new Coard(x, y));
    }

    public static Coard getSize()
    {
        return size;
    }
    public static ArrayList<Coard> getAllCoards()
    {
        return allCoards;
    }
    static boolean inRange (Coard coard)
    {
        return coard.x >= 0 && coard.x < size.x &&
                coard.y >=0 && coard.y < size.y;
    }
    static Coard getRandomCoard(){
        return new Coard(random.nextInt(size.x), random.nextInt(size.y));
    }

    static ArrayList<Coard> getCoardsAround(Coard coard){
        Coard around;
        ArrayList<Coard> list = new ArrayList<Coard>();
        for (int x = coard.x - 1; x<=coard.x+1; x++)
            for (int y = coard.y-1; y<=coard.y+1; y++)
                if (inRange(around = new Coard(x, y)))
                    if (!around.equals(coard))
                        list.add(around);
        return list;
    }
}
