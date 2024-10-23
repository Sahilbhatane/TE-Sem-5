import java.util.Scanner;

public class PreemptivePriority {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        int n, p[], pp[], bt[], rem_bt[], w[], t[], awt = 0, atat = 0, i, time = 0, smallest;
        p = new int[10];
        pp = new int[10];
        bt = new int[10];
        rem_bt = new int[10];
        w = new int[10];
        t = new int[10];

        System.out.print("Enter the number of processes: ");
        n = s.nextInt();
        System.out.print("\n\tEnter burst time and priorities:\n");

        for (i = 0; i < n; i++) {
            System.out.print("\nProcess[" + (i + 1) + "]: ");
            bt[i] = s.nextInt();
            pp[i] = s.nextInt();
            rem_bt[i] = bt[i];
            p[i] = i + 1;
        }

        boolean is_completed[] = new boolean[n];
        int completed = 0;
        int current_time = 0;

        while (completed != n) {
            smallest = -1;
            int highest_priority = Integer.MAX_VALUE;

            for (i = 0; i < n; i++) {
                if (!is_completed[i] && pp[i] < highest_priority && rem_bt[i] > 0) {
                    highest_priority = pp[i];
                    smallest = i;
                }
            }

            if (smallest == -1) {
                current_time++;
                continue;
            }

            rem_bt[smallest]--;
            current_time++;

            if (rem_bt[smallest] == 0) {
                completed++;
                is_completed[smallest] = true;

                t[smallest] = current_time;
                w[smallest] = t[smallest] - bt[smallest];
                awt += w[smallest];
                atat += t[smallest];
            }
        }

        System.out.print("\n\nProcess \t Burst Time \t Wait Time \t Turnaround Time \t Priority\n");
        for (i = 0; i < n; i++) {
            System.out.print("\n " + p[i] + "\t\t " + bt[i] + "\t\t " + w[i] + "\t\t " + t[i] + "\t\t " + pp[i] + "\n");
        }

        awt /= n;
        atat /= n;
        System.out.print("\nAverage Wait Time: " + awt);
        System.out.print("\nAverage Turnaround Time: " + atat);

        s.close();
    }
}
