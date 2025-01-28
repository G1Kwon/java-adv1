package thread.sync.test;

public class SyncTestMain {

  public static void main(String[] args) throws InterruptedException {
    Counter counter = new Counter();

    Runnable task = new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 10000; i++) {
          counter.increment();
        }
      }
    };

    Thread t1 = new Thread(task); // 10000번 호출
    Thread t2 = new Thread(task); // 10000번 호출

    t1.start();
    t2.start();

    t1.join();
    t2.join();
    System.out.println("결과 : " + counter.getCount());
  }


  private static class Counter {

    private int count = 0;

    public synchronized void increment() {
      count = count + 1;
    }

    public int getCount() {
      return count;
    }
  }
}
