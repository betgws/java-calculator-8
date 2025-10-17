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

        // 커스텀 구분자 형식
        if (input.startsWith("//")) {
            input = input.replaceAll("\\\\n", "\n");
            Matcher m = Pattern.compile("//(.)\n(.*)").matcher(input);
            if (m.matches()) {
                delimiter += "|" + m.group(1);
                numbers = m.group(2);
            }
        }

        int sum = 0;
        for (String numStr : numbers.split(delimiter)) {
            try {
                int num = Integer.parseInt(numStr);
                if (num < 0) {
                    throw new IllegalArgumentException("음수는 허용되지 않습니다: " + num);
                }
                sum += num;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("잘못된 입력값이 포함되어 있습니다: " + numStr);
            }
        }
        return sum;

    }
}
