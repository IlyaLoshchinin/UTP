/**
 *
 *  @author Loshchinin Illia S13579
 *
 */

package zadGenerics;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ListCreator<T> {


    private List<T> mylist = new LinkedList<>();

     public static <T> ListCreator<T> collectFrom(List<T> list){
         ListCreator<T> listCreator = new ListCreator<>();
         listCreator.mylist = list;
         return listCreator;
        }

        public ListCreator<T> when(Selector<T> sel){
            for (Iterator<T> iterator = this.mylist.iterator();  iterator.hasNext(); ) {
                T listElem = iterator.next();
                if(!sel.select(listElem)){
                   iterator.remove();
                }
            }
            return this;
        }

        public <D> List<D> mapEvery(Mapper<T,D> item){

            return mylist.stream().map(item::map).collect(Collectors.toList());
        }
}
