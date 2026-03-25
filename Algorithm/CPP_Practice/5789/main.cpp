#include<iostream>
#include <vector>
using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T;

	freopen("input.txt", "r", stdin);
	cin>>T;
	/*
	   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
	*/
	for(test_case = 1; test_case <= T; ++test_case)
	{
        int N, Q;
        int L, R;
        cin >> N >> Q;
        vector<int> boxex(N);

        for(int j = 0; j < Q; j++){
            cin >> L >> R;
            for(int k = L-1; k < R; k++){
                boxex[k] = j + 1;
            }
        }
        cout << "#" << test_case << " ";
        for(int i = 0; i < N; i ++){
            cout << boxex[i] << " ";
        }
        cout << endl;

	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}