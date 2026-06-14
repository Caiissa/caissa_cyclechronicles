package cyclechronicles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;


class ShopTest {

    private Shop shop;

    @BeforeEach
    void setUp() {
        shop = new Shop();
    }

    private static Order mockOrder(Type type, String customer) {
        Order o = mock(Order.class);
        lenient().when(o.getBicycleType()).thenReturn(type);
        lenient().when(o.getCustomer()).thenReturn(customer);
        return o;
    }

    @Test
    public void fahradtyp_race_true(){
        Order o = mockOrder(Type.RACE, "name");

        assertTrue(shop.accept(o));
    }

    @Test
    public void fahradtyp_single_speed_true(){
        Order o = mockOrder(Type.SINGLE_SPEED, "name");

        assertTrue(shop.accept(o));
    }

    @Test
    public void fahradtyp_fixie_true(){
        Order o = mockOrder(Type.FIXIE, "name");

        assertTrue(shop.accept(o));
    }

    @Test
    public void fahradtyp_gravel_false(){
        Order o = mockOrder(Type.GRAVEL, "name");

        assertFalse(shop.accept(o));
    }

    @Test
    public void fahradtyp_ebike_false(){
        Order o = mockOrder(Type.EBIKE, "name");

        assertFalse(shop.accept(o));
    }

    @Test
    public void offene_anfrage_false(){
        Order o1 = mockOrder(Type.RACE, "name");
        Order o2 = mockOrder(Type.RACE, "name");

        assertTrue(shop.accept(o1));

        assertFalse(shop.accept(o2));
    }

    @Test
    public void vorhandener_auftrag_fueng_true(){
        Order o1 = mockOrder(Type.RACE, "name1");
        Order o2 = mockOrder(Type.RACE, "name2");
        Order o3 = mockOrder(Type.RACE, "name3");
        Order o4 = mockOrder(Type.RACE, "name4");
        Order o5 = mockOrder(Type.RACE, "name5");

        shop.accept(o1);
        shop.accept(o2);
        shop.accept(o3);
        shop.accept(o4);

        assertTrue(shop.accept(o5));
    }

    @Test
    public void vorhandener_auftrag_sechs_true(){
        Order o1 = mockOrder(Type.RACE, "name1");
        Order o2 = mockOrder(Type.RACE, "name2");
        Order o3 = mockOrder(Type.RACE, "name3");
        Order o4 = mockOrder(Type.RACE, "name4");
        Order o5 = mockOrder(Type.RACE, "name5");
        Order o6 = mockOrder(Type.RACE, "name6");

        shop.accept(o1);
        shop.accept(o2);
        shop.accept(o3);
        shop.accept(o4);
        shop.accept(o5);

        assertFalse(shop.accept(o6));
    }


}
