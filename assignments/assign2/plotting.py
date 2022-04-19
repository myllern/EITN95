from cProfile import label
from tokenize import Double
import numpy as np
import matplotlib.pyplot as plt

# with open("./task_24.txt") as f:
#     data_raw = f.readline()
#     data = np.fromiter(map(lambda y: int(y), data_raw.split(";")[:-1]), np.int64)
#     xs = np.linspace(0, 100, 1000)
#     plt.plot(data)
#     plt.show()ö
    
with open("./task_4_50Arrivals.txt") as f:
    
    lines = f.read()
    lines = lines.splitlines()
    aY = []
    bY = []
    x = []
    for line in lines:
        line = line.split()
        bY.append(round(float(line[1]),1))
        aY.append(round(float(line[2]),1))
        x.append(round(float(line[0]),1))
        
        
    fig,ax= plt.subplots()
    ax.plot(x, aY, 'o', color='red', label = "Normal Person")
    ax.plot(x, bY, 'o', color='blue', label = "Special Person")
    ax.set_ylim([0,200])
    ax.set_xlabel("Part special arrivals (%)")
    ax.set_ylabel("Average time in queue (s)")
    ax.legend()
    plt.show()
    plt.savefig('task4_50.png')
        
    