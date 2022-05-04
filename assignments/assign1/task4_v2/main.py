from cProfile import label
from tokenize import Double
from xml.etree.ElementTree import tostring
import numpy as np
import matplotlib.pyplot as plt



 
with open("./task_4_1_Arrivals.txt") as f:
    
    lines = f.read()
    lines = lines.splitlines()
    aY1 = []
    bY1 = []
    x = []
    aY2 = []
    for line in lines:
        line = line.split()
        bY1.append(round(float(line[1]),1))
        aY1.append(round(float(line[2]),1))
        x.append(round(float(line[0]),1))
        aY2.append(round(float(line[3]),1))
        
        
    fig,ax1= plt.subplots()
    ax2 = ax1.twinx()
    ax2.set_ylim(bottom=0.)
    ax1.plot(x, aY1, 'o', color='red', label = "Special Person")
    ax1.plot(x, bY1, 'o', color='blue', label = "Normal Person")
    ax2.plot(x, aY2, 'x', color='green', label = "Number of max queue-time exceeded")

    ax1.set_xlabel("Part special arrivals (%)")
    ax1.set_ylabel("Average time in queue (s)")
    ax2.set_ylabel("Nummber of queues exceeded")
    ax1.legend(loc=(0.7,1.01))
    ax2.legend(loc= (0.0,1.01)) 
    
    plt.savefig('task4_20_two_queues.png')
    
    
 
with open("./task_4_2_Arrivals.txt") as f:
    
    lines = f.read()
    lines = lines.splitlines()
    aY1 = []
    bY1 = []
    x = []
    aY2 = []
    for line in lines:
        line = line.split()
        bY1.append(round(float(line[1]),1))
        aY1.append(round(float(line[2]),1))
        x.append(round(float(line[0]),1))
        aY2.append(round(float(line[3]),1))
        
        
    fig,ax1= plt.subplots()
    ax2 = ax1.twinx()
    ax2.set_ylim(bottom=0.)
    ax1.plot(x, aY1, 'o', color='red', label = "Special Person")
    ax1.plot(x, bY1, 'o', color='blue', label = "Normal Person")
    ax2.plot(x, aY2, 'x', color='green', label = "Number of max queue-time exceeded")

    ax1.set_xlabel("Part special arrivals (%)")
    ax1.set_ylabel("Average time in queue (s)")
    ax2.set_ylabel("Nummber of queues exceeded")
    ax1.legend(loc=(0.7,1.01))
    ax2.legend(loc= (0.0,1.01)) 
    
    plt.savefig('task4_50_two_queues.png')
    
    
 
with open("./task_4_3_Arrivals.txt") as f:
    
    lines = f.read()
    lines = lines.splitlines()
    aY1 = []
    bY1 = []
    x = []
    aY2 = []
    for line in lines:
        line = line.split()
        bY1.append(round(float(line[1]),1))
        aY1.append(round(float(line[2]),1))
        x.append(round(float(line[0]),1))
        aY2.append(round(float(line[3]),1))
        
        
    fig,ax1= plt.subplots()
    ax2 = ax1.twinx()
    ax2.set_ylim(bottom=0.)
    ax1.plot(x, aY1, 'o', color='red', label = "Special Person")
    ax1.plot(x, bY1, 'o', color='blue', label = "Normal Person")
    ax2.plot(x, aY2, 'x', color='green', label = "Number of max queue-time exceeded")

    ax1.set_xlabel("Part special arrivals (%)")
    ax1.set_ylabel("Average time in queue (s)")
    ax2.set_ylabel("Nummber of queues exceeded")
    ax1.legend(loc=(0.7,1.01))
    ax2.legend(loc= (0.0,1.01)) 
    
    plt.savefig('task4_100_two_queues.png')
    
    
 
with open("./task_4_4_Arrivals.txt") as f:
    
    lines = f.read()
    lines = lines.splitlines()
    aY1 = []
    bY1 = []
    x = []
    aY2 = []
    for line in lines:
        line = line.split()
        bY1.append(round(float(line[1]),1))
        aY1.append(round(float(line[2]),1))
        x.append(round(float(line[0]),1))
        aY2.append(round(float(line[3]),1))
        
        
    fig,ax1= plt.subplots()
    ax2 = ax1.twinx()
    ax1.plot(x, aY1, 'o', color='red', label = "Special Person")
    ax1.plot(x, bY1, 'o', color='blue', label = "Normal Person")
    ax2.plot(x, aY2, 'x', color='green', label = "Number of max queue-time exceeded")

    ax1.set_xlabel("Part special arrivals (%)")
    ax1.set_ylabel("Average time in queue (s)")
    ax2.set_ylabel("Nummber of queues exceeded")
    ax1.legend(loc=(0.7,1.01))
    ax2.legend(loc= (0.0,1.01)) 
    
    plt.savefig('task4_1000_two_queues.png')
        