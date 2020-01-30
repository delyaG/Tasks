import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyStream<T extends Comparable<T>> {
    private LinkedList<T> list;

    public MyStream() {
        this.list = new LinkedList<>();
    }

    public MyStream(LinkedList<T> list) {
        this.list = list;
    }

    public <R extends Comparable<R>> MyStream<R> map(Function<T, R> function) {
        MyStream<R> result = new MyStream<>();
        for (T x : this.list) {
            result.list.add(function.apply(x));
        }
        return result;
    }

    public MyStream<T> filter(Predicate<T> predicate) {
        MyStream<T> result = new MyStream<>();
        for (T x : this.list) {
            if (predicate.test(x)) {
                result.list.add(x);
            }
        }
        return result;
    }

    public void forEach(Consumer<T> consumer) {
        for (T x : this.list) {
            consumer.accept(x);
        }
    }
}
