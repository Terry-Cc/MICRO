package com.micro.common.cases.thread;

import lombok.SneakyThrows;
import java.util.concurrent.*;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 * 多线程轮询打印A1B2C3, BlockingQueue + Runnable实现
 * </p>
 * @since 2023/6/20 18:05
 */
public class PrintA1B2C3_BlockQueue {

    public static void main(String[] args) {
        // 阻塞队列
        BlockingQueue<String> letQueue = new LinkedBlockingDeque<>(1);
        BlockingQueue<String> numQueue = new LinkedBlockingDeque<>(1);
        // 打印字母和数字的执行器
        Print letPrint = new Print(65, 9, letQueue, numQueue, true);
        Print numPrint = new Print(49, 9, numQueue, letQueue, false);
        // 打印字母和数字的线程
        Thread printLetter = new Thread(letPrint);
        Thread printNum = new Thread(numPrint);
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
         * @since 2023/6/20 18:36
         * @description <p>
         *  当前打印队列
         * </p>
         */
        private BlockingQueue<String> currentQueue;

        /**
         * @since 2023/6/20 18:36
         * @description <p>
         *  下一个打印队列
         * </p>
         */
        private BlockingQueue<String> nextQueue;

        /**
         * @since 2023/6/20 18:57
         * @description <p>
         *  是否第一个
         * </p>
         */
        private boolean isFirst;

        Print (int printStart, int loop, BlockingQueue<String> currentQueue, BlockingQueue<String> nextQueue, boolean isFirst) {
            this.printStart = printStart;
            this.loop = loop;
            this.currentQueue = currentQueue;
            this.nextQueue = nextQueue;
            this.isFirst = isFirst;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (0 < loop) {
                if (!isFirst) {
                    System.out.print(currentQueue.take());
                } else {
                    isFirst = false;
                }
                nextQueue.put(String.valueOf((char)printStart));
                printStart = printStart + 1;
                loop = loop - 1;
            }
        }
    }
}
