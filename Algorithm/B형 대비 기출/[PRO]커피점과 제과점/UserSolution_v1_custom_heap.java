class UserSolution {
    static final int MAX_V = 10010;
    static final int MAX_E = 100000;
    static final int INF = 1000000000;

    static int[] head = new int[MAX_V];
    static int[] to = new int[MAX_E];
    static int[] next = new int[MAX_E];
    static int[] weight = new int[MAX_E];
    static int edgeCnt;

    static int[][] distCB = new int[2][MAX_V];
    static boolean[] isStore = new boolean[MAX_V]; // 커피점/제과점 여부 체크

    // 힙 구조
    static int[] heapW = new int[MAX_E * 2];
    static int[] heapV = new int[MAX_E * 2];
    static int heapSize;

    static void addEdge(int u, int v, int w) {
        to[edgeCnt] = v;
        weight[edgeCnt] = w;
        next[edgeCnt] = head[u];
        head[u] = edgeCnt++;
    }

    static void hq_push(int w, int v) {
        int cur = ++heapSize;
        while (cur > 1) {
            int parent = cur / 2;
            if (heapW[parent] <= w) break;
            heapW[cur] = heapW[parent];
            heapV[cur] = heapV[parent];
            cur = parent;
        }
        heapW[cur] = w;
        heapV[cur] = v;
    }

    static int poppedW, poppedV;
    static boolean hq_pop() {
        if (heapSize == 0) return false;
        poppedW = heapW[1];
        poppedV = heapV[1];

        int lastW = heapW[heapSize];
        int lastV = heapV[heapSize--];

        int cur = 1;
        while (cur * 2 <= heapSize) {
            int child = cur * 2;
            if (child + 1 <= heapSize && heapW[child + 1] < heapW[child]) child++;
            if (lastW <= heapW[child]) break;

            heapW[cur] = heapW[child];
            heapV[cur] = heapV[child];
            cur = child;
        }
        heapW[cur] = lastW;
        heapV[cur] = lastV;
        return true;
    }

    public void init(int N, int K, int sBuilding[], int eBuilding[], int mDistance[]) {
        for (int i = 0; i < N; i++) head[i] = -1;
        edgeCnt = 0;
        for (int i = 0; i < K; i++) {
            addEdge(sBuilding[i], eBuilding[i], mDistance[i]);
            addEdge(eBuilding[i], sBuilding[i], mDistance[i]);
        }
    }

    public void add(int sBuilding, int eBuilding, int mDistance) {
        addEdge(sBuilding, eBuilding, mDistance);
        addEdge(eBuilding, sBuilding, mDistance);
    }

    public int calculate(int M, int mCoffee[], int P, int mBakery[], int R) {
        for (int i = 0; i < MAX_V; i++) {
            distCB[0][i] = INF;
            distCB[1][i] = INF;
            isStore[i] = false;
        }

        for (int i = 0; i < M; i++) isStore[mCoffee[i]] = true;
        for (int i = 0; i < P; i++) isStore[mBakery[i]] = true;

        heapSize = 0;
        for (int i = 0; i < M; i++) {
            distCB[0][mCoffee[i]] = 0;
            hq_push(0, mCoffee[i]);
        }
        runDijkstra(0, R);

        heapSize = 0;
        for (int i = 0; i < P; i++) {
            distCB[1][mBakery[i]] = 0;
            hq_push(0, mBakery[i]);
        }
        runDijkstra(1, R);

        int minSum = INF;
        for (int i = 0; i < MAX_V; i++) {
            if (!isStore[i]) {
                if (distCB[0][i] <= R && distCB[1][i] <= R) {
                    int sum = distCB[0][i] + distCB[1][i];
                    if (sum < minSum) minSum = sum;
                }
            }
        }

        return (minSum == INF) ? -1 : minSum;
    }

    private void runDijkstra(int type, int R) {
        while (hq_pop()) {
            int currW = poppedW;
            int currV = poppedV;

            if (distCB[type][currV] < currW) continue;

            for (int i = head[currV]; i != -1; i = next[i]) {
                int nv = to[i];
                int nw = currW + weight[i];

                if (nw <= R && distCB[type][nv] > nw) {
                    distCB[type][nv] = nw;
                    hq_push(nw, nv);
                }
            }
        }
    }
}