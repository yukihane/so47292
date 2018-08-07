package sun.nio.fs;

import java.awt.event.WindowStateListener;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileSystemProvider;
import java.util.Collections;
import java.util.Set;

/**
 * Hello world!
 */
public class App {

    public static void main(final String[] args) throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, InstantiationException {
        Path file = FileSystems.getDefault().getPath("mydir..");
        
        Method getPathForWin32Calls = file.getClass().getDeclaredMethod("getPathForWin32Calls");
        getPathForWin32Calls.setAccessible(true);
        String  pathForWin32Calls = (String) getPathForWin32Calls.invoke(file);
        
        Method getPathForPermissionCheck = file.getClass().getDeclaredMethod("getPathForPermissionCheck");
        getPathForPermissionCheck.setAccessible(true);
        String  pathForPermissionCheck = (String) getPathForPermissionCheck.invoke(file);
        
        
        
        Class<?> windowsChannelFactory = Class.forName("sun.nio.fs.WindowsChannelFactory");
        Method newFileChannel = windowsChannelFactory.getDeclaredMethod("newFileChannel", String.class, String.class, Set.class, Long.TYPE);
        newFileChannel.setAccessible(true);
//        newFileChannel.invoke(null, pathForWin32Calls, pathForPermissionCheck, Collections.emptySet(), 0L);
        
        Class<?> windowsDirectoryStream = Class.forName("sun.nio.fs.WindowsDirectoryStream");
        Class<?> windowsPath = Class.forName("sun.nio.fs.WindowsPath");
        Constructor<?> con = windowsDirectoryStream.getDeclaredConstructor(windowsPath, DirectoryStream.Filter.class);
        con.setAccessible(true);
        con.newInstance(file, null);

        System.out.println(file);
        FileSystemProvider provider = file.getFileSystem().provider();
        provider.checkAccess(file);
        
        boolean res = Files.exists(Paths.get("mydir.."));
        System.out.println(res);
    }

    public boolean exists(String fileName) {
        Path path = Paths.get(fileName);
        return Files.exists(path);
    }

    public boolean isFileSameCaseOld(final Path file) throws IOException {
        String canonicalName = file.toRealPath().getFileName().toString();
        if (canonicalName.equals(file.getFileName().toString())) {
            return true;
        } else {
            return !canonicalName.equalsIgnoreCase(file.getFileName().toString());
        }
    }

    public boolean isFileSameCaseNew(final Path file) throws IOException {
        String canonicalName = file.toRealPath().getFileName().toString();
        return canonicalName.equals(file.getFileName().toString());
    }
}
