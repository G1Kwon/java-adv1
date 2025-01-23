package thread.control.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMain {

  public static void main(String[] args) {
    MyTask task = new MyTask();
    Thread t = new Thread(task, "work");
    log("runFlag : " + task.runFlag);
    t.start();

    sleep(1000);
    log("make runFlag to false");
    task.runFlag = false;
    log("runFlag : " + task.runFlag);
    log("main end");
  }

  static class MyTask implements Runnable {

    //    boolean runFlag = true;
    volatile boolean runFlag = true;

    @Override
    public void run() {
      log("task 시작");
      while (runFlag) {
        //runFlag 가 false 로 변하면 탈출
      }
      log("task 종료");
    }
  }

}
