import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suit for the entirety of the code.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        LongTermTest.class,
        LockerTest.class
})
public class SpaceshipDepositoryTest {
}
