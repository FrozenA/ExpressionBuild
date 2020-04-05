public class Pair {
    int first;
    int second;

    Pair() {
        this.first = 0;
        this.second = 1;
    }

    Pair(Pair p) {
        this.first = p.first;
        this.second = p.second;
    }

    Pair(int fi, int sc) {
        this.first = fi;
        this.second = sc;
    }

    Pair(String str) {
        this.first = 0;
        this.second = 1;
        String[] strs = str.split("\'|/");
        if (strs.length == 1) {
            this.first = Integer.parseInt(strs[0]);
        } else if (strs.length == 2) {
            this.first = Integer.parseInt(strs[0]);
            this.second = Integer.parseInt(strs[1]);
        } else if (strs.length == 3) {
            this.first = Integer.parseInt(strs[1]);
            this.second = Integer.parseInt(strs[2]);
            this.first += this.second * Integer.parseInt(strs[0]);
        }
        gcd();
    }

    public void gcd() {
        int a = this.first;
        int b = this.second;
        if (a < b) {
            a ^= b;
            b ^= a;
            a ^= b;
        }
        while (b > 0) {
            int c = a % b;
            a = b;
            b = c;
        }
        this.first /= a;
        this.second /= a;
    }

    public Pair add(Pair b) {
        Pair a = new Pair(b);
        a.first *= this.second;
        this.first *= a.second;
        this.first += a.first;
        this.second *= a.second;
        gcd();
        return this;
    }

    public Pair dec(Pair b) {
        Pair a = new Pair(b);
        a.first *= this.second;
        this.first *= a.second;
        this.first -= a.first;
        if (this.first < 0) {
            this.first = -1;
            this.second = 0;
            return this;
        }
        this.second *= a.second;
        gcd();
        return this;
    }

    public Pair mul(Pair b) {
        Pair a = new Pair(b);
        this.first *= a.first;
        this.second *= a.second;
        gcd();
        return this;
    }

    public Pair div(Pair b) {
        if (b.first == 0)
            return new Pair(-1, 0);
        Pair a = new Pair(b);
        this.mul(new Pair(a.second, a.first));
        gcd();
        return this;
    }

    public boolean equal(Pair b) {
        if (this.first == b.first && this.second == b.second)
            return true;
        return false;
    }

    public boolean compare(Pair b) {
        Pair a = new Pair(this);
        a.dec(b);
        if (a.equal(new Pair(-1, 0)))
            return false;
        return true;
    }

    public boolean isLessZero() {
        if (this.equal(new Pair(-1, 0)))
            return true;
        return false;
    }

    public String toString() {
        if (this.second != 1)
            return "" + this.first + "/" + this.second;
        else
            return "" + this.first;
    }
}