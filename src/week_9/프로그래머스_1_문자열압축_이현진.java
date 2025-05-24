package week_9;

public class 프로그래머스_1_문자열압축_이현진 {
    public static void main(String[] args) {
        String s = "aabbaccc";
        System.out.println(solution(s));
    }

    public static int solution(String s) {
        int minLength = s.length(); // 압축 전 최대 길이

        for (int idx = 1; idx <= s.length() / 2; idx++) {
            int count = 1;
            StringBuilder result = new StringBuilder();
            String word = s.substring(0, idx);

            for (int i = idx; i < s.length(); i += idx) {
                int end = Math.min(i + idx, s.length());
                String now = s.substring(i, end);

                if (now.equals(word)) {
                    count++;
                } else {
                    if (count > 1) result.append(count);
                    result.append(word);
                    word = now;
                    count = 1;
                }
            }

            // 마지막 덩어리 처리
            if (count > 1) result.append(count);
            result.append(word);

            minLength = Math.min(minLength, result.length());
        }

        return minLength;
    }
}