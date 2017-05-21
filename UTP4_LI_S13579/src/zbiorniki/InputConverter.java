package zbiorniki;


import java.util.function.Function;

public class InputConverter<T> {
    T fileUrl;

    public InputConverter(T url) {
        fileUrl = url;
    }

    public <R> R convertBy(Function ... func){
        R result = (R)fileUrl;
        for (Function fn: func) {
            result = (R)fn.apply(result);
        }
        return result;
    }
}
