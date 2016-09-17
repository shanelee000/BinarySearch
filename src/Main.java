import java.util.*;

/**
 * Created by lx on 2016/6/15.
 */
public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static int index = 0;    //元素序号
    public static int compareTimes = 0; //比较次数


    public static void main(String[] args) {

        //输入数据
        System.out.print("请输入一组数据：");
        String[] stringArray = scanner.next().split(",");
        System.out.print("您输入的数据为：");
        for (String string : stringArray){
            System.out.print(string + ",");
        }
        System.out.println();

        //执行去重
        String[] newArray = makeUnique(stringArray);
        System.out.print("去重后的数据：");
        for (String string : newArray){
            System.out.print(string + ",");
        }
        System.out.println();

        //输入要查找的数据
        System.out.print("请输入要查找的数据：");
        int data = scanner.nextInt();

        //执行二分查找
        index = doSearch(data, newArray, 0, newArray.length-1);
        System.out.println("此数据的索引：" + (index+1));
        System.out.println("比较次数：" + compareTimes);

    }

    /**
     * 数组去重
     * @param stringArray
     * @return
     */
    public static String[] makeUnique(String[] stringArray){
        List<String> temp = new ArrayList<>();
        for (String str : stringArray){
            if(!temp.contains(str)){
                temp.add(str);
            }
        }

        Collections.sort(temp, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (Integer.parseInt(o1) < Integer.parseInt(o2)){
                    return -1;
                } else if (Integer.parseInt(o1) == Integer.parseInt(o2)){
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        String[] newArray = new String[temp.size()];
        for (int i = 0; i < temp.size(); i++){
            newArray[i] = temp.get(i);
        }
        return newArray;
    }

    /**
     * 二分查找
     * @param data
     * @param stringArray
     */
    public static int doSearch(int data, String[] stringArray, int beginIndex, int endIndex){
        //比较次数增加
        compareTimes++;
        int midIndex = (beginIndex + endIndex) / 2;
        //输入参数不合法
        if(data < Integer.parseInt(stringArray[beginIndex])
                || data > Integer.parseInt(stringArray[endIndex])
                || beginIndex > endIndex){
            return -1;
        }
        if(data < Integer.parseInt(stringArray[midIndex])){
            //前半部分查找
            return doSearch(data, stringArray, beginIndex, midIndex-1);
        } else if (data > Integer.parseInt(stringArray[midIndex])) {
            //后半部分查找
            return doSearch(data, stringArray, midIndex+1, endIndex);
        } else {
            //相等，直接返回
            return midIndex;
        }
    }
}
