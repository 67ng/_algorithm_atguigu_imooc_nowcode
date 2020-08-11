package interview.ByteDance._2019;

/**
 * @Description: 找零
 * <p>
 * Z国的货币系统包含面值1元、4元、16元、64元共计四种硬币，以及面值1024元的纸币。
 * <p>
 * 现在小Y使用1024元的纸币购买了一件价值为N的商品，请问最少他会收到多少硬币。
 * @Author: caffebaby
 * @Date: 2020/6/6
 */
public class GetChanges {
    public int getChanges(int n) {
        n = 1024 - n;
        int res = 0;
        int[] coins = {64, 16, 4, 1};//由于是倍数关系可以使用贪心，不然只能使用dp
        for (int i = 0; i < 4; i++) {
            res += n / coins[i];
            n %= coins[i];
        }
        return res;
    }
}
