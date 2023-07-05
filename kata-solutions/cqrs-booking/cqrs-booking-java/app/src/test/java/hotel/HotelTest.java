import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import java.util.UUID;
import java.util.ArrayList;
import java.time.LocalDate;

class HotelTest {
    private static final String BLUE_ROOM_NAME = "The Blue Room";
    private static final String RED_ROOM_NAME = "The Red Room";
    private static final LocalDate AN_ARRIVAL_DATE = LocalDate.of(2020, 1, 20);
    private static final LocalDate A_DEPARTURE_DATE = LocalDate.of(2020, 1, 22);
    private static final UUID A_UUID = UUID.randomUUID();

    private final BookingCommand blueRoomBookingCommand = new BookingCommand(
        A_UUID, BLUE_ROOM_NAME, AN_ARRIVAL_DATE, A_DEPARTURE_DATE
      );

    private final BookingCommand redRoomBookingCommand = new BookingCommand(
        A_UUID, RED_ROOM_NAME, AN_ARRIVAL_DATE, A_DEPARTURE_DATE
      );

    private Hotel hotel;

    @BeforeEach
    void setUpHotelWithBlueRoomBooking() {
        this.hotel = new Hotel(new ArrayList<Event>());
        hotel.onCommand(blueRoomBookingCommand);
    }

    @Test 
    void firstBookingCanAlwaysBeMade() {
        assertTrue(hotel.persist().size() == 1);
        assertTrue(hotel.persist().get(0) instanceof BookingCreatedEvent);

        BookingCreatedEvent event = (BookingCreatedEvent) hotel.persist().get(0);
        assertEquals(event.clientId, A_UUID);
        assertEquals(event.roomName, BLUE_ROOM_NAME);
        assertEquals(event.arrivalDate, AN_ARRIVAL_DATE);
        assertEquals(event.departureDate, A_DEPARTURE_DATE);
    }

    @Test 
    void bookingTheSameRoomForTheSameDateShouldFail() {
        hotel.onCommand(blueRoomBookingCommand);
      
        assertTrue(hotel.persist().size() == 2);
        assertTrue(hotel.persist().get(1) instanceof BookingFailedEvent);

        BookingFailedEvent event = (BookingFailedEvent) hotel.persist().get(1);
        assertEquals(event.clientId, A_UUID);
        assertEquals(event.roomName, BLUE_ROOM_NAME);
        assertEquals(event.arrivalDate, AN_ARRIVAL_DATE);
        assertEquals(event.departureDate, A_DEPARTURE_DATE);
    }

    @Test 
    void bookingDifferentRoomForTheSameDateShouldSucceed() {
        hotel.onCommand(redRoomBookingCommand);
      
        assertTrue(hotel.persist().size() == 2);
        assertTrue(hotel.persist().get(1) instanceof BookingCreatedEvent);

        BookingCreatedEvent event = (BookingCreatedEvent) hotel.persist().get(1);
        assertEquals(event.clientId, A_UUID);
        assertEquals(event.roomName, RED_ROOM_NAME);
        assertEquals(event.arrivalDate, AN_ARRIVAL_DATE);
        assertEquals(event.departureDate, A_DEPARTURE_DATE);
    }

    @Test 
    void bookingIdenticalRoomForAvailableDateShouldSucceed() {
        BookingCommand command = new BookingCommand(
          A_UUID, BLUE_ROOM_NAME, AN_ARRIVAL_DATE.plusDays(4), A_DEPARTURE_DATE.plusDays(4)
        );
        hotel.onCommand(command);
      
        assertTrue(hotel.persist().size() == 2);
        assertTrue(hotel.persist().get(1) instanceof BookingCreatedEvent);

        BookingCreatedEvent event = (BookingCreatedEvent) hotel.persist().get(1);
        assertEquals(event.clientId, A_UUID);
        assertEquals(event.roomName, BLUE_ROOM_NAME);
        assertEquals(event.arrivalDate, AN_ARRIVAL_DATE.plusDays(4));
        assertEquals(event.departureDate, A_DEPARTURE_DATE.plusDays(4));
    }
  
    @Test 
    void bookingIdenticalRoomWithDepartureBeforeArrivalOfExistingBookingShouldSucceed() {
        BookingCommand command = new BookingCommand(
          A_UUID, BLUE_ROOM_NAME, AN_ARRIVAL_DATE.minusDays(4), A_DEPARTURE_DATE.minusDays(4)
        );
        hotel.onCommand(command);
      
        assertTrue(hotel.persist().size() == 2);
        assertTrue(hotel.persist().get(1) instanceof BookingCreatedEvent);

        BookingCreatedEvent event = (BookingCreatedEvent) hotel.persist().get(1);
        assertEquals(event.clientId, A_UUID);
        assertEquals(event.roomName, BLUE_ROOM_NAME);
        assertEquals(event.arrivalDate, AN_ARRIVAL_DATE.minusDays(4));
        assertEquals(event.departureDate, A_DEPARTURE_DATE.minusDays(4));
    }
  
    @Test 
    void bookingTheSameRoomForOverlappingDatesShouldFail() {
        BookingCommand command = new BookingCommand(
          A_UUID, BLUE_ROOM_NAME, AN_ARRIVAL_DATE.plusDays(1), A_DEPARTURE_DATE.plusDays(1)
        );
        hotel.onCommand(command);
      
        assertTrue(hotel.persist().size() == 2);
        assertTrue(hotel.persist().get(1) instanceof BookingFailedEvent);

        BookingFailedEvent event = (BookingFailedEvent) hotel.persist().get(1);
        assertEquals(event.clientId, A_UUID);
        assertEquals(event.roomName, BLUE_ROOM_NAME);
        assertEquals(event.arrivalDate, AN_ARRIVAL_DATE.plusDays(1));
        assertEquals(event.departureDate, A_DEPARTURE_DATE.plusDays(1));
    }
}