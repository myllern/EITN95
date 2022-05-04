
 
from matplotlib import pyplot as plt, ticker
from matplotlib.ft2font import LOAD_NO_SCALE
import numpy as np


with open("./plot_assign2_task1_2.txt") as f:
    
    
    lines = f.read()
    lines = lines.splitlines()
    aY1 = []
    for line in lines:
        line = line.split()
        aY1.append(round(float(line[1]),1))
        
    x = range(0,len(aY1),1)
   

        
    fig,ax1= plt.subplots()
    ax1.xaxis.set_major_locator(ticker.MultipleLocator(100))
    ax1.plot(aY1,label = "Nbr in system")

    ax1.set_xlabel("Mesures")
    ax1.set_ylabel("Nbr in system")
    ax1.legend(loc=(0.7,1.01))
    
    plt.savefig('assign2_task1_2.png')
