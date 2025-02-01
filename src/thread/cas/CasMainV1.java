package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CasMainV1 {

  public static void main(String[] args) {
    AtomicInteger atomicInteger = new AtomicInteger(0);
    System.out.println("start value = " + atomicInteger.get());

    boolean result1 = atomicInteger.compareAndSet(0, 1);
    // 1. 기대하는 값이 0이면 2. 값을 2로 변경하라. 이것 자체는 원자적 연산이 아니다. 하지만 CPU 에서 원자적인 연산으로 처리한다.
    System.out.println("result1 = " + result1 + "; value = " + atomicInteger.get()); // 1

    boolean result2 = atomicInteger.compareAndSet(0, 1);
    System.out.println("result2 = " + result2 + "; value = " + atomicInteger.get());
  }

}
