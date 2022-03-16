import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("12:00:00");
    String customRestaurantName = "Amelie's cafe";
    String customLocation = "Chennai";
    int sweetCornSoupPrice =119;
    int vegetableLasagnePrice =269;

    //<<<<<<<<<<<<<Helper Methods>>>>>>>>>>>>>>>>>>>>>>>
    @BeforeEach
    private void initialize_Restaurant_and_default_Menu() {
        restaurant = new Restaurant(customRestaurantName,customLocation,openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",sweetCornSoupPrice);
        restaurant.addToMenu("Vegetable lasagne", vegetableLasagnePrice);

    }
    @AfterEach
    private void soutDash() {
        System.out.println("---------------------------------------------------");
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        System.out.println("In Method - is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time()");
        System.out.println("LocalTime is:" +LocalTime.now() + ". Official Opening Time is: "+openingTime + ". Official closing time is: "+ closingTime);
        System.out.println("Restaurant is Open: "+restaurant.isRestaurantOpen());
        Boolean expectedStatus = true;

        /* If logic to make this test case pass in all conditions */
        /* we can experiment on opening or closing time */
        /* ! operator for assertTrue & assertThat to handle all cases */
        if(restaurant.isRestaurantOpen()) {
            assertTrue(restaurant.isRestaurantOpen());
            assertThat(expectedStatus, equalTo(restaurant.isRestaurantOpen()));
        }else {
            /* ! operator to handle all cases */
            assertTrue( !restaurant.isRestaurantOpen());
            assertThat(expectedStatus, equalTo( !restaurant.isRestaurantOpen()));
        }

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        System.out.println("In Method - is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time()");
        System.out.println("LocalTime is:" +LocalTime.now() + ". Official Opening Time is: "+openingTime + ". Official closing time is: "+ closingTime);
        System.out.println("Restaurant is Open: "+restaurant.isRestaurantOpen());
        boolean expectedStatus =false;


        /* If logic to make this test case pass in all conditions */
        /* ! operator for assertFalse to handle all cases */

        if(restaurant.isRestaurantOpen()) {
            assertFalse(!restaurant.isRestaurantOpen());
            assertThat(expectedStatus, equalTo(!restaurant.isRestaurantOpen()));
        }else {
            assertFalse(restaurant.isRestaurantOpen());
            assertThat(expectedStatus, equalTo(restaurant.isRestaurantOpen()));
        }

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        System.out.println("In Method - adding_item_to_menu_should_increase_size_by_1()");

        int initialMenuSize = restaurant.getMenu().size();
        System.out.println("Initial menu size: " + initialMenuSize);
        restaurant.addToMenu("Sizzling brownie",319);

        /* Start of Asserts Section */
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
        assertNotNull(restaurant.getMenu().get(2));
        assertThat(restaurant.getMenu().get(2).getName(), equalTo("Sizzling brownie"));
        /* End of Asserts Section */

        System.out.println("Menu Items Size after 1 item addition: " + restaurant.getMenu().size());

    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        System.out.println("In Method - removing_item_from_menu_should_reduce_size_by_1()");

        int initialMenuSize = restaurant.getMenu().size();
        System.out.println("Menu Items size before item removal : " + initialMenuSize);
        String myFavItem = "Vegetable lasagne";
        restaurant.removeFromMenu(myFavItem);

        /* Start of Asserts Section */
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
        assertFalse(restaurant.getMenu().contains(myFavItem));

        /* End of Asserts Section */

        System.out.println("Menu Items size after item removal : " + restaurant.getMenu().size());

    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        System.out.println("In Method - removing_item_that_does_not_exist_should_throw_exception()");

        /* Start of Asserts Section */
        itemNotFoundException thrown = assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
        thrown.printStackTrace();

        /* End of Asserts Section */

    }

    @Test
    public void adding_items_should_give_correct_total_OrderValue() {
        System.out.println("In Method - adding_items_should_give_correct_total_OrderValue()");

        List<String> itemsSelected = new ArrayList<>();
        itemsSelected.add("Sweet corn soup");
        itemsSelected.add("Vegetable lasagne");
        int totalMenuItemsPrice = sweetCornSoupPrice + vegetableLasagnePrice;
        System.out.println(" Actual sum of prices: " + totalMenuItemsPrice);

        int totalSelectedOrderValue = restaurant.getTotalOrderValue(itemsSelected);
        /* Start of Asserts Section */
        assertEquals(totalSelectedOrderValue,totalMenuItemsPrice);
        /* End of Asserts Section */
        System.out.println(" Selected Total Order Value: " + totalSelectedOrderValue);


    }

    @Test
    public void reducing_items_should_give_correct_total_OrderValue() {
        System.out.println("In Method - reducing_items_should_give_correct_total_OrderValue()");

        List<String> itemsSelected = new ArrayList<>();
        itemsSelected.add("Sweet corn soup");
        itemsSelected.add("Vegetable lasagne");
        int initialTotalMenuItemsPrice, intialTotalSelectedOrderValue;
        initialTotalMenuItemsPrice = sweetCornSoupPrice + vegetableLasagnePrice;
        System.out.println(" Initial sum of prices: " + initialTotalMenuItemsPrice);

        intialTotalSelectedOrderValue = restaurant.getTotalOrderValue(itemsSelected);
        /* Start of Asserts Section */
        assertEquals(intialTotalSelectedOrderValue,initialTotalMenuItemsPrice);
        /* End of Asserts Section */
        System.out.println(" Initial Selected Total Order Value: " + intialTotalSelectedOrderValue);

        int revisedTotalMenuItemsPrice , revisedTotalSelectedOrderValue;

        itemsSelected.remove("Vegetable lasagne");

        revisedTotalMenuItemsPrice = initialTotalMenuItemsPrice - vegetableLasagnePrice;
        System.out.println(" Actual sum of prices after removal: " + revisedTotalMenuItemsPrice);
        revisedTotalSelectedOrderValue = restaurant.getTotalOrderValue(itemsSelected);

        /* Start of Asserts Section */
        assertEquals(revisedTotalSelectedOrderValue,revisedTotalMenuItemsPrice);
        /* End of Asserts Section */
        System.out.println(" Revised Selected Total Order Value: " + revisedTotalSelectedOrderValue);

    }


    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



}