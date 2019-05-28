import groovy.lang.GroovyShell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GroovyTest {

    @Test
    public void simpleScriptTest() {
        GroovyShell shell = new GroovyShell();
        String script = "return 'hello world'";

        Object result = shell.evaluate(script);
        Assertions.assertEquals(result, "hello world");
    }
}
