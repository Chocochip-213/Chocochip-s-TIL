#include <iostream>
#include <vector>
#include <string>

using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T;

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	//freopen("input.txt", "r", stdin);
	cin >> T;
	/*
	   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
	*/

	for (test_case = 1; test_case <= T; ++test_case)
	{
		string trash;
		int N;
		cin >> trash;
		cin >> N;
		vector<int> word_cnt(N);
		vector<string> word_value(N);
		vector<string> word_output(10);
		string word_out[10] = { "ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN" };


		for (int i = 0; i < N; i++) {
			cin >> word_value[i];
			// cout << word_value[i];
			if (word_value[i] == "ZRO") {
				word_cnt[0] += 1;
			}
			else if (word_value[i] == "ONE") {
				word_cnt[1] += 1;
			}
			else if (word_value[i] == "TWO") {
				word_cnt[2] += 1;
			}
			else if (word_value[i] == "THR") {
				word_cnt[3] += 1;
			}
			else if (word_value[i] == "FOR") {
				word_cnt[4] += 1;
			}
			else if (word_value[i] == "FIV") {
				word_cnt[5] += 1;
			}
			else if (word_value[i] == "SIX") {
				word_cnt[6] += 1;
			}
			else if (word_value[i] == "SVN") {
				word_cnt[7] += 1;
			}
			else if (word_value[i] == "EGT") {
				word_cnt[8] += 1;
			}
			else {
				word_cnt[9] += 1;
			}
		}

		cout << "#" << test_case << endl;
		for (int j = 0; j < 10; j++) {
			for (int k = 0; k < word_cnt[j]; k++) {
				cout << word_out[j] << " ";
			}
		}



	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}