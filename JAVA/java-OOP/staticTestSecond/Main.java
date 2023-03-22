package staticTestSecond;

public class Main {

    public static void main(String[] args) {
        Employee employeeLee = new Employee();
        employeeLee.setEmployeeName("이순신");

        Employee employeeKim = new Employee();
        employeeKim.setEmployeeName("김유신");
        employeeKim.serialNum++;

        System.out.println(Employee.serialNum);
        System.out.println(employeeLee.serialNum);
        System.out.println(employeeKim.serialNum);

    }
}
