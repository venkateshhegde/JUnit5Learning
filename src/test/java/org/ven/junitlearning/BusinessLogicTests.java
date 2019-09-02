package org.ven.junitlearning;

import org.junit.jupiter.api.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("MAIN")
public class BusinessLogicTests {

    BusinessLogic bl;
    @BeforeEach
    public void setup()
    {
        bl = new BusinessLogic();
        System.out.println("Buisness Object initialized" + bl.hashCode());
    }

    @Test
    @DisplayName("A + B simple case ")
    @Order(1)
    public void testAPlusBSimple()
    {
        System.out.println(bl.hashCode());
        Assertions.assertEquals(5, bl.add(1,4));
    }

    @Test
    @DisplayName("A + B negative  case ")
    @Order(2)
    public void testAPlusBNegative()
    {
        System.out.println(bl.hashCode());
        Assertions.assertEquals(5, bl.add(7,-2));
    }

    @Test
    @DisplayName("A + B large Overflow case ")
    @Order(3)
    public void testAPlusBOverflow()
    {
        System.out.println(bl.hashCode());
        Assertions.assertEquals(-2147483648, bl.add(Integer.MAX_VALUE,1));
    }

    @Test
    @DisplayName("A[] + B[] simple Array case")
    @Order(4)
    public void testAPlusBArrays()
    {
        System.out.println(bl.hashCode());
        Assertions.assertArrayEquals(new int[]{10, 20}, bl.add(new int[]{1,9},new int[]{9, 11}));
    }

    @BeforeAll
    public static  void  before()
    {
        System.out.println("Before All");
    }
    @AfterAll
    public static  void  after()
    {
        System.out.println("After All");
    }

    @RepeatedTest(10)
    void exceptionThrower()
    {
        String str = null;
        Assertions.assertThrows(NullPointerException.class,
                () ->{
            str.length();
                });
    }

    @DisplayName("Params test INPUT Only ")
    @ParameterizedTest( name = "DisplayName with params {0}")
    @ValueSource(ints={1,2,3,4})
    void parameterizedTests(int i)
    {
        Assertions.assertTrue(i < 5);
    }


    @DisplayName("This is a In/Out test for {0} {1}")
    @ParameterizedTest(name = "This is a In/Out test for {0} {1}")
    @MethodSource("compareInsAndOuts")
    void parameterizedTestsOutputsToo(int  actual, int expected)
    {
        Assertions.assertEquals(expected, actual);
    }


    public static Collection<Object[]> compareInsAndOuts() {
        return Arrays.asList(new Object[][] {
                { 2, 2 },
                { 6, 6 },
                { 19, 19 },
                { 22, 22 },
                { 23, 23 }
        });
    }

    @Disabled
    @Test
    void testPerf()
    {
        Assertions.assertTimeout(Duration.ofSeconds(10l),
        () -> {
            Thread.sleep(11);
        }
        );
    }

    @Nested
    @DisplayName("Inner Ones...")
    class NestedTestCases{

        @BeforeEach
        void setup(){

        }
        @Test
        @DisplayName("Perf tests ")
        void testPerf()
        {
            Assertions.assertEquals(20, bl.add(10, 10));
            Assertions.assertTimeout(Duration.ofSeconds(10l),
                    () -> {
                        Thread.sleep(11);
                    }
            );
        }

    }


}
