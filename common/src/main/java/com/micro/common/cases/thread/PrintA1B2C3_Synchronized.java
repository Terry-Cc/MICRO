package com.micro.common.cases.thread;

import lombok.SneakyThrows;
import java.util.concurrent.CountDownLatch;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 * 多线程轮询打印A1B2C3, Synchronized + lock + Runnable + CountDownLatch实现
 * </p>
 * @since 2023/6/20 16:05
 */
public class PrintA1B2C3_Synchronized {

    public static void main(String[] args){
        // block锁
        Object block = new Object();
        // 门栓同步器保证先打印字母(保证顺序)
        CountDownLatch latch = new CountDownLatch(1);
        // 打印字母和数字的执行器
        Print runLet = new Print(65, 9, block, null, latch);
        Print runNum = new Print(49, 9, block, latch, null);
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
        private final Object block;

        /**
         * @since 2023/6/20 17:41
         * @description <p>
         *  当前门栓
         * </p>
         */
        private CountDownLatch current;

        /**
         * @since 2023/6/20 17:41
         * @description <p>
         *  下一个门栓
         * </p>
         */
        private CountDownLatch next;

        Print(int printStart, int loop, Object block, CountDownLatch current, CountDownLatch next) {
            this.printStart = printStart;
            this.loop = loop;
            this.block = block;
            this.current = current;
            this.next = next;
        }

        @SneakyThrows
        @Override
        public void run() {
            if (null != current) {
                current.await();
            }
            synchronized (block) {
                if (null != next) {
                    next.countDown();
                }
                for (int i = 0; i < loop; i++) {
                    System.out.print((char) (printStart + i));
                    block.notify();
                    block.wait();
                }
                block.notify();
            }
        }
    }
}
