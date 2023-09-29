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
        System.out.println(l);
        System.out.println(l.get(1));

        l.delete(1);
        System.out.println(l);
    }
}