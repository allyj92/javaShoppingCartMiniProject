import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderMain {
    public static <BufferWiter> void main(String[] args) throws IOException {


        Scanner sc = new Scanner(System.in);

        Order order;
//new Order("둘리","셔츠",1,20000);

        boolean isOn = true;
        while(isOn) {
            System.out.println("1. 상품 주문하기");
            System.out.println("2. 전체 주문 이력 보기");
            System.out.println("3. 고객별 주문 이력 보기");
            System.out.println("4. 특정날짜에 들어온 주문이력 보기");
            System.out.println("5. 끝내기");

            System.out.print("옵션을 선택하세요: ");


            int num = sc.nextInt();
            sc.nextLine();
            System.out.println();

            // 1. 상품 주문하기 order.txt 파일에 저장

            if (num == 1) {


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
                sc.nextLine();
                Order orderInfo = new Order(name, productName, orderAmount, price);


                File file = new File("c:\\imeunjae\\shoppingCarProject\\order.txt");

                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter writer = new BufferedWriter(fw);

                writer.write("주문번호: " + orderInfo.getId() + ", 고객명: " + orderInfo.getCustomerName() + ", 제품명: " + orderInfo.getProductName() + ", 주문수량: " + orderInfo.getOrderAmount() + ", 가격: " + orderInfo.getPrice() + ", 주문일시: " + orderInfo.getCurDateTime() + "\n");

                writer.close();


            }


            // 2. 전체 주문 이력 보기
            else if (num == 2) {
                BufferedReader reader = new BufferedReader(
                        new FileReader("c:\\imeunjae\\shoppingCarProject\\order.txt")
                );

                String str;
                while ((str = reader.readLine()) != null) {
                    System.out.println(str);
                }
                reader.close();
            }

            // 3. 고객별 주문 이력 보기

            else if (num == 3) {

                int count = 0;
                // 조회할 고객명
                System.out.print("고객명: ");
                String orderedCustomerName = sc.nextLine();

                File file = new File("c:/imeunjae/shoppingCarProject/order.txt");
                Charset charset = StandardCharsets.UTF_8;

                try {
                    List<String> lines = Files.readAllLines(file.toPath(), charset);
                    boolean hasOrders = false;

                    for (String line : lines) {
                        if (line.contains(orderedCustomerName)) {
                            System.out.println(line);
                            count++;
                            hasOrders = true;
                        }
                        System.out.println("전체 주문 건수: " + count);
                    }

                    if (!hasOrders) {
                        System.out.println("해당 고객님의 주문 이력이 없습니다.");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }






            }


            // 5. 주문 완료 끝내기
            else if (num == 5) {
                isOn = false;
            }

        }
}}
