package yankov.jutils.functional;

import org.junit.Assert;
import org.junit.Test;
import yankov.jutils.functional.tuples.Tuple;

import java.util.ArrayList;
import java.util.List;

public class ImmutableListTest {
    @Test
    public void numberOfSlides_EvenLength() {
        Assert.assertEquals(2, ImmutableList.from(1, 2, 3, 4).numberOfSlides(2));
    }

    @Test
    public void numberOfSlides_OddNumber() {
        Assert.assertEquals(2, ImmutableList.from(1, 2, 3).numberOfSlides(2));
    }

    @Test
    public void sliding_EvenStep() {
        ImmutableList<ImmutableList<Integer>> expected = ImmutableList.from(
                ImmutableList.from(1, 2),
                ImmutableList.from(3, 4),
                ImmutableList.from(5, 6)
        );

        Assert.assertTrue(expected
                .zip(ImmutableList.from(1, 2, 3, 4, 5, 6).sliding(2))
                .stream()
                .allMatch(x -> x._1().eq(x._2()))
        );
    }

    @Test
    public void sliding_OddStep() {
        ImmutableList<ImmutableList<Integer>> expected = ImmutableList.from(
                ImmutableList.from(1, 2),
                ImmutableList.from(3, 4),
                ImmutableList.from(5)
        );

        Assert.assertTrue(expected.zip(ImmutableList.from(1, 2, 3, 4, 5).sliding(2)).stream().allMatch(x -> x._1().eq(x._2())));
    }

    @Test
    public void fromArray2d() {
        Integer[][] a = new Integer[][]{
                new Integer[]{1, 2, 3},
                new Integer[]{4, 5, 6},
                new Integer[]{7, 8, 9}
        };

        ImmutableList<ImmutableList<Integer>> expected = ImmutableList.from(
                ImmutableList.from(1, 2, 3),
                ImmutableList.from(4, 5, 6),
                ImmutableList.from(7, 8, 9)
        );

        Assert.assertTrue(expected.zip(ImmutableList.fromArray2d(a)).stream().allMatch(x -> x._1().eq(x._2())));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void immutable() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        ImmutableList.of(list).set(0, 5);
    }

    @Test
    public void mutable() {
        try {
            List<Integer> mutable = ImmutableList.from(1, 2, 3).asMutable();
            mutable.set(0, 5);
            ImmutableList.from(5, 2, 3).eq(ImmutableList.of(mutable));
        } catch (UnsupportedOperationException ignored) {
            Assert.fail();
        }
    }

    @Test
    public void append() {
        Assert.assertTrue(ImmutableList.from(1, 2, 3, 4, 5).eq(ImmutableList.from(1, 2, 3).append(4, 5)));
    }

    @Test
    public void appendAll() {
        Assert.assertTrue(ImmutableList.from(1, 2, 3, 4, 5).eq(ImmutableList.from(1, 2, 3).appendAll(ImmutableList.from(4, 5))));
    }

    @Test
    public void insert_ValidIndex_Succeed() {
        Assert.assertTrue(ImmutableList.from(1, 2, 3, 4, 5).eq(ImmutableList.from(1, 2, 4, 5).insert(2, 3)));
    }

    @Test
    public void insert_AtEmptyList_Succeed() {
        Assert.assertTrue(ImmutableList.from(1).eq(ImmutableList.<Integer>from().insert(0, 1)));
    }

    @Test
    public void insert_IndexEqualsToListSize_Succeed() {
        Assert.assertTrue(ImmutableList.from(1, 2, 3, 4, 5).eq(ImmutableList.from(1, 2, 3, 4).insert(4, 5)));
    }

    @Test
    public void insert_NegativeIndex_UnmodifiedList() {
        Assert.assertTrue(ImmutableList.from(1, 2, 4, 5).eq(ImmutableList.from(1, 2, 4, 5).insert(-1, 3)));
    }

    @Test
    public void insert_IndexGreaterThanListSize_UnmodifiedList() {
        Assert.assertTrue(ImmutableList.from(1, 2, 4, 5).eq(ImmutableList.from(1, 2, 4, 5).insert(8, 3)));
    }

    @Test
    public void fill() {
        Assert.assertTrue(ImmutableList.from(1, 1, 1, 1, 1).eq(ImmutableList.fill(5, 1)));
    }

    @Test
    public void tabulate() {
        Assert.assertTrue(ImmutableList.from(0, 1, 4, 9, 16).eq(ImmutableList.tabulate(5, x -> x * x)));
    }

    @Test
    public void removeElement_ValidIndex_ListWithoutRemovedElement() {
        Assert.assertTrue(ImmutableList.from(1, 2, 4, 5).eq(ImmutableList.from(1, 2, 3, 4, 5).removeElement(2)));
    }

    @Test
    public void removeElement_InvalidIndex_ListWithoutChange() {
        Assert.assertTrue(ImmutableList.from(1, 2, 3, 4, 5).eq(ImmutableList.from(1, 2, 3, 4, 5).removeElement(8)));
    }

    @Test
    public void updateElement_ValidIndex_ListWithUpdatedElement() {
        Assert.assertTrue(ImmutableList.from(1, 2, 8, 4, 5).eq(ImmutableList.from(1, 2, 3, 4, 5).updateElement(2, 8)));
    }

    @Test
    public void updateElement_InvalidIndex_ListWithoutChange() {
        Assert.assertTrue(ImmutableList.from(1, 2, 3, 4, 5).eq(ImmutableList.from(1, 2, 3, 4, 5).updateElement(16, 8)));
    }

    @Test
    public void swapElements_EmptyList_EmptyList() {
        Assert.assertTrue(ImmutableList.from().swapElements(1, 2).isEmpty());
    }

    @Test
    public void swapElements_InvalidIndexes_ListWithoutChange() {
        Assert.assertTrue(ImmutableList.from(1, 2, 5, 4, 3).eq(ImmutableList.from(1, 2, 5, 4, 3).swapElements(10, 8)));
    }

    @Test
    public void swapElements_ValidIndexes_ListWithSwappedElements() {
        Assert.assertTrue(ImmutableList.from(1, 2, 3, 4, 5).eq(ImmutableList.from(1, 2, 5, 4, 3).swapElements(4, 2)));
    }

    @Test
    public void zip_NonEmptyLists() {
        Assert.assertTrue(
                ImmutableList.from(new Tuple<>(1, "10"), new Tuple<>(2, "20"))
                        .eq(ImmutableList.from(1, 2).zip(ImmutableList.from("10", "20")))
        );
    }

    @Test
    public void zip_EmptyList() {
        Assert.assertTrue(ImmutableList.from(1, 2).zip(ImmutableList.from()).isEmpty());
    }

    @Test
    public void zipWithIndex_NonEmptyLists() {
        Assert.assertTrue(
                ImmutableList.from(new Tuple<>(1, 0), new Tuple<>(2, 1))
                        .eq(ImmutableList.from(1, 2).zipWithIndex())
        );
    }

    @Test
    public void zipWithIndex_EmptyList() {
        Assert.assertTrue(ImmutableList.from().zipWithIndex().isEmpty());
    }

    @Test
    public void mkString() {
        Assert.assertEquals("1s; 2s; 3s", ImmutableList.from(1, 2, 3).mkString("; ", x -> x + "s"));
    }
}
