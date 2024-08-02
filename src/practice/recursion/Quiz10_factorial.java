package practice.recursion;

public class Quiz10_factorial {
    public static void main(String[] args) {
        int result = factorial(3);
        System.out.println(result);

    }

    public static int factorial(int num){
        if (num==1){
            return 1;
        }else{
            return num * factorial(num-1);
        }
    }


}

// 호출순서
// main()
// factorial(3);
// 3 * factorial(2)
// factorial(1)

// 완료 순서
// factorial(1)
// 3* factorial(2)
// factorial(3);
// main
