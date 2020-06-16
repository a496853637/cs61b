public class ArrayDeque<T>{

        private int size;
        private T[] items;
        private int nextFirst;
        private int nextLast;

        /**
         * Create an empty list
         */
        /*
        create an empty array deque.
         */
        public ArrayDeque(){
                items = (T[]) new Object[8];
                size = 0;
                nextFirst = 0;
                nextLast = 1;
        }

        /*
        Returns size of the deque.
         */
        public int size(){
                return size;
        }

        /*
        Returns true if deque is empty,false otherwise.
         */
        public boolean isEmpty(){
                return size == 0;
        }

        /*
        Returns true if deque is full,false otherwise.
         */
        private boolean isFull(){
                return size == items.length;
        }

        /*
        to define if the deque need to be cut.
         */
        private  boolean isSpare(){
                return items.length >= 16 && size < (items.length / 4);
        }

        /*
        resize the deque
         */
        private void resize(int capacity){
                T[] a = (T[]) new Object[capacity];
                System.arraycopy(items,0, a,0, size);
                items = a;
        }

        /*
        find the next position.
         */
        private int addOne(int a){
                return ((a+1)%items.length);
        }

        /*
        find the previous position.
         */
        private int minusOne(int a){
                return ((a-1+items.length)%items.length);
        }

        /*
        adds an item of type T to the back of the deque.
         */
        public void addLast(T x){
                if (isFull()){
                        resize(size * 2);
                }
                items[nextLast] = x;
                size += 1;
                nextLast = addOne(nextLast);
        }

        /*
        adds an item of type T to the front of the deque
         */
        public void addFirst(T x){
                if (isFull()){
                        resize(size * 2);
                }
                items[nextFirst] = x;
                size += 1;
                nextFirst = minusOne(nextFirst);
        }

        /*
        removes and returns the item at the front of the deque
         */
        public T removeFirst(){
                if (isEmpty()){
                        return null;
                }
                nextFirst = addOne(nextFirst);
                T toRemove = items[nextFirst];
                items[nextFirst] = null;
                size -= 1;
                if (isSpare()){
                        resize(items.length / 2);
                }
                return toRemove;
        }

        /*
        removes and returns the item at the back of the deque
         */
        public T removeLast(){
                if (isEmpty()){
                        return null;
                }
                nextLast = minusOne(nextLast);
                T toRemove = items[nextLast];
                items[nextLast] = null;
                size -= 1;
                if (isSpare()){
                        resize(items.length / 2);
                }
                return toRemove;
        }

        /*
        get the item at the given index
         */
        public T get(int index){
                if (index >= size){
                        return null;
                }
                int start = addOne(nextFirst);
                return items[(start + index) % items.length];
        }

        /*
        print the items in the deque from first to last
         */
        public void printDeque(){
                for(int i = addOne(nextFirst); i != nextLast; i = addOne(i)){
                      System.out.print(items[i] + " ");
                }
                System.out.println();
        }

        /*
        deep copy an array from other
         */
        public ArrayDeque(ArrayDeque other){
                items = (T[]) new Object[other.size];
                nextFirst = other.nextFirst;
                nextLast = other.nextLast;
                size = other.size;

                System.arraycopy(other.items, 0, items, 0, other.size);
        }
}