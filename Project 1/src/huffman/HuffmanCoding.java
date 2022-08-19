package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class contains methods which, when used together, perform the
 * entire Huffman Coding encoding and decoding process
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class HuffmanCoding {
    /**
     * Writes a given string of 1's and 0's to the given file byte by byte
     * and NOT as characters of 1 and 0 which take up 8 bits each
     * 
     * @param filename The file to write to (doesn't need to exist yet)
     * @param bitString The string of 1's and 0's to write to the file in bits
     */
    public static void writeBitString(String filename, String bitString) {
        byte[] bytes = new byte[bitString.length() / 8 + 1];
        int bytesIndex = 0, byteIndex = 0, currentByte = 0;

        // Pad the string with initial zeroes and then a one in order to bring
        // its length to a multiple of 8. When reading, the 1 signifies the
        // end of padding.
        int padding = 8 - (bitString.length() % 8);
        String pad = "";
        for (int i = 0; i < padding-1; i++) pad = pad + "0";
        pad = pad + "1";
        bitString = pad + bitString;

        // For every bit, add it to the right spot in the corresponding byte,
        // and store bytes in the array when finished
        for (char c : bitString.toCharArray()) {
            if (c != '1' && c != '0') {
                System.out.println("Invalid characters in bitstring");
                System.exit(1);
            }

            if (c == '1') currentByte += 1 << (7-byteIndex);
            byteIndex++;
            
            if (byteIndex == 8) {
                bytes[bytesIndex] = (byte) currentByte;
                bytesIndex++;
                currentByte = 0;
                byteIndex = 0;
            }
        }
        
        // Write the array of bytes to the provided file
        try {
            FileOutputStream out = new FileOutputStream(filename);
            out.write(bytes);
            out.close();
        }
        catch(Exception e) {
            System.err.println("Error when writing to file!");
        }
    }
    
    /**
     * Reads a given file byte by byte, and returns a string of 1's and 0's
     * representing the bits in the file
     * 
     * @param filename The encoded file to read from
     * @return String of 1's and 0's representing the bits in the file
     */
    public static String readBitString(String filename) {
        String bitString = "";
        
        try {
            FileInputStream in = new FileInputStream(filename);
            File file = new File(filename);

            byte bytes[] = new byte[(int) file.length()];
            in.read(bytes);
            in.close();
            
            // For each byte read, convert it to a binary string of length 8 and add it
            // to the bit string
            for (byte b : bytes) {
                bitString = bitString + 
                String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            }

            // Detect the first 1 signifying the end of padding, then remove the first few
            // characters, including the 1
            for (int i = 0; i < 8; i++) {
                if (bitString.charAt(i) == '1') return bitString.substring(i+1);
            }
            
            return bitString.substring(8);
        }
        catch(Exception e) {
            System.out.println("Error while reading file!");
            return "";
        }
    }

    /**
     * Reads a given text file character by character, and returns an arraylist
     * of CharFreq objects with frequency > 0, sorted by frequency
     * 
     * @param filename The text file to read from
     * @return Arraylist of CharFreq objects, sorted by frequency
     */
    public static ArrayList<CharFreq> makeSortedList(String filename) {
        StdIn.setFile(filename);
        /* Your code goes here */

        ArrayList<CharFreq> sortedList = new ArrayList<CharFreq>();
        int totalCount[] = new int[256];
        int counter = 0;

        while(StdIn.hasNextChar()){
            char value = StdIn.readChar();
            totalCount[value]++;
            counter++; 
        }

        for(int i = 0; i < 256; i++){
            if(totalCount[i] != 0){
                    char data = (char)i;
                    CharFreq newvalue = new CharFreq(data, totalCount[data]/(counter*1.0));
                sortedList.add(newvalue);
            }
        }
        if(sortedList.size() == 1){
            int fakeB = (((int)sortedList.get(0).getCharacter())+1);
            if(fakeB == 256){
                fakeB = 0;
            }
            CharFreq addingB = new CharFreq((char)fakeB, 0);
            sortedList.add(addingB);
        }
        Collections.sort(sortedList);
        return sortedList;
    }

    /**
     * Uses a given sorted arraylist of CharFreq objects to build a huffman coding tree
     * 
     * @param sortedList The arraylist of CharFreq objects to build the tree from
     * @return A TreeNode representing the root of the huffman coding tree
     */
    public static TreeNode makeTree(ArrayList<CharFreq> sortedList) {
        /* Your code goes here */

        Queue<TreeNode> target = new Queue<TreeNode>();
        Queue<CharFreq> source = new Queue<CharFreq>();

        for(int i = 0; i < sortedList.size(); i++){
            source.enqueue(sortedList.get(i));
        }
   
        while(!source.isEmpty() || target.size() != 1){
            TreeNode rightSide = new TreeNode();
            TreeNode leftSide = new TreeNode();    

            if(source.isEmpty()){
                leftSide = target.dequeue();
                rightSide = target.dequeue();
            }else if(target.isEmpty()){
                CharFreq left = source.dequeue();
                leftSide = new TreeNode(left, null, null);
                CharFreq right = source.dequeue();
                rightSide = new TreeNode(right, null, null);
            }else{ 
                if(source.peek().getProbOccurrence() <= target.peek().getData().getProbOccurrence()){
                    CharFreq left = source.dequeue();
                    leftSide = new TreeNode(left, null, null);
                    if(source.isEmpty()){
                        rightSide = target.dequeue();
                    }
                    else{
                        if(source.peek().getProbOccurrence() <= target.peek().getData().getProbOccurrence()){
                            CharFreq right = source.dequeue();
                            rightSide = new TreeNode(right, null, null);
                        }else{
                            rightSide = target.dequeue();
                        }
                    }
                }else{
                    leftSide = target.dequeue();
                    if(target.isEmpty()){
                        CharFreq right = source.dequeue();
                        rightSide = new TreeNode(right, null, null);
                    }
                    else if(source.peek().getProbOccurrence() <= target.peek().getData().getProbOccurrence()){
                        CharFreq right = source.dequeue();
                        rightSide = new TreeNode(right, null, null);
                    }else{
                        rightSide = target.dequeue();  
                    }
                }
            }
            double probability = rightSide.getData().getProbOccurrence() + leftSide.getData().getProbOccurrence();
            CharFreq variable = new CharFreq(null, probability);
            TreeNode root = new TreeNode(variable, leftSide, rightSide);
            target.enqueue(root);
        }
        
        return target.dequeue(); 
    }

    /**
     * Uses a given huffman coding tree to create a string array of size 128, where each
     * index in the array contains that ASCII character's bitstring encoding. Characters not
     * present in the huffman coding tree should have their spots in the array left null
     * 
     * @param root The root of the given huffman coding tree
     * @return Array of strings containing only 1's and 0's representing character encodings
     */
    public static String[] makeEncodings(TreeNode root) {
        /* Your code goes here */

        String [] array = new String[128];
        String returned = "";
        if(root == null){
            return array;
        }
        String [] encoder = helpEncode(root, array, returned);
        return encoder; 
    }

    private static String[] helpEncode(TreeNode root, String[] array, String returned){
        if(root.getLeft() != null){
            helpEncode(root.getLeft(), array, returned + "0");
        }

        if(root.getRight() != null){
            helpEncode(root.getRight(), array, returned + "1");
        }

        if(root.getLeft() == null && root.getRight() == null){
            int a = (int)root.getData().getCharacter();
            array[a] = returned;
        }

        return array;
    }

    /**
     * Using a given string array of encodings, a given text file, and a file name to encode into,
     * this method makes use of the writeBitString method to write the final encoding of 1's and
     * 0's to the encoded file.
     * 
     * @param encodings The array containing binary string encodings for each ASCII character
     * @param textFile The text file which is to be encoded
     * @param encodedFile The file name into which the text file is to be encoded
     */
    public static void encodeFromArray(String[] encodings, String textFile, String encodedFile) {
        StdIn.setFile(textFile);
        /* Your code goes here */

        String enocdeS = "";
        while(StdIn.hasNextChar()){
            char value = StdIn.readChar();
            enocdeS = enocdeS + encodings [value];
        }   
        writeBitString(encodedFile, enocdeS);
    }
    
    /**
     * Using a given encoded file name and a huffman coding tree, this method makes use of the 
     * readBitString method to convert the file into a bit string, then decodes the bit string
     * using the tree, and writes it to a file.
     * 
     * @param encodedFile The file which contains the encoded text we want to decode
     * @param root The root of your Huffman Coding tree
     * @param decodedFile The file which you want to decode into
     */
    public static void decode(String encodedFile, TreeNode root, String decodedFile) {
        StdOut.setFile(decodedFile);
        /* Your code goes here */

        String numbers = readBitString(encodedFile);
        TreeNode locat = root;
        char [] array = numbers.toCharArray();
        int counter = 0;
        
        while (counter < array.length){
            if(locat.getData().getCharacter() != null){ 
                StdOut.print(locat.getData().getCharacter());
                locat = root;
            }
            if(array[counter] == '0'){
                locat = locat.getLeft();
                counter++;
            }else if(array[counter] == '1'){
                locat = locat.getRight();
                counter++;
            } 
        }
        if(locat.getData().getCharacter() != null){ 
            StdOut.print(locat.getData().getCharacter());
            locat = root;
        }
    }
}
