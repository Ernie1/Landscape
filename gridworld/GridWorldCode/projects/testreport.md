**`alice`和`bob`是`Jumper`对象**，对象默认朝向北，故默认下一步的row数比其小2。

---

a. What will a jumper do if the location in front of it is empty, but the location two cells in front contains a flower or a rock?

---
- 测试内容：if rock, turn, if flower, move Jumper and remove the flower
- 测试用例：alice(6, 6)，flower(4, 6)，rock(2, 6)
- 测试结果分析：
```
alice.act();
// 测试alice第一次跳remove flower
assertNull(flower.getGrid());
alice.act();
// 测试alice第一次跳到flower的位置
assertEquals(alice.getLocation(), new Location(4, 6));
// 测试alice下一步是rock要turn
assertEquals(alice.getDirection(), Location.NORTHEAST);
```

---
b. What will a jumper do if the location two cells in front of the jumper is out of the grid?

---
- 测试内容：turn
- 测试用例：alice(1, 6)
- 测试结果分析：
```
alice.act();
// 测试alice保留在原来位置
assertEquals(alice.getLocation(), new Location(1, 6));
// 测试alice有turn
assertEquals(alice.getDirection(), Location.NORTHEAST);
```

---
c. What will a jumper do if it is facing an edge of the grid?

---
- 测试内容：turn
- 测试用例：alice(0, 6)
- 测试结果分析：
```
alice.act();
// 测试alice保留在原来位置
assertEquals(alice.getLocation(), new Location(0, 6));
// 测试alice有turn
assertEquals(alice.getDirection(), Location.NORTHEAST);
```

---
d. What will a jumper do if another actor (not a flower or a rock) is in the cell that is two cells in front of the jumper?

---
- 测试内容：turn
- 测试用例：alice(6, 6)，actor(4, 6)，
- 测试结果分析：
```
alice.act();
// 测试alice保留在原来位置
assertEquals(alice.getLocation(), new Location(6, 6));
// 测试alice有turn
assertEquals(alice.getDirection(), Location.NORTHEAST);
```

---
e. What will a jumper do if it encounters another jumper in its path?

---
- 测试内容：turn
- 测试用例：alice(6, 6)，bob(4, 6, SOUTH)，
- 测试结果分析：
```
alice.act();
bob.act();
// 测试alice保留在原来位置
assertEquals(alice.getLocation(), new Location(6, 6));
// 测试bob保留在原来位置
assertEquals(bob.getLocation(), new Location(4, 6));
// 测试alice有turn
assertEquals(alice.getDirection(), Location.NORTHEAST);
// 测试bob有turn
assertEquals(bob.getDirection(), Location.SOUTHWEST);
```

---
f. Can jumper “jumps” over other instance of Actor besides rocks and flowers?

---
- 测试内容：yes
- 测试用例：alice(6, 6)，bob(5, 6, SOUTH)，actor(3, 6)
- 测试结果分析：
```
alice.act();
bob.act();
// 测试alice可以“jumps” over Jumper对象
assertEquals(alice.getLocation(), new Location(4, 6));
alice.act();
// 测试alice可以“jumps” over Actor对象
assertEquals(alice.getLocation(), new Location(2, 6));
```

---