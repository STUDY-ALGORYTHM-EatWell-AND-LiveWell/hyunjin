package week_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 프로그래머스_3_추석트래픽_이현진 {
    public static void main(String[] args) {
        String[] lines =
                {
                        "2016-09-15 01:00:04.002 2.0s",
                        "2016-09-15 01:00:07.000 2s"
                };
        System.out.println(Arrays.toString(new int[]{(solution(lines))}));
    }

    // 밀리초 단위로 변환하는 함수
    public static long getMilSecByString(String time) {
        long val = 0;
        String[] split = time.split(":");
        val += (Long.parseLong(split[0]) * 60 * 60); // 시
        val += (Long.parseLong(split[1]) * 60); // 분
        val *= 1000; // 밀리초 단위로 변환하기 위해
        val += (long) (Double.parseDouble(split[2]) * 1000); // 초 (1초 = 1000밀리초)

        System.out.println("val = " + val);
        return val;
    }

    // 시작시간과 끝시간만 저장
    private static class Job {
        long startTime;
        long endTime;

        public Job(long startTime, long endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    public static int solution(String[] lines) {
        int answer = 0;
        List<Job> jobList = new ArrayList<>();
        for (String line : lines) {
            String[] splits = line.split(" ");
            long end = getMilSecByString(splits[1]);
            long start = end - (long) (Double.parseDouble(splits[2].replace("s", "")) * 1000) + 1;

            Job job = new Job(start, end);
            jobList.add(job);
        }

        for(int i = 0; i < jobList.size(); i++) {
            int cnt = 0;
            long end = jobList.get(i).endTime;
            for (Job job : jobList) {
                if(job.startTime < end + 1000 && job.endTime >= end) cnt++;
            }
            answer = Math.max(answer, cnt);
        }
        return answer;
    }
}
