package thread.control;

import static util.MyLogger.log;

import thread.start.HelloRunnable;

public class ThreadInfoMain {

  public static void main(String[] args) {
    //main 스레드
    Thread mainThread = Thread.currentThread();
    log("mainThread = " + mainThread);
    log("mainThread.threadId() = " + mainThread.getId());
    log("mainThread.getName() = " + mainThread.getName());
    log("mainThread.getPriority() = " + mainThread.getPriority());
    log("mainThread.getThreadGroup() = " + mainThread.getThreadGroup());
    log("mainThread.getState() = " + mainThread.getState());

    //myThread 스레드
    Thread myThread = new Thread(new HelloRunnable(), "myThread");
    log("mainThread = " + myThread);
    log("mainThread.threadId() = " + myThread.getId());
    log("mainThread.getName() = " + myThread.getName());
    log("mainThread.getPriority() = " + myThread.getPriority());
    log("mainThread.getThreadGroup() = " + myThread.getThreadGroup());
    log("mainThread.getState() = " + myThread.getState());
  }

}
