import org.junit.Assert;
import org.junit.Test;
import praktikum.IngredientType;

public class IngredientTypeTest {

    @Test
    public void getFirstValueOfEnumTest() {
        IngredientType firstValue = IngredientType.values()[0];
        Assert.assertEquals(firstValue, IngredientType.SAUCE);
    }

    @Test
    public void getSecondValueOfEnumTest() {
        IngredientType secondValue = IngredientType.values()[1];
        Assert.assertEquals(secondValue, IngredientType.FILLING);
    }


    @Test
    public void CorrectSizeOftheEnumTest() {
        int size = IngredientType.values().length;
        Assert.assertEquals(size, IngredientType.values().length);
    }

}
