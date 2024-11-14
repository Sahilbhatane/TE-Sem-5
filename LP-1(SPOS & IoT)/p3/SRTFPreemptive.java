import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
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

public class SRTFPreemptive {
    public static double calculateAverageWaitingTime(ArrayList<Process> processes) {
        int time = 0, completed = 0, totalWaitingTime = 0;
        int n = processes.size();
        
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
        PriorityQueue<Process> queue = new PriorityQueue<>(Comparator.comparingInt(p -> p.remainingTime));
        
        int i = 0;
        while (completed < n) {
            while (i < n && processes.get(i).arrivalTime <= time) {
                queue.add(processes.get(i));
                i++;
            }
            if (!queue.isEmpty()) {
                Process current = queue.poll();
                current.remainingTime--;
                time++;
                
                if (current.remainingTime == 0) {
                    completed++;
                    totalWaitingTime += time - current.arrivalTime - current.burstTime;
                } else {
                    queue.add(current);
                }
            } else {
                time++;
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
        System.out.println("Average Waiting Time (Preemptive): " + avgWaitingTime);
    }
}
