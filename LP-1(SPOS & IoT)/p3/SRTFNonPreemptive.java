import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class Process {
    String pid;
    int arrivalTime, burstTime, remainingTime;
    
    Process(String pid, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }
}

public class SRTFNonPreemptive {
    public static double calculateAverageWaitingTime(ArrayList<Process> processes) {
        int time = 0, completed = 0, totalWaitingTime = 0;
        int n = processes.size();
        
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
        
        while (completed < n) {
            int idx = -1;
            int minBurstTime = Integer.MAX_VALUE;
            
            for (int i = 0; i < n; i++) {
                Process process = processes.get(i);
                if (process.arrivalTime <= time && process.remainingTime > 0 && process.remainingTime < minBurstTime) {
                    minBurstTime = process.remainingTime;
                    idx = i;
                }
            }
            
            if (idx == -1) {
                time++;
            } else {
                Process current = processes.get(idx);
                time += current.remainingTime;
                current.remainingTime = 0;
                completed++;
                totalWaitingTime += time - current.arrivalTime - current.burstTime;
            }
        }
        
        return (double) totalWaitingTime / n;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Process> processes = new ArrayList<>();
        
        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Process ID for process " + (i + 1) + ": ");
            String pid = scanner.next();
            System.out.print("Enter Arrival Time for process " + pid + ": ");
            int arrivalTime = scanner.nextInt();
            System.out.print("Enter Burst Time for process " + pid + ": ");
            int burstTime = scanner.nextInt();
            processes.add(new Process(pid, arrivalTime, burstTime));
        }
        
        double avgWaitingTime = calculateAverageWaitingTime(processes);
        System.out.println("Average Waiting Time (Non-Preemptive): " + avgWaitingTime);
    }
}
