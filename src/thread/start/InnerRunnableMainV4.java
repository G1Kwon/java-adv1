package thread.start;

import static util.MyLogger.log;

public class InnerRunnableMainV4 {

  public static void main(String[] args) {
    log("main() start");

    Thread thead = new Thread(() -> log(Thread.currentThread().getName() + " : run()"));
    thead.start();

    log("main() end");

  }

}
