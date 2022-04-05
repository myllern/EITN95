import numpy as np
import matplotlib.pyplot as plt

with open("./task_24.txt") as f:
    data_raw = f.readline()
    data = np.fromiter(map(lambda y: int(y), data_raw.split(";")[:-1]), np.int64)
    xs = np.linspace(0, 100, 1000)
    plt.plot(data)
    plt.show()
