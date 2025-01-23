package thread.control.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileCountMain {

  public static void main(String[] args) {
    MyTask task = new MyTask();
    Thread t = new Thread(task, "work");
    log("flag : " + task.flag);
    t.start();

    sleep(1000);

    log("make flag to false");
    task.flag = false;
    log("flag = " + task.flag + ", count = " + task.count + " in main");

  }

  static class MyTask implements Runnable {

//    boolean flag = true;
//    long count;

    // main 스레드가 flag 를 변경하는 시점에 work 스레드도 flag 의 변경 값을 정확하게 확인할 수 있다.
    // volatile 을 적용하면 캐시 메모리가 아니라 메인 메모리에 항상 직접 접근하기 때문에 성능이 상대적으로 떨어진다.
    volatile boolean flag = true;
    volatile long count;

    @Override
    public void run() {
      log("task 시작");
      while (flag) {
        count++;
        if (count % 100_000_000 == 0) {
          log("flag = " + flag + ", count = " + count + " in while()");
        }
      }
      log("flag = " + flag + ", count = " + count + " 종료");
    }
  }

}
