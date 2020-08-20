package algorithm.math.probability;

/**
 * @Description: 使用蒙特卡洛算法计算圆周率的近似值
 * @Author: matreeix
 * @Date: 2020/8/20
 */

public class CalculatePI {

    /**
     * 数学原理：
     * S1/S2 = 圆面积 /正方形面积 = (Pi*r^2)/(4r^2)=Pi/4
     */
    public static double MonteCarloPI(int points) {
        double PI, x, y;
        int inCircle = 0;
        for (int i = 0; i < points; i++) {
            x = Math.random();
            y = Math.random();
            if ((x * x + y * y) <= 1) inCircle++;
        }
        PI = 4.0 * inCircle / points;
        return PI;
    }

    public static void main(String[] args) {
        System.out.println("PI:" + CalculatePI.MonteCarloPI(1000000000));
    }

}
