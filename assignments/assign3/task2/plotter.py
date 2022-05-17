
from matplotlib import pyplot as plt
from matplotlib.ft2font import LOAD_NO_SCALE
import numpy as np


with open("./assignment3_task2_1") as f:
    
    
    lines = f.read()
    lines = lines.splitlines()
    aY1 = []
    for line in lines:
        line = line.split()
        aY1.append(round(float(line[1]),1))
        

    fig,ax1= plt.subplots()
    ax1.plot(aY1,label = "Seconds spent Talking, T = 1, V = 2 m/s")

    ax1.set_xlabel("Run time (s)")
    ax1.set_ylabel("Talking time (s)")
    ax1.legend()
    
    plt.savefig('assign3_task2_1.png')
    
    
    
with open("./assignment3_task2_2") as f:
    
    
    lines = f.read()
    lines = lines.splitlines()
    aY1 = []
    for line in lines:
        line = line.split()
        aY1.append(round(float(line[1]),1))
        

    fig,ax1= plt.subplots()
    ax1.plot(aY1,label = "Seconds spent Talking, T = 1, V = 4 m/s")

    ax1.set_xlabel("Run time (s)")
    ax1.set_ylabel("Talking time (s)")
    ax1.legend()

    plt.savefig('assign3_task2_2.png')
    
    
        
with open("./assignment3_task2_3") as f:
    
    
    lines = f.read()
    lines = lines.splitlines()
    aY1 = []
    for line in lines:
        line = line.split()
        aY1.append(round(float(line[1]),1))
        

    fig,ax1= plt.subplots()
    ax1.plot(aY1,label = "Seconds spent Talking, T = 1, V = U(1,7) m/s")
    ax1.set_xlabel("Run time (s)")
    ax1.set_ylabel("Talking time (s)")
    ax1.legend()

    
    plt.savefig('assign3_task2_3.png')
    
    
    