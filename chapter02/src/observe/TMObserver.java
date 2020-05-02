package observe;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者天猫
 */
public class TMObserver implements Observer {
    @Override
    public void update(Observable o, Object product) {
        String newProduct=(String)product;
        System.out.println("发送新产品【"+newProduct+"】同步到天猫");
    }

    //观察者模式测试
    public static void main(String[] args) {
        ProductList observerable=ProductList.getInstance();
        JDObserver jdObserver=new JDObserver();
        TMObserver tmObserver=new TMObserver();
        observerable.addObserver(jdObserver);
        observerable.addObserver(tmObserver);
        observerable.addProduct("apple");
        //产品列表新加了产品:apple
        //发送新产品【apple】同步到天猫
        //发送新产品【apple】同步到京东
    }
}
