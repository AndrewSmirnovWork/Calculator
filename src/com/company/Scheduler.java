package com.company;

public class Scheduler {
    //Implement a job scheduler which takes in a function f and an integer n, and calls f after n milliseconds.
    interface Function{
        public void execute();
    }
    public static void schedule(Function function, int n){
        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                function.execute();
            }
        }, n);

    }
    public static void main(String args[]) {
        schedule(new Function() {
            @Override
            public void execute() {
                {
                    System.out.println("Hello World");
                }
            }
        }, 1000);

        // java 8 Lambda function
        schedule(() -> System.out.println("Hello World"), 1000);
    }
}
