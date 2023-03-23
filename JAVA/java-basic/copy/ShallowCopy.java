public class ShallowCopy {

    public static void main(String[] args) {
        String[] taebaekSet = new String[]{"태백산맥1", "태백산맥2", "태백산맥3", "태백산맥", "태백산맥5"};
        String author = "조정래";

        Book[] library = new Book[5];
        Book[] copyLibrary = new Book[5];

        for(int i=0; i<library.length; i++)
            library[i] = new Book(taebaekSet[i], author);

        System.arraycopy(library, 0, copyLibrary, 0, 5);

        System.out.println("library address | copyLibrary address");
        for(int i=0; i<library.length; i++)
            System.out.println( library[i] + " | " + copyLibrary[i]);


        Book[] copyLibrary2 = library;
        System.out.println("library address | copyLibrary2 address");
        for(int i=0; i<library.length; i++)
            System.out.println( library[i] + " | " + copyLibrary2[i]);
    }

}
