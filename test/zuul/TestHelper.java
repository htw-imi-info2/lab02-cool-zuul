package zuul;

import static org.junit.jupiter.api.Assertions.fail;

public class TestHelper {
    public static void assertContains(String result, String whatShouldBeContained){
        if (!result.contains(whatShouldBeContained))
            fail(result +"\n does not contain: --- "+whatShouldBeContained);
    }
    public static void assertDoesNotContain(String result, String whatShouldNotBeContained){
        if (result.contains(whatShouldNotBeContained))
            fail(result +"\n should not contain: --- "+whatShouldNotBeContained);
    }


}
