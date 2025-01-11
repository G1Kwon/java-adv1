package thread.start;

public class HelloThread extends Thread {

  @Override
  public void run() {
    System.out.println("Hello World!");
    System.out.println(Thread.currentThread().getName() + " : run()");
  }


}
