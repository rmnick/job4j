package ru.job4j.gc;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class TestReferences {
    private String name;

    public TestReferences(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        TestReferences testWeak = new TestReferences("testWeak");
        TestReferences testSoft = new TestReferences("testSoft");
        WeakReference<TestReferences> weakReference = new WeakReference<>(testWeak);
        SoftReference<TestReferences> softReference = new SoftReference<>(testSoft);
        System.out.println(weakReference + " " + weakReference.get());
        System.out.println(softReference + " " + softReference.get());
        testWeak = null;
        testSoft = new TestReferences("new soft");
        System.out.println(weakReference + " " + weakReference.get());
        System.out.println(softReference + " " + softReference.get());
        System.out.println("after GC");
//        TestReferences[] arr = new TestReferences[100000];
//        for(int i = 0; i< 100000; i++) {
//            arr[i] = new TestReferences(String.valueOf(i));
//        }
        System.gc();
        System.out.println(weakReference + " " + weakReference.get());
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(softReference + " " + softReference.get());
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public void finalize() throws Throwable {
        System.out.println("delete");
        super.finalize();
    }
}
