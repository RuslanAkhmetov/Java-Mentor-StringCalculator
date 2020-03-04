import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StrCalcTest {
    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try{
            while(true){
                System.out.println("Input string formula for exit type \" exit \":");
                String formula = input.readLine();
                if (!formula.equals("exit")){
                    StrCalculator strcalc = new StrCalculator(formula);

                    if (strcalc.argstr1 !="") {
                        String strout = strcalc.Calculate();
                        System.out.print("output :");
                        for (int i = 0; i < strcalc.result.length; i++) {
                           System.out.print(strcalc.result[i]);
                        }
                        System.out.print("\n");
                    }
                } else {
                    break;
                }
            }
        } catch (IOException | IllegalArgumentException e){
            System.out.println("Error:" + e.getMessage());

        }
    }
}
