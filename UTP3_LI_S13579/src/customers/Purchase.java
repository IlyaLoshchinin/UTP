/**
 * @author Loshchinin Illia S13579
 */

package customers;


public class Purchase {

    int idCustomer;
    String surname, name;
    String nameOfItem;
    double costOfItem;
    double valueOfItem;

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setNameOfItem(String nameOfItem) {
        this.nameOfItem = nameOfItem;
    }

    public void setCostOfItem(double costOfItem) {
        this.costOfItem = costOfItem;
    }

    public void setValueOfItem(double valueOfItem) {
        this.valueOfItem = valueOfItem;
    }

   /* public String printCustomer(){
      return "c" + String.format("%04d", idCustomer) + ";" + surname + " " + name + ";" + costOfItem + ";" + valueOfItem;
    }*/

    @Override
    public String toString() {
        return "c" + String.format("%04d", idCustomer) + ";" + surname + " " + name + ";" + nameOfItem + ";" + costOfItem + ";" + valueOfItem;
    }
}
