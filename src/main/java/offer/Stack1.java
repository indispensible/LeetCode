package offer;


/**
 * @author lvgang
 * @date 2020/12/14 9:45
 */
public class Stack1 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        boolean judge = true;
        int i = 0,j = 0, length = 0;
        int[] newPushed = new int[pushed.length];
        while (i < pushed.length) {
            if (pushed[i] == popped[j]) {
                i++;
                j++;
            } else {
                while (length > 0) {
                    if (newPushed[length - 1] != popped[j]) {
                        break;
                    }
                    length--;
                    j++;
                }
                newPushed[length++] = pushed[i++];
            }
        }
        while (j < pushed.length) {
            if (popped[j++] != newPushed[--length]) {
                judge = false;
                break;
            }
        }
        return judge;
    }
}
