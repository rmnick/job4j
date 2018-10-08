package list;

public class CustomQueue<E> {
    private CustomStack<E> stack = new CustomStack<>();
    private int size = 0;
    private boolean flag = false;

    public void push(E value) {
        if (flag) {
            stack = reverse(stack);
        }
        stack.push(value);
        size++;
    }

    public E poll() {
        E result;
        if (size == 0) {
            result = null;
        } else {
            if (!flag) {
                stack = reverse(stack);
            }
            result = stack.poll();
            size--;
        }
        return result;
    }

    private CustomStack<E> reverse(CustomStack<E> input) {
        CustomStack<E> result = new CustomStack<>();
        for (int i = 0; i < size; i++) {
            result.push(input.poll());
        }
        flag = !flag;
        return result;
    }
}
