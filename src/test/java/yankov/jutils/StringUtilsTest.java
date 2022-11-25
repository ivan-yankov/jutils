package yankov.jutils;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {
    @Test
    public void fill() {
        Assert.assertEquals("111", StringUtils.fill(3, '1'));
    }

    @Test
    public void containsLetter_WithLetter_True() {
        Assert.assertTrue(StringUtils.containsLetter("123,.!@#a"));
    }

    @Test
    public void containsLetter_WithoutLetter_False() {
        Assert.assertFalse(StringUtils.containsLetter("123,.!@#"));
    }
}
