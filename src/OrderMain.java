import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;




public class OrderMain{


    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

             boolean isOn = true;
             BufferedReader reader = null;


        while (isOn) {


            displayMenu();

            int num = sc.nextInt();
            sc.nextLine();


            // 1. 상품 주문하기 order.txt 파일에 저장
            if (num == 1) {
                addOrder(sc);

            }
            // 2. 전체 주문 이력 보기
            else if (num == 2) {
                showOrder();
            }


            // 3. 고객별 주문 이력 보기
            else if (num == 3) {
                orderforCustomer(sc);

            }
            // 4. 특정날짜에 들어온 주문 이력 보기
            else if (num == 4) {
                orderForDate(sc);

            }
            // 5. 주문 완료 끝내기
            else if (num == 5) {
                isOn = false;
            }
        }

    }







    // ============== 메소드 ================ //


    private static void displayMenu(){
        System.out.println("1. 상품 주문하기");
        System.out.println("2. 전체 주문 이력 보기");
        System.out.println("3. 고객별 주문 이력 보기");
        System.out.println("4. 특정날짜에 들어온 주문이력 보기");
        System.out.println("5. 끝내기");
        System.out.print("옵션을 선택하세요: ");
    }

    private static void addOrder(Scanner sc){
        System.out.print("고객명: ");
        String name = sc.nextLine();
        System.out.println();
        System.out.print("제품명: ");
        String productName = sc.nextLine();
        System.out.println();
        System.out.print("주문수량: ");
        int orderAmount = sc.nextInt();
        System.out.println();
        System.out.print("가격: ");
        int price = sc.nextInt();


        Order orderInfo = new Order(name, productName, orderAmount, price);

        File file = new File("order.txt"); // 파일 경로 설정
        try {
            // 1. 파일 읽어오기
            List<Integer> orderNumbers = new ArrayList<>();
            try (BufferedReader bufReader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = bufReader.readLine()) != null) {
                    // 주문 번호 추출
                    String[] parts = line.split(","); // 콤마로 분리
                    if (parts.length > 0) {
                        String orderNumberPart = parts[0].trim();
                        if (orderNumberPart.startsWith("주문번호:")) {
                            try {
                                int orderNumber = Integer.parseInt(orderNumberPart.split(":")[1].trim());
                                orderNumbers.add(orderNumber);
                            } catch (NumberFormatException e) {
                                // 주문 번호가 잘못된 형식일 경우 무시
                            }
                        }
                    }
                }
            }

            // 3. 가장 큰 주문 번호 찾기
            int nextOrderNumber = 1;
            if (!orderNumbers.isEmpty()) {
                nextOrderNumber = Collections.max(orderNumbers) + 1;
            }

            // 주문 정보 추가
            String newOrderInfo = String.format(
                    "주문번호: %d, 고객명: %s, 제품명: %s, 주문수량: %d, 가격: %d, 주문일시: %s\n",
                    nextOrderNumber, orderInfo.getCustomerName(), orderInfo.getProductName(),orderInfo.getOrderAmount(), orderInfo.getPrice(), orderInfo.getCurDateTime()
            );

            // 4. 파일에 기록
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(newOrderInfo);
                System.out.println("새 주문 정보가 파일에 추가되었습니다.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private static void showOrder(){
        File file = new File("c:/imeunjae/shoppingCarProject/order.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String str;

            List<String> orderLines = new ArrayList<>();
            HashMap<String, String> orderMap = new HashMap<>();
            while ((str = br.readLine()) != null) {

                System.out.println(str);



            }


        } catch (IOException e) {
            System.err.println("에러시: " + e.getMessage());
        }
    }


    private static void orderforCustomer(Scanner sc){
        int count = 0;
        System.out.print("고객명: ");
        String orderedCustomerName = sc.nextLine();

        File file = new File("c:/imeunjae/shoppingCarProject/order.txt");
        Charset charset = StandardCharsets.UTF_8;

        try {
            List<String> lines = Files.readAllLines(file.toPath(), charset);
            boolean hasOrders = false;

            int orderTotalPrice = 0;
            for (String line : lines) {
                if (line.contains(orderedCustomerName)) {
                    System.out.println(line);
                    count++;
                    hasOrders = true;


                    String[] parts = line.split(",");
                    if (parts.length > 0) {
                        String orderNumberPart = parts[4].trim();
                        if (orderNumberPart.startsWith("가격:")) {
                            try {
                                orderTotalPrice += Integer.parseInt(orderNumberPart.split(":")[1].trim());
                            } catch (NumberFormatException e) {

                            }
                        }
                    }
                }
            }



            // 금액 추출


            System.out.println("전체 주문 건수: " + count);
            System.out.println("총 금액: " + orderTotalPrice + "원");


            if (!hasOrders) {
                System.out.println("해당 고객님의 주문 이력이 없습니다.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void orderForDate(Scanner sc){
        int count = 0;
        System.out.print("조회 날짜: ");
        String orderedCustomerDate = sc.nextLine();

        File file = new File("c:/imeunjae/shoppingCarProject/order.txt");
        Charset charset = StandardCharsets.UTF_8;

        try {
            List<String> lines = Files.readAllLines(file.toPath(), charset);
            boolean hasOrders = false;

            for (String line : lines) {
                if (line.contains(orderedCustomerDate)) {
                    System.out.println(line);
                    count++;
                    hasOrders = true;
                }

                // 날짜 추출
                String[] parts = line.split(","); // 콤마로 분리

            }

            System.out.println("전체 주문 건수: " + count);

            if (!hasOrders) {
                System.out.println("해당 날짜의 주문 이력이 없습니다.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    }












