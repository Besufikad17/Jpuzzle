package tests;

import org.junit.Test;

import static org.junit.Assert.*;
import static util.utils.isDuplicate;

public class UtilTest {

    @Test
    public void testIsDuplicate(){
        int[] arr = {1, 1, 2, 2};
        int a = 1;
        assertTrue(isDuplicate(arr, a));
        assertFalse(isDuplicate(arr, 3));
    }
}
