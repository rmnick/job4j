package list;

public class CustomQueue<E> {
    private CustomStack<E> stackInput = new CustomStack<>();
    private CustomStack<E> stackOutput = new CustomStack<>();
    private int size = 0;
    private boolean flagAdd = false;
    private boolean flagRem = false;


    public void push(E value) {
        if (flagRem) {
            stackInput = reverse(stackOutput);
        }
        stackInput.push(value);
        flagAdd = true;
        flagRem = false;
        size++;
    }

    public E poll() {
        E result;
        if (size == 0) {
            result = null;
        } else {
            if (flagAdd) {
                stackOutput = reverse(stackInput);
            }
            result = stackOutput.pop();
            flagRem = true;
            flagAdd = false;
            size--;
        }
        return result;
    }

    private CustomStack<E> reverse(CustomStack<E> input) {
        CustomStack<E> result = new CustomStack<>();
        for (int i = 0; i < size; i++) {
            result.push(input.pop());
        }
        return result;
    }
}
