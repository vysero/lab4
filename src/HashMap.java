
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class HashMap {

    public static void main(String[] args) {
        try {
            // TODO code application logic here

            String inputFile = "src/input/StarWarsPlanets.csv";

            File file = new File(inputFile);

            Scanner inputStream = new Scanner(file);

            HashMap newHashMap = new HashMap();

            while (inputStream.hasNext()) {

                String data = inputStream.nextLine();

                String value[] = data.split(",");

                newHashMap.add(Integer.parseInt(value[0]), value[1]);

            }

            newHashMap.print();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(HashMap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static class KeyValuedPairs {

        int key;
        String value;
    }

    int arraySize;
    int data;
    KeyValuedPairs[] array;

    HashMap() {

        arraySize = 5;
        data = 0;
        array = new KeyValuedPairs[arraySize];
        fillArray(array);
    }

    public void fillArray(KeyValuedPairs[] dArray) {

        for (int i = 0; i < dArray.length; i++) {

            if (dArray[i] == null) {

                dArray[i] = new KeyValuedPairs();
            }
        }
    }

    public void add(int key, String theValue) {

        int indexValue = key % array.length;

        while (array[indexValue].value != null) {

            indexValue++;

            if (indexValue == array.length) {

                indexValue = 0;
            }
        }

        array[indexValue].key = key;
        array[indexValue].value = theValue;
        data++;

        if ((data / arraySize) >= .8) {

            reHash();
        }
    }

    public void reHash() {

        arraySize = arraySize * 2;

        KeyValuedPairs[] temporaryArray = new KeyValuedPairs[arraySize];

        fillArray(temporaryArray);

        for (KeyValuedPairs newArray : array) {

            if (newArray.value != null) {

                int index = newArray.key % arraySize;

                while (temporaryArray[index].value != null) {

                    index++;

                    if (index == temporaryArray.length) {

                        index = 0;
                    }

                }

                temporaryArray[index].key = newArray.key;
                temporaryArray[index].value = newArray.value;
            }
        }

        array = temporaryArray;
    }

    public void print() {
        System.out.println("Index          Key            Data");
        System.out.println("--------------------------------------------");

        final Object[][] table = new String[array.length][];

        for (int i = 0; i < array.length; i++) {

            table[i] = new String[]{String.valueOf(i), String.valueOf(array[i].key), array[i].value};
        }

        for (final Object[] row : table) {

            System.out.format("%-15s%-15s%-15s\n", row);
            System.out.println("--------------------------------------------");
        }
    }
}
