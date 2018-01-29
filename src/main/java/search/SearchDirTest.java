package search;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearchDirTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService s = Executors.newFixedThreadPool(100);
        if (args.length != 2) {
            System.out.println("programName <path> <filename>");
            System.exit(-1);
        }
        String path = args[0];
        String search = args[1];
        Runnable task = new SearchDir(s, new File(path), search);
        s.submit(task);
    }
}