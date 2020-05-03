package builder;

/**
 * 构建模式，构建分成若干步，通过一步步构建信息吧一个复杂的对象构建出来
 * @author Administrator
 */

public class TicketHelper {
    public void buildAdult(String info) {
        System.err.println("构建成年人的票："+info);
    }

    public void buildSeatForChildren(String info) {
        System.err.println("构建儿童的票："+info);
    }

    public void buildSeatForChildrenWithoutSeat(String info) {
        System.err.println("构建无座儿童的票："+info);
    }

    public void buildSeatForElderly(String info) {
        System.err.println("构建老年人的票："+info);
    }

    public void buildSolder(String info) {
        System.out.println("构建军人专属票："+info);
    }
}
