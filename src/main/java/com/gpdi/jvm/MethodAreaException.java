package com.gpdi.jvm;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.List;
/**
 *
 *1、方法区异常 方法区在jdk1.8中叫Metaspace，在1.7叫Perm Space
 *
 *2、-XX:MetaspaceSize=50M -XX:MaxMetaspaceSize=50M
 *
**/

public class MethodAreaException extends ClassLoader {


    public static List<Class<?>> createClasses() {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for (int i = 0; i < 10000000; ++i) {
            ClassWriter cw = new ClassWriter(0);
            cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, "Class" + i, null,
                    "java/lang/Object", null);
            MethodVisitor mw = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            mw.visitVarInsn(Opcodes.ALOAD, 0);
            mw.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object",
                    "<init>", "()V");
            mw.visitInsn(Opcodes.RETURN);
            mw.visitMaxs(1, 1);
            mw.visitEnd();
            MethodAreaException test = new MethodAreaException();
            byte[] code = cw.toByteArray();
            Class<?> exampleClass = test.defineClass("Class" + i, code, 0, code.length);
            classes.add(exampleClass);
        }
        return classes;
    }

    public static void main(String[] args) throws Exception {
        List<Class<?>> list = new ArrayList<Class<?>>();
        while (true) {
            list.addAll(MethodAreaException.createClasses());
            Thread.sleep(5);
        }

    }
}