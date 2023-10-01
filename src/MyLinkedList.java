import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements Iterable<E> {

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<E> {
        private Node<E> currentNode = head;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public E next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            E value = currentNode.item;
            currentNode = currentNode.next;
            return value;
        }

    }

    // 내부 static 클래스로 node
    private static class Node<E>{
        private E item; // Node에 담을 데이터
        private Node<E> next;   // 다음 Node 객체를 가르키는 래퍼런스(참조)

        // 생성자
        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }


    // 제네릭으로
    private Node<E> head;   // 노드의 첫부분 가리키는 포인트
    private Node<E> tail;   // 노드의 마지막 가리키는 포인트

    private int size;   // 노드 개수


    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /************************************
     * search 구현
     * 인덱스가 없으므로 랜덤 액세스 불가능 => O(N)
    *************************************/
    // index 전까지만 탐색(노드 next 자체가 다음 노드를 가리킴)
    private Node<E> search(int index) {
        // head (처음위치)서 부터 차례로 index까지 검색
        Node<E> n = head;
        for (int i = 0; i < index; i++){
            n = n.next;
        }

        return n;
    }

    /************************************
     * add 구현
     * 1. 첫번재 삽입 하는 경우
     * 2. 마지막에 삽입 하는 경우
     * 3. 지정된 위치에 삽입 하는 경우
     *************************************/
//    public void addFirst(E value){
//        // 1. 먼저 가장 앞의 요소를 가져옴
//        Node<E> first = head;
//
//        // 2. 새 노드 생성(next에 head를 연결한다.)
//        Node<E> newNode = new Node<>(value, first);
//
//        // 3. 요소 추가 size 늘리기
//        size++;
//
//        // 4. 맨앞에 요소가 추가되었으니 head업데이트
//        head = newNode;
//
//        // 5. 만약 최초로 요소가 add된것이면 head와 tail이 가르키고있는 노드는 같게 된다.
//        if(first == null){
//            tail = newNode;
//        }
//    }

    public void addLast(E value) {
        // 1. 먼저 가장 뒤의 요소를 가져옴
        Node<E> last = tail;

        // 2. 새 노드 생성 (맨 마지막 요소로 삽입 하므로 next는 null)
        Node<E> newNode = new Node<>(value, null);

        // 3. 요소에 추가 되엇으니 size증가 시키미
        size++;

        // 4. 맨뒤 요소가 추가되엇으니 tail 업데이트
        tail = newNode;

        if (last == null) {
            // 5. 만일 최초로 요소가 add된것이면 head와 tail이 같은 노드를 가르켜야함
            head = newNode;
        } else {
            // 6. 최소 노드 추가가 아니라면 last 변수에서 추가된 새노드를 링크해줘야함
            last.next = newNode;
        }
    }

    public boolean add(E value) {
        addLast(value);
        return true;
    }


//    public void add(int index, E value){
//
//        // 1. 인덱스가 0보다 작거나 size보다 같거나 클 경우 에러
//        if(index < 0 || index >= size){
//            throw new IndexOutOfBoundsException();
//        }
//
//        // 2. 추가하려는 index가 0이면 addFirst 호출
//        if(index == 0){
//            addFirst(value);
//            return;
//        }
//
//        // 3. 추가하려는 idx가 size - 1와 같으면 addLast호출
//        if(index == (size - 1)){
//            addLast(value);
//            return;
//        }
//
//        // 4. 추가하려는 위치의 이전 노드 얻기
//        Node<E> prev_node = search(index - 1);
//
//        // 5. 추가하려는 위치의 다음 노드 얻기
//        Node<E> next_node = prev_node.next;
//
//        // 6. 새 노드 생성 (바로 다음 노드와 연결)
//        Node<E> newNode = new Node<>(value, next_node);
//
//        // 7. size 증가
//        size++;
//
//        // 8. 이전 노드를 새노드와 링크
//        prev_node.next = newNode;
//    }
    /************************************
     * delete 구현
     *************************************/
    public E deleteFirst(){
        // 1. 만약 삭제 할 요소가 아무것도 없으면 에러
        if (head == null){
            throw new IndexOutOfBoundsException();
        }

        // 2. 삭제할 첫번째 요소의 데이터를 백업
        E returnValue = head.item;

        // 3. 두번째 노드를 임시 저장
        Node<E> first = head.next;

        // 4. 첫번재 노드의 내부 요소를 모두 삭제
        head.next = null;
        head.item = null;

        // 5. head가 두번째 노드를 가리키도록 업데이트
        head = first;

        // 6. 크기 감소
        size--;

        // 7. 만약 리스트의 유일한 값을 삭제해서 빈 리스트가 될 경우, tail도 null처리
        if (head == null){
            tail = null;
        }

        // 8. 마지막으로 삭제된 요소를 반환
        return returnValue;
    }

    public E delete(){
        return deleteFirst();
    }

    public E delete(int index){
        // 1. 인덱스가 0보다 작거나 size보다 크거나 같은 경우 에러
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        // 2. 인덱스 0이면 removeFirst 메서드 실행하고 리턴
        if(index == 0){
            return deleteFirst();
        }

        // 3. 삭제할 위치의 이전 노드 저장
        Node<E> prev_node = search(index - 1);

        // 4. 삭제할 위치의 노드 저장
        Node<E> del_node = prev_node.next;

        // 5. 삭제 할 위치의 다음 노드 저장
        Node<E> next_node = del_node.next;

        // 6. 삭제될 첫번째 요소의 데이터를 백업
        E returnValue = del_node.item;

        // 7. 삭제 노드의 내부 요소 제거
        del_node.next = null;
        del_node.item = null;

        // 8. 크기 감소
        size--;

        // 9. 이전노드가 다음 노드를 가르키도록
        prev_node.next = next_node;

        return returnValue;
    }


    public E deleteLast(){
        return delete(size - 1);
    }

    /************************************
     * get 구현
     * search메서드로 노드를 검색하여 값을 반환
     *************************************/

    public E get(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        return search(index).item;
    }

    /************************************
     * set 구현
     *************************************/
    public void set(int index, E value){
        if (index<0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        // 1. search 메소드를 이용해 교체할 노드를 얻는다.
        Node<E> replace_node = search(index);

        // 2. 교체할 노드의 요소를 변경한다.
        replace_node.item = null;
        replace_node.item = value;
    }

    /************************************
     * 출력 구현
     *************************************/
    @Override
    public String toString() {
        // 1. 만일 head가 null 일 경우 빈 배열
        if (head==null){
            return "[]";
        }

        // 2. 현재 size만큼 배열 생성
        Object[] array = new Object[size];

        // 3. 노드 next를 순회하면서 배열에 노드값을 저장
        int index = 0;
        Node<E> n = head;
        while(n!=null){
            array[index] = (E)n.item;
            index++;
            n = n.next;
        }

        // 3. 배열을 스트링화하여 반환
        return Arrays.toString(array);
    }

}
