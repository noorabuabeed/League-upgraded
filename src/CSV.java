
import java.io.FileWriter;

import static java.lang.System.out;

public class CSV {
    public static void main(String[] args)  {
        StringBuilder stringBuilder = new StringBuilder() ;

        stringBuilder.append("number").append(",").append("name").append(",").append("id").append("\n");
        stringBuilder.append(1).append(",").append("Barselona").append(",").append("123890").append("\n");
        stringBuilder.append(2).append(",").append("Realmadr").append(",").append("568012").append("\n");
        stringBuilder.append(3).append(",").append("AlhlalSUD").append(",").append("452186").append("\n");
        stringBuilder.append(4).append(",").append("Alnsr").append(",").append("701456").append("\n");
        stringBuilder.append(5).append(",").append("Manchester").append(",").append("142241").append("\n");
        stringBuilder.append(6).append(",").append("Milan").append(",").append("901973").append("\n");
        stringBuilder.append(7).append(",").append("InterMilan").append(",").append("908032").append("\n");
        stringBuilder.append(8).append(",").append("Ajax").append(",").append("863251").append("\n");
        stringBuilder.append(9).append(",").append("Atletco").append(",").append("506070").append("\n");
        stringBuilder.append(10).append(",").append("Dortmond").append(",").append("102745").append("\n");


        try (FileWriter fileWriter = new FileWriter("Team.CSV")) { //
            fileWriter.write(stringBuilder.toString());
            out.println("GOOD");
        }catch (Exception e ){
            out.println("you have proplem");
        }


    }
}