//Exercise 7.3
public class maxIndex {

    public static int indexOfMax(int[] a) {
        //takes an array of integers and returns the index of the largest element.
        int max = a[0];
        int index;
        for (int i = 0; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
                index = i;
            }
        }
        return index;
    }

    public static int indexOfMaxEnhanced(int[] a) {
        //same function as indexOfMax, but using an enhanced for loop.

        //can't make it using enhanced for loop, because we are not just focusing on the value, but we are aiming to find the index.
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 2, 5, 4};
        System.out.println(indexOfMax(numbers));
    }
}
