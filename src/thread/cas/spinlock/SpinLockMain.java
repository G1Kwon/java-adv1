package thread.cas.spinlock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class SpinLockMain {

  public static void main(String[] args) {
//    SpinLockBad spinLock = new SpinLockBad();
    SpinLock spinLock = new SpinLock();

    Runnable task = new Runnable() {
      @Override
      public void run() {
        spinLock.lock();
        try {
          // critical section
          log("비즈니스 로직 실행");
          sleep(10); // 참고로 오래 걸리는 로직에서는 스핀락을 사용할 필요가 없다. 그럴때 동기화 락을 쓸때가 더 좋음
        } finally {
          spinLock.unlock();
        }
      }
    };

    Thread thread1 = new Thread(task, "Thread-1");
    Thread thread2 = new Thread(task, "Thread-2");

    thread1.start();
    thread2.start();

  }

}
