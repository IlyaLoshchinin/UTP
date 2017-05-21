package xl;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList<T> extends ArrayList<T> {


    @SafeVarargs
    public XList(T... args) {
        Collections.addAll(this, args);
    }

    public XList() {
    }

    public XList(Collection<T> args) {
        this.addAll(args);
    }


    public static <T> XList<T> of(T... args) {
        return new XList<>(args);
    }


    public static <T> XList<T> of(Collection<T> args) {
        return new XList<>(args);
    }

    public static <T> XList<List<T>> of(List<T>... args) {
        XList<List<T>> list = new XList<>();
        for (List<T> arg : args) {
            list.add(arg);
        }
        return list;
    }

    @Override
    public String toString() {
        if (!isEmpty())
            return Arrays.toString(toArray());
        return "[]";
    }

    // ofChars(napis) - zwraca x-listę znaków napisu
    public static XList<String> charsOf(String str) {
        return new XList<>(str.split(""));
    }

    //ofTokens(napis, [ sep ]) - zwraca x-listę symboli napisu, rozdzielonych separatorami z sep (jesśi brak - to białymi znakami).
    public static XList<String> tokensOf(String... str) {
        if (str.length == 1) {
            return new XList<>(str[0].split(" "));
        } else if (str.length == 2) {
            return new XList<>(str[0].split(str[1]));
        } else {
            return null;
        }
    }


    // union(dowolna_kolekcja)  -  zwraca  nową x-list z dołączaną do tej x-list  zawartością kolekcji,
    public XList<T> union(Collection<T> coll) {
        XList<T> tmp = new XList<>(this);
        tmp.addAll(coll);
        return tmp;
    }

    public XList<T> union(T... args) {
        XList<T> tmp = new XList<>(this);
        Collections.addAll(tmp, args);
        return tmp;
    }

    //diff(dowolna_kolekcja) - zwraca x-list zawierającą te elementy tej x-list, które nie występują w kolekcji,
    public XList<T> diff(Collection<T> coll) {
        List<T> diff = stream().filter(a -> !coll.contains(a)).collect(Collectors.toList());
        return new XList<>(diff);
    }

    //unique() - zwraca nową x-list, która zawiera wszystkie niepowtarzające się elementy tej x-list
    public XList<T> unique() {
        List<T> unique = stream().distinct().collect(Collectors.toList());
        return new XList<>(unique);
    }

    //combine() - zwraca x-listę list-kombinacji elementów z poszczególnych kolekcji, będących elementami tej x-listy
    public XList combine() {
        XList<List<T>> lists = (XList<List<T>>) this;
        XList<XList<T>> combineList = new XList<>();

        List<T>[] massList = new List[lists.size()];

        int countIter = 1;
        int maxCounters[] = new int[lists.size()];
        int currCounters[] = new int[lists.size()];

        for (int i = 0; i < lists.size(); i++) {
            massList[i] = lists.get(i);
            maxCounters[i] = massList[i].size() - 1;
            countIter *= massList[i].size();
        }

        XList<T> tmp;
        for (int i = 0; i < countIter; i++) {
            tmp = new XList<>();
            for (int j = 0; j < lists.size(); j++) {
                if (currCounters[j] > maxCounters[j] && j != lists.size() - 1) {
                    currCounters[j + 1] += 1;
                    currCounters[j] = 0;
                }

                tmp.add(massList[j].get(currCounters[j]));

                if (j == 0) {
                    currCounters[j]++;
                }
            }
            combineList.add(tmp);
        }
        return combineList;
    }


    //collect(Function) - zwraca nową x-listę, której elemenatmi są wyniki funkcji stosowanej wobec elementów tej x-listy,
    public <G> XList<G> collect(Function<XList<T>, G> func) {
        XList<G> tmp = new XList<>();
        XList<XList<T>> lists = (XList<XList<T>>) this;
        for (XList<T> el : lists) {
            tmp.add(func.apply(el));
        }
        return tmp;
    }

    //join([sep]) - zwraca napis, będący połączeniem elementów tej x-listy, z ewentualnie wstawionym pomiędzy nie separatorem,
    public String join(String... sep) {
        StringBuffer buff = new StringBuffer();
        String st = "";
        if (sep.length != 0) st = sep[0];

        for (T s : this) {
            buff.append(s);
            if (this.get(this.size() - 1) != s) {
                buff.append(st);
            }
        }
        return buff.toString();
    }

    //forEachWithIndex(konsumer_z_dwoma argumentami: element, index) -
    // do iterowania po liście z dostępem i do elementów i do ich indeksów.
    public void forEachWithIndex(BiConsumer<T, Integer> cons) {
        for (int i = 0; i < this.size(); i++) {
            cons.accept(get(i), i);
        }
    }
}
