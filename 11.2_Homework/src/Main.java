import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import java.util.Comparator;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Date date = new Date(System.currentTimeMillis());

        Airport airport = Airport.getInstance();
        airport.getTerminals().stream().
                flatMap(terminal -> terminal.getFlights().stream()).
                filter(flight -> flight.getType() == Flight.Type.DEPARTURE).
                filter(flight -> flight.getDate().before(Date.from(date.toInstant().plusSeconds(7200)))).
                filter(flight -> flight.getDate().after(Date.from(date.toInstant()))).
                forEach(System.out::println);
    }
}
