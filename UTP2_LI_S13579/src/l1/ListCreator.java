package l1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class ListCreator<I> {

    private List<I> listArr = new LinkedList<>();

    static <I1> ListCreator<I1> collectFrom(List<I1> list){
       ListCreator<I1> listCreator = new ListCreator<>();
        listCreator.listArr.addAll(list);
        return listCreator;
    }

    public ListCreator<I> when(Predicate<I> pr){
        for (Iterator<I> iterator = this.listArr.iterator(); iterator.hasNext(); ) {
            I listElem = iterator.next();
            if(!pr.test(listElem)){
                iterator.remove();
            }
        }
        return this;
    }


    public List<I> mapEvery(UnaryOperator<I> pr) {
        List<I> finalList = new ArrayList<>();
        for (I value: listArr) {
           finalList.add(pr.apply(value));
        }
        return finalList;
    }
}
