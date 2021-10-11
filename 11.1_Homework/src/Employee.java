import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Employee
{
    private String name;//переменная имени, типа Строка
    private Integer salary;//переменная зарплата, типа Число
    private Date workStart;//переменная дата, типа Дата (нало работы)

    public Employee(String name, Integer salary, Date workStart)//конструктор
    {
        this.name = name;
        this.salary = salary;
        this.workStart = workStart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getWorkStart() {
        return workStart;
    }

    public void setWorkStart(Date workStart) {
        this.workStart = workStart;
    }

    public String toString()//метод возврата информации имя, зарпалата и дата начала работы (в формате)
    {
        return name + " - " + salary + " - " +
            (new SimpleDateFormat("dd.MM.yyyy")).format(workStart);
    }
}
