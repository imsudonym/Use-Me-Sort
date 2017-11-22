/*
  UseMe.java implements Use-Me sort algorithm --a new algorithm
  with a time complexity of O(nlog(n)).
  For more references, see link: https://www.google.com.ph/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&cad=rja&uact=8&ved=0ahUKEwjD8tOI6tHXAhXBJpQKHQv4DbsQFggnMAA&url=http%3A%2F%2Fresearch.ijais.org%2Fvolume7%2Fnumber8%2Fijais14-451228.pdf&usg=AOvVaw2niWMBbAe4c4AO9a5w2U20

  Code Author(s):
        Edcel Balite <mdbalite@up.edu.ph>
*/

import java.io.*;
import java.util.StringTokenizer;

public class UseMe{
  static int j = 0;
  static long i = 0;
  public static void main (String[] args){
    String inputdata = "";
    try{
      BufferedReader br = new BufferedReader(new FileReader("inputdata2.txt"));
      inputdata = br.readLine();
    }catch(Exception e){

    }

    String tokenStrings[] = inputdata.split( " " );
    int[] tokens = new int[tokenStrings.length];

    for(int r = 0; r < tokenStrings.length; r++){
      tokens[r] = Integer.parseInt("" + tokenStrings[r]);
    }
    int[] array = tokens;

    sort(array, array.length);
  }

  public static void sort(int[] A, int N){
    long startTime = System.currentTimeMillis();

    int m;
    int k;

    int i = 0;
    while(i < N-1){
      if(A[i] > A[i+1]){
        k = i+1;

        do{
          if(A[k] > A[k+1] && k < N-1){
            k++;
          }
          if(k == A.length || k+1 == A.length) break;

        }while (A[k] > A[k+1] && k < N-1);

        if (k != i+1){
          m = k-i-1;
          DESC(A, i, k, m);
          i = k;
        }else{

            do{

              if(A[k] <= A[k+1] && k < N-1 && A[k+1] < A[i]){
                k++;
              }
              if(k == A.length || k+1 == A.length) break;

            }while(A[k] <= A[k+1] && k < N-1 && A[k+1] < A[i]);


          m = k-i-1;
          ASC(A, i, k, m);
          i = k;
        }
      }else{
        i++;
      }
    }

    long elapsedTimeMillis = System.currentTimeMillis() - startTime;
    System.out.print("sorted array: ");
    for(int j = 0; j < N; j++){
      System.out.print(" " + A[j]);
    }
    System.out.println();
    System.out.println("\nlength of Array: " + A.length);
    System.out.printf("OK! (%.4fs)\n", Double.parseDouble(""+elapsedTimeMillis)/1000);


  }

  public static int DESC(int[] A, int i, int k, int m){
    int temp;
    int low;
    int high;
    int l;
    int med;

    if(m == -1){
        return 0;
    }

    temp = A[i+m+1];
    l = DESC(A, i, k, m-1);

    if (temp < A[0])
      low = -1;
    else{
      high = i-l;
      low = 0;

      do{
        med = (low+high)/2;

        if (temp > A[med])
          low = med;
        else
          high = med;

      }while (low < high-1);

      while (temp >= A[low+1]){
        low++;
      }
    }

    k = k-l-m;
    i = i-l;
    while (i > low){
      A[k] = A[i];
      i--; k--; l++;
    }

    A[k] = temp;
    return l;
  }

  public static int ASC(int[] A, int i, int k, int m){
    int temp = 0;
    int l = 0;
    int high = 0;
    int low = 0;
    int med = 0;

    if (m == -1){
      return 0;
    }

    temp = A[k-m];
    l = ASC(A, i, k, m-1);

    if (temp < A[0])
        low = -1;
    else{
      high = i-l;
      low = 0;

      do{
        med = (low+high)/2;
        if (temp > A[med])
          low = med;
        else
          high = med;
      }while(low < high-1);

      while (temp >= A[low+1]){
          low++;
      }
    }

    k = k-l-m;
    i = i-l;
    while (i > low){
      A[k] = A[i];
      i--; k--; l++;
    }

    A[k] = temp;
    return l;
  }

}
