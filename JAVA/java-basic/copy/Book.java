public class Book {

    private String author;
    private String title;

    public Book(String title, String author){
        this.author = author;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void showInfo(){
        System.out.println("제목: " + title + " 저자: " + author);
    }
}
