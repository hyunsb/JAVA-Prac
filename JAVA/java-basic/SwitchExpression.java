public class SwitchExpression {
    public static void main(String[] args) {
        int month = 3;
        int day = switch (month) {
            case 1, 3, 5, 7, 8, 10, 12: yield 31;
            case 4, 6, 9, 11: yield 30;
            case 2: yield 28;

            default:
                throw new IllegalStateException("존재하지 않는 달입니다.: " + month);
        };
    }
}
