## Set 3
### Assume the following statements when answering the following questions.
```
Location loc1 = new Location(4, 3); Location loc2 = new Location(3, 4);
```

### How would you access the row value for loc1?
`loc1.getRow()`
```
// @file: info/gridworld/grid/Location.java
// @line: 110
public int getRow()
```
### What is the value of b after the following statement is executed?
```
boolean b = loc1.equals(loc2);
```
false
```
// @file: info/gridworld/grid/Location.java
// @line: 205
public boolean equals(Object other)
```
### What is the value of loc3 after the following statement is executed?
```
Location loc3 = loc2.getAdjacentLocation(Location.SOUTH);
```
(4,4)
```
// @file: info/gridworld/grid/Location.java
// @line: 130
public Location getAdjacentLocation(int direction)
```
### What is the value of dir after the following statement is executed?
```
int dir = loc1.getDirectionToward(new Location(6, 5));
```
135
```
// @file: info/gridworld/grid/Location.java
// @line: 178
public int getDirectionToward(Location target)
```
### How does the getAdjacentLocation method know which adjacent location to return?
它将接收到的`direction`进行*reduce mod 360 and round to closest multiple of 45*的处理，对应最接近的8个罗盘方向之一，然后再根据对应方向返回当前`Location`行数和列数的分别加一或减一或不变的`Location`对象。
```
// @file: info/gridworld/grid/Location.java
// @line: 130~169
public Location getAdjacentLocation(int direction)
{
		// reduce mod 360 and round to closest multiple of 45
		int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
		if (adjustedDirection < 0)
				adjustedDirection += FULL_CIRCLE;

		adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
		int dc = 0;
		int dr = 0;
		if (adjustedDirection == EAST)
				dc = 1;
		else if (adjustedDirection == SOUTHEAST)
		{
				dc = 1;
				dr = 1;
		}
		else if (adjustedDirection == SOUTH)
				dr = 1;
		else if (adjustedDirection == SOUTHWEST)
		{
				dc = -1;
				dr = 1;
		}
		else if (adjustedDirection == WEST)
				dc = -1;
		else if (adjustedDirection == NORTHWEST)
		{
				dc = -1;
				dr = -1;
		}
		else if (adjustedDirection == NORTH)
				dr = -1;
		else if (adjustedDirection == NORTHEAST)
		{
				dc = 1;
				dr = -1;
		}
		return new Location(getRow() + dr, getCol() + dc);
}
```
## Set 4
### How can you obtain a count of the objects in a grid? How can you obtain a count of the empty locations in a bounded grid?
设`Grid`对象`gr`。  
使用`gr.getOccupiedLocations().size()`方法。  
使用`gr.getNumRows()*gr.getNumCols() - gr.getOccupiedLocations().size()`方法。
```
// @file: info/gridworld/grid/Grid.java
// @line: 85
ArrayList<Location> getOccupiedLocations();

// @file: info/gridworld/grid/BoundedGrid.java
// @line: 48
public int getNumRows()

// @file: info/gridworld/grid/BoundedGrid.java
// @line: 53
public int getNumCols()
```
### How can you check if location (10,10) is in a grid?
使用`gr.isValid(new Location(10,10))`方法。
```
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 60
public boolean isValid(Location loc)
```
### Grid contains method declarations, but no code is supplied in the methods. Why? Where can you find the implementations of these methods?
`Grid`是一个接口。接口指定了类必须实现的方法。可以在`AbstractGrid`和`BoundedGrid`和`UnboundedGrid`中找到the implementations of these methods。`AbstractGrid`作为抽象类实现了`Grid`的一些必需的方法。`BoundedGrid`和`UnboundedGrid`继承`AbstractGrid`，实现其他方法。
### All methods that return multiple objects return them in an ArrayList. Do you think it would be a better design to return the objects in an array? Explain your answer.
No.  
使用`ArrayList`比`array`更容易维护，拥有更多的方法，而且不需要预知数组大小，可动态添加元素。
## Set 5
### Name three properties of every actor.
```
// @file: info/gridworld/actor/Actor.java
// @line: 31~34
private Grid<Actor> grid;  // 所在Grid的引用
private Location location;  // 位置
private int direction;  // 朝向
private Color color;  // 颜色
```
### When an actor is constructed, what is its direction and color?
朝北，蓝色。
```
// @file: info/gridworld/actor/Actor.java
// @line: 41~42
color = Color.BLUE;
direction = Location.NORTH;
```
### Why do you think that the Actor class was created as a class instead of an interface?
`Actor`既有状态又有行为，需要声明变量实例或实现方法，`interface`不支持。
### Can an actor put itself into a grid twice without first removing itself? Can an actor remove itself from a grid twice? Can an actor be placed into a grid, remove itself, and then put itself back? Try it out. What happens?
- No. 会抛出异常
```
Exception in thread "main" java.lang.IllegalStateException: This actor is already contained in a grid.
	at info.gridworld.actor.Actor.putSelfInGrid(Actor.java:118)
	at BoxBugRunner.main(BoxBugRunner.java:58)
```
```
public class BoxBugRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        BoxBug alice = new BoxBug(6);
        world.add(new Location(7, 8), alice);
        alice.putSelfInGrid(alice.getGrid(), new Location(7, 8));
        world.show();
    }
}
```
- No. 会抛出异常
```
Exception in thread "main" java.lang.IllegalStateException: This actor is not contained in a grid.
	at info.gridworld.actor.Actor.removeSelfFromGrid(Actor.java:136)
	at BoxBugRunner.main(BoxBugRunner.java:59)
```
```
public class BoxBugRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        BoxBug alice = new BoxBug(6);
        world.add(new Location(7, 8), alice);
        alice.removeSelfFromGrid();
        alice.removeSelfFromGrid();
        world.show();
    }
}
```
- Yes.
```
public class BoxBugRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        BoxBug alice = new BoxBug(6);
        world.add(new Location(7, 8), alice);
        Grid gr = alice.getGrid();
        alice.removeSelfFromGrid();
        alice.putSelfInGrid(gr, new Location(7, 8));
        world.show();
    }
}
```
### How can an actor turn 90 degrees to the right?
设`Actor`对象`ac`，使用`ac.setDirection(getDirection() + Location.RIGHT)`或`setDirection(getDirection() + 90)`方法。
## Set 6
### Which statement(s) in the canMove method ensures that a bug does not try to move out of its grid?
```
// @file: info/gridworld/actor/Bug.java
// @line: 98~99
if (!gr.isValid(next))
	return false;
```
### Which statement(s) in the canMove method determines that a bug will not walk into a rock?
```
// @file: info/gridworld/actor/Bug.java
// @line: 100~101
Actor neighbor = gr.get(next);
return (neighbor == null) || (neighbor instanceof Flower);
```
如果bug前方的是空的或是花才会返回真，所以前方是rock会返回假。
### Which methods of the Grid interface are invoked by the canMove method and why?
`isValid`和`get`。确保前方下个位置是valid的，且获取前方下个位置的`Actor`对象，以判断是否是空的或是可以被bug取代的对象。
```
// @file: info/gridworld/actor/Bug.java
// @line: 98~100
if (!gr.isValid(next))
	return false;
Actor neighbor = gr.get(next);
```
### Which method of the Location class is invoked by the canMove method and why?
```
// @file: info/gridworld/actor/Bug.java
// @line: 97
Location next = loc.getAdjacentLocation(getDirection());
```
`getAdjacentLocation`。获取前方下个位置。
### Which methods inherited from the Actor class are invoked in the canMove method?
`getGrid`, `getLocation`, `getDirection`
```
// @file: info/gridworld/actor/Bug.java
// @line: 93~97
Grid<Actor> gr = getGrid();
if (gr == null)
	return false;
Location loc = getLocation();
Location next = loc.getAdjacentLocation(getDirection());
```
### What happens in the move method when the location immediately in front of the bug is out of the grid?
会将自己从`Grid`中移除。
```
// @file: info/gridworld/actor/Bug.java
// @line: 78~81
if (gr.isValid(next))
	moveTo(next);
else
	removeSelfFromGrid();
```
### Is the variable loc needed in the move method, or could it be avoided by calling getLocation() multiple times?
Needed. `loc`记录了`bug`移动前的位置，用于在这个位置放花。
### Why do you think the flowers that are dropped by a bug have the same color as the bug?
设置花的颜色与`bug`相同。
```
// @file: info/gridworld/actor/Bug.java
// @line: 82
Flower flower = new Flower(getColor());
```
### When a bug removes itself from the grid, will it place a flower into its previous location?
如果这是在`move`方法中发生的，Yes。
```
// @file: info/gridworld/actor/Bug.java
// @line: 78~83
if (gr.isValid(next))
	moveTo(next);
else
	removeSelfFromGrid();
Flower flower = new Flower(getColor());
flower.putSelfInGrid(gr, loc);
```
如果是直接调用`removeSelfFromGrid`方法，No。
### Which statement(s) in the move method places the flower into the grid at the bug's previous location?
```
// @file: info/gridworld/actor/Bug.java
// @line: 82~83
Flower flower = new Flower(getColor());
flower.putSelfInGrid(gr, loc);
```
### If a bug needs to turn 180 degrees, how many times should it call the turn method?
4。`Location.HALF_RIGHT`为45，$45\times4=180$。
```
// @file: info/gridworld/actor/Bug.java
// @line: 62~65
public void turn()
{
	setDirection(getDirection() + Location.HALF_RIGHT);
}
```