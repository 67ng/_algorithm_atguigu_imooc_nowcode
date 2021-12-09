这篇文章是对适用于类似[*378. Kth Smallest Element in a Sorted Matrix*](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/)问题的所有常见解决方案的快速总结，其中我们得到一个`n x n`矩阵，其中每个行和列都按升序排序，并且需要在矩阵中找到`kth`最小的元素。仅供参考，以下是一系列 LeetCode 问题，这些问题可以转化为等效于查找*“**有序矩阵中的第 K 个最小元素**”的问题*，因此也可以使用此处的算法有效解决：

1. [373. 找出和最小的 K 对](https://leetcode.com/problems/find-k-pairs-with-smallest-sums/description/)
2. [378. 排序矩阵中的第 K 个最小元素](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/)
3. [668. 乘法表中第 K 个最小的数](https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/description/)
4. [719. 找出第 K 个最小对距离](https://leetcode.com/problems/find-k-th-smallest-pair-distance/description/)
5. [786. 第 K 个最小质数分数](https://leetcode.com/problems/k-th-smallest-prime-fraction/description/)

下面我列出了几个解决方案，作为对即将推出的算法的演示。



**I -- Sorting-based solution**



这是最直接的解决方案，我们将矩阵中的所有元素放入一个数组中并按升序排序，那么矩阵中`kth`最小的元素将是`k-1`数组索引处的元素。代码如下：

```java
public int kthSmallest(int[][] matrix, int k) {
    int n = matrix.length, i = 0;
    
    int[] nums = new int[n * n];
    
    for (int[] row : matrix) {
        for (int ele : row) {
            nums[i++] = ele;
        }
    }
    
    Arrays.sort(nums);
    
    return nums[k - 1];
}
```

时间复杂度：`O(n^2 * log(n^2))`
空间复杂度：`O(n^2)`

正如预期的那样，这个简单的解决方案在时间（由于排序）和空间（由于额外的数组）复杂性方面的性能不是很好，所以让我们看看如何改进它并推导出更有效的算法。



**II -- PriorityQueue-based solution**



这里有个需要注意的地方是，我们不需要跟踪所有`n^2`元素，因为只需要求矩阵中的第k小的元素。我们可以维护一个`PriorityQueue`且大小为 `k`，以跟踪矩阵中的第k小的元素。最后，矩阵中的第k小的元素即是`PriorityQueue`中的最大的元素。这是这个想法的一个实现：

```java
public int kthSmallest(int[][] matrix, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    
    for (int[] row : matrix) {
        for (int ele : row) {
            pq.offer(ele);
            
            if (pq.size() > k) {
                pq.poll();
            }
        }
    }
    
    return pq.peek();
}
```

时间复杂度：`O(n^2 * logk)`
空间复杂度：`O(k)`

虽然在最坏的情况下（`k = n^2`），该解决方案的时间和空间复杂度和上述的解决方案一样的，但在平均的情况下，时间复杂度有轻微的性能提升，因为其中`k`普遍都小于`n^2`。



**III -- PriorityQueue-based solution with optimization**



到目前为止，您可能会注意到上述两种解决方案实际上适用于任意矩阵（无论其行或列是否已排序，它们都会找到矩阵中第k小的元素）。如果我们利用矩阵的排序属性会发生什么？



好吧，我还没有提到另一种寻找`kth`矩阵中最小元素的直接方法：如果我们`k-1`多次从矩阵中移除最小元素，那么移除之后，现在矩阵中的`kth`最小元素将是我们现在的最小元素寻找。



如果矩阵中的元素按随机顺序排列，除了将所有元素添加到 a 之外，我们没有更好的方法来查找和删除矩阵中的最小元素`PriorityQueue`。这将产生的时间（和空间）复杂度不会比朴素解决方案的时间（和空间）复杂度更好，甚至更差。但是，在对矩阵的行（或列）进行排序后，我们不必`PriorityQueue`一次将所有元素添加到。相反，我们可以创建一个候选元素池，只要我们可以确保它包含矩阵的最小元素（即使在删除之后）。



假设行按升序排序，这意味着最初矩阵的最小元素必须在第一列内，因此可以使用第一列中的元素初始化池。现在，当我们从池中提取和移除最小元素时，我们需要补充更多候选元素。这里的关键观察是，对于每个提取的元素，只需将同一行中紧随其后的元素添加到池中就足够了，而不会违反池始终包含矩阵中最小元素的特性（即使在删除之后）。下面是基于这个想法的解决方案：

```java
public int kthSmallest(int[][] matrix, int k) {
    PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
        public int compare(int[] a, int[] b) { // (a[0], a[1]) and (b[0], b[1]) are positions in the matrix
            return Integer.compare(matrix[a[0]][a[1]], matrix[b[0]][b[1]]);
        }
    });
    
    int n = matrix.length;
    
    for (int i = 0; i < n; i++) {
        pq.offer(new int[] {i, 0});  // initialize the pool with elements from the first column
    }
    
    while (--k > 0) {                // remove the smallest elements from the matrix (k-1) times
        int[] p = pq.poll();
        
        if (++p[1] < n) {
            pq.offer(p);             // add the next element in the same row if it exists
        }
    }
    
    return matrix[pq.peek()[0]][pq.peek()[1]];
}
```

时间复杂度：`O(max(n, k) * logn)`
空间复杂度：`O(n)`

请注意，通过利用矩阵的排序属性，我们能够将空间复杂度降低到`O(n)`与朴素解决方案相比略好的时间复杂度（尽管最坏的情况是相同的）。



**IV -- BinarySearch-based solution**



二分搜索解决方案本质上是我另一篇[文章中](https://leetcode.com/problems/find-k-th-smallest-pair-distance/discuss/109082/Approach-the-problem-using-the-"trial-and-error"-algorithm)描述的更通用的“试错法”算法的一个特例，我在那里针对`LeetCode 719`总结了该算法的关键思想，我建议您至少阅读开头部分，以熟悉将在此处使用的术语。

**候选解**：在这种情况下，候选解只是一个整数。

**搜索空间**：在这种情况下，搜索空间由 给出`[MIN, MAX]`，其中`MIN`和`MAX`分别是矩阵中的最小和最大元素。

**遍历方法**：在这种情况下，我们可以进行二分搜索，因为搜索空间是按升序自然排序的（这也说明了“基于二进制搜索的解决方案”这个名称）。

**验证算法**：在这种情况下，验证算法是通过比较矩阵元素的个数来实现**小于或等于**候选解，表示为`cnt`，与排名`k`：如果`cnt < k`，我们扔掉的搜索空间的左半; 否则我们丢弃右半部分。

请注意，验证算法基于以下两个观察结果：

1. 有**至少** `k`个素数是元素*小于或等于*所述`kth`最小元素。
2. 如果矩阵中**至少有** `k`元素*小于或等于*候选解，则`kth`矩阵中实际最小的元素*不能大于*该候选解。

此外，通过采用利用矩阵排序特性的经典双指针技术，可以在线性时间内完成不大于候选解的元素计数。以下是该算法的解决方案：

```java
public int kthSmallest(int[][] matrix, int k) {
    int n = matrix.length;
    
    int l = matrix[0][0];               // minimum element in the matrix
    int r = matrix[n - 1][n - 1];       // maximum element in the matrix
    
    for (int cnt = 0; l < r; cnt = 0) { // this is the binary search loop
        int m = l + (r - l) / 2;
        
        for(int i = 0, j = n - 1; i < n; i++)  {
            while (j >= 0 && matrix[i][j] > m) j--;  // the pointer j will only go in one direction
            cnt += (j + 1);
        }
        
        if (cnt < k) {
            l = m + 1;   // cnt less than k, so throw away left half
        } else {
            r = m;       // otherwise discard right half
        }
    }
    
    return l;
}
```

时间复杂度：`O(n * log(MAX - MIN))`
空间复杂度：`O(1)`

与前几节得出的解决方案相比，二分搜索解决方案要高效得多。我们只使用常数空间，对于任何的整数矩阵（其中`MIN`和`MAX`都在`32-bit`整数范围内），时间复杂度几乎是线性的。



**V -- ZigzagSearch-based solution**



“之”字形搜索解决方案是更通用的“试错”算法的另一个特殊版本，其中现在搜索空间仅由包含在输入矩阵本身中的整数组成，而不是范围内的连续整数`[MIN, MAX]`。我们可以总结此解决方案的以下属性：

**候选解**：在这种情况下，候选解也是一个整数，但它必须是输入矩阵的一个元素。

**搜索空间**：在这种情况下，搜索空间由输入矩阵本身给出。

**遍历方法**：在这种情况下，我们不能进行二分查找，因为候选解没有完全排序。但是，我们确实有部分排序，其中矩阵的每一行和每一列都按升序排序。这使我们能够进行 zigzag 搜索，从矩阵的右上角开始，然后根据验证算法的结果继续到下一行或上一列（我们也可以从左下角开始继续前一行或下一列）。

**验证算法**：在这种情况下，验证算法是通过比较两个计数来实现的，分别表示为`cnt_lt`和 ，`cnt_le`分别表示为`k`：如果`cnt_le < k`，我们继续下一行；else if `cnt_lt >= k`，我们继续上一列；否则我们已经找到了`kth`矩阵中的最小元素，所以返回它。这里`cnt_lt`表示矩阵中**小于**候选解`cnt_le`的元素个数，而表示矩阵中**小于或等于**候选解的元素个数（我们需要两次计数的原因是输入矩阵中可能存在重复） .

请注意，验证算法基于以下两个观察结果：

1. 会有**至多** `k - 1`在矩阵是元件*小于*所述`kth`最小元素。
2. 会有**至少** `k`在基质是元素*小于或等于*所述`kth`最小元素。

再次使用经典的两点技术可以在线性时间内完成计数。下面是这个算法的代码：

```java
public int kthSmallest(int[][] matrix, int k) {
    int n = matrix.length;
    
    int row = 0;          // we start from the upper-right corner
    int col = n - 1;      
    
    for (int cnt_le = 0, cnt_lt = 0; true; cnt_le = 0, cnt_lt = 0) {
        for (int i = 0, j = n - 1, p = n - 1; i < n; i++) {
            while (j >= 0 && matrix[i][j] > matrix[row][col]) j--;    // pointer j for counting cnt_le
            cnt_le += (j + 1);
            
            while (p >= 0 && matrix[i][p] >= matrix[row][col]) p--;   // pointer p for counting cnt_lt
            cnt_lt += (p + 1);
        }
        
        if (cnt_le < k) {         // candidate solution too small so increase it
            row++; 
        } else if (cnt_lt >= k) { // candidate solution too large so decrease it
            col--;
        } else {                  // candidate solution equal to the kth smallest element so return
            return matrix[row][col];
        }
    }
}
```

时间复杂度：`O(n^2)`
空间复杂度：`O(1)`

zigzag 搜索解决方案与二分搜索解决方案相比效率较低，但与其他三种解决方案相比仍然实现了显着的性能提升。



**VI -- BiSelect solution**



这是解决此类问题的最先进算法，可实现**线性时间**复杂度。主要理论是基于[*该论文*](http://www.cse.yorku.ca/~andy/pubs/X+Y.pdf)提出的思想。下面是Java的一个实现，非常冗长。我会向您[*推荐 stefanpochmann 的*](https://discuss.leetcode.com/user/stefanpochmann)这篇很棒的[文章](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/85170/on-from-paper-yes-orows)，以获得更简洁的 Python 版本，以及[zhiqing_xiao 的](https://leetcode.com/zhiqing_xiao/)这篇[文章](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/85201/c-on-time-on-space-solution-with-detail-intuitive-explanation)以获得更多解释（以及 C++ 的解决方案）。这个 BiSelect 算法很有趣，但可能不适合面试，所以你可以选择跳过它并继续。

```java
public int kthSmallest(int[][] matrix, int k) {
    int n = matrix.length;
		
    int[] index = new int[n];
	    
    for (int i = 0; i < n; i++) {
        index[i] = i;
    }
   
    int[] L = new int[12 * n];
        
    return biselect(matrix, index, k, k, L)[0];
}

private int[] biselect(int[][] matrix, int[] index, int k1, int k2, int[] L) {
    int n = index.length;
        
    if (n <= 2) {
        int[] nums = new int[n * n];
            
        for (int i = 0, k = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nums[k++] = matrix[index[i]][index[j]];
            }
        }
            
        Arrays.sort(nums);
            
        return new int[] {nums[k1 - 1], nums[k2 - 1]};
    }
        
    int[] index_ = new int[(n + 1) / 2 + (n - 1) % 2];
    int k1_ = 0;
    int k2_ = (k2 + 3) / 4;
        
    for (int i = 0, k = 0; i < n; i += 2) {
        index_[k++] = index[i];
    }
        
    if (n % 2 == 0) {
        index_[index_.length - 1] = index[n - 1];
        k1_ = (k1 + 3) / 4 + n + 1;
    } else {
        k1_ = (k1 + 2 * n) / 4 + 1;
    }
        
    int[] pair = biselect(matrix, index_, k1_, k2_, L);
    int a = pair[0], b = pair[1];
    int ra_less = 0, rb_more = 0;
        
    int Len = 0;
        
    for (int i = 0, ja = n, jb = n; i < n; i++) {
        while (ja > 0 && matrix[index[i]][index[ja - 1]] >= a) ja--;
        ra_less += ja;
            
        while (jb > 0 && matrix[index[i]][index[jb - 1]] > b) jb--;
        rb_more += n - jb;
            
        for (int j = jb; j < ja; j++) {
            L[Len++] = matrix[index[i]][index[j]];
        }
    }
        
    int x = 0, y = 0;
        
    if (ra_less <= k1 - 1) {
        x = a;
    } else if (k1 + rb_more - n * n <= 0) {
        x = b;
    } else {
        x = L[pick(L, 0, Len, k1 + rb_more - n * n)];
    }
        
    if (ra_less <= k2 - 1) {
        y = a;
    } else if (k2 + rb_more - n * n <= 0) {
        y = b;
    } else {
        y = L[pick(L, 0, Len, k2 + rb_more - n * n)];
    }
        
    return new int[] {x, y};
}
	
private int pick(int[] nums, int l, int r, int k) {
    int[] pos = partition(nums, l, r, medianOfMedians(nums, l, r));
        
    int p = pos[0], q = pos[1];
        
    if (q - l < k) {
        return pick(nums, q, r, k - (q - l));
    } else if (k <= p - l) {
        return pick(nums, l, p, k);
    } else {
        return p;
    }
}
    
private int[] partition(int[] nums, int l, int r, int pos) {
    int pivot = nums[pos];
    swap(nums, pos, r - 1);
        
    int p = l, q = r - 1;
        
    for (int i = l; i < q;) {
        if (nums[i] < pivot) {
            swap(nums, p++, i++);
        } else if (nums[i] > pivot) {
            swap(nums, i, --q);
        } else {
            i++;
        }
    }
        
    swap(nums, q++, r - 1);
        
    return new int[] {p, q};
}
    
private int medianOfMedians(int[] nums, int l, int r) {
    if (r - l <= 5) return medianOfFive(nums, l, r);
        
    int rr = l;
        
    for (int i = l; i < r; i += 5) {
        swap(nums, rr++, medianOfFive(nums, i, Math.min(i + 5,  r)));
    }
        
    return pick(nums, l, rr, (rr - l + 1) / 2);
}
    
private int medianOfFive(int[] nums, int l, int r) {
    Arrays.sort(nums, l, r);
    return l + (r - l - 1) / 2;
}
    
private void swap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
}
```

时间复杂度：`O(n)`
空间复杂度：`O(n)`



**VII -- Transform other problems into "Kth Smallest Element in a Sorted Matrix"**



对于“排序矩阵中的第 K 个最小元素”以外的问题，要应用上面开发的算法，我们需要重新表述并将它们转换为一个等效于查找`Kth Smallest Element in a Sorted Matrix`. 我们是否可以这样做取决于问题的性质，但至少对于帖子开头列出的那些是正确的。

现在转换过程的关键是根据问题输入构建排序矩阵。一方面，我们需要将每个矩阵元素`matrix[i][j]`与问题输入相关联。另一方面，我们需要排列这些元素，以便矩阵的每一行和每一列都按升序排列。同样，这些细节将取决于问题的性质。但就这篇文章而言，我将假设输入是两个按升序排序的数组，分别表示为`a`和`b`，并且可以使用两个数组元素的算术运算来计算矩阵元素。

1. 添加： `matrix[i][j] = a[i'] + b[j']`
2. 减法： `matrix[i][j] = a[i'] - b[j']`
3. 乘法： `matrix[i][j] = a[i'] * b[j']`
4. 分配： `matrix[i][j] = a[i'] / b[j']`

请注意，索引映射`i --> i'`和`j --> j'`不一定是身份映射，而是将选择它们以确保矩阵中的行和列按升序排序。为简单起见，我们将其命名两点式这里映射它们的*身份映射*（其中`i' = i`和`j' = j`）和*“负”的映射*（其中`i' = na - 1 - i`和`j' = nb - 1 - j`，用`na`，`nb`是两个阵列的长度）。

接下来我将重新表述 LeetCode 问题`378`, `668`,`719`和`786`作为示例来展示如何完成转换：

1.[373. Find K Pairs with Smallest Sums](https://leetcode.com/problems/find-k-pairs-with-smallest-sums/description/)：在这种情况下，两个输入数组是`nums1`and `nums2`，因此`a`将引用`nums1`and`b`到`nums2`。我们有`matrix[i][j] = nums1[i] + nums2[j]`，其中为行和列索引选择标识映射。请注意，对于这个问题，我们需要找到`K`总和最小的所有对，因此优化的 PriorityQueue 解决方案将是最好的尝试。

2.[668. 乘法表中的第 K 个最小数](https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/description/)：在这种情况下，只给出两个数组的长度作为输入，但获取数组元素 ( `a[i] = i + 1`, `b[j] = j + 1`) 很简单。我们有`matrix[i][j] = a[i] * b[j]`，再次为行和列索引选择身份映射。的`Kth`在乘法表最小元素将是一样的`Kth`矩阵中的最小元素。

3.[719.查找第k小的距离对](https://leetcode.com/problems/find-k-th-smallest-pair-distance/description/)：在这种情况下，只有一个阵列`nums`作为输入，所以既`a`和`b`将参考`nums`。排序后`nums`按升序排列，我们有`matrix[i][j] = nums[i] - nums[n - 1 - j]`，在那里`n = nums.length`。请注意，列索引的映射选择为“负”，以确保列按升序排序。还要注意，我们的矩阵将包含所有对距离（包括负距离），而原始问题要求`Kth`绝对对距离中的最小对距离（有`n(n-1)/2`这样的对）。所以我们需要将等级向上移动到`K' = K + n(n+1)/2`。所述`Kth`然后对最小距离将是`K'th`在基质中的最小元素。

4.[786.第K个最小的](https://leetcode.com/problems/k-th-smallest-prime-fraction/description/)：在这种情况下再次，只有一个阵列`A`作为输入，所以既`a`和`b`将参考`A`。我们有`matrix[i][j] = nums[i] / nums[n - 1 - j]`, 其中`n = A.length`和`/`是浮点除法（不是整数除法）。再次选择列索引的映射为“负”以确保列按升序排序。在`Kth`随后最小素部分将是`Kth`在基体中最小的元素。

请注意，矩阵可能存在特定于特定问题的其他属性，例如矩阵是否包含重复项、矩阵元素是否为整数等。这些额外的属性可能有助于优化之前开发的算法。