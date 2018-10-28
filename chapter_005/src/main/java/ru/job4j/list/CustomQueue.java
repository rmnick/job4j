package ru.job4j.list;

public class CustomQueue<E> {
    private CustomStack<E> in = new CustomStack<>();
    private CustomStack<E> out = new CustomStack<>();

    public void push(E value) {
        in.push(value);
    }

    public E poll() {
        E result = null;
        if (out.getSize() == 0 && in.getSize() > 0) {
            transfer();
        }
        if (out.getSize() > 0) {
            result = out.poll();
        }
        return result;
    }

    private void transfer() {
        for (int i = in.getSize(); i > 0; i--) {
            out.push(in.poll());
        }
    }
}
