package thread.start;

import static util.MyLogger.log;

public class InnerRunnableMainV1 {

  public static void main(String[] args) {
    log("main() start");
    MyRunnable myRunnable = new MyRunnable();
    Thread thead = new Thread(myRunnable);
    thead.start();
    log("main() end");

  }

  static class MyRunnable implements Runnable {

    @Override
    public void run() {
      log(Thread.currentThread().getName() + " : run()");
    }
  }

}
