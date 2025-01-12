package thread.start;

import static util.MyLogger.log;

public class ManyThreadMainV1 {

  public static void main(String[] args) {
    log("main() start");

    HelloRunnable runnable = new HelloRunnable();
    Thread thead1 = new Thread(runnable);
    thead1.start();
    Thread thead2 = new Thread(runnable);
    thead2.start();
    Thread thead3 = new Thread(runnable);
    thead3.start();

    log("main() end");
  }

}
