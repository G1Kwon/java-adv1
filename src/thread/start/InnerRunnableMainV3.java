package thread.start;

import static util.MyLogger.log;

public class InnerRunnableMainV3 {

  public static void main(String[] args) {
    log("main() start");

    Thread thead = new Thread(new Runnable() {
      @Override
      public void run() {
        log(Thread.currentThread().getName() + " : run()");
      }
    });
    thead.start();

    log("main() end");

  }

}
