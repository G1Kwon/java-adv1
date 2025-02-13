package thread.executor.future;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCancelMain {

  //  private static boolean mayInterruptIfRunning = true;
  private static boolean mayInterruptIfRunning = false;

  public static void main(String[] args) {
    ExecutorService es = Executors.newFixedThreadPool(1);
    Future<String> future = es.submit(new Mytask());
    log("future.state : " + future.state());

    // 일정 시간 후 취소 시도
    sleep(3000);

    // cancel() 호출
    log("future.cancel(" + mayInterruptIfRunning + ") 호출");
    boolean cancelResult = future.cancel(mayInterruptIfRunning);
    log("cancel(" + mayInterruptIfRunning + ") result : " + cancelResult);

    // 결과 확인
    try {
      log("future.result : " + future.get());
    } catch (CancellationException e) {
      log("future 는 이미 취소되었습니다.");
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }

    es.close();
  }

  static class Mytask implements Callable<String> {

    @Override
    public String call() throws Exception {
      try {
        for (int i = 0; i < 10; i++) {
          log("작업 중 : " + i);
          Thread.sleep(1000);
        }

      } catch (InterruptedException e) {
        log("인터럽트 발생");
        return "Interrupted";
      }
      return "completed";
    }
  }

}
