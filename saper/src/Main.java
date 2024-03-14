public class Main implements Runnable{
    Menu menu = new Menu();
    public static void main(String[] args){
        new Thread(new Main()).start();

    }
    public void run() {
    }
}