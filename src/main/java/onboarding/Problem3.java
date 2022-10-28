package onboarding;

public class Problem3 {
    public static int solution(int number) {
        int answer = 0;

        for (int i = 1; i <= number; i++) {
            answer += count(i);
        }

        return answer;
    }

    public static int count(int n) {
        int cnt = 0;
        int num = n;

        while (n > 0) {
            int tmp = n % 10;
            if (tmp == 3 || tmp == 6 || tmp == 9)
                cnt++;
            n /= 10;
        }

        return cnt;
    }
}
