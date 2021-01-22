package offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

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

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(candidates);
        int left = 0, right = 0, sum = 0, i, j;
        while (2 * candidates[left] <= target) {
            j = target / candidates[left];
            for (i = 1; i <= j; i++) {
                sum = i * candidates[left];
                list.add(candidates[left]);
                if (sum == target) {
                    lists.add((List<Integer>) ((ArrayList<Integer>) list).clone());
                    break;
                }
                for (right = left + 1; right < candidates.length; right++) {
                    if (sum + candidates[right] > target) {
                        break;
                    } else if (sum + candidates[right] == target) {
                        list.add(candidates[right]);
                        lists.add((List<Integer>) ((ArrayList<Integer>) list).clone());
                        list.remove(list.size() - 1);
                    }
                }
            }
            list.clear();
            left++;
        }
        return lists;
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int max = 0;
        int[] heights = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                heights[j] = 0;
                if (matrix[i][j] == '1') {
                    for (int idx = i; idx >= 0; idx--) {
                        if (matrix[idx][j] == '0') {
                            break;
                        }
                        heights[j]++;
                    }
                }
            }
            max = Math.max(max, largestRectangleArea(heights));
        }
        return max;
    }

    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int max = 0, idx = 0, i = 0;
        for (; i < heights.length; i++) {
            if (stack.empty() || heights[i] > heights[stack.peek()]) {
                stack.push(i);
            } else if (heights[i] < heights[stack.peek()]) {
                while (stack.size() > 0 && heights[i] <= heights[stack.peek()]) {
                    idx = stack.pop();
                    max = Math.max(max, (i - idx) * heights[idx]);
                }
                stack.push(idx);
                heights[idx] = heights[i];
            }
        }
        while (stack.size() > 0) {
            idx = stack.pop();
            max = Math.max(max, (i - idx) * heights[idx]);
        }
        return max;
    }

    public static void main(String[] args) {
        Numbers numbers = new Numbers();
        char[][] matrix = {{}};
        System.out.println(numbers.maximalRectangle(matrix));
//        float a = 9.f;
//        Numbers numbers = new Numbers();
//        System.out.println(numbers.isNumber("+.8"));
//        System.out.println(numbers.isNumber("1e."));
//        System.out.println(numbers.isNumber("0e"));
//        System.out.println(numbers.isNumber("1 4"));
//        System.out.println(numbers.isNumber("e9"));
//        System.out.println(numbers.isNumber("."));
//        System.out.println(numbers.isNumber("+e"));
//        System.out.println(numbers.isNumber(" "));
//        System.out.println(numbers.isNumber("+."));
//        System.out.println(numbers.isNumber(".0"));
//        System.out.println(numbers.isNumber(" 0"));
//        System.out.println(numbers.isNumber("0."));
//        System.out.println(numbers.isNumber("-1E-16"));
    }
}
