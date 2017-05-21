package zad2;


import java.util.Iterator;

public class Hailstone implements Iterable<Integer> {
    private int value;

    public Hailstone(int value) {
        this.value = value;
    }

    class MyIterator implements Iterator<Integer> {

        @Override
        public boolean hasNext() {

            if (value != 1) {
                return true;
            }
            return false;
        }

        int flag = 0;

        @Override
        public Integer next() {

            if (flag == 0) {
                flag++;
                return value;
            } else {
                if (value % 2 == 0) {
                    value = value / 2;
                    return value;
                } else {
                    value = 3 * value + 1;
                    return value;
                }
            }

        }

    }

    @Override
    public Iterator<Integer> iterator() {
        return new MyIterator();
    }
}
