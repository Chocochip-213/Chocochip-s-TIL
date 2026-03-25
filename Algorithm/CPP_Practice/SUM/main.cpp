#include <iostream>
#include <vector>
#include <limits>

using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T;
	int buffer_sum = 0;
	int i, j;
	T = 10;
	for (test_case = 1; test_case <= T; ++test_case)
	{
		int tc;
		cin >> tc;
		int max_value = numeric_limits<int>::min();
		vector<vector<int>> matrix(100, vector<int>(100));

		for (i = 0; i < 100; i++) {
			for (j = 0; j < 100; j++) {
				cin >> matrix[i][j];
			}
		}

		for (i = 0; i < 100; i++) {
			for (j = 0; j < 100; j++) {
				buffer_sum += matrix[i][j];
			}
			if (max_value < buffer_sum) max_value = buffer_sum;
			buffer_sum = 0;
		}

		for (j = 0; j < 100; j++) {
			for (i = 0; i < 100; i++) {
				buffer_sum += matrix[i][j];
			}
			if (max_value < buffer_sum) max_value = buffer_sum;
			buffer_sum = 0;
		}

		for (j = 0; j < 2; j++) {
			if (j == 0) {
				for (i = 0; i < 100; i++) {
					buffer_sum += matrix[i][i];
				}
				if (max_value < buffer_sum) max_value = buffer_sum;
				buffer_sum = 0;
			}
			else {
				for (i = 0; i < 100; i++) {
					buffer_sum += matrix[i][99-i];
				}
				if (max_value < buffer_sum) max_value = buffer_sum;
				buffer_sum = 0;
			}
		}

		cout << "#" << tc << " " << max_value << endl;

	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}