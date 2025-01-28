package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccountV5 implements BankAccount {

  private int balance;

  private final Lock lock = new ReentrantLock();

  public BankAccountV5(int initialBalance) {
    this.balance = initialBalance;
  }

  @Override
  public boolean withdraw(int amount) {
    log("거래 시작 : " + getClass().getSimpleName());

    if (!lock.tryLock()) {
      log("[진입 실패] 이미 처리중인 작업이 있습니다.");
      return false;
    }

    try {
      // ==임계영역 시작==
      log("[검증 시작] 출금액 : " + amount + ", 잔액 : " + balance);
      //잔고가 출금액 보다 적으면, 진행하면 안된다.
      if (amount > balance) {
        log("[검증 실페] 출금액 : " + amount + ", 잔액 : " + balance);
        return false;
      }

      //잔고가 출금액 보다 많으면, 진행
      log("[검증 완료] 출금액 : " + amount + ", 잔액 : " + balance);

      //출금에 걸리는 시간으로 가정
      sleep(1000);

      //balance = balance - amount;
      balance -= amount;
      log("[검증 완료] 출금액 : " + amount + ", 잔액 : " + balance);

    } finally {
      // ReentrantLock 을 이용하여 Unlock
      lock.unlock();
    }

    log("거래 종료");
    return true;
  }

  @Override
  public int getBalance() {
    // ReentrantLock 을 이용하여 Lock
    lock.lock();
    try {
      return balance;
    } finally {
      // ReentrantLock 을 이용하여 Unlock
      lock.unlock();
    }
  }
}
