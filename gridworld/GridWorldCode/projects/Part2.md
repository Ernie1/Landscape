### What is the role of the instance variable sideLength?
The `sideLength` defines the steps a bug can work per side in its box.
```
// @file: projects/boxBug/BoxBug.java
// @line: 31
* Constructs a box bug that traces a square of a given side length
```
### What is the role of the instance variable steps?
记录bug在当前box边走了多少步
```
// @file: projects/boxBug/BoxBug.java
// @line: 45～49
if (steps < sideLength && canMove())
{
	move();
	steps++;
}
```
### Why is the turn method called twice when steps becomes equal to sideLength?
当步数达到sideLength，需要转90度走box的下一边，turn方法每次转45度，所以需要调用2次。
```
// @file: info/gridworld/actor/Bug.java
// @line: 62~65
public void turn()
{
	setDirection(getDirection() + Location.HALF_RIGHT);
}
```
### Why can the move method be called in the BoxBug class when there is no move method in the BoxBug code?
BoxBug继承Bug类，Bug类有move方法，BoxBug继承了这个方法
```
// @file: info/gridworld/actor/Bug.java
// @line: 71
public void move()
```
### After a BoxBug is constructed, will the size of its square pattern always be the same? Why or why not?
Yes.当BoxBug构造时就已经定义了sidelength的值，后面没有修改它的代码
```
// @file: projects/boxBug/BoxBug.java
// @line: 37
sideLength = length;
```
### Can the path a BoxBug travels ever change? Why or why not?
Yes.当BoxBug当前方不空且不是花会改变路径
```
// @file: info/gridworld/actor/Bug.java
// @line: 100~101
Actor neighbor = gr.get(next);
return (neighbor == null) || (neighbor instanceof Flower);
// @file: projects/boxBug/BoxBug.java
// @line: 45~55
if (steps < sideLength && canMove())
{
	move();
	steps++;
}
else
{
	turn();
	turn();
	steps = 0;
}
```
### When will the value of steps be zero?
BoxBug构造时和当steps等于sideLength时
```
// @file: projects/boxBug/BoxBug.java
// @line: 36
steps = 0;
// @file: projects/boxBug/BoxBug.java
// @line: 54
steps = 0;
```