import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

class AviaSoulsTest {

    AviaSouls tickets = new AviaSouls();
    Comparator<Ticket> comp = new TicketTimeComparator();
    Ticket ticket1 = new Ticket("Москва", "Санкт-Петербург", 4_500, 11, 13);
    Ticket ticket2 = new Ticket("Москва", "Пекин", 30_000, 6, 14);
    Ticket ticket3 = new Ticket("Санкт-Петербург", "Москва", 4_500, 21, 23);
    Ticket ticket4 = new Ticket("Москва", "Санкт-Петербург", 3_500, 8, 10);
    Ticket ticket5 = new Ticket("Владивосток", "Бангкок", 10_000, 9, 13);
    Ticket ticket6 = new Ticket("Санкт-Петербург", "Москва", 5_000, 10, 11);

    @Test
    public void CompareFirstTicketIsLower() {

        int expected = -1;
        int actual = ticket1.compareTo(ticket2);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void CompareFirstTicketIsHigher() {

        int expected = 1;
        int actual = ticket5.compareTo(ticket1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void CompareEqualPriceTickets() {

        int expected = 0;
        int actual = ticket1.compareTo(ticket3);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndSortArrayByPriceTwoMatches() {
        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);
        tickets.add(ticket4);
        tickets.add(ticket5);
        tickets.add(ticket6);

        Ticket[] actual = tickets.search("Москва", "Санкт-Петербург");
        Ticket[] expected = {ticket4, ticket1};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchOneMatch() {
        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);
        tickets.add(ticket4);
        tickets.add(ticket5);
        tickets.add(ticket6);

        Ticket[] actual = tickets.search("Владивосток", "Бангкок");
        Ticket[] expected = {ticket5};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchNoMatches() {
        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);
        tickets.add(ticket4);
        tickets.add(ticket5);
        tickets.add(ticket6);

        Ticket[] actual = tickets.search("Владивосток", "Москва");
        Ticket[] expected = {};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void CompareFirstTicketIsFaster() {

        int expected = -1;
        int actual = comp.compare(ticket1, ticket2);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void CompareFirstTicketIsSlower() {

        int expected = 1;
        int actual = comp.compare(ticket5, ticket4);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void CompareEqualTimeTickets() {

        int expected = 0;
        int actual = comp.compare(ticket1, ticket3);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSortByTimeTwoMatches() {
        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);
        tickets.add(ticket4);
        tickets.add(ticket5);
        tickets.add(ticket6);

        Ticket[] actual = tickets.searchAndSortBy("Санкт-Петербург", "Москва", comp);
        Ticket[] expected = {ticket6, ticket3};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSortByTimeOneMatch() {
        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);
        tickets.add(ticket4);
        tickets.add(ticket5);
        tickets.add(ticket6);

        Ticket[] actual = tickets.searchAndSortBy("Москва", "Пекин", comp);
        Ticket[] expected = {ticket2};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchByTimeNoMatches() {
        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);
        tickets.add(ticket4);
        tickets.add(ticket5);
        tickets.add(ticket6);

        Ticket[] actual = tickets.searchAndSortBy("Владивосток", "Пекин", comp);
        Ticket[] expected = {};

        Assertions.assertArrayEquals(expected, actual);
    }
}