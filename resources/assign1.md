# Task5

## Random

| mean arrival times | $mean nbr in system$ |
| ------------------ | -------------------- |
| 0.11               | 46.4139              |
| 0.15               | 9.46985              |
| 2.00               | 0.2584               |

## Round robin

| mean arrival times | $mean nbr in system$ |
| ------------------ | -------------------- |
| 0.11               | 28.29335             |
| 0.15               | 6.05675              |
| 2.00               | 0.24725              |

## Smallest

| mean arrival times | $mean nbr in system$ |
| ------------------ | -------------------- |
| 0.11               | 10.6773              |
| 0.15               | 3.9307               |
| 2.00               | 0.2503               |


## Best algorithm?

Smallest number of jobs. 

# Task1

## New solution result

RESULTS FROM OTHER IMPLEMENTATION IN BRANCH task1NewSolution

| Time | $NQ_2$  | $P("rejected")$    |
| ---- | ------- | ------------------ |
| 1    | 9.56889 | 0.5211274272463934 |
| 2    | 4.30167 | 0.0725295970450416 |
| 5    | 0.42570 | 0.0                |


## Old code results

$NQ_2$ = "number of customers in Q2" 

To get the measurements we used 1000 measurements of Q2 and did that 100 times and took the mean of the mean.

| Time | $NQ_2$             |
| ---- | ------------------ |
| 1    | 2.9404700000000004 |
| 2    | 0.5808700000000002 |
| 5    | 0.2065699999999999 |

<!-- ----------------------------- -->
<!-- ----------------------------- -->
<!-- ----------------------------- -->

# Task2

0.1 * 1000 = 100 seconds total

150 * 100 = 15000 Job A arrivals


## 1

Mean = 0.019 jobs

## 2

Mean = 0.01 jobs

## 3

Mean = 0.01 jobs

## 4

* The prio B will has constant delay 1 s. This leads to many jobs of type A being done before any job B is ready, resulting in oscillating instable pattern where no job B are in the queue -> decrease in size or many job B in queue -> increase in size
* For prio A, we always have capacity to handle the queue and therefore spikes happen only stochastically