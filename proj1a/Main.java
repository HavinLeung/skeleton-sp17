public class Main {
    public static void main(String[] args) {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        Integer temp;
        a.addLast(0);
        temp = a.removeFirst();
        a.addFirst(2);
        temp = a.removeFirst();
    }
}
