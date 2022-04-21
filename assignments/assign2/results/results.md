# Task 1

## 1.

The transient phase is about 150-200 seconds long

## 2.

About 10 seconds 

## 3.

Around 300-400 seconds


## 4.

For the system we want the steady state solution and therefore need to throw away the transient phase. Looking at the graph we see that only a small amount of samples need to be thrown (10).


1000 runs

| T   | M    | $\bar{\bar{x}}$ | $\sigma_{mean}$ | CI length |
| --- | ---- | --------------- | --------------- | --------- |
| 4   | 1000 | 40.0356         | 0.3211          | 1.2588    |
| 1   | 4000 | 39.9870         | 0.3247          | 1.2729    |
| 4   | 4000 | 39.9973         | 0.1633          | 0.6401    |


Adding more samples for (T, M) = (4, 10 000) we see that the std estimate reduces even more, following the theory. If we are taking 1 000 samples every four seconds then we sample at time 4, 8, 12, ..., 4 000. If we are taking 4000 samples every second then we sample at time 1, 2, 3, 4, ..., 4 000. Over time we have sampled the same interval in time but with different frequencies. For the last example we do 4000 samples every four seconds we sample at time 4, 8, 12, ..., 16 000. This means that we have sampled the process over longer time. We believe this is the reason for the better results.