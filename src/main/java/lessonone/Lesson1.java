package lessonone;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Lesson1 {

    public static void main(String[] args) {
        start();
    }

    private static void start(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Lesson1Config.class);

        Cart cart1 = context.getBean("cart", Cart.class);
        cart1.addProduct(1);
        cart1.addProduct(2);

        Cart cart2 = context.getBean("cart", Cart.class);
        cart2.addProduct(3);
        cart2.addProduct(4);
        cart2.addProduct(1);
        cart2.removeProduct(1);

        System.out.println(cart1.toString());
        System.out.println(cart2.toString());
    }
}
