#include<iostream>
#include <vector>


using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T;
	int chkLeftRight[] = {-2, 2, -1, 1};
	// 좌 우측 2칸 전망부터 확인

    freopen("input.txt", "r", stdin);

    T = 10;

    for(test_case = 1; test_case <= T; ++test_case)
	{
		int N;
		int sum = 0;
		bool break_flag;
		cin >> N;
		vector<int> building(N);
		// 전체 환경 생성
		for(int i = 0; i < N; i++){
			cin >> building[i];
		}
		for(int j = 2; j < N-2; j++){
			int buffer = 256;
			break_flag = false;
			for(int k = 0; k < 4; k++){
				int height = building[j] - building[j+chkLeftRight[k]];
				if(height > 0){
					if(height < buffer)
						buffer = height;
				}
				else{
					break_flag = true;
					// 건물 조망이 가려진다면 바로
					break;
				}
			}
			if(!break_flag){
				sum += buffer;
			}	

		}

		cout << "#" << test_case << " " << sum << endl;
		
	}
	

	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}