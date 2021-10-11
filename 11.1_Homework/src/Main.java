import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main
{
    private static final String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";
    public static void main(String[] args) throws ParseException {
        System.out.println ( Main.start ());
    }

    public static String start() throws ParseException{
        ArrayList<Employee> staff = loadStaffFromFile();//txt-файл, через метод loadStaffFromFile в ArrayList
        //staff.stream().sorted(Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName)).forEach(System.out::println);
        String dateBegin = "01.01.2017";
        Date parseDateBegin = new SimpleDateFormat("dd.MM.yyyy").parse(dateBegin);
        String dateAnd = "31.12.2017";
        Date parseDateAnd = new SimpleDateFormat("dd.MM.yyyy").parse(dateAnd);
        String string = staff.stream().filter(e -> e.getWorkStart()
                .after(parseDateBegin)&& e.getWorkStart()
                .before(parseDateAnd))
                .collect(Collectors.toList())
                .stream().max(Comparator.comparing(Employee::getSalary))
                .map ( Employee::toString )
                .stream().collect ( Collectors.joining () );

        // staff.stream().max(Comparator.comparing(Employee::getSalary)).ifPresent(System.out::println);
        return string;
    }

    private static ArrayList<Employee> loadStaffFromFile()
    {
        ArrayList<Employee> staff = new ArrayList<>();
        try//определяет блок кода, в котором может произойти исключение;
        {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));//У класса Files вызывается метод readAllLines, который возвращает коллекцию
            for(String line : lines)
            {
                String[] fragments = line.split("\t");//делит строку по табуляции
                if(fragments.length != 3) //Если в строке не три элемента, тогда сообщение, и выход
                {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(fragments[0], Integer.parseInt(fragments[1]),
                    (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        }
        catch (Exception ex)//определяет блок кода, в котором происходит обработка исключения;
        {
            ex.printStackTrace();
        }
        return staff;
    }
}