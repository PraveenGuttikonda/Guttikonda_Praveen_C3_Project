import org.junit.jupiter.api.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
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
    private void initialize_RestaurantService_with_default_Restaurant_and_Menu() {
        restaurant = service.addRestaurant(customRestaurantName,customLocation,openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",sweetCornSoupPrice);
        restaurant.addToMenu("Vegetable lasagne", vegetableLasagnePrice);

    }
    @AfterEach
    private void soutDash() {
        System.out.println("---------------------------------------------------");
    }


    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        System.out.println("In Method - searching_for_existing_restaurant_should_return_expected_restaurant_object()" );

        Restaurant myFavRestaurant = service.findRestaurantByName(customRestaurantName);

        /* Start of Asserts Section */

        assertNotNull(myFavRestaurant);
        assertEquals(customRestaurantName,  myFavRestaurant.getName());
        /* End of Asserts Section */

        System.out.println("Searching for restaurant :"+customRestaurantName + ". Got Restaurant with name :" +myFavRestaurant.getName());
        myFavRestaurant.displayDetails();
    }
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        System.out.println("In Method - searching_for_non_existing_restaurant_should_throw_exception()");
        String wrongCustomRestaurantName =" Bhagini Fine Dine ";
        System.out.println("Wrong restaurant name used for testing is :" +wrongCustomRestaurantName);

          /* Start of Asserts Section */
        restaurantNotFoundException thrown = assertThrows(restaurantNotFoundException.class,() -> {
            //Exception code under Test
            service.findRestaurantByName(wrongCustomRestaurantName);
        });
        thrown.printStackTrace();
        assertFalse(service.getRestaurants().contains(wrongCustomRestaurantName));

        /* End of Asserts Section */

    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        System.out.println("In Method - remove_restaurant_should_reduce_list_of_restaurants_size_by_1()");

        int initialNumberOfRestaurants = service.getRestaurants().size();
        System.out.println("Current restaurant service size before restaurant removal is: " +initialNumberOfRestaurants);

        //service.removeRestaurant("Amelie's cafe");
        service.removeRestaurant(customRestaurantName);

        /*  Start of Asserts Section */
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
        assertFalse(service.getRestaurants().contains(restaurant));

        /* End of Asserts Section */

        System.out.println("Current restaurant service size after restaurant removal is: " +service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        System.out.println("In Method - removing_restaurant_that_does_not_exist_should_throw_exception()");
        restaurantNotFoundException thrown = assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
        thrown.printStackTrace();
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        System.out.println("In Method - add_restaurant_should_increase_list_of_restaurants_size_by_1()");
        int initialNumberOfRestaurants = service.getRestaurants().size();
        System.out.println("Current restaurant service initial size is: "+initialNumberOfRestaurants);

        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));

        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());

        System.out.println("Current restaurant service after 1 restaurant addition is: "+service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}