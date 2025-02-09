package thread.executor.future;

import static util.MyLogger.log;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SumTaskMainV2_Bad {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    SumTAsk task1 = new SumTAsk(1, 50);
    SumTAsk task2 = new SumTAsk(51, 100);

    ExecutorService es = Executors.newFixedThreadPool(2);

    Integer sum1 = es.submit(task1).get(); // 2초
//    Future<Integer> f1 = es.submit(task1);
//    Integer sum1 = f1.get(); // 2초

    Future<Integer> f2 = es.submit(task2);
    Integer sum2 = f2.get(); // 2초˚

    log("task1.result = " + sum1);
    log("task2.result = " + sum2);

    int sumAll = sum1 + sum2;
    log("sumAll = " + sumAll);

    es.close();
  }

  static class SumTAsk implements Callable<Integer> {

    int startValue;
    int endValue;

    public SumTAsk(int startValue, int endValue) {
      this.startValue = startValue;
      this.endValue = endValue;
    }

    @Override
    public Integer call() throws Exception {
      log("작업 시작");
      Thread.sleep(2000);
      int sum = 0;
      for (int i = startValue; i <= endValue; i++) {
        sum += i;
      }
      log("작업 완료 result = " + sum);
      return sum;
    }
  }

}
