package aa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 먼저 배열 2개
// X 배열에 X좌표
// Y 배열에 Y좌표를 받고
// for문을 돌면서 그래프를 그려야하는데
// 가중치는 (E * L^2)각 해저터널 길이의 제곱만큼
// 거리는 X좌표 뺸거 제곱 + Y좌표 뺸거 제곱
// 아니면 그냥 대각선이 아니라면 좌표뺀거 제곱
// 가중치는 for문을 돌리면서
// X[0], Y[0] 이랑 X[1~islandNum], Y[islandNum]각각 빼고 제곱한거를
// E 받은거랑 곱한다음에.
// 해당 인덱스를 인접리스트로해서 0, 1, 가중치 이렇게 넣으면 될거같음.


public class HanaRoad {
	
	static final int MAX_V = 1005; 
	static final int MAX_E = 1005 * 1005; 
	// 이게 정점기준으로 해야하는지 간선기준으로 해야하는지 다시 확인이 필요.
	static int[] head = new int[MAX_V];
	static int[] to = new int[MAX_E];
	static int[] next = new int[MAX_E];
	static double[] weight = new double[MAX_E];
	static int edgeCnt;
	
	static void init(int v) {
		for(int i = 0; i <= v; i++) {
			head[i] = -1;
		}
		edgeCnt = 0;
		heapSize = 0;
	}
	
	static void addEdge(double w, int u, int v) {
		to[edgeCnt] = v;
		next[edgeCnt] = head[u];
		weight[edgeCnt] = w;
		head[u] = edgeCnt++;
	}
	
	static final int MAX_Q = 1005 * 1005;
	static double[] heapW = new double[MAX_Q];
	static int[] heapV = new int[MAX_Q];
	static int heapSize;
	static double poppedW;
	static int poppedV;
	
	
	static void hq_push(double w, int v) {
		int cur = ++heapSize;
		heapW[cur] = w;
		heapV[cur] = v;
		
		while(cur > 1) {
			int parent = cur / 2;
			
			if(heapW[cur] > heapW[parent]) break;
			
			double tmpW = heapW[cur];
			heapW[cur] = heapW[parent];
			heapW[parent] = tmpW;
			
			int tmpV = heapV[cur];
			heapV[cur] = heapV[parent];
			heapV[parent] = tmpV;
			
			cur = parent;
		}
	}
	
	static boolean hq_pop() {
		if(heapSize == 0) return false; 
		
		poppedW = heapW[1];
		poppedV = heapV[1];
		
		heapW[1] = heapW[heapSize];
		heapV[1] = heapV[heapSize--];
		
		int cur = 1;
		
		while(cur * 2 <= heapSize) {
			int child = cur * 2;
			
			if(child + 1 <= heapSize && heapW[child+1] < heapW[child] ) {
				child++;
			}
			if (heapW[cur] <= heapW[child]) break;
			
			double tmpW = heapW[cur];
			heapW[cur] = heapW[child];
			heapW[child] = tmpW;
			
			int tmpV = heapV[cur];
			heapV[cur] = heapV[child];
			heapV[child] = tmpV;
			
			cur = child;
		}
		return true;
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		
		for(int tc = 1; tc <= T; tc++) {
			int islandNum = Integer.parseInt(br.readLine());
			// 섬 개수
			int[] X_map = new int[islandNum];
			int[] Y_map = new int[islandNum];
			init(islandNum);
			for(int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < islandNum; j++) {
					if(i == 0) {
						// X좌표 입력
						X_map[j] = Integer.parseInt(st.nextToken());
					}
					else {
						// Y좌표 입력
						Y_map[j] = Integer.parseInt(st.nextToken());
					}
				}
			}// 섬 좌표 입력받기
			
			double E = Double.parseDouble(br.readLine());
			
			for(int i = 0; i < islandNum - 1; i++) {
				for(int j = i + 1; j < islandNum; j++) {
					double w = E * (Math.pow((X_map[i] - X_map[j]), 2) + Math.pow((Y_map[i] - Y_map[j]), 2));
					addEdge(w, i, j);
					addEdge(w, j, i);
				}
			}
			
			boolean[] visited = new boolean[MAX_V];
			int cnt = 0;
			double total_w = 0;
			
			hq_push(0, 0);
			
			while(heapSize != 0) {
				hq_pop();
				double curr_W = poppedW;
				int curr_V = poppedV;
				
				if(visited[curr_V]) continue;
				visited[curr_V] = true;
				total_w += curr_W;
				cnt ++;
				
				if(cnt >= islandNum) break;
				
				for(int i = head[curr_V]; i != -1; i = next[i]) {
					int nextNode = to[i];
					double nextWeight = weight[i];
					
					if(!visited[nextNode]) {
						hq_push(nextWeight, nextNode);
					} // 힙큐 방문 처리 확인
				} // 인접 리스트 순회
			} // Prim
			
			long result = Math.round(total_w);
			sb.append("#").append(tc).append(" ");
			sb.append(result).append("\n");
			
		} // tc 문 끝
		System.out.print(sb);
	} // 메인문 끝
}
