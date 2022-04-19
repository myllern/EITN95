from cProfile import label
from tokenize import Double
import numpy as np
import matplotlib.pyplot as plt


def main():
    assign2_task1_p1()


def assign2_task1_p1():
    with open("./assign2_task1_p1.txt") as f:
        ys = np.fromiter(
            map(
                lambda data: int(data),
                f.read().splitlines()
            ),
            dtype=np.int64
        )

        T = 1
        M = 1000

        xs = np.linspace(T, T * M, M)
        plt.plot(xs, ys)
        plt.xlabel("Measurement Time")
        plt.ylabel("# Customers in system being served")
        plt.grid()
        plt.show()


# with open("./task_4_50Arrivals.txt") as f:

#     lines = f.read()
#     lines = lines.splitlines()
#     aY = []
#     bY = []
#     x = []
#     for line in lines:
#         line = line.split()
#         bY.append(round(float(line[1]),1))
#         aY.append(round(float(line[2]),1))
#         x.append(round(float(line[0]),1))


#     fig,ax= plt.subplots()
#     ax.plot(x, aY, 'o', color='red', label = "Normal Person")
#     ax.plot(x, bY, 'o', color='blue', label = "Special Person")
#     ax.set_ylim([0,200])
#     ax.set_xlabel("Part special arrivals (%)")
#     ax.set_ylabel("Average time in queue (s)")
#     ax.legend()
#     plt.show()
#     plt.savefig('task4_50.png')
if __name__ == "__main__":
    main()
