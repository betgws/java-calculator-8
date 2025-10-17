package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public static int add(String input) {

        // input 값이 없을 경우 0
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String delimiter = ",|:";
        String numbers = input;

        // 커스텀 구분자 형식: //;\n1;2;3
        if (input.startsWith("//")) {
            Matcher m = Pattern.compile("//(.)\n(.*)").matcher(input);
            if (m.matches()) {
                delimiter += "|" + m.group(1);
                numbers = m.group(2);
            }
        }

        int sum = 0;
        for (String numStr : numbers.split(delimiter)) {
            int num = Integer.parseInt(numStr);
            if (num < 0) {
                throw new IllegalArgumentException("음수는 허용되지 않습니다: " + num);
            }
            sum += num;
        }
        return sum;

    }
}
