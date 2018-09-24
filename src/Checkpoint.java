import java.util.Random;

public class Checkpoint {
	public static void main(String[] args) {
		int[][] arr = new int[5][5];
		Random rng = new Random();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++)
				arr[i][j] = rng.nextInt(9);
		}
		for (int[] a : arr) {
			for (int i : a)
				System.out.print(i + " ");
			System.out.println();
		}
	}
}
