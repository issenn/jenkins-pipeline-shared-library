import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import maintainer

class MaintainerTest {

    /**
     * Use Groovy metaclass programming to add methods to the Jenkins pipeline shared library exposed class.
     * This allows for unit testing of classes that makes use of Jenkins pipeline steps, such as
     * 'sh', 'echo' or e.g. other steps available through the workflow-basic-steps-plugin
     */
    @BeforeAll
    static void setup() {
        maintainer.metaClass.echo { String input ->
            println input
            return input
        }
    }

    @Test
    void shouldOutputMaintainerInformation() {
        def varsFile = new maintainer()
        def expectedArg = 'Issenn Knight'
        def returnVal = varsFile.call(expectedArg)

        assert returnVal != null
        assert returnVal == "Project maintained by $expectedArg"
    }

}
