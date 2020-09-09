package algorithm.math.prime;

/**
 * @Description: 欧拉函数
 * @Author: matreeix
 * @Date: 2020/9/1
 */

public class EulerFunction {
    //f(x) = x(1-1/p1)(1-1/p2)(1-1/p3).....(1-1/pn),其中p1, p2……pn为x的所有质因数，x是不为0的整数
    public static int euler(int x) {
        int res = x;
        for (int i = 2; i <= x / i; i++)
            if (x % i == 0) {////如果i是x的质因数
                res = res / i * (i - 1);//提了一个1/i出来，先进行除法是为了防止中间数据的溢出
                while (x % i == 0) x /= i;//欧拉函数只记算一种质因数
            }
        if (x > 1) res = res / x * (x - 1);//如果最后还有剩余因子

        return res;
    }

    //筛法求欧拉函数
    private int primes[], cnt;     //primes[]存储所有素数
    private int euler[];           //存储每个数的欧拉函数
    private boolean st[];          //st[x]存储x是否被筛掉

    public void euler2(int n) {
        primes = new int[n];
        euler[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (!st[i]) {
                primes[cnt++] = i;
                euler[i] = i - 1;
            }
            for (int j = 0; primes[j] <= n / i; j++) {
                int t = primes[j] * i;
                st[t] = true;
                if (i % primes[j] == 0) {
                    euler[t] = euler[i] * primes[j];
                    break;
                }
                euler[t] = euler[i] * (primes[j] - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(euler(10));//1、3、7、9
    }
}












