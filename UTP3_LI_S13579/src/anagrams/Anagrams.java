/**
 *
 *  @author Loshchinin Illia S13579
 *
 */

package anagrams;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Anagrams {
    ArrayList<String> allwords = new ArrayList<>();
    List<ArrayList<String>> finalCollectionOfArrayList;
    Anagrams(String words){

        try {
            BufferedReader in = new BufferedReader(new FileReader(words));

            if(in.ready()){
                int ch ;
                StringBuilder strBL = new StringBuilder();
                while((ch = in.read()) != -1) {

                    if(ch == 13){
                        continue;
                    }
                    if (ch != 32 && ch != 10) {
                        strBL.append((char)ch);
                    } else  {
                        allwords.add(strBL.toString());
                        strBL = new StringBuilder();
                    }
                }
                in.close();


                allwords.add(strBL.toString());
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         finalCollectionOfArrayList = new ArrayList<>();

        for (int i = 0; i < allwords.size(); i++) {
            String str = allwords.get(i);
          char[] charArr = str.toCharArray();
            int sum = 0;
            for (int j = 0; j < str.length(); j++) {
                sum += charArr[j];
            }
            //System.out.println(sum);
            ArrayList<String>  someArray;
            if(i == 0){
                 someArray = new ArrayList<>();
                someArray.add(String.valueOf(sum)); //add on 0 index value of word
                someArray.add(str); // add string on 1 index
                finalCollectionOfArrayList.add(someArray); // add ArrayList to List

            }else{
                if(sum == 0) {
                    continue;
                }
                for (int j = 0; j < finalCollectionOfArrayList.size(); j++) {
                    int tmp = Integer.parseInt(finalCollectionOfArrayList.get(j).get(0));
                    if (sum == tmp) {
                        finalCollectionOfArrayList.get(j).add(str); // add next str if same
                        break;
                    } else if( j == finalCollectionOfArrayList.size()-1) {
                         someArray = new ArrayList<>();
                        someArray.add(String.valueOf(sum)); //add on 0 index value of word
                        someArray.add(str); // add string on 1 index
                        finalCollectionOfArrayList.add(someArray); // add ArrayList to List
                        break;
                    }
                }
            }

        }

        /*for (ArrayList<String> list : finalCollectionOfArrayList) {
            System.out.println(Arrays.toString(list.toArray()));
        }*/



    }

    public List<ArrayList<String>> getSortedByAnQty() {
        List<ArrayList<String>>  listProdaction = new ArrayList<>();

        for (int i = 0; i < finalCollectionOfArrayList.size(); i++) {
            listProdaction.add(new ArrayList<>(finalCollectionOfArrayList.get(i).size()));
            for (int j = 0; j < finalCollectionOfArrayList.get(i).size(); j++) {
                listProdaction.get(i).add(finalCollectionOfArrayList.get(i).get(j));
            }

        }

        for (ArrayList<String> list: listProdaction) {

                list.remove(0);
        }

        listProdaction.sort((p,d)->  d.size()-p.size()); //понять как реализовано

        return listProdaction;
    }

    public String getAnagramsFor(String str) {
        ArrayList<String> listProd = new ArrayList<>();
        char[] charArr = str.toCharArray();
        int sum = 0;
        for (int j = 0; j < str.length(); j++) {
            sum += charArr[j];
        }


        for (int i = 0; i < finalCollectionOfArrayList.size(); i++) {
            int tmp = Integer.parseInt(finalCollectionOfArrayList.get(i).get(0));
            if( tmp == sum){
                for (int j = 2; j < finalCollectionOfArrayList.get(i).size(); j++) {
                    listProd.add(finalCollectionOfArrayList.get(i).get(j));
                }

            }
        }

     return str+ ": " + Arrays.toString(listProd.toArray());
    }
}
