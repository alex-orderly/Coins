import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Monobuild {

    private static final List<String> PROJECT_DIRS = List.of(
            "java/servers/",
            "java/libraries/",
            "java/api/"
    );

    public static void main(String[] args) throws Throwable {
        Monobuild monobuild = new Monobuild();
    }

    public Monobuild() throws Throwable {
        Path root = Util.findMonoRepoRoot().toPath();

        // Create a list of all project directories
        List<Path> allProjects = new ArrayList<>();
        for (String projectsDir : PROJECT_DIRS) {
            File[] projects = root.resolve(projectsDir).toFile().listFiles();
            if (projects != null) {
                allProjects.addAll(Arrays.stream(projects).map(File::toPath).collect(Collectors.toList()));
            }
        }

        // List them to the console
        System.out.println();
        System.out.println(Util.divider("All Projects"));
        for (Path project : allProjects) {
            System.out.println(project);
        }
        System.out.println();

    }

}

class Util {

    private static final String MONOREPO_FILE = ".monorepo";
    private static final int DIVIDER_LENGTH = 60;

    static String divider(String string) {
        StringBuilder stringBuilder = new StringBuilder("-- " + string + " ");
        int len = DIVIDER_LENGTH - stringBuilder.length();
        stringBuilder.append("-".repeat(Math.max(0, len)));
        return stringBuilder.toString();
    }

    public static String divider() {
        return "-".repeat(DIVIDER_LENGTH);
    }

    public static File findMonoRepoRoot() throws IOException {
        File dir = new File("").getCanonicalFile();

        do {
            String[] files = dir.list();
            if (files != null) {
                if (Arrays.asList(files).contains(MONOREPO_FILE)) {
                    return dir;
                }
            }
            dir = dir.getParentFile();
        } while (dir != null);

        throw new RuntimeException("Could not find root of monorepo");
    }

}