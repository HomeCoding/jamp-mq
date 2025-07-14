import java.util.Scanner;

public class NonRepeatedElements {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of elements in the array: ");
        int n = scanner.nextInt();

        int[] elements = new int[n];
        System.out.println("Enter all the elements:");
        for (int i = 0; i < n; i++) {
            elements[i] = scanner.nextInt();
        }

        System.out.print("Non-repeated elements are: ");
        int nonRepeatedCount = 0;
        for (int i = 0; i < n; i++) {
            if (isUnique(elements, i)) {
                nonRepeatedCount++;
                System.out.print(elements[i] + " ");
            }
        }

        System.out.println();
        System.out.println("Number of non-repeated elements: " + nonRepeatedCount);
    }

    private static boolean isUnique(int[] array, int index) {
        for (int j = 0; j < array.length; j++) {
            if (j != index && array[j] == array[index]) {
                return false;
            }
        }
        return true;
    }
}