package thread.start;

import static util.MyLogger.log;

public class InnerRunnableMainV2 {

  public static void main(String[] args) {
    log("main() start");

    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        log(Thread.currentThread().getName() + " : run()");
      }
    };
    Thread thead = new Thread(runnable);
    thead.start();

    log("main() end");

  }

}