package thread.start;

public class HelloThreadMain {

  public static void main(String[] args) {
    System.out.println(Thread.currentThread().getName() + " + main() start");

    HelloThread helloThread = new HelloThread();
    System.out.println(Thread.currentThread().getName() + " + helloThread.start() start");
    helloThread.start();
    System.out.println(Thread.currentThread().getName() + " + helloThread.start() end");

    System.out.println(Thread.currentThread().getName() + " + main() end");

    //스레드는 순서와 실행 기간을 모두 보장하지 않는다 다양한 실행 결과가 나올 수 있다. 이것이 바로 멀티 스레드다.
  }

}
