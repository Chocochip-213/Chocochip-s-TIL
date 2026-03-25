import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

class UserSolution {
    // 상품
    // mID, mCategory, mCompany, mPrice
    static class product {
        int mID;
        int mCategory;
        int mCompany;
        int mPrice;

        product(int mID, int mCategory, int mCompany, int mPrice) {
            this.mID = mID;
            this.mCategory = mCategory;
            this.mCompany = mCompany;
            this.mPrice = mPrice;
        }
    } // 상품 객체

    static class tComp implements Comparator<product> {
        @Override
        public int compare(product a, product b) {
            if (a.mPrice != b.mPrice)
                return Integer.compare(a.mPrice, b.mPrice);
            return Integer.compare(a.mID, b.mID);
        }
    }

    // 2. sell() 50,000 이하.
    // 판매 후에
    // 품목이 mCategory고 제조사가 mCompany인 판매중인 상품의 개수를 반환해야함.
    // 개수관리를 따로?
    // [mCategory][mCompany] 각각 5개니까 조합이 25개가 나옴.
    //

    // 3. closeSale() 5,000 이하.
    // 판매종료 시 종료전 상품 가격 반환

    // 4. discount() 10,000 이하.
    // 단순하게 다 적용한다고 하면 50000 * 10000 = 5억번임 불가능
    // 할인 적용 후
    // 품목이 mCategory고 제조사가 mCompany인 판매중인 상품의 개수를 반환해야함.
    // [mCategory][mCompany]

    // 5. show() 1,000 이하.
    // 모든상품에 대해서, 품목이 mCode인 상품, 제조사가 mCode인 상품
    // 가격이 낮은 순서로 5개, 가격이 같다면 ID가 작은 순서로.
    // 얘를 하려면 [전체], [mCategory 전체], [mCompnay 전체] 이렇게 3개 그룹도 필요함.
    // [전체]는 조합 25개에서 가장 우선순위가 높은거를 25개 조합에서 다 뽑아서를 힙큐에 넣고
    // mCategory전체는 [mCategory][회사 5개]에서 뽑아서 넣고
    // mCompany전체도 반대로 [카테고리 5개][mCompany]로 뽑아서 넣고 pop하고.
    // 두번째넣고 pop하고.. 5번째까지 이렇게 하면 될듯.


    // discount 할떄마다 discount Version을 배열로 관리하고
    // sell할때는 discount Version + 1한거를 주면될듯.
    // Version * 1000 + mCategory * 100 + mCompany * 10 해서 해시맵으로 관리하자 버전을.
    // 할인 버전과 상품 버전을 관리해야한다.
    // discount할때마다 할인을 진행한다. 상품이 보통 먼저 버전이 생성된다.
    // 상품은  현재 최신 discount Version + 1로 생성된다.
    // 만약 해시맵에 키가 없다면 할인율은 0이다.
    // 할인 버전은 0부터 시작해서 첫 할인을 시작하면 할인은 + 1을 해서 1로 생성된다.
    // 그러면 상품 버전이 1인 상품은 할인 Version이 1인 할인율을 적용받는다.

    static TreeSet<product>[][] pd = new TreeSet[6][6];
    static tComp tc = new tComp();
    static Map<Integer, product> pInfo;
    static PriorityQueue<product> showPQ;
    static Iterator<product>[][] it = new Iterator[6][6];
    static int[][] discount = new int[6][6];

    public void init() {
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 6; j++) {
                pd[i][j] = new TreeSet<UserSolution.product>(tc);
                discount[i][j] = 0;
            }
        }
        pInfo = new HashMap<>();
        return;
    }

    public int sell(int mID, int mCategory, int mCompany, int mPrice) {
        int key = (mCategory*100) + (mCompany*10);
        // 첫 버전이 존재하지 않는다면.
        product p = new product(mID, mCategory, mCompany, mPrice + discount[mCategory][mCompany]);
        pd[mCategory][mCompany].add(p);
        pInfo.put(mID, p);
        return pd[mCategory][mCompany].size();
    }

    public int closeSale(int mID) {
        // 판매중이지 않으면 return -1
        if(!pInfo.containsKey(mID)) return -1;
        product p = pInfo.get(mID);
        // 판매가 종료되었으면 -1
        if(!pd[p.mCategory][p.mCompany].contains(p)) return -1;
        pd[p.mCategory][p.mCompany].remove(p);
        return p.mPrice - discount[p.mCategory][p.mCompany];
    }

    public int discount(int mCategory, int mCompany, int mAmount) {
        ArrayList<product> tmpList = new ArrayList<>(pd[mCategory][mCompany]);
        // 누적 할인값.
        discount[mCategory][mCompany] += mAmount;
        for(product p : tmpList) {
            if(p.mPrice - discount[mCategory][mCompany] <= 0) {
                pd[mCategory][mCompany].remove(p);
                continue;
            }
            break;
        }
        return pd[mCategory][mCompany].size();
    }

    Solution.RESULT show(int mHow, int mCode) {
        Solution.RESULT res = new Solution.RESULT();
        res.cnt = 0;

        int finalPrice;
        showPQ = new PriorityQueue<>(tc);
        product p;
        // 모든 상품에 대해서
        if(mHow == 0) {
            for(int i = 1; i <= 5; i++) {
                for(int j = 1; j <= 5; j++) {
                    it[i][j] = pd[i][j].iterator();
                }
            }

            for(int k = 0; k < 5; k++) {
                for(int i = 1; i <= 5; i++) {
                    for(int j = 1; j <= 5; j++) {
                        if(!it[i][j].hasNext()) continue;
                        p = it[i][j].next();
                        finalPrice = p.mPrice - discount[i][j];
                        showPQ.add(new product(p.mID, p.mCategory, p.mCompany, finalPrice));
                    }
                }
            }
        }
        // mCategory가 mCode인 상품
        else if(mHow == 1) {
            for(int i = 1; i <= 5; i++) {
                it[mCode][i] = pd[mCode][i].iterator();
            }

            for(int k = 0; k < 5; k++) {
                for(int i = 1; i <= 5; i++) {
                    if(!it[mCode][i].hasNext()) continue;
                    p = it[mCode][i].next();
                    finalPrice = p.mPrice - discount[mCode][i];
                    showPQ.add(new product(p.mID, p.mCategory, p.mCompany, finalPrice));
                }
            }
        }
        // mCompany가 mCode인 상품
        // 나중에 들어온 상품은 시발 할인율이 적용이 안됨 ㅋㅋㅋㅋ
        else {
            for(int i = 1; i <= 5; i++) {
                it[i][mCode] = pd[i][mCode].iterator();
            }

            for(int k = 0; k < 5; k++) {
                for(int i = 1; i <= 5; i++) {
                    if(!it[i][mCode].hasNext()) continue;
                    p = it[i][mCode].next();
                    finalPrice = p.mPrice - discount[i][mCode];
                    showPQ.add(new product(p.mID, p.mCategory, p.mCompany, finalPrice));
                }
            }

        }

        for(int i = 0; i < 5 && !showPQ.isEmpty(); i++) {
            res.cnt++;
            res.IDs[i] = showPQ.poll().mID;
        }

        return res;
    }
}