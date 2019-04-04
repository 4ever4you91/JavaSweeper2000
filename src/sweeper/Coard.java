package sweeper;

import sun.security.provider.certpath.CertId;

public class Coard {
    public int x;
    public int y;

    public Coard(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals(Object o){
        if (o instanceof Coard)
        {
            Coard to = (Coard)o;
            return to.x==x && to.y==y;
        }
        return super.equals(o);
    }
}
