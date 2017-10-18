public class Main {
    public static void main(String[] args) {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        Integer temp;
        a.addFirst(0);
        temp = a.removeFirst();
        a.isEmpty();
        a.isEmpty();
        a.isEmpty();
        a.addFirst(5);
        temp = a.removeFirst();

//        a.addFirst(0);
//        a.addLast(1);
//        a.addLast(2);
//        temp = a.removeLast();//      ==> 2
//        temp = a.removeFirst();//    ==> 0
//        a.addFirst(5);
//        a.addLast(6);
//        temp = a.get(2);//      ==> 6
//        temp = a.removeFirst();//     ==> 5
//        temp = a.removeFirst();//     ==> 1
//        a.addFirst(10);
//        temp = a.get(1);//      ==> 6
//        temp = a.removeLast(); //     ==> 6
//        temp = a.removeLast();//      ==> 10
//        a.addFirst(14);
//        a.addLast(15);
//        a.addFirst(16);
//        temp = a.get(0);//      ==> 16
//        temp = a.removeLast();//      ==> 15
//        temp = a.get(1);//      ==> null.... but i actually get 14 lmao
    }
}
