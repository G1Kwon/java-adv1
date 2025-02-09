package thread.executor.poolsize;

import static util.MyLogger.log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import thread.executor.ExecutorUtils;
import thread.executor.RunnableTask;

public class PoolSizeMainV2 {

  public static void main(String[] args) {
    ExecutorService es = Executors.newFixedThreadPool(2);
//    ExecutorService es = new ThreadPoolExecutor(2. nThreads, 0L, TimeUnit.MICROSECONDS, new LinkedBlockingDeque<Runnable>());

    log("pool 생성");
    ExecutorUtils.printState(es);

    for (int i = 1; i <= 6; i++) {
      String taskName = "task" + i;
      es.execute(new RunnableTask(taskName));
      ExecutorUtils.printState(es);
    }

    es.close();
    log("== shutdown 완료 ==");
  }

}
