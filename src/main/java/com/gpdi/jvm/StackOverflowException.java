package com.gpdi.jvm;

/**
 * 1、栈描述：jvm中有两种栈，第一种是本地方法栈，用户与其他语言进行交互
 *            第二种是方法操作栈，在jvm中方法调用就是进行压栈和出栈操作
 *            当栈的深度（方法调用方法的次数）超过jvm设置的大小时会出现栈溢出
 *
 * 2、设置jvm栈深度  :-Xss128k
 *
 *
 */
public class StackOverflowException {

    static int i = 0;

    public static void stackException() {
        i++;
        System.out.println("第" + i + "次压栈");
        stackException();
    }

    public static void main(String[] args) {
        stackException();
    }
}
