import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.TreeSet;

class UserSolution {
    static class Product {
        int mID;
        int mCategory;
        int mCompany;
        int mPrice;

        Product(int mID, int mCategory, int mCompany, int mPrice) {
            this.mID = mID;
            this.mCategory = mCategory;
            this.mCompany = mCompany;
            this.mPrice = mPrice;
        }
    }

    static class Comp implements Comparator<Product> {
        @Override
        public int compare(Product a, Product b) {
            if (a.mPrice != b.mPrice)
                return Integer.compare(a.mPrice, b.mPrice);
            return Integer.compare(a.mID, b.mID);
        }
    }

    static int[][] discount;
    static TreeSet<Product>[][] ts = new TreeSet[6][6];
    static int[][] proSize;
    static HashMap<Integer, Product> ProductRaw;
    static Comp cp = new Comp();
    static PriorityQueue<Product> hq;

    public void init() {
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
                ts[i][j] = new TreeSet<>(cp);
            }
        }
        discount = new int[6][6];
        proSize = new int[6][6];
        ProductRaw = new HashMap<>();
        return;
    }

    public int sell(int mID, int mCategory, int mCompany, int mPrice) {
        // 호출 50,000
        Product p = new Product(mID, mCategory, mCompany, mPrice + discount[mCategory][mCompany]);
        proSize[mCategory][mCompany]++;
        ts[mCategory][mCompany].add(p);
        ProductRaw.put(mID, p);
        return proSize[mCategory][mCompany];
    }

    public int closeSale(int mID) {
        // 호출 5,000
        if (!ProductRaw.containsKey(mID))
            return -1;
        Product p = ProductRaw.get(mID);
        proSize[p.mCategory][p.mCompany]--;
        ProductRaw.remove(mID);
        return p.mPrice - discount[p.mCategory][p.mCompany];
    }

    public int discount(int mCategory, int mCompany, int mAmount) {
        discount[mCategory][mCompany] += mAmount;
        Product p;
        while (!ts[mCategory][mCompany].isEmpty()) {
            p = ts[mCategory][mCompany].first();
            if(!ProductRaw.containsKey(p.mID)) {
                ts[mCategory][mCompany].pollFirst();
                continue;
            }
            if (p.mPrice - discount[mCategory][mCompany] <= 0) {
                ts[mCategory][mCompany].pollFirst();
                proSize[mCategory][mCompany]--;
                ProductRaw.remove(p.mID);
            }
            else break;
        }

        return proSize[mCategory][mCompany];
    }

    Solution.RESULT show(int mHow, int mCode) {
        // 호출 1,000
        Solution.RESULT res = new Solution.RESULT();
        Iterator<Product>[][] it = (Iterator<Product>[][]) new Iterator[6][6];
        res.cnt = 0;
        hq = new PriorityQueue<>(cp);
        if(mHow == 0) {
            // 모든 상품
            for(int i = 1; i <= 5; i++) {
                for(int j = 1; j <= 5; j++) {
                    if(ts[i][j].size() == 0) continue;
                    it[i][j] = ts[i][j].iterator();
                    if(it[i][j].hasNext()) {
                        Product p = it[i][j].next();
                        if(!ProductRaw.containsKey(p.mID))
                            while(!ProductRaw.containsKey(p.mID)) {
                                if(!it[i][j].hasNext()) break;
                                p = it[i][j].next();
                            }
                        if(!ProductRaw.containsKey(p.mID)) continue;
                        p = new Product(p.mID, p.mCategory, p.mCompany, p.mPrice - discount[i][j]);
                        hq.add(p);
                    }
                }
            }
        }
        else if(mHow == 1) {
            // 품목이 mCode인 상품
            for(int i = 1; i <= 5; i++) {
                if(ts[mCode][i].size() == 0) continue;
                it[mCode][i] = ts[mCode][i].iterator();
                if(it[mCode][i].hasNext()) {
                    Product p = it[mCode][i].next();
                    if(!ProductRaw.containsKey(p.mID))
                        while(!ProductRaw.containsKey(p.mID)) {
                            it[p.mCategory][p.mCompany].remove();
                            if(!it[mCode][i].hasNext()) break;
                            p = it[mCode][i].next();
                        }
                    if(!ProductRaw.containsKey(p.mID)) continue;
                    p = new Product(p.mID, p.mCategory, p.mCompany, p.mPrice - discount[mCode][i]);
                    hq.add(p);
                }
            }
        }
        else {
            // 제조사가 mCode인 상품
            for(int i = 1; i <= 5; i++) {
                if(ts[i][mCode].size() == 0) continue;
                it[i][mCode] = ts[i][mCode].iterator();
                if(it[i][mCode].hasNext()) {
                    Product p = it[i][mCode].next();
                    if(!ProductRaw.containsKey(p.mID))
                        while(!ProductRaw.containsKey(p.mID)) {
                            it[p.mCategory][p.mCompany].remove();
                            if(!it[i][mCode].hasNext()) break;
                            p = it[i][mCode].next();
                        }
                    if(!ProductRaw.containsKey(p.mID)) continue;
                    p = new Product(p.mID, p.mCategory, p.mCompany, p.mPrice - discount[i][mCode]);
                    hq.add(p);
                }
            }
        }

        while(!hq.isEmpty() && res.cnt < 5) {
            Product p = hq.poll();
            res.IDs[res.cnt] = p.mID;
            res.cnt++;
            if(it[p.mCategory][p.mCompany].hasNext()) {
                p = it[p.mCategory][p.mCompany].next();
                if(!ProductRaw.containsKey(p.mID))
                    while(!ProductRaw.containsKey(p.mID)) {
                        it[p.mCategory][p.mCompany].remove();
                        if(!it[p.mCategory][p.mCompany].hasNext()) break;
                        p = it[p.mCategory][p.mCompany].next();
                    }
                if(!ProductRaw.containsKey(p.mID)) continue;
                p = new Product(p.mID, p.mCategory, p.mCompany, p.mPrice - discount[p.mCategory][p.mCompany]);
                hq.add(p);
            }
        }
        return res;
    }
}