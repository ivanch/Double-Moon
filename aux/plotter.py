from random import random
import matplotlib.pyplot as plt
import math

points = 20000

x1 = [0 for _ in range(points)]
y1 = [0 for _ in range(points)]
x2 = [0 for _ in range(points)]
y2 = [0 for _ in range(points)]

for i in range(points):
    d = 0
    r = 4
    w = 6
    # 0 ~ 180
    a = random()*math.pi
    x1[i] = math.sqrt(random()) * math.cos(a)*(w/2) + ((-(r+w/2) if(random() < 0.5) else (r+w/2)) * math.cos(a))
    y1[i] = math.sqrt(random()) * math.sin(a)*(w) + (r * math.sin(a)) - d
    # 180 ~ 360
    a = random()*math.pi + math.pi
    x2[i] = (r+w/2) + math.sqrt(random()) * math.cos(a)*(w/2) + ((-(r+w/2)) if(random() < 0.5) else (r+w/2)) * math.cos(a)
    y2[i] = -(math.sqrt(random()) * math.sin(a)*(-w) + (-r * math.sin(a))) + d

plt.scatter(x1, y1, color="r")
plt.scatter(x2, y2, color="b")
plt.show()
