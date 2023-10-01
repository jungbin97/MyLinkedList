public class Main {
    public static void main(String[] args) {
        MyLinkedList<Integer> l = new MyLinkedList<>();

        l.add(3);
        l.add(6);
        l.add(4);
        l.add(3);
        l.add(8);
        l.add(10);
        l.add(11);

        for (Integer val : l){
            System.out.println(val);
        }
    }
}