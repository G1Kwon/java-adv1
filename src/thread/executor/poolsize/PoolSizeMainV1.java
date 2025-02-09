package thread.executor.poolsize;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import thread.executor.ExecutorUtils;
import thread.executor.RunnableTask;

public class PoolSizeMainV1 {

  public static void main(String[] args) {
    ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
    ExecutorService es = new ThreadPoolExecutor(2, 4, 3000,
        TimeUnit.MILLISECONDS, workQueue);
    ExecutorUtils.printState(es);

    es.execute(new RunnableTask("task1"));
    ExecutorUtils.printState(es, "task1");

    es.execute(new RunnableTask("task2"));
    ExecutorUtils.printState(es, "task2");

    es.execute(new RunnableTask("task3"));
    ExecutorUtils.printState(es, "task3");

    es.execute(new RunnableTask("task4"));
    ExecutorUtils.printState(es, "task4");

    // Maximum pool 은 queue 도 가득 차면 그이후에 늘어난다.
    es.execute(new RunnableTask("task5"));
    ExecutorUtils.printState(es, "task5");

    es.execute(new RunnableTask("task6"));
    ExecutorUtils.printState(es, "task6");

    try {
      es.execute(new RunnableTask("task5"));
      ExecutorUtils.printState(es, "task5");
    } catch (RejectedExecutionException e) {
      log("task7 실행 거절 예외 발생 : " + e);
    }

    sleep(3000);
    log("== 작업 수행 완료==");
    ExecutorUtils.printState(es);

    sleep(3000);
    ExecutorUtils.printState(es);

    es.close();
    log("== shutdown 완료 ==");
    ExecutorUtils.printState(es);
  }

}
