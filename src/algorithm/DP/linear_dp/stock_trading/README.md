**状态机原理**

for 状态1 in 状态1的所有取值：
     for 状态2 in 状态2的所有取值：
     for ...
     dp[状态1][状态2][...] = 择优(选择1，选择2...)

     此问题下即是:
     dp[i][k][0 or 1]
     0 <= i <= n-1, 1 <= k <= K
     n 为天数，大 K 为最多交易数
     此问题共 n × K × 2 种状态，全部穷举就能搞定。
     for 0 <= i < n:
     for 1 <= k <= K:
     for s in {0, 1}:
     dp[i][k][s] = max(buy, sell, rest)
     求dp[n - 1][K][0]。

     状态转移方程：
     1.持有股票 dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     2.没有股票 dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])

     base case：
     dp[-1][k][0] = dp[i][0][0] = 0
     dp[-1][k][1] = dp[i][0][1] = -infinity