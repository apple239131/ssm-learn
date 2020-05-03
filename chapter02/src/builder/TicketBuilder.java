package builder;

public class TicketBuilder {
    public static Object builder(TicketHelper ticketHelper) {
        System.out.println("通过TicketHelper构建套票信息");
        return null;
    }

    public static void main(String[] args) {
        TicketHelper ticketHelper=new TicketHelper();
        ticketHelper.buildAdult("成人票");
        ticketHelper.buildSeatForChildren("儿童票");
        Object ticket=TicketBuilder.builder(ticketHelper);
    }
}
