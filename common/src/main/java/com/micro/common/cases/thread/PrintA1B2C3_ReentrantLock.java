package com.micro.common.cases.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 * 多线程轮询打印A1B2C3, ReentrantLock + Runnable + CountDownLatch实现
 * </p>
 * @since 2023/6/20 16:48
 */
public class PrintA1B2C3_ReentrantLock {

    public static void main(String[] args){
        // 锁
        ReentrantLock lock = new ReentrantLock();
        Condition conditionLet = lock.newCondition();
        Condition conditionNum = lock.newCondition();
        // 门栓(保证顺序)
        CountDownLatch latch = new CountDownLatch(1);
        // 打印字母和数字的执行器
        Print runLet = new Print(65, 9, lock, conditionLet, conditionNum, null, latch);
        Print runNum = new Print(49, 9, lock, conditionNum, conditionLet, latch, null);
        // 打印字母和数字的线程
        Thread printLetter = new Thread(runLet);
        Thread printNum = new Thread(runNum);
        // 启动
        printNum.start();
        printLetter.start();
    }

    private static class Print implements Runnable {

        /**
         * @since 2023/6/20 17:39
         * @description <p>
         *  开始打印的字母
         * </p>
         */
        private int printStart;

        /**
         * @since 2023/6/20 17:39
         * @description <p>
         *  顺序打印的长度
         * </p>
         */
        private int loop;

        /**
         * @since 2023/6/20 17:41
         * @description <p>
         *  锁对象
         * </p>
         */
        private ReentrantLock lock;

        private Condition currentCondition;

        private Condition nextCondition;

        /**
         * @since 2023/6/20 17:41
         * @description <p>
         *  当前门栓
         * </p>
         */
        private CountDownLatch currentLatch;

        /**
         * @since 2023/6/20 17:41
         * @description <p>
         *  下一个门栓
         * </p>
         */
        private CountDownLatch nextLatch;


        Print(int printStart, int loop, ReentrantLock lock, Condition currentCondition, Condition nextCondition, CountDownLatch currentLatch, CountDownLatch nextLatch) {
            this.printStart = printStart;
            this.loop = loop;
            this.lock = lock;
            this.currentCondition = currentCondition;
            this.nextCondition = nextCondition;
            this.currentLatch = currentLatch;
            this.nextLatch = nextLatch;
        }

        @Override
        public void run() {
            try {
                if (null != currentLatch) {
                    currentLatch.await();
                }
                lock.lock();
                if (null != nextLatch) {
                    nextLatch.countDown();
                }
                for (int i = 0; i < loop; i++) {
                    System.out.print((char) (printStart + i));
                    nextCondition.signal();
                    currentCondition.await();
                }
                nextCondition.signal();
            } catch (InterruptedException ignored) {
                // none
            } finally {
                lock.unlock();
            }
        }
    }
}
