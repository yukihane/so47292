package sun.nio.fs;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private App sut;

    @Before
    public void setUp() {
        sut = new App();
    }

    @Test
    public void testNormal() {
        assertTrue(sut.exists("hello.txt"));
    }

    @Test
    public void testDot() {
        assertTrue(sut.exists("hello.txt."));
    }

    @Test
    public void testSlash() {
        assertTrue(sut.exists("hello.txt/"));

    }

    @Test
    public void testSpace() {
        assertTrue(Files.exists(Paths.get("hello.txt")));
        assertTrue(Files.exists(Paths.get("hello.txt..../////././")));
        assertTrue(sut.exists("hello.txt...////./"));

    }

    @Test
    public void testX() {
        assertFalse(sut.exists("hello.txtx"));
    }

    @Test
    public void testIsFileSameCaseOld() throws IOException {
        String name = "hello.txt.";
        Path file = Paths.get(name);
        
        assertTrue(sut.isFileSameCaseOld(file));
    }

    @Test
    public void testIsFileSameCaseNew() throws IOException {
        String name = "hello.txt.";
        Path file = Paths.get(name);
        
        assertFalse(sut.isFileSameCaseNew(file));
    }
}
