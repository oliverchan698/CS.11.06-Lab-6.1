import java.io.*;
import java.util.Scanner;

/*
 * This lab has FOUR challenges.

There is a single correct answer to each challenge.

You must write a different method to solve each challenge.

Your methods will be called challengeOne, challengeTwo, challengeThree, and challengeFour.

You must call the method readFile inside each of the methods that you write. NOTE that in one or two of the four challenges you may need to create a slightly different readFile method. If you need to do so, do not delete the original readFile method. Call the new method readFileTwo. So, in each of the four methods you need to call either readFile or readFileTwo.

Each method that you write must return a value (the answer to the specific challenge).

Test your methods inside the main method.

When testing your methods, store the returned value from the method call into a variable. Name your variable challengeOneAnswer, challengeTwoAnswer, …

Use these variables in a call to writeFileAllAnswers (pass them as arguments) to write all of your solutions to a file called AdventureTime.txt. This call to writeFileAllAnswers must also be inside the main method.

Include all four methods as well as the main method in a single class called AdventureTime.

 */

 public class AdventureTime {

    /** This is the main method and it is where you will test your implementations for challengeOne, challengeTwo, etc.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String directory = System.getProperty("user.dir");
        String inputOTPathname = directory + "/CS.11.06-Lab-6.1-master/inputOneTwo.txt";
        String inputTFPathname = directory + "/CS.11.06-Lab-6.1-master/inputThreeFour.txt";
        int challengeOneAnswer = challengeOne(inputOTPathname);
        int challengeTwoAnswer = challengeTwo(inputOTPathname);
        int challengeThreeAnswer = challengeThree(inputTFPathname);
        int challengeFourAnswer = challengeFour(inputTFPathname);
        System.out.println(challengeOneAnswer);
        System.out.println(challengeTwoAnswer);
        System.out.println(challengeThreeAnswer);
        System.out.println(challengeFourAnswer);
        writeFileAllAnswers("AdventureTime.txt", challengeOneAnswer, challengeTwoAnswer, challengeThreeAnswer, challengeFourAnswer);

    }

    /** TODDO 1
     *
     * Challenge 1: Zhongmou the Big Meanie
     * 
     * Also I'm pretty sure it's spelled "coolly", just saying
     *
     * @param fileName
     * @return Answer to Challenge 1
     * @throws IOException
     */
    public static int challengeOne(String fileName) throws IOException {
        int[] sonarReport = readFile(fileName);
        int safeSpots = 0;
        for (int i = 0; i < sonarReport.length; i++) {
            if (i > 0) {
                if (sonarReport[i] > sonarReport[i-1]) {
                    safeSpots++;
                }
            }
        }

        return safeSpots;

    }

    /** TODDO 2
     *
     * Challenge 2: Sonar Suspicion
     *
     * @param fileName
     * @return Answer to Challenge 2
     * @throws FileNotFoundException
     */
    public static int challengeTwo(String fileName) throws FileNotFoundException {
        int[] sonarReport = readFile(fileName);
        int safeZones = 0;
        int window;
        int prevWindow;
        for (int i = 0; i < sonarReport.length - 2; i++) {
            window = sonarReport[i] + sonarReport[i+1] + sonarReport[i+2];
            if (i > 0) {
                prevWindow = sonarReport[i-1] + sonarReport[i] + sonarReport[i+1];
                if (window > prevWindow) {
                    safeZones++;
                }
            }
        }
        
        return safeZones;

    }

    /** TODDO 3
     *
     * Challenge 3: Navigation Nonsense
     *
     * str1.contains(str2)...?
     * 
     * @param fileName
     * @return Answer to Challenge 3
     * @throws FileNotFoundException
     */
    public static int challengeThree(String fileName) throws FileNotFoundException {
        String[] course = readFileTwo(fileName);
        int dist = 0;
        int depth = 0;
        for (String i : course) {
            if (i.contains("forward")) {
                dist += i.charAt(8);
            } else if (i.contains("up")) {
                depth -= i.charAt(3);
            } else if (i.contains("down")) {
                depth += i.charAt(5);
            }
        }
        
        return dist * depth;

    }

    /** TODDO 4
     *
     * Challenge 4: Iggy's Horrifying Discovery (and some bad news for the 4-dimensional person programming her events in Java)
     *
     * @param filename
     * @return Answer to Challenge 4
     * @throws FileNotFoundException
     */
    public static int challengeFour(String fileName) throws FileNotFoundException {
        String[] course = readFileTwo(fileName);
        int dist = 0;
        int depth = 0;
        int aim = 0;
        for (String i : course) {
            if (i.contains("forward")) {
                dist += i.charAt(8);
                depth += aim * i.charAt(8);
            } else if (i.contains("up")) {
                aim -= i.charAt(3);
            } else if (i.contains("down")) {
                aim += i.charAt(5);
            }
        }

        return dist * depth;

    }

    /** This method will write the values passed as challengeOne, challengeTwo, challengeThree, and challengeFour to a text file.
     * Do not edit this method.
     */
    private static void writeFileAllAnswers(String outPutFilename, int challengeOne, int challengeTwo, int challengeThree, int challengeFour) throws IOException {
        File file = new File(outPutFilename);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write("Challenge 1: " + "\t" + challengeOne + "\n");
        bufferedWriter.write("Challenge 2: " + "\t" + challengeTwo + "\n");
        bufferedWriter.write("Challenge 3: " + "\t" + challengeThree + "\n");
        bufferedWriter.write("Challenge 4: " + "\t" + challengeFour + "\n");
        bufferedWriter.close();
    }

    /** This method will read the values in inputFilename into an array of integers, which it will return.
     * Do not edit this method.
     */
    private static int[] readFile(String inputFilename) throws FileNotFoundException {
        File file = new File(inputFilename);
        Scanner scanner = new Scanner(file);
        int numberOfLinesInFile = countLinesInFile(inputFilename);
        int[] data = new int[numberOfLinesInFile];
        int index = 0;
        while (scanner.hasNextLine()) {
            data[index++] = scanner.nextInt();
        }
        scanner.close();
        return data;
    }

    private static String[] readFileTwo(String inputFilename) throws FileNotFoundException {
        File file = new File(inputFilename);
        Scanner scanner = new Scanner(file);
        int numberOfLinesInFile = countLinesInFile(inputFilename);
        String[] data = new String[numberOfLinesInFile];
        int index = 0;
        while (scanner.hasNextLine()) {
            data[index++] = scanner.nextLine();
        }
        scanner.close();
        return data;
    }

    /** This method will count the number of lines in a text file, which it will return.
     * Do not edit this method.
     */
    private static int countLinesInFile(String inputFilename) throws FileNotFoundException {
        File file = new File(inputFilename);
        Scanner scanner = new Scanner(file);
        int lineCount = 0;
        while (scanner.hasNextLine()) {
            lineCount++;
            scanner.nextLine();
        }
        scanner.close();
        return lineCount;
    }

}
