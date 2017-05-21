/**
 *
 *  @author Loshchinin Illia S13579
 *
 */


package zbiorniki;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  public static void main(String[] args) {


      //  definicja operacji w postaci lambda-wyrażeń:
      //  - flines - zwraca listę wierszy z pliku tekstowego
      Function<String,List<String>> flines = s -> {
          List<String> list = new ArrayList<>();
          File file = new File(s);
          try {
              BufferedReader reader = new BufferedReader(new FileReader(file));
          String line = null;
          while ((line = reader.readLine()) != null){
              list.add(line);
          }
          } catch (IOException e) {
              e.printStackTrace();
          }
          return list;
      };
      //   - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
      Function<List<String>,String> join = list -> {
        StringBuffer strBuf = new StringBuffer();
          list.forEach(strBuf::append);
          return strBuf.toString();
      };
      // - collectInts - zwraca listę liczb całkowitych zawartych w napisie
      Function<String,List<Integer>>   collectInts = str ->{
            List<Integer> list = new ArrayList<>();
          Matcher m = Pattern.compile("\\d+").matcher(str);
          while(m.find()){
                list.add(Integer.parseInt(m.group()));
            }
          return list;
      };
      //   - sum - zwraca sumę elmentów listy liczb całkowitych
      Function<List<Integer>,Integer> sum = list -> {
          int result = 0;
          for (Integer integer : list) {
              result += integer;
          }
          return result;
      };


      String fname = System.getProperty("user.home") + "/LamComFile.txt";
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);


  }
}

