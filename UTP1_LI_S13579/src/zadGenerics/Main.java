/**
 *
 *  @author Loshchinin Illia S13579
 *
 */

package zadGenerics;



import java.util.*;



public class Main {
  public Main() {
    List<Integer> src1 = new LinkedList<>(Arrays.asList(1 , 4 , 7, 9, 11, 12));
    System.out.println(test1(src1));

    List<String> src2 = new LinkedList<>(Arrays.asList("a", "zzzz", "vvvvvvv" ));
    System.out.println(test2(src2));
  }

  public List<Integer> test1(List<Integer> src) {
    Selector<Integer> sel = new Selector<Integer>() {
      @Override
      public boolean select(Integer arg) {
        return arg < 10;
      }
    };
    Mapper<Integer,Integer> map = new Mapper<Integer,Integer>() {
      @Override
      public Integer map(Integer func) { return func+10; }
    };

    return ListCreator.collectFrom(src).when(sel).mapEvery(map);
  }

  public List<Integer> test2(List<String> src) {
    Selector<String> sel = new Selector<String>() {
      @Override
      public boolean select(String arg) { return arg.length() > 3 ;}
    };
    Mapper<String,Integer> map = new Mapper<String,Integer>() {
      @Override
      public Integer map(String func) { return func.length()+10;}
    };
    return ListCreator.collectFrom(src).when(sel).mapEvery(map);
  }

  public static void main(String[] args) {
    new Main();
  }
}
