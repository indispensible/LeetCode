package test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lvgang
 * @date 2020/12/14 14:57
 */
public class Test4 {
    public static void main(String[] args) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        for (int i = 1; i <= 10; i++) {
            if (i > lists.size()) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i * 10 + i);
                list.add(i * 10 + i + 1);
                lists.add(list);
            }
        }
        System.out.println(lists);
    }
}
