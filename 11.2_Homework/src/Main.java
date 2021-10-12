import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final Date date = new Date(System.currentTimeMillis());
    private static Airport airport;
    private static final int minutes = 120;
    public static void main(String[] args) {
        printFlightList ( findPlanesLeavingInTheNextTwoHours ( minutes ) );
        printFlightList ( test ( minutes ) );

    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(long minut) {
        airport = Airport.getInstance();
        final var collect = airport.getTerminals ().stream ().
                flatMap ( terminal -> terminal.getFlights ().stream () ).
                filter ( flight -> flight.getType () == Flight.Type.DEPARTURE ).
                filter ( flight -> flight.getDate ().before ( Date.from ( date.toInstant ().plusSeconds ( minut * 60 ) ) ) ).
                filter ( flight -> flight.getDate ().after ( Date.from ( date.toInstant () ) ) ).
                collect ( Collectors.toList () );
        return collect;
    }
    
    public static List<Flight> test(long minutes) {
        airport = Airport.getInstance();
        List<Flight> collect = new ArrayList<> ();
        for (Terminal terminal : airport.getTerminals ()) {
            for (Flight flight : terminal.getFlights ()) {
                if (flight.getType () == Flight.Type.DEPARTURE) {
                    if (flight.getDate ().before ( Date.from ( date.toInstant ().plusSeconds ( minutes * 60 ) ) )) {
                        if (flight.getDate ().after ( Date.from ( date.toInstant () ) )) {
                            collect.add ( flight );
                        }
                    }
                }
            }
        }
        return collect;
    }

    public static void printFlightList(List<Flight> list) {
        System.out.println("=================================");
        list.forEach(System.out::println);
        System.out.println("=================================");
    }
}
