import java.io.*;
import java.util.*;

public class MainClass {

    static ArrayList<String> readFileOfStrings (String fileName) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(fileName));
            ArrayList<String> result = new ArrayList<>();
            while (scanner.hasNext()) {
                result.add(scanner.next());
            }
            scanner.close();
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("The input file you entered does not exist");
        }
        return null;
    }

    static ArrayList<Integer> readFileOfInt (String fileName) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(fileName));
            ArrayList<Integer> result = new ArrayList<>();
            while (scanner.hasNextInt()) {
                result.add(scanner.nextInt());
            }
            scanner.close();
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("The input file you entered does not exist");
        }
        return null;
    }

    public static void main(String[] args) {
        boolean isDescend = false;
        String typeToSort;
        String outputFileName;
        String[] inputFileNames;
        boolean isInputValid = false;
        boolean isDefaultMode;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputString = "";
        System.out.println("Please, enter in one line divided by spaces:\n"+
                "1. Sorting mode (-a or -d), optional, sorted in ascending order by default;\n" +
                "2. Data type (-s or -i), required;\n" +
                "3. The full name with directory of the output file, required;\n" +
                "4. Full names of the input files, required, at least one.");

        try {
            inputString = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] splitInput = inputString.split(" ");
        isDefaultMode = !(splitInput[0].equals("a") || splitInput[0].equals("d"));

        while (!isInputValid) {
            if (isDefaultMode && (splitInput[0].equals("s") || splitInput[0].equals("i")) && splitInput.length >= 3) {
                isInputValid = true;
            }
            else if (!isDefaultMode && (splitInput[1].equals("s") || splitInput[1].equals("i")) && splitInput.length >= 4) {
                isInputValid = true;
            }
            else {
                System.out.println("Please, enter valid data");
                try {
                    inputString = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                splitInput = inputString.split(" ");
                isDefaultMode = !(splitInput[0].equals("a") || splitInput[0].equals("d"));
            }
        }

        if (splitInput[0].equals("a") || splitInput[0].equals("d")) {
            if (splitInput[0].equals("d")) isDescend = true;
            typeToSort = splitInput[1];
            outputFileName = splitInput[2];
            inputFileNames = Arrays.copyOfRange(splitInput, 3, splitInput.length);
        }
        else {
            typeToSort = splitInput[0];
            outputFileName = splitInput[1];
            inputFileNames = Arrays.copyOfRange(splitInput, 2, splitInput.length);
        }

        // Merge arrays of strings
        if (typeToSort.equals("s")) {
            List<ArrayList<String>> stringList = new ArrayList<>();

            for (String str : inputFileNames) {
                stringList.add(readFileOfStrings(str));
            }

            String[][] stringArray = stringList.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
            System.out.println("The given arrays:");
            for (String[] i: stringArray) {
                for (String j: i) {
                    System.out.print(j + " ");
                }
                System.out.println();
            }
            System.out.println();
            List<String> resultList = MergeKSortedArraysOfStrings.mergeKArrays(stringArray);
            if (isDescend)
                Collections.reverse(resultList);
            System.out.println("Result array:\n" + resultList + "\n");

            try (PrintStream out = new PrintStream(new FileOutputStream(outputFileName))) {
                for (String str: resultList) {
                    out.println(str);
                }
                System.out.println("The result is written to a file");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        // Merge arrays of int
        if (typeToSort.equals("i")) {
            List<ArrayList<Integer>> integerList = new ArrayList<>();

            for(String str: inputFileNames) {
                integerList.add(readFileOfInt(str));
            }

            Integer[][] integerArray = integerList.stream().map(u -> u.toArray(new Integer[0])).toArray(Integer[][]::new);
            System.out.println("The given arrays:");
            for (Integer[] i: integerArray) {
                for (Integer j: i) {
                    System.out.print(j + " ");
                }
                System.out.println();
            }
            System.out.println();

            List<Integer> resultList = MergeKSortedArraysOfInt.mergeKArrays(integerArray);
            if (isDescend)  Collections.reverse(resultList);
            System.out.println("Result array:\n" + resultList + "\n");

            try (PrintStream out = new PrintStream(new FileOutputStream(outputFileName))) {
                for (Integer i: resultList) {
                    out.println(i);
                }
                System.out.println("The result is written to a file");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
