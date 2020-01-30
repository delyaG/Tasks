public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(4); linkedList.add(747); linkedList.add(94); linkedList.add(167);
        linkedList.add(186); linkedList.add(145); linkedList.add(12); linkedList.add(4);

        MyStream<Integer> stream = new MyStream<>(linkedList);
        stream.filter(x -> x > 100).forEach(x -> System.out.print(x + " "));
        System.out.println();
        stream.map(x -> x < 100 ? x : x - 100).forEach(x -> System.out.print(x + " "));
    }
}
