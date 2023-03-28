package Abstract;

public class ComputerTest {
    public static void main(String[] args) {

        Computer computer = new Desktop();
        Computer computer2 = new MyNoteBook();

        computer2.typing();
    }
}
