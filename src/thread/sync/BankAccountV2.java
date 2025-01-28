package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV2 implements BankAccount {

  private int balance;

  public BankAccountV2(int initialBalance) {
    this.balance = initialBalance;
  }

  @Override
  public synchronized boolean withdraw(int amount) {
    log("거래 시작 : " + getClass().getSimpleName());

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

    log("거래 종료");
    return true;
  }

  @Override
  public synchronized int getBalance() {
    return balance;
  }
}
