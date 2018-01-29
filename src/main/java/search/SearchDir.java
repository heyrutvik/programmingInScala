package search;

import java.io.File;
import java.util.concurrent.ExecutorService;

public class SearchDir implements Runnable {

    ExecutorService s;
    File dir;
    String search;

    public SearchDir(ExecutorService s, File dir, String search) {
        this.s = s;
        this.dir = dir;
        this.search = search;
    }

    @Override
    public void run() {
        for (File f: dir.listFiles()) {
            if (f.isFile() && f.getName().compareTo(search) == 0) {
                System.out.println("file [" + search + "] found from path \"" + dir.getAbsolutePath() + "\"");
                s.shutdownNow();
            } else if (f.isFile()) {
                // do nothing
            } else {
                s.submit(new SearchDir(s, f, search));
            }
        }
    }
}
