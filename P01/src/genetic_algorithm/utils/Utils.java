package genetic_algorithm.utils;

public class Utils {

  public static int decimalToBinary(int num[]) {
    int aux = 0;

    for (int i = num.length; i >= 1; ++i) {
      aux += Math.pow(2, i) * num[i];
    }
    
    if(num[0] == 1)
    {
      aux *= -1;
    }
    return aux;
  }

}
