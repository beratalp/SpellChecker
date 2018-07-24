import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
/**
 * This class is used for handling offline dictionary file
 * @author 404 Not Found
 * @version 0.2
 */
public class Dictionary
{
    //data members
    static final int BUCKET_COUNT = 1723 ;
    final private Bucket[] bucketList;

    /**
     * Creates a new Dictionary object
     */
    public Dictionary()
    {
        bucketList = new Bucket[BUCKET_COUNT];
        for (int i = 0; i < BUCKET_COUNT; i++)
        {
            bucketList[i] = new Bucket();
        }
    }


    /**
     * sets the hash code of a string value
     * @param key The String value
     * @return hash code
     */
    private int setHash(String key)
    {
        int h = key.hashCode();
        if ( h < 0 ) { h = -h; }
        return ( h % BUCKET_COUNT );
    }

    /**
     * Adds the word to the dictionary if it is not already present
     * @param key the word to add to the list
     */
    public void addWord(String key)
    {
        bucketList[setHash(key)].put(key);
    }

    /**
     * Determines if the key is present in this dictionary.
     * @param input the word to be checked
     * @return whether the word is present in the dictionary
     */
    public boolean contains(String input)
    {
        input = input.toLowerCase();
        return bucketList[setHash(input)].findWord(input);
    }


    /**
     * creates the hashtable including the words from the dictionary
     * @param filePath the file directory
     */
    public void createDictionary(String filePath)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String word;
            while ((word = reader.readLine()) != null)
            {
                addWord(word);
            }
        } catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    /**
     * This inner class defines a Bucket that holds Nodes
     */
    class Bucket
    {
        private Node start;

        /**
         * search for the words
         * @param word - the word to be searched
         */
        public boolean findWord(String word)
        {         //return key true if key exists
            Node next = start;
            while (next != null)
            {
                if (next.data.equals(word))
                {
                    return true;
                }
                next = next.next;
            }
            return false;
        }


        public void put(String word)
        {
            for (Node current = start; current != null; current = current.next)
            {
                if (word.equals(current.data))
                {
                    return;                     //search hit: exitting
                }
            }
            start = new Node(word, start); //search miss: adding a new node
        }

        /**
         * This inner class implements a node with links
         */
        class Node
        {
            String data;
            Node next;

            public Node(String data, Node next)
            {
                this.data = data;
                this.next = next;
            }
        }
    }
}