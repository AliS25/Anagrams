import java.util.*;

import static java.util.Arrays.parallelSort;

//Ali Sbeih
public class Anagrams {

    public static void main(String[] args) {
        System.out.println("Welcome to the anagram finder!\n");

        if (args.length != 1) {
            System.out.println("  usage: java Anagram some-text.txt\n");
            System.exit(0);
        }

        System.out.println("  reading file " + args[0] + "...");

        WordReader wr = new WordReader(args[0]);

        // A map where the keys are hashcodes and the values are lists of strings
        HashMap<Integer, ArrayList<String>> map = new HashMap<>();
// A list of integers that represent keys for the previous map
        ArrayList<Integer> keys = new ArrayList<>();
// The first word in the text
        String word = wr.nextWord();

//The timer
        RunTimer rt = new RunTimer();
        rt.start();
//loop through all the words
        while (word != null) {
//An array of the characters inside the current word
            char[] letters = new char[word.length()];

            for (int i = 0; i < word.length(); i++) {
                letters[i] = word.charAt(i);
            }
//Sort the array in numerical order
            parallelSort(letters);
//Get the hashcode for the array
            int hashCode = Arrays.hashCode(letters);
            //If it is already in the map add a new word to the list of word(anagrams)
            if (map.containsKey(hashCode)) map.get(hashCode).add(word);
                //If it is not in the map, add a new key and value pair
            else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(word);
                map.put(hashCode, list);
                keys.add(hashCode);
            }
            word = wr.nextWord();
        }

        rt.stop();

//an int to keep track of the highest number of anagrams
        int counter = 0;
        //an int to keep track of the index of the key for the list containing the highest number of anagrams
        int index = 0;
        //an int to keep track of the number of sets of anagrams
        int anagrams = 0;

        System.out.println("Found anagrams:");
        //print the anagrams
        for (int i = 0; i < keys.size(); i++) {
            int size = map.get(keys.get(i)).size();
            if (size > counter) {
                counter = size;
                index = i;
            }
            if (size > 1) {
                anagrams++;
                System.out.println(Arrays.toString(map.get(keys.get(i)).toArray()));
            }
        }
        //print additional information about the anagrams
        System.out.println("There are " + anagrams + " sets of anagrams");
        System.out.println("That took " + rt.getElapsedMillis() + "ms");
        System.out.println("Words with the most anagrams are: ");
        for (int i = 0; i < counter; i++) {
            System.out.println(map.get(keys.get(index)).get(i));
        }

    }
}
