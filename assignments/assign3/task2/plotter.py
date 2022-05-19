
from cProfile import label
from matplotlib import pyplot as plt
from matplotlib.ft2font import LOAD_NO_SCALE
import numpy as np


with open("./assignment3_task2_1.txt") as f:
    
    
    lines = f.read()
    lines = lines.splitlines()
    aY1 = []
    avgTime = 0;
    for line in lines:
        if line[0] != "T":
            line = line.split()
            aY1.append(round(float(line[1]),1))
        else:
            line = line.split()
            avgTime = line[1]
        

    fig,ax1= plt.subplots()
    ax1.plot(aY1,label = "Seconds spent Talking, T = 1, V = 2 m/s")
    plt.axvline(x = float(avgTime), color='red', label = "Avg time taken untill everyone talked to each other")
    

    ax1.set_xlabel("Run time (s)")
    ax1.set_ylabel("Talking time (s)")
    ax1.legend()
    
    plt.savefig('assign3_task2_1.png')
    
    
    
with open("./assignment3_task2_2.txt") as f:
    lines = f.read()
    lines = lines.splitlines()
    aY1 = []
    avgTime = 0;
    for line in lines:
        if line[0] != "T":
            line = line.split()
            aY1.append(round(float(line[1]),1))
        else:
            line = line.split()
            avgTime = line[1]

    fig,ax1= plt.subplots()
    ax1.plot(aY1,label = "Seconds spent Talking, T = 1, V = 4 m/s")
    plt.axvline(x = float(avgTime), color='red', label = "Avg time taken untill everyone talked to each other")
    
    ax1.set_xlabel("Run time (s)")
    ax1.set_ylabel("Talking time (s)")
    ax1.legend()

    plt.savefig('assign3_task2_2.png')
    
    
        
with open("./assignment3_task2_3.txt") as f:
    
    
    lines = f.read()
    lines = lines.splitlines()
    aY1 = []
    avgTime = 0;
    for line in lines:
        if line[0] != "T":
            line = line.split()
            aY1.append(round(float(line[1]),1))
        else:
            line = line.split()
            avgTime = line[1]

    fig,ax1= plt.subplots()
    ax1.plot(aY1,label = "Seconds spent Talking, T = 1, V = U(1,7) m/s")
    plt.axvline(x = float(avgTime), color='red', label = "Avg time taken untill everyone talked to each other")
    
    ax1.set_xlabel("Run time (s)")
    ax1.set_ylabel("Talking time (s)")
    ax1.legend()

    
    plt.savefig('assign3_task2_3.png')
    
    
    