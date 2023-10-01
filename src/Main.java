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

        // 스택 확인
        Stack<Integer> stack = new Stack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.toString());

        stack.pop();
        System.out.println(stack.toString());
    }
}