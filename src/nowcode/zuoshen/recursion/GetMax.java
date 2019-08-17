package nowcode.zuoshen.recursion;

/**
 * Description:
 *
 * @author: 黑山老妖
 * @date: 2018/10/12 22:24
 */
//          递归时间复杂度:T(n) = a*T(n/b) + O(n^d),下例中a=b=2,d=0
public class GetMax {
    public static int getMax(int[] arr, int l,int r){
        if (l == r)return arr[l];
        int mid = (l+r)/2;
        int maxLeft = getMax(arr, l, mid);
        int maxRight = getMax(arr, mid+1, r);
        return Math.max(maxLeft, maxRight);
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,2,213,412,34,123,4,4};
        System.out.println(getMax(arr, 0, 9));
    }
}
