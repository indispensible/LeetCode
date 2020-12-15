package offer;

/**
 * @author lvgang
 * @date 2020/12/8 9:40
 */
public class Numbers {

    private char[] firstSign = {'+', '-', '.'};

    public int[] printNumbers(int n) {
        int arrayLength = (int) Math.pow(10, n) - 1;
        if (arrayLength > Integer.MAX_VALUE) {
            arrayLength = Integer.MAX_VALUE;
        }
        int[] numberArray = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            numberArray[i] = i + 1;
        }
        return numberArray;
    }

    public boolean isNumber(String s) {
        boolean judge = true, needNum = false, isE = false;
        int pointNum = 0, eNum = 0, plusNum = 0, subtractionNum = 0;
        s = s.trim();
        s = s.toLowerCase();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (needNum && (chars[i] > '9' || chars[i] < '0')) {
                judge = false;
                break;
            }
            if (isE && !"-0123456789".contains(String.valueOf(chars[i]))) {
                judge = false;
                break;
            }
            needNum = false;
            isE = false;
            if (chars[i] == '+' && plusNum > 0) {
                judge = false;
                break;
            }
            if (chars[i] == '-' && subtractionNum > 0) {
                judge = false;
                break;
            }
            if (chars[i] == '.') {
                if (pointNum > 0) {
                    judge = false;
                    break;
                }
                pointNum++;
                needNum = true;
            }
            if (chars[i] == 'e') {
                if (eNum > 0) {
                    judge = false;
                    break;
                }
                eNum++;
                plusNum = 1;
                subtractionNum = 0;
                isE = true;
            }
            if (i == 0 || chars[i] == '+' || chars[i] == '-') {
                if (chars[i] == '+' || chars[i] == '-') {
                    needNum = true;
                }
                plusNum++;
                subtractionNum++;
            }
            if (!"+-e.0123456789".contains(String.valueOf(chars[i]))) {
                judge = false;
                break;
            }
            if (i == chars.length - 1) {
                if (chars[i] > '9' || chars[i] < '0') {
                    if (chars[i] != '.' || chars.length == 1) {
                        judge = false;
                        break;
                    }
                }
            }
        }
        if (chars.length == 0) {
            judge = false;
        } else if (chars[0] > '9' || chars[0] < '0') {
            if (chars[0] != '+' && chars[0] != '-' && chars[0] != '.' || chars.length == 1) {
                judge = false;
            }
        }
        return judge;
    }

    public static void main(String[] args) {
        float a = 9.f;
        Numbers numbers = new Numbers();
        System.out.println(numbers.isNumber("+.8"));
        System.out.println(numbers.isNumber("1e."));
        System.out.println(numbers.isNumber("0e"));
        System.out.println(numbers.isNumber("1 4"));
        System.out.println(numbers.isNumber("e9"));
        System.out.println(numbers.isNumber("."));
        System.out.println(numbers.isNumber("+e"));
        System.out.println(numbers.isNumber(" "));
        System.out.println(numbers.isNumber("+."));
        System.out.println(numbers.isNumber(".0"));
        System.out.println(numbers.isNumber(" 0"));
        System.out.println(numbers.isNumber("0."));
        System.out.println(numbers.isNumber("-1E-16"));
    }
}
