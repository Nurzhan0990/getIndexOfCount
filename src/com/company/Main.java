package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        var range = askRange(scanner);
        var listDigits = writeCondition(range);
        var index = askIndex(scanner, listDigits);
        writeResult(index, listDigits);
        scanner.close();
    }

    private static int askIndex(Scanner scanner, List<Integer> listDigits) {
        var from = 1;
        var to = listDigits.size();
        System.out.printf("Введите индекс(от %d, до %d):", from, to);
        int digit = readDigit(scanner);
        if (digit < from || digit > to) {
            System.out.println("Введите число в заданном диапазоне");
            return askIndex(scanner, listDigits);
        }
        return digit;
    }

    private static List<Integer> askRange(Scanner scanner) {
        List<Integer> range = new ArrayList<>();
        System.out.println("Введите диапазон положительных чисел: ");
        int from = getFrom(scanner, "От: ");
        range.add(from);
        System.out.println();
        int to = getFrom(scanner, "До: ");
        if (to <= from) {
            System.out.println("Меньше числа или равно числу ОТ");
            to = getFrom(scanner, "До: ");
        }
        range.add(to);
        return range;
    }

    private static int getFrom(Scanner scanner, String s) {
        System.out.print(s);
        return readDigit(scanner);
    }

    private static int readDigit(Scanner scanner) {
        var string = scanner.next();
        try {
            int digit = Integer.parseInt(string.trim());
            if (digit < 1) {
                System.out.println("Число должно быть положительным");
                return readDigit(scanner);
            }
            return digit;
        } catch (NumberFormatException nfe) {
            System.out.println("Введите именно число");
            return readDigit(scanner);
        }
    }

    private static List<Integer> writeCondition(List<Integer> range) {
        System.out.println("Дано число:");
        getValues(range).forEach(System.out::print);
        System.out.println();
        return getValues(range);
    }

    private static List<Integer> getValues(List<Integer> range) {
        return getElements(range.get(0), range.get(1)).stream()
                .map(Main::getValueInElement)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private static List<Integer> getValueInElement(int i) {
        List<Integer> list = new ArrayList<>();
        while (i != 0) {
            list.add(i % 10);
            i /= 10;
        }
        Collections.reverse(list);
        return list;
    }

    private static List<Integer> getElements(int from, int to) {
        List<Integer> digits = new ArrayList<>();
        for (int i = from; i <= to; i++) digits.add(i);
        return digits;
    }

    private static void writeResult(int index, List<Integer> digits) {
        Integer value = digits.get(index - 1);
        System.out.printf("Значение индекса %d = %d", index, value);
    }
}
