package org.hsmak.datastructures.heap;

import java.util.ArrayList;
import java.util.List;

public class MyHeap<E extends Comparable<E>> {
    List<E> data = new ArrayList<>();

    private int leftIndex(int index) {
        return 2 * index + 1;
    }

    private int rightIndex(int index) {
        return 2 * index + 2;
    }

    private int parentIndex(int index) {
        return (index - 1) / 2;
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void insert(E e) {
        data.add(e); // add to the end
        int bottomIndex = data.size() - 1;
        trickleUp(bottomIndex);
    }

    private void trickleUp(int bottomIndex) {
        int parentIndex = parentIndex(bottomIndex);
        E bottomElement = data.get(bottomIndex); // get hold of the bottom element

        while (bottomIndex > 0 &&
                bottomElement.compareTo(data.get(parentIndex)) > 0) {

            data.set(bottomIndex, data.get(parentIndex));  // move the parent down

            bottomIndex = parentIndex;
            parentIndex = parentIndex(parentIndex); // parent of the parent
        }
        data.set(bottomIndex, bottomElement);
    }

    public E remove() {
        if (data.size() == 1)
            return data.remove(0);

        E root = data.get(0);
        E bottomElement = data.remove(data.size() - 1);
        data.set(0, bottomElement);

        trickleDown(0);

        return root;
    }

    private void trickleDown(int index) {
        int largerChild;
        E top = data.get(index);

        while (index < data.size() / 2) {
            int leftChild = leftIndex(index);
            int rightChild = rightIndex(index);

            // find larger child
            if (rightChild < data.size() &&
                    data.get(leftChild).compareTo(data.get(rightChild)) < 0)
                largerChild = rightChild;
            else
                largerChild = leftChild;

            // top >= largerChild?
            if (top.compareTo(data.get(largerChild)) >= 0)
                break;

            // shift child up
            data.set(index, data.get(largerChild));
            index = largerChild;            // go down
        }
        data.set(index, top);            // root to index
    }
}

