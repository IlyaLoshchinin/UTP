/**
 * @author Loshchinin Illia S13579
 */

package customers;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomersPurchaseSortFind {
    ArrayList<Purchase> listOfPurchases = new ArrayList<>();
    ArrayList<Purchase> cloneOfListPurchases = new ArrayList<>();
    public void readFile(String fname) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(fname));

            if (in.ready()) {
                String strLine;
                StringBuilder strBL = new StringBuilder();
                while ((strLine = in.readLine()) != null) {
                    Matcher m = Pattern.compile("((?!c)\\d{5}(?=;))|((?!;)\\w+(?= ))|((?! )[A-Q]\\w+(?=;))|((?!;)\\w+[^\\d](?=;))|((?!;)\\d+[.]*(?=;))|((?!;)\\d+\\.*\\d*)").matcher(strLine);
                    Purchase customer = new Purchase();
                    for (int i = 1; m.find() && i < 7; i++) {
                        switch (i) {
                            case 1: customer.setIdCustomer(Integer.parseInt(m.group())); break;
                            case 2: customer.setSurname(m.group()); break;
                            case 3: customer.setName(m.group()); break;
                            case 4: customer.setNameOfItem(m.group()); break;
                            case 5: customer.setCostOfItem(Double.parseDouble(m.group())); break;
                            case 6: customer.setValueOfItem(Double.parseDouble(m.group())); break;
                        }
                    }
                    /*System.out.println(
                                    customer.idCustomer + " " + customer.surname + " "
                                    + customer.name + " " + customer.nameOfItem + " " + customer.costOfItem + " "
                                    + customer.valueOfItem);*/
                    // System.out.println(customer);
                    listOfPurchases.add(customer);
                    cloneOfListPurchases.add(customer);
                }
                in.close();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSortedBy(String sort) {
        System.out.println();
        System.out.println(sort);
        switch (sort) {
            case "Nazwiska":
                listOfPurchases.sort((a, b) -> a.surname.compareTo(b.surname));
                listOfPurchases.forEach(System.out::println);
                break;
            case "Koszty":
                listOfPurchases.sort((a, b) -> (int) ((b.valueOfItem * b.costOfItem) - (a.valueOfItem * a.costOfItem)));
                for (Purchase pr : listOfPurchases) {
                    System.out.println(pr + " (koszt: " + pr.costOfItem * pr.valueOfItem + ")");
                }
                break;
        }

    }

    public void showPurchaseFor(String id) {
        System.out.println("\nKlient "+id);
        for (Purchase pr : cloneOfListPurchases) {
            if(String.format("%04d", pr.idCustomer).equals(id.substring(2))){
                System.out.println(pr);
            }
        }
    }
}
