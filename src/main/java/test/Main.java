package test;

import java.net.URL;

public class Main {
    public static void main(String[] args) {
        URL resource = Main.class.getClassLoader().getResource("images/foot.png");
        String path = resource.getPath();
        System.out.println(path);
        System.out.println(resource.getFile());
        System.out.println(resource);
    }
}
