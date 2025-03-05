import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BunTest {

    private final String bunName;
    private final Float bunPrice;

    public BunTest(String bunName, Float bunPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
    }

    @Parameterized.Parameters(name = "{index}: Тест: Имя={0}, Цена={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Белый хлеб", 1.20f},
                {"", 1.20f},
                {null, 1.20f},
                {"Белый хлебБелый хлебБелый хлебБелый хлебБелый хлебБелый хлеб", 1.20f},
                {"WhiteBread", 1.20f},
                {"@c9dp)@:3[_", 1.20f},
                {"Б", 1.20f},
                {"Белый хлеб", -1.20f},
                {"Белый хлеб", 0.0f},
                {"Белый хлеб", 1.00f},
                {"Белый хлеб", 500.0f}
        });
    }

    @Test
    public void getBunNameTest() {
        Bun bun = new Bun(bunName, bunPrice);
        Assert.assertEquals("Failed to retrieve the bun name", bunName, bun.getName());
    }


    @Test
    public void getBunPriceTest() {
        Bun bun = new Bun(bunName, bunPrice);
        Assert.assertEquals(bunPrice, bun.getPrice(), 0.0f);
    }

}
