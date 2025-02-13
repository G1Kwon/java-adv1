package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV4 {

  public static void main(String[] args) {
    MyTask task = new MyTask();
    Thread thread = new Thread(task, "work");
    thread.start();

    sleep(100);
    log("작업 중단 지시 thread.interrupt()");
    thread.interrupt();
    log("work 스레드 인터럽트 상태 1 = " + thread.isInterrupted());
  }

  static class MyTask implements Runnable {

    @Override
    public void run() {
      while (!Thread.interrupted()) { //Thread.currentThread().isInterrupted() 와 달리 인터럽트 상태를 변경한다.
        log("작업 중");

      }
      log("work 스레드 인터럽트 상태 2 = " + Thread.currentThread().isInterrupted());

      try {
        log("자원 정리");
        Thread.sleep(1000); //InterruptedException 발생
        log("자원 종료");

      } catch (InterruptedException e) {
        log("자원 정리 실패 - 자원 정리 중 인터럽트 발생");
        log("work 스레드 인터럽트 상태3 = " + Thread.currentThread().isInterrupted());
      }
      log("자원 종료");
    }
  }

}
