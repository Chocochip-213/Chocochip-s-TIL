package aa;

// 크루스칼
// 가중치를 기준으로 연결 관계 리스트를 정렬한다.
// union - find 구현하고
// 순서대로 돌면서 연결한다.
// ?


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MST_kruskal {
	static final int MAX_E = 100001;
	static int[] u = new int[MAX_E]; // 정점
	static int[] v = new int[MAX_E]; // 간선
	static int[] w = new int[MAX_E]; // 가중치
	static Integer[] idx = new Integer[MAX_E];
	static int[] parents = new int[10005];
	
	static void init(int v) {
		for(int i = 1; i <= v; i++){
			parents[i] = i;
		}
	}
	
	static int find(int x) {
		if (parents[x] == x) return x;
		
		return parents[x] = find(parents[x]);
	}
	
	static void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);
		
		if (rootX != rootY) {
			parents[rootY] = rootX;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V_num = Integer.parseInt(st.nextToken());
		int E_num = Integer.parseInt(st.nextToken());
		// V: 정점 개수(10,000), E: 간선의 개수 (100,000)
		int w_sum = 0;
		int earlyCount = 0;
		for(int i = 0; i < E_num; i++) {
			st = new StringTokenizer(br.readLine());
			u[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
			w[i] = Integer.parseInt(st.nextToken());
			
			idx[i] = i;
		}// 그래프 입력받기 / 정점1 정점2 가중치
		
		Arrays.sort(idx, 0, E_num, (a, b) -> Integer.compare(w[a], w[b]));
		// 가중치순으로 인덱스 정렬하기.
		
		init(V_num);
		for (int i = 0; i < E_num; i++) {
			if(find(u[idx[i]]) != find(v[idx[i]])) {
				union(u[idx[i]], v[idx[i]]);
				w_sum += w[idx[i]];
				earlyCount++;
				if(earlyCount >= V_num - 1) break;
			}
		}
		
		System.out.println(w_sum);
		
		
	} // 메인문 끝
}
