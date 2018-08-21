import random as r
import matplotlib.pyplot as plt
import math

points = 20000

x1 = [0 for _ in range(points)]
y1 = [0 for _ in range(points)]
x2 = [0 for _ in range(points)]
y2 = [0 for _ in range(points)]

for i in range(points):
    # 0 ~ 180
    a = r.random()*math.pi
    x1[i] = math.sqrt(r.random()) * math.cos(a)*3 + (((-7) if(r.random() < 0.5) else (7)) * math.cos(a))
    y1[i] = math.sqrt(r.random()) * math.sin(a)*6 + (4 * math.sin(a))
    # 180 ~ 360
    a = r.random()*math.pi + math.pi
    x2[i] = 7 + math.sqrt(r.random()) * math.cos(a)*3 + ((-7) if(r.random() < 0.5) else (7)) * math.cos(a)
    y2[i] = -(math.sqrt(r.random()) * math.sin(a)*-6 + (-4 * math.sin(a)))

plt.scatter(x1, y1, color="r")
plt.scatter(x2, y2, color="b")
plt.show()
