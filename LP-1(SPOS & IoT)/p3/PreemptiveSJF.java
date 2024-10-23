import java.util.Scanner;

class PreemptiveSJF {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        int n, i, smallest = 0, time, count = 0, end;
        int at[], bt[], rem_bt[], wt[], tat[];
        float awt = 0, atat = 0;

        System.out.print("Enter number of processes: ");
        n = s.nextInt();

        at = new int[n];
        bt = new int[n];
        rem_bt = new int[n];
        wt = new int[n];
        tat = new int[n];

        for (i = 0; i < n; i++) {
            System.out.print("\nEnter Arrival Time for Process[" + (i + 1) + "]: ");
            at[i] = s.nextInt();
            System.out.print("Enter Burst Time for Process[" + (i + 1) + "]: ");
            bt[i] = s.nextInt();
            rem_bt[i] = bt[i];
        }

        int current_time = 0;
        int completed = 0;
        boolean is_completed[] = new boolean[n];

        while (completed != n) {
            smallest = -1;
            int minimum_remaining_time = Integer.MAX_VALUE;

            for (i = 0; i < n; i++) {
                if (at[i] <= current_time && !is_completed[i] && rem_bt[i] < minimum_remaining_time && rem_bt[i] > 0) {
                    minimum_remaining_time = rem_bt[i];
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
                wt[smallest] = current_time - at[smallest] - bt[smallest];
                tat[smallest] = current_time - at[smallest];
                awt += wt[smallest];
                atat += tat[smallest];
            }
        }

        awt /= n;
        atat /= n;

        System.out.println("\nProcess\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");
        for (i = 0; i < n; i++) {
            System.out.println("\nP" + (i + 1) + "\t\t" + at[i] + "\t\t" + bt[i] + "\t\t" + wt[i] + "\t\t" + tat[i]);
        }

        System.out.println("\nAverage Waiting Time: " + awt);
        System.out.println("Average Turnaround Time: " + atat);

        s.close();
    }
}
