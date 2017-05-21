package mb;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {

    private static final Maybe<?> EMPTY = new Maybe<>();

    private T value;

    Maybe(T value){
        this.value = Objects.requireNonNull(value);
    }

    Maybe(){this.value = null;}

    public static <I> Maybe<I> of(I value) { return value == null ? ItsEmpty() : new Maybe<>(value); }

    public static <I> Maybe<I> ItsEmpty(){
        return (Maybe<I>) EMPTY;
    }

    public void ifPresent(Consumer<T> doSmth){
        if (isPresent())
          doSmth.accept(value);
    }

    public <R> Maybe<R> map(Function<T,R> func){
            if(isPresent())
              return new Maybe<>(func.apply(value));
            return (Maybe<R>) EMPTY;
    }

    public T get(){
        if (value == null)
            throw new NullPointerException("maybe is empty");
        return value;
    }

    public boolean isPresent(){
        return value != null;
    }

    public T orElse(T defVal){
        return isPresent() ? value : defVal;
    }

    public  Maybe<T> filter(Predicate<T> pred){
        if(pred.test(value) || !isPresent())
            return this;
        return  (Maybe<T>) EMPTY;
    }

    @Override
    public String toString() {
        return value == null ? "Maybe is empty" : "Maybe has value " + value;
    }
}
