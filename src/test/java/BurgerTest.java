import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

@RunWith(Parameterized.class)
public class BurgerTest {

    private final String bunName;
    private final Float bunPrice;
    private final IngredientType firstIngredientType;
    private final String firstIngredientName;
    private final Float firstIngredientPrice;
    private final IngredientType secondIngredientType;
    private final String secondIngredientName;
    private final Float secondIngredientPrice;
    SoftAssertions softAssertions = new SoftAssertions();
    @Mock
    Bun bunMock;
    @Mock
    Ingredient ingredientOneMock;
    @Mock
    Ingredient ingredientTwoMock;
    private Burger burger;

    public BurgerTest(
            String bunName,
            Float bunPrice,
            IngredientType firstIngredientType,
            String firstIngredientName,
            Float firstIngredientPrice,
            IngredientType secondIngredientType,
            String secondIngredientName,
            Float secondIngredientPrice
    ) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.firstIngredientType = firstIngredientType;
        this.firstIngredientName = firstIngredientName;
        this.firstIngredientPrice = firstIngredientPrice;
        this.secondIngredientType = secondIngredientType;
        this.secondIngredientName = secondIngredientName;
        this.secondIngredientPrice = secondIngredientPrice;
    }

    @Parameterized.Parameters(name = "{index}: Тест: Тип Булки={0}, Цена={1}, Тип ингредиента1={2}, Название ингредиента1={3}, Цена ингредиента1={4},Тип ингредиента2={5}, Название ингредиента2={6}, Цена ингредиента2={7}")
    public static Object[][] getDate() {
        return new Object[][]{
                {"White bun", 1.04f, IngredientType.FILLING, "Pickle", 0.5f, IngredientType.SAUCE, "Ketchup", 0.9f},
                {"Dark bun", 1.08f, IngredientType.FILLING, "Tomato", 0.7f, IngredientType.SAUCE, "Cheese Sauce", 0.9f}
        };
    }

    @Before
    public void init() {

        //Инициализируем Мокито
        MockitoAnnotations.initMocks(this);

        //Создаем бутер
        burger = new Burger();

        // Булка
        Mockito.when(bunMock.getName()).thenReturn(bunName);
        Mockito.when(bunMock.getPrice()).thenReturn(bunPrice);

        //Ингридиент 1
        Mockito.when(ingredientOneMock.getType()).thenReturn(firstIngredientType);
        Mockito.when(ingredientOneMock.getName()).thenReturn(firstIngredientName);
        Mockito.when(ingredientOneMock.getPrice()).thenReturn(firstIngredientPrice);

        //Ингредиент 2
        Mockito.when(ingredientTwoMock.getType()).thenReturn(secondIngredientType);
        Mockito.when(ingredientTwoMock.getName()).thenReturn(secondIngredientName);
        Mockito.when(ingredientTwoMock.getPrice()).thenReturn(secondIngredientPrice);

    }

    @Test
    public void setBunTest() {

        // Помещаем булку в бутер
        burger.setBuns(bunMock);

        //Проверяем инфо о булке
        softAssertions.assertThat(burger.bun.getName()).isEqualTo(bunName);
        softAssertions.assertThat(burger.bun.getPrice()).isEqualTo(bunPrice);
        softAssertions.assertAll();
    }

    @Test
    public void addIngredientTest() {

        //добавляем ингредиент в булку
        burger.addIngredient(ingredientOneMock);

        softAssertions.assertThat(burger.ingredients).hasSize(burger.ingredients.size());
        softAssertions.assertThat(burger.ingredients.get(0).getName()).isEqualTo(firstIngredientName);
        softAssertions.assertThat(burger.ingredients.get(0).getPrice()).isEqualTo(firstIngredientPrice);
        softAssertions.assertThat(burger.ingredients.get(0).getType()).isEqualTo(firstIngredientType);
        softAssertions.assertAll();
    }

    @Test
    public void removeIngredientTest() {

        //добавляем ингредиент в булку и удаляем
        burger.addIngredient(ingredientOneMock);
        burger.removeIngredient(0);

        //Проверяем что лист пуст
        softAssertions.assertThat(burger.ingredients).isEmpty();
        softAssertions.assertAll();
    }

    @Test
    public void moveIngredientTest() {

        //добавляем ингредиент в булку и удаляем
        burger.addIngredient(ingredientOneMock);
        burger.addIngredient(ingredientTwoMock);
        burger.moveIngredient(0, 1);

        //Проверяем что лист пуст
        softAssertions.assertThat(burger.ingredients).hasSize(burger.ingredients.size());
        softAssertions.assertThat(burger.ingredients.get(0).getName()).isEqualTo(secondIngredientName);
        softAssertions.assertThat(burger.ingredients.get(1).getName()).isEqualTo(firstIngredientName);
        softAssertions.assertAll();

    }

    @Test
    public void getPriceTest() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientOneMock);
        burger.addIngredient(ingredientTwoMock);

        Float expectedPrice = (2 * bunPrice) + firstIngredientPrice + secondIngredientPrice;
        softAssertions.assertThat(burger.getPrice()).isEqualTo(expectedPrice);
        softAssertions.assertAll();
        System.out.println(bunMock.getPrice());
    }

    @Test
    public void getReceiptTest() {

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientOneMock);
        burger.addIngredient(ingredientTwoMock);

        String expectedReceipt = String.format("(==== %s ====)%n", bunName)
                + String.format("= %s %s =%n", firstIngredientType.toString().toLowerCase(), firstIngredientName)
                + String.format("= %s %s =%n", secondIngredientType.toString().toLowerCase(), secondIngredientName)
                + String.format("(==== %s ====)%n", bunName)
                + String.format("%nPrice: %f%n", (2 * bunPrice) + firstIngredientPrice + secondIngredientPrice);

        softAssertions.assertThat(burger.getReceipt()).isEqualTo(expectedReceipt);
        softAssertions.assertAll();
    }


}
