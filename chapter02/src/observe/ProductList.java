package observe;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ProductList extends Observable {
    //产品列表
    private List<String> productList=null;
    //类唯一实例
    private static ProductList instance;
    //构造方法私有化，避免通过new创建对象，而是通过getInstance方法获得产品列表单例，单例模式
    private ProductList() {

    };

    /**
     * 取得唯一实例
     *
     * @return 产品列表唯一实例
     */
    public static ProductList getInstance() {
        if (instance == null) {
            instance=new ProductList();
            instance.productList=new ArrayList<String>();
        }
        return instance;
    }

    /**
     * 增加观察者
     * @param observer 观察者
     */
    public void addProductListObserver(Observer observer) {
        this.addObserver(observer);
    }

    public void addProduct(String newProduct) {
        productList.add(newProduct);
        System.out.println("产品列表新加了产品:"+newProduct);
        //设置被观察对象发生变化
        this.setChanged();
        //通知观察者，传递变化内容
        this.notifyObservers(newProduct);
    }
}
