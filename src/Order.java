import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    // 주문 번호
    private static int nextId = 1;
    private int id=1;


    // 고객명
    private String customerName;
    // 제품명
    private String productName;
    // 주문 수량
    private int OrderAmount;
    // 가격
    private int price;
    // 주문 일시
    String curDateTime = setNowDateTime();


    public Order(String customerName, String productName, int orderAmount, int price) {
        this.id=nextId++;
        this.customerName = customerName;
        this.productName = productName;
        OrderAmount = orderAmount;
        this.price = price;
    }

    final int getId() {
        return id++;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProductName() {
        return productName;
    }

    public int getOrderAmount() {
        return OrderAmount;
    }

    public int getPrice() {
        return price;
    }

    public String getCurDateTime() {
        return curDateTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", productName='" + productName + '\'' +
                ", OrderAmount=" + OrderAmount +
                ", price=" + price +
                ", curDateTime='" + curDateTime + '\'' +
                '}';
    }

    // 메소드
    public String setNowDateTime(){
        // 현재 날짜와 시간 가져오기
        LocalDateTime now = LocalDateTime.now();

        // 날짜를 유지하면서 시간, 분, 초를 00시로 설정
        LocalDateTime updatedDate = now.withHour(0).withMinute(0).withSecond(0).withNano(0);

        // 포맷터 설정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 포맷팅된 문자열 출력
        String formattedDate = updatedDate.format(formatter);


        // 현재 시간 출력
        String currentTime = now.format(formatter);

        return currentTime;
    }


}





