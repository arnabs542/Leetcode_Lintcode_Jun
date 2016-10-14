package a_oa;

import java.util.Arrays;
import java.util.Stack;

/**
 * 给一个数组,代表一组人的身高。然后输出一个数组,表示在当前人之后的所有 比他高的人里,离他最近的人的身高。比如输入是[3, 6, 7, 2, 3]
 * 输出就是[6, 7, null, 3, null]。 我给出了俩解,都是O(n2)的。她希望得到一个O(n)的解
 *
 */
public class FindPeopleHeight {

	public static void main(String[] args) {
		int[] heights = { 3, 6, 7, 2, 3 };
		System.out.println(Arrays.toString(findHeight(heights)));
	}

	/**
	 * 类似那个Leetcode里面的histgram吧. 用一个stack记录从右到左的身高 且保持身高递减的, 从堆栈顶部到底部，应该是一个递减序列
	 * 
	 * 比如例子里面[3,6,7,2,3], 从右往左扫:
	 * 
	 * 3, 因为是最右, 肯定是NULL, 然后把 3放到stack里;
	 * 
	 * 2, 发现stack的top比2大, 于是就是3, 然后把2也放进stack里;
	 * 
	 * 7, 发现2比7小, pop, 发现3 比7小, pop, stack空了 所以是NULL, 然后把7放进stack里;
	 * 
	 * 6, 发现top=7>6, 所以就是7,然后把6放进stack里; 3, 发现top=6>3, 所以就是3, 然后把3放进stack里;
	 * 
	 * 
	 * 时间空间复杂度都是O(n)
	 * 
	 */
	public static Integer[] findHeight(int[] heights) {
		Integer[] result = new Integer[heights.length];
		Stack<Integer> stack = new Stack<>();
		int len = heights.length;
		for (int i = len - 1; i >= 0; i--) {
			if (i == len - 1) {
				result[i] = null;
				stack.push(heights[i]);
			} else {
				// 一次把从栈顶，把比当前元素小的都pop出去, 这些元素用不到了，因为我们只要找，当前元素右边最近的一个比他大的数
				while (!stack.isEmpty() && stack.peek() <= heights[i]) {
					stack.pop();
				}
				if (stack.isEmpty()) {
					result[i] = null;
				} else {
					result[i] = stack.peek();
				}
				// note
				stack.push(heights[i]);
			}
		}
		return result;
	}
}
