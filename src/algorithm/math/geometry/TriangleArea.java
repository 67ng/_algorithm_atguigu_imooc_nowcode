package algorithm.math.geometry;

/**
 * @Description: 812. 最大三角形面积
 *  * 给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。
 *  * 注意:
 *  * 1.3 <= points.length <= 50.
 *  * 2.不存在重复的点。
 *  * 3.-50 <= points[i][j] <= 50.
 *  * 4.结果误差值在 10^-6 以内都认为是正确答案。
 * @Created by: matreeix
 * @Date: 2021/5/4
 */
public class TriangleArea {
    public double largestTriangleArea(int[][] points) {
        int N = points.length;
        double ans = 0;
        for (int i = 0; i < N; ++i)
            for (int j = i + 1; j < N; ++j)
                for (int k = j + 1; k < N; ++k)
                    ans = Math.max(ans, area(points[i], points[j], points[k]));
        return ans;
    }

    //鞋带公式
    private double area(int[] A, int[] B, int[] C) {
        return 0.5 * Math.abs(A[0] * B[1] + B[0] * C[1] + C[0] * A[1] - A[1] * B[0] - B[1] * C[0] - C[1] * A[0]);
    }
    public double largestTriangleArea2(int[][] points) {
        double a, b, c;
        double S = 0, temp;
        for (int i = 0; i < points.length - 2; i++) {
            for (int j = 0; j < points.length - 1; j++) {
                for (int k = 0; k < points.length; k++) {
                    a = Math.sqrt((points[i][0] - points[j][0]) * ((points[i][0] - points[j][0])) + (points[i][1] - points[j][1]) * ((points[i][1] - points[j][1])));
                    b = Math.sqrt((points[i][0] - points[k][0]) * ((points[i][0] - points[k][0])) + (points[i][1] - points[k][1]) * ((points[i][1] - points[k][1])));
                    c = Math.sqrt((points[j][0] - points[k][0]) * ((points[j][0] - points[k][0])) + (points[j][1] - points[k][1]) * ((points[j][1] - points[k][1])));
                    temp = getS(a, b, c);
                    if (S < temp) {
                        S = temp;
                    }
                }
            }
        }
        return S;
    }

    //海伦公式
    public double getS(double a, double b, double c) {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

}
