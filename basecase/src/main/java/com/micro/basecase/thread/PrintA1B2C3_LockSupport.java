package com.micro.basecase.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  多线程轮询打印A1B2C3, LockSupport + Runnable实现
 * </p>
 * @since 2023/6/19 19:21
 */
public class PrintA1B2C3_LockSupport {

    public static void main(String[] args){
        // 线程组
        Map<Thread, Thread> threadMap = new HashMap<>();
        // 打印字母和数字的执行器
        Print runLet = new Print(65, 9, threadMap);
        Print runNum = new Print(49, 9, threadMap);
        // 打印字母和数字的线程
        Thread printLetter = new Thread(runLet);
        Thread printNum = new Thread(runNum);
        // 设置唤醒顺序
        threadMap.put(printLetter, printNum);
        threadMap.put(printNum, printLetter);
        // 启动并同时阻塞
        printLetter.start();
        printNum.start();
        // 唤醒字母线程开始顺序打印(保证顺序)
        LockSupport.unpark(printLetter);
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
         * @since 2023/6/20 17:39
         * @description <p>
         *  线程顺序池
         * </p>
         */
        private Map<Thread, Thread> threadMap;

        Print(int printStart, int loop, Map<Thread, Thread> threadMap) {
            this.printStart = printStart;
            this.loop = loop;
            this.threadMap = threadMap;
        }

        @Override
        public void run() {
            for (int i = 0; i < loop; i++) {
                LockSupport.park();
                System.out.print((char) (printStart + i));
                LockSupport.unpark(threadMap.get(Thread.currentThread()));
            }
        }
    }
}
