/**
 *
 *  @author Loshchinin Illia S13579
 *
 */

package zadGenerics;


public interface Mapper<T,D> { // Uwaga: interfejs musi być sparametrtyzowany
       D map(T func);
}
