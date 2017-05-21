/**
 *
 *  @author Loshchinin Illia S13579
 *
 */

package l2;


/*<-- niezbędne importy */

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) { // исправить c помощью stream().filter().map().....
    // Lista destynacji: port_wylotu port_przylotu cena_EUR 
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = dest.stream().filter(pr -> pr.startsWith("WAW")).map(pr -> "to " + pr.split(" ")[1] + " - price in PLN:  " + Math.round(Double.parseDouble(pr.split(" ")[2]) * ratePLNvsEUR)).collect(Collectors.toList());
    for (String r : result) System.out.println(r);
  }
}
