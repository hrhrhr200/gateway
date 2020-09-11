package com.example.gateway.demo4;

import com.alibaba.fastjson.JSON;
import com.baitao.common.response.R;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Created by hrhrh on 2020/8/31 15:17
 */
public class AlgorithmDemo {

    /**
     * 环形问题 判断链表是否有环
     * 解题思路 ：1.追逐解法  创建两个指针p1 p2，让他们同时指向一个链表的头结点,
     * 然后开始一个大循环,在循环体中,让p11格1格的走,p2 两格两格的走
     */
    public static Boolean cycle(Node head) {
        Node p1 = head;
        Node p2 = head;

        while(null != p2 && null != p2.next) {
            p1 = p1.next;
            p2 = p2.next.next;

            if(p1.value.equals(p2.value)) {
                return true;
            }
        }

        return false;
    }


    @Data
    private static class Node {

        private Integer value;

        private Node next;

        public Node(Integer value) {
            this.value = value;
        }
    }


    public static void main(String[] args) {
        //链表环问题
        Node node1 = new Node(5);
        Node node2 = new Node(3);
        Node node3 = new Node(7);
        Node node4 = new Node(2);
        Node node5 = new Node(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2;

        System.out.println(cycle(node1));

        //最小栈问题
        push(4);
        push(6);
        push(7);
        push(9);
        push(11);
        push(2);
        push(4);
        push(8);

        System.out.println(getMin());

        pop();
        pop();
        pop();
        pop();
        pop();

        System.out.println(getMin());

        //非排序数组相邻最大差值
        int[]array = new int[]{2,4,9,33,21,5,8,2,6};
        System.out.println(getMaxSortedDistance(array));

        //寻找全排列的下一个数
        int[]array2 = new int[]{1,7,5,4,3};
        System.out.println(JSON.toJSONString(findNearestNumber(array2)));

        //清除k次的最小数
        System.out.println(removeKdigits2("541270936",3));
        System.out.println(removeKDigits("541270936",3));

        //大整数相加
        System.out.println(plus("426709752318","95481253129"));

    }

    /**
     * 最小栈问题  实现一个栈，该栈的出栈，入栈，取最小元素时间复杂度都为O(1)
     * 解题思路 1.设原有栈叫作栈A，此时创建一个额外的备胎栈B,用于辅助栈A
     *          2.当第1个元素进入栈A时,让新元素也进入栈B。这个唯一的元素是栈A的当前最小值
     *          3.之火，每当新元素进入栈A时，比较新元素和栈A当前最小值的大小,如果小于栈A当前最小值，则让进入栈B，此时栈B的栈顶元素
     *          就是最小值
     *          4.每当栈A有元素出栈时，如果出栈元素是栈A最小值，栈B栈顶元素出栈。栈B新栈顶为最小值
     *          5.getMin() 时 栈B栈顶出栈
     */

    private static Stack<Integer> mainStack = new Stack<>();
    private static Stack<Integer> minStack = new Stack<>();

    public static void push(Integer data) {
        mainStack.push(data);
        if(mainStack.isEmpty() || minStack.empty()) {
            minStack.push(data);
        }else {
            Integer pop = minStack.peek();
            if(data < pop) {
                minStack.push(data);
            }
        }
    }

    public static void pop() {
        Integer pop = mainStack.pop();
        if(!minStack.empty() && minStack.peek().equals(pop)) {
            minStack.pop();
        }
    }

    public static Integer getMin() {
        return minStack.empty() ? null : minStack.peek();
    }


    /**
     * 最大公约数
     *  解题思路 ： 1.辗转相除法 两个正整数大数取余数 和小数的最大公约数也是大数和小数的最大公约数
     *  2.更相减损数 两个正整数 大数减小数的值和小数的最大公约数也是大数和小数的最大公约数
     *  3.移位法  当a ,b 均为偶数时 gcb(a,b) = 2 * gcb(a/2, b/2) = 2 * gcb(a>>1, b>>1)
     *              当a为奇数，b为偶数 gcb(a, b) = gcb(a, b/2) = gcb(a, b>>1)
     *              当a为偶数，b为奇数 gcb(a, b) = gcb(a/2, b) = gcb(a>>1, b)
     *              当a b都为奇数,先减一次后可继续移位
     */

    public static int gcb(int a, int b) {
        if(a == b) {
            return a;
        }

        if((a&1) == 0 && (b&1) == 0) {
            return gcb(a>>1, b>>1)<<1;
        }else if((a&1) ==0 && (b&1) != 0) {
            return gcb(a>>1, b);
        }else if((a&1) != 0 && (b&1) == 0) {
            return gcb(a, b>>1);
        }else {
            int big = Math.max(a, b);
            int small = Math.min(a, b);
            return gcb(small, big - small);
        }
    }


    /**
     * 判断一个数是2的整数次幂
     * 解题思路: 原数转化为二进制后与原数减1转化为二进制的数进行与运算结果为0 则是2的倍数
     */
    public static boolean isPowerOf2(int num) {
        return (num & num-1) == 0;
    }

    /**
     * 无序数组排序后的最大相邻差
     * 解题思路 ：1.利用桶排序的思想，根据原数组的长度n，创建出n个桶，每一个桶代表一个区间范围。其中第1个桶从原数组的最小值min开始，区间跨度是(max-min)/(n-1).
     *      2.遍历原数组,把原数组每一个元素插入到对应的桶中，记录每一个桶的最大和最小值。
     *      3.遍历所有的桶，统计出每一个桶的最大值，和这个桶右侧非空桶的最小值的差，数值最大的差即为原数组排序后的相邻最大差
     */
    public static int getMaxSortedDistance(int[] array) {
        //1.得到最大最小值
        int max = array[0];
        int min = array[0];

        for (int d : array) {
            if(d > max) {
                max = d;
            }
            if(d < min) {
                min = d;
            }
        }

        int a = max - min;
        if(a == 0) {
            return 0;
        }

        //2.初始化桶
        Bucket[] buckets = new Bucket[array.length];
        for(int i = 0; i < array.length; i++) {
            buckets[i] = new Bucket();
        }

        //3.桶放值
        for(int d : array) {
            int index = d * (array.length - 1) / a;

            if(buckets[index].min == null || d < buckets[index].min) {
                buckets[index].min = d;
            }

            if(buckets[index].max == null || d > buckets[index].max) {
                buckets[index].max = d;
            }
        }

        //4.比最大差值
        Integer max1 = buckets[0].max;
        int distance = 0;
        for(int i = 1;i<buckets.length;i++) {
            if(buckets[i].min != null && buckets[i].min - max1 > distance) {
                distance = buckets[i].min - max1;
            }
            if(buckets[i].max != null) {
                max1 = buckets[i].max;
            }
        }

        return distance;
    }

    private static class Bucket {
        private Integer max;
        private Integer min;
    }


    /**
     * 栈实现队列
     *  解题思路:1.用两个栈 一个栈入 一个栈出
     */

    private static Stack<Integer> stackA = new Stack<>();
    private static Stack<Integer> stackB = new Stack<>();

    public static void enQueue(int element) {
        stackA.push(element);
    }

    public static void transfer() {
        while (!stackA.isEmpty()) {
            stackB.push(stackA.pop());
        }
    }

    public static Integer deQueue() {
        if(stackB.isEmpty()) {
            if(stackA.isEmpty()) {
                return null;
            }
            transfer();
        }

        return stackB.pop();
    }

    /**
     * 寻找全排列的下一个数
     *  1.从后向前查看逆序区域，找到逆序区域的前一位，也就是数字置换的边界。
     *  2.让逆序区域的前一位和逆序区域中大于它的最小的数字交换位置。
     *  3.把原来的逆序区域转为顺序状态。
     *  字典序算法 时间复杂度O(n) 空间复杂度O(n)
     */
    public static int[] findNearestNumber(int[] numbers) {
        int index = 0;
        for(int i = numbers.length - 1; i>0;i--) {
            if(numbers[i] > numbers[i - 1]) {
                index = i;
                break;
            }
        }

        //如果数字置换边界为0，说明整个数组已经逆序，无法等到更大的相同数
        if(index == 0) {
            return null;
        }

        int head = numbers[index - 1];
        for(int i = numbers.length - 1;i>0;i--) {
            if(head < numbers[i]) {
                numbers[index - 1] = numbers[i];
                numbers[i] = head;
                break;
            }
        }

        //逆序
        for(int i = index, j = numbers.length -1 ;i<j;i++,j--) {
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }

        return numbers;
    }


    /**
     * 删除K个数字后的最小数
     * 解题思路 把原整数所有数字从左到右进行比较,如果发现某一位数字大于它后面的数字,那么在删除该数字后,必然会使该数值降低
     *  这样依次求得局部最优解,最终得到全局最优解的思想,叫作贪心算法
     */
    public static String removeKDigits(String num, int k) {
        long l = System.currentTimeMillis();
        for(int i = 0; i<k ; i++) {
            //boolean hasCut = false;
            for(int j = 0;j<num.length()-1;j++) {
                if(num.charAt(j) > num.charAt(j+1)) {
                    num = num.substring(0, j) + num.substring(j+1);
                    //hasCut = true;
                    break;
                }
            }
        }
        System.out.println(System.currentTimeMillis() - l);
        //清0
        return Long.toString(Long.parseLong(num));
    }

    /**
     * 贪心算法优化
     * 上面方法每一次内层循环都需要从头开始遍历所有的数组
     * subString() 方法本身性能不高
     */
    public static String removeKdigits2(String num, int k) {
        long l = System.currentTimeMillis();
        Stack<Character> stack = new Stack<>();
        for(int i = 0;i<num.length();i++) {
            if(k ==0 || stack.isEmpty()) {
                stack.push(num.charAt(i));
                continue;
            }

            if(num.charAt(i) < stack.peek()) {
                stack.pop();
                stack.push(num.charAt(i));
                k--;
            }else {
                stack.push(num.charAt(i));
            }
        }
        StringBuilder s = new StringBuilder();
        stack.forEach(s::append);
        System.out.println(System.currentTimeMillis() - l);
        return Long.toString(Long.parseLong(s.toString()));
    }

    /**
     * 大整数相加
     * 解题思路：
     *  1.创建两个整型数组,数组长度是较大整数的位数+1.把每一个整数倒序存储到数组中,整数的个位存于数组下标为0的位置，最高位存于数组的尾部。之所以是倒序
     *  是因为这样更符合从左到右访问数组的习惯
     *  2.创建结果数组，结果数组的长度同样是较大整数的位数+1，+1的目的很明显，是给高位进位预留的。
     *  3.遍历两个数组，从左到右按照对应下标把元素两两相加，就像竖式计算一样
     */

    public static String plus(String numberA, String numberB) {
        //1
        int i = numberB.length() > numberA.length() ? numberB.length() + 1 : numberA.length() + 1;
        int[] array1 = new int[i];

        for(int j = 0;j<numberA.length();j++) {
            array1[j] = numberA.charAt(numberA.length() - j - 1) - '0';
        }
        int[] array2 = new int[i];

        for(int j = 0;j<numberB.length();j++) {
            array2[j] = numberB.charAt(numberB.length() - j - 1) - '0';
        }

        //2
        int[] result = new int[i];

        //3
        for(int j = 0;j<i;j++) {
            int num = array1[j] + array2[j];
            if(num >= 10) {
                result[j] = result[j] + num - 10;
                result[j + 1] = 1;
            }else {
                result[j] = result[j] + num;
            }
        }

        StringBuilder s = new StringBuilder();

        for (int j = result.length - 1;j>=0;j--) {
            if(j == result.length - 1 && result[j] == 0) {
                continue;
            }
            s.append(result[j]);
        }

        return s.toString();
    }

    /**
     * 挖矿问题寻最优解
     * 动态规划问题 局部最优子结构
     * @param w 工人数
     * @param n 金矿数
     * @param p 金矿开采所需的工人数量
     * @param g 金矿储量
     */
    public static int getBestGoldMining(int w, int n, int[] p, int[] g) {
        //没有工人或者没有矿可以挖的时候，收益为0
        if(w == 0 || n == 0) {
            return 0;
        }

        //当工人数量不足挖当前矿时，跳过该矿往下个矿走
        if(w < p[n-1]) {
            return getBestGoldMining(w, n-1,p,g);
        }

        return Math.max(getBestGoldMining(w, n-1, p, g), getBestGoldMining(w-p[n-1], n-1, p,g));
    }

    /**
     * 金矿解法二维数组解法
     * F(n,w) = max(F(n-1,w), F(n-1,w-p[n-1])+g[n-1]) (n>1,w>=p[n-1])
     */
    public static int getBestGoldMining2(int w, int[]p, int[]g) {
        //创建表格
        int[][] resultTable = new int[g.length+1][w+1];
        for(int i = 1;i<=g.length;i++) {
            for(int j = 1;j<=w;j++) {
                if(j<p[i-1]) {
                    resultTable[i][j] = resultTable[i-1][j];
                }else {
                    resultTable[i][j] = Math.max(resultTable[i-1][j], resultTable[i-1][j-p[i-1]] + g[i-1]);
                }
            }
        }

        return resultTable[g.length][w];
    }

    /**
     * 金矿解法一维数组解法
     */
    public static int getBestGoldMining3(int w, int[]p, int[]g) {
        int[] results = new int[w+1];
        for(int i = 1;i<=g.length;i++) {
            for(int j=w;j>=1;j--) {
                if(j>=p[i-1]) {
                    results[j] = Math.max(results[j], results[j-p[i-1]] + g[i-1]);
                }
            }

        }
        return results[w];
    }

    /**
     * 寻找缺失的整数
     * 解题思路 把2个出现了奇数次的整数命名为A和B。遍历整个数组，然后依次做异或运算，进行异或运算的最终结果，等同于A和B进行异或
     */
    public static int[] findLostNum(int[] array) {

        //用于存储2个出现奇数次的整数
        int[] result = new int[2];

        //第1次进行整体异或运算
        int xorResult = 0;
        for (int item : array) {
            xorResult ^= item;
        }
        //如果进行异或运算的结果为0，则说明输入的数组不符合题目要求
        if(xorResult == 0) {
            return null;
        }

        //确定2个整数的不同位,以此来做分组
        int separator = 1;
        while (0 == (xorResult&separator)) {
            separator = separator<<1;
        }

        //第2次分组进行异或运算
        for (int value : array) {
            if (0 == (value & separator)) {
                result[0] ^= value;
            } else {
                result[1] ^= value;
            }
        }

        return result;
    }


    /**
     * 红包拆分算法
     *  二倍均值法
     *  假设剩余的红包金额为m元,剩余人数为n，那么有如下公式
     *  每次抢到的金额 = 随机区间[0.01, m/n*2-0.01]元
     *  这个公式,保证了每次随机金额的平均值是相等
     */
    public static List<Integer> divideRedPackage(Integer totalAmount, Integer totalPeopleNum) {
        List<Integer> amountList = new ArrayList<>(totalPeopleNum);
        Integer restAmount = totalAmount;
        Integer restTotalPeopleNum = totalPeopleNum;
        Random random = new Random();
        for(int i = 0;i<totalPeopleNum - 1;i++) {
            int amount = random.nextInt(restAmount / restTotalPeopleNum * 2 - 1) + 1;
            restAmount -= amount;
            restTotalPeopleNum--;
            amountList.add(restAmount);
        }

        amountList.add(restAmount);
        return amountList;

    }

    /**
     * 找寻最优路径
     * A*寻路算法
     * 解题思路
     *  openList 可到达的格子  closeList 已到达的格子
     *  F = G + H
     * G 从起点走到当前格子的成本,也就是已经花费了多少步
     * H 在不考虑障碍的情况下,从当前格子走到目标格子的距离,也就是离目标还有多远
     * F G和H的综合评估,也就是从起点到达当前格子,再从当前各自到达目标格子的总步数
     */

    public static final int[][] MAZE = {
            {0,0,0,0,0,0,0},
            {0,0,0,1,0,0,0},
            {0,0,0,1,0,0,0},
            {0,0,0,1,0,0,0},
            {0,0,0,0,0,0,0},
    };

    private static class Grid {
        public int x;
        public int y;
        public int f;
        public int g;
        public int h;
        public Grid parent;

        public Grid(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void initGrid(Grid parent, Grid end) {
            this.parent = parent;
            if(parent != null) {
                this.g = parent.g+1;
            }else {
                this.g = 1;
            }
            this.h = Math.abs(this.x - end.x) + Math.abs(this.y - end.y);

            this.f = this.g + this.h;
        }
    }

    private static boolean containGrid(List<Grid> grids, int x, int y) {
        for (Grid grid : grids) {
            if(grid.x == x && grid.y == y) {
                return true;
            }
        }

        return false;
    }

    private static boolean isValidGrid(int x,int y, List<Grid> openList, List<Grid> closeList) {
        //是否超边界
        if(x<0 || x>=MAZE.length || y<0 || y>= MAZE[0].length) {
            return false;
        }

        //是否有障碍物
        if(MAZE[x][y] == 1) {
            return false;
        }

        //是否已经在openList中
        if(containGrid(openList, x,y)) {
            return false;
        }

        //是否已经在closeList中
        if(containGrid(closeList, x, y)) {
            return false;
        }

        return true;
    }

    private static ArrayList<Grid> findNeighbors(Grid grid, List<Grid> openList, List<Grid> closeList) {
        ArrayList<Grid> grids = new ArrayList<>();

        if(isValidGrid(grid.x - 1, grid.y, openList, closeList)) {
            grids.add(new Grid(grid.x - 1, grid.y));
        }

        if(isValidGrid(grid.x + 1, grid.y, openList, closeList)) {
            grids.add(new Grid(grid.x + 1, grid.y));
        }

        if(isValidGrid(grid.x , grid.y - 1, openList, closeList)) {
            grids.add(new Grid(grid.x , grid.y - 1));
        }

        if(isValidGrid(grid.x, grid.y + 1, openList, closeList)) {
            grids.add(new Grid(grid.x, grid.y + 1));
        }

        return grids;
    }

    private static Grid findMinGrid(ArrayList<Grid> grids) {
        Grid minGrid = grids.get(0);
        for (Grid grid : grids) {
            if(grid.f < minGrid.f) {
                minGrid = grid;
            }
        }

        return minGrid;
    }

    public static Grid aStarSearch(Grid start, Grid end) {
        ArrayList<Grid> openList = new ArrayList<>();
        ArrayList<Grid> closeList = new ArrayList<>();

        //加起点
        openList.add(start);

        //主循环，每一轮检查1个当前方格节点
        while (openList.size() > 0) {
            //找下一步走的节点
            Grid minGrid = findMinGrid(openList);
            //去除该节点
            openList.remove(minGrid);

            //加入路径集合
            closeList.add(minGrid);

            //找相邻节点
            ArrayList<Grid> grids = findNeighbors(minGrid, openList, closeList);

            for (Grid grid : grids) {
                if(!openList.contains(grid)) {
                    grid.initGrid(minGrid, end);
                    openList.add(grid);
                }
            }

            //如果终点节点在openList中,则直接返回
            for (Grid grid : openList) {
                if(grid.x == end.x && grid.y == end.y) {
                    return grid;
                }
            }
        }

        //openList用尽还没找到终点节点，说明终点节点不可达
        return null;
    }
}
