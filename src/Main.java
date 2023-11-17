import com.diogonunes.jcolor.Attribute;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Main {
	public static Scanner scanner = new Scanner(System.in);
	public static int[][] map;
	public static int height, width;

	static void printArray(int[] @NotNull [] array) {
		for (int[] row : array) {
			for (int col : row) {
				switch (col) {
					case 0:
						System.out.print(colorize(" 0 ", Attribute.BRIGHT_BLUE_BACK(), Attribute.BRIGHT_BLUE_TEXT()));
						break;
					case 1:
						System.out.print(colorize(" 1 ", Attribute.BRIGHT_GREEN_BACK(), Attribute.BRIGHT_GREEN_TEXT()));
						break;
					case 2:
						System.out.print(colorize(" 2 ", Attribute.BRIGHT_RED_BACK(), Attribute.BRIGHT_RED_TEXT()));
						break;
					case 3:
						System.out.print(colorize(" 3 ", Attribute.BRIGHT_WHITE_BACK(), Attribute.BRIGHT_WHITE_TEXT()));
						break;
				}
			}
			System.out.println();
		}
	}

	static int calculateIslands(int[][] array) {
		int res = 0;
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				if (!(r == 0) && array[r - 1][c] == 1)
					continue;
				if (!(c == 0) && array[r][c - 1] == 1)
					continue;
				if (array[r][c] == 1)
					res++;
			}
		}
		return res;
	}

	static void updateMap() {
		Random random = new Random();
		for (int r = 0; r < height; r++)
			for (int c = 0; c < width; c++)
				if (random.nextInt(10) > 3)
					map[r][c] = 0;
				else
					map[r][c] = 1;
	}

	public static void main(String[] args) {
		System.out.print("Wprowadź wysokość mapy: ");
		height = getInteger();

		System.out.print("Wprowadź szerokość mapy: ");
		width = getInteger();

		System.out.print("Czy chcesz wygenerować mapę? (t/n):");

		map = new int[height][width];

		if (scanner.next().charAt(0) == 't') {
			System.out.println("Teraz generowana jest mapa");
			updateMap();
		} else {
			System.out.println("Musisz samodzielnie wprowadzić mapę");
			System.out.println("Aby utworzyć ziemie wpisz 1, aby utworzyć wodę wpisz 0");
			System.out.println("Jesteś na polu zaznaczonym na czerwono");
			for (int r = 0; r < height; r++)
				for (int c = 0; c < width; c++) {
					map[r][c] = 3;
				}
			for (int r = 0; r < height; r++)
				for (int c = 0; c < width; c++) {
					map[r][c] = 2;
					printArray(map);
					while (map[r][c] > 1)
						map[r][c] = getInteger();
				}
		}

		System.out.println("Mapa wygenerowana, wyświetlanie:");
		printArray(map);


		System.out.println("Liczenie wysp!");
		int countOfIslands = calculateIslands(map);

		System.out.println("Znalezione wyspy: " + countOfIslands);


		System.out.println();
		System.out.println();
		System.out.println("Generowanie 20 map!");
		for (int i = 0; i < 20; i++) {
			System.out.println("Mapa nr " + (i + 1));
			printArray(map);
			updateMap();
			System.out.println("Ilość wysp: " + calculateIslands(map));
		}
	}

	static int getInteger() {
		try {
			System.out.print("Wprowadź liczbę: ");
			return scanner.nextInt();
		} catch (
				Exception e) {
			if (e.toString().equals("NumberFormatException"))
				System.out.println("Parsowanie nie powiodło się");
			else
				System.out.println("Wystąpił wyjątek: " + e.getMessage());
		}
		return getInteger();
	}
}
