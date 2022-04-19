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


if __name__ == "__main__":
    main()
