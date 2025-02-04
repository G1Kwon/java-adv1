package thread.collection.simple;

import static util.MyLogger.log;

import thread.collection.simple.list.BasicList;
import thread.collection.simple.list.SimpleList;
import thread.collection.simple.list.SyncProxyList;

public class SimpleListMainV2 {

  public static void main(String[] args) throws InterruptedException {
//    test(new BasicList());
//    test(new SyncList());
    BasicList basicList = new BasicList();
    test(new SyncProxyList(basicList));
  }

  private static void test(SimpleList list) throws InterruptedException {
    log(list.getClass().getSimpleName());

    // A 를 리스트에 저장하는 코드
    Runnable addA = new Runnable() {
      @Override
      public void run() {
        list.add("A");
        log("Thread-1 : list.add(A)");
      }
    };

    // B 를 리스트에 저장하는 코드
    Runnable addB = new Runnable() {
      @Override
      public void run() {
        list.add("B");
        log("Thread-2 : list.add(B)");
      }
    };

    Thread thread1 = new Thread(addA, "Thread-1");
    Thread thread2 = new Thread(addB, "Thread-2");
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();
    log(list);
  }

}
