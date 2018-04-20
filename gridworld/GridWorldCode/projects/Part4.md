## Set 7
The source code for the Critter class is in the critters directory

### What methods are implemented in Critter?
act, getActors, processActors, getMoveLocations, selectMoveLocation, makeMove
### What are the five basic actions common to all critters when they act?
getActors, processActors, getMoveLocations, selectMoveLocation, makeMove
### Should subclasses of Critter override the getActors method? Explain.
Yes.  
如果它的子类对actors的选择规则跟Critter不同就要重写。
```
// @file: info/gridworld/actor/Critter.java
// @line: 88~91
public ArrayList<Location> getMoveLocations()
{
	return getGrid().getEmptyAdjacentLocations(getLocation());
}
```
### Describe the way that a critter could process actors.
One type of critter might get all the neighboring actors and process each one of them in some way (remove them, change their color, make them move, and so on). Here remove them.
```
// @file: info/gridworld/actor/Critter.java
// @line: 71~78
public void processActors(ArrayList<Actor> actors)
{
	for (Actor a : actors)
	{
		if (!(a instanceof Rock) && !(a instanceof Critter))
			a.removeSelfFromGrid();
	}
}
```
### What three methods must be invoked to make a critter move? Explain each of these methods.
getMoveLocations, selectMoveLocation, makeMove
- getMoveLocations  
返回在critter周围所有空位置的列表
- selectMoveLocation  
随机选择空位置列表其中一个位置并返回，如果没有空位置，将返回critter的当前位置
- makeMove  
将critter移到该位置，如果没有位置参数将自我移除
```
// @file: info/gridworld/actor/Critter.java
// @line: 44~46
ArrayList<Location> moveLocs = getMoveLocations();
Location loc = selectMoveLocation(moveLocs);
makeMove(loc);
// @file: info/gridworld/actor/Critter.java
// @line: 88~91
public ArrayList<Location> getMoveLocations()
{
	return getGrid().getEmptyAdjacentLocations(getLocation());
}
// @file: info/gridworld/actor/Critter.java
// @line: 104~111
public Location selectMoveLocation(ArrayList<Location> locs)
{
	int n = locs.size();
	if (n == 0)
		return getLocation();
	int r = (int) (Math.random() * n);
	return locs.get(r);
}
// @file: info/gridworld/actor/Critter.java
// @line: 125~131
public void makeMove(Location loc)
{
	if (loc == null)
		removeSelfFromGrid();
	else
		moveTo(loc);
}
```
### Why is there no Critter constructor?
Critter继承了Actor，Actor有default constructor. If you do not create a constructor in a class, Java will write a default constructor for you. The Critter default constructor that Java provides will call super(), which calls the Actor default constructor. The Actor default constructor will make a blue critter that faces north.
## Set 8
The source code for the ChameleonCritter class is in the critters directory
### Why does act cause a ChameleonCritter to act differently from a Critter even though ChameleonCritter does not override act?
由*Question 2*可知，act调用getActors, processActors, getMoveLocations, selectMoveLocation, makeMove。ChameleonCritter重写了processActors和makeMove导致ChameleonCritter的act与Critter不同。  
Critter的processes方法移除相邻不是Rock或Critter的actor
```
// @file: info/gridworld/actor/Critter.java
// @line: 75~76
if (!(a instanceof Rock) && !(a instanceof Critter))
	a.removeSelfFromGrid();
```
ChameleonCritter的processes方法随机选择一个相邻actor，设置自己的颜色与其相同
```
// @file: projects/critters/ChameleonCritter.java
// @line: 43~44
Actor other = actors.get(r);
setColor(other.getColor());
```
Critter的makeMove方法不会改变方向
```
// @file: info/gridworld/actor/Critter.java
// @line: 125~131
public void makeMove(Location loc)
{
	if (loc == null)
		removeSelfFromGrid();
	else
		moveTo(loc);
}
```
ChameleonCritter的makeMove方法改变自己方向与原位置到loc方向相同
```
// @file: projects/critters/ChameleonCritter.java
// @line: 50~54
public void makeMove(Location loc)
{
	setDirection(getLocation().getDirectionToward(loc));
	super.makeMove(loc);
}
```
### Why does the makeMove method of ChameleonCritter call super.makeMove?
ChameleonCritter的makeMove方法先改变自己方向与原位置到loc方向相同，之后调用super.makeMove移动到loc（这个行为与Critter相同）。
### How would you make the ChameleonCritter drop flowers in its old location when it moves?
修改makeMove方法，用一个临时变量记录原来的位置，如果原来的位置与loc不同，新建一个花并将其放到原来的位置。
```
public void makeMove(Location loc)
{
  Location former = getLocation();
  setDirection(getLocation().getDirectionToward(loc));
  super.makeMove(loc);
  if(!former.equals(loc))
  {
    Flower flower = new Flower(getColor());
    flower.putSelfInGrid(getGrid(), former);
  }
}
```
### Why doesn't ChameleonCritter override the getActors method?
因为ChameleonCritter没有给getActors方法定义新的行为，所以没有重写这个方法，沿用Critter的getActors方法。
### Which class contains the getLocation method?
Actor类
```
// @file: info/gridworld/actor/Actor.java
// @line: 102
public Location getLocation()
```
### How can a Critter access its own grid?
调用Actor的getGrid方法
```
// @file: info/gridworld/actor/Actor.java
// @line: 92
public Grid<Actor> getGrid()
```
## Set 9
The source code for the CrabCritter class is reproduced at the end of this part of GridWorld.
### Why doesn't CrabCritter override the processActors method?
Critter的getActors方法对get处理getActors()方法返回的结果，CrabCritter除了要process的actor不同外其他与Critter相同，不需要重写
```
// @file: info/gridworld/actor/Critter.java
// @line: 71~78
public void processActors(ArrayList<Actor> actors)
{
    for (Actor a : actors)
    {
        if (!(a instanceof Rock) && !(a instanceof Critter))
            a.removeSelfFromGrid();
    }
}
```
### Describe the process a CrabCritter uses to find and eat other actors. Does it always eat all neighboring actors? Explain.
Eats whatever is found in the locations immediately in front, to the right-front, or to the left-front of it.  
No. 不会eat neighboring actors中不符合上述条件的
```
// @file: projects/critters/CrabCritter.java
// @line: 47~48
int[] dirs =
	{ Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
```
### Why is the getLocationsInDirections method used in CrabCritter?
这个方法接受一个directions数组，返回valid的相邻的这些方向的位置数组，用于eat actors和移动
```
// @file: projects/critters/CrabCritter.java
// @line: 44~49
 public ArrayList<Actor> getActors()
{
	ArrayList<Actor> actors = new ArrayList<Actor>();
	int[] dirs =
		{ Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
	for (Location loc : getLocationsInDirections(dirs))
// @line: 62~67
public ArrayList<Location> getMoveLocations()
{
	ArrayList<Location> locs = new ArrayList<Location>();
	int[] dirs =
		{ Location.LEFT, Location.RIGHT };
	for (Location loc : getLocationsInDirections(dirs))
```
### If a CrabCritter has location (3, 4) and faces south, what are the possible locations for actors that are returned by a call to the getActors method?
(4,3), (4,4), (4,5)
### What are the similarities and differences between the movements of a CrabCritter and a Critter?
similarities: 移动时不会改变自己的方向，随机选取可移动位置移动  
differences: 蟹只能左右移动，critter可以移动八方向；当不能移动时蟹会随机转左或转右而critter不会
### How does a CrabCritter determine when it turns instead of moving?
loc与当前位置相同
```
// @file: projects/critters/CrabCritter.java
// @line: 79~88
if (loc.equals(getLocation()))
{
	double r = Math.random();
	int angle;
	if (r < 0.5)
		angle = Location.LEFT;
	else
		angle = Location.RIGHT;
	setDirection(getDirection() + angle);
}
```
### Why don't the CrabCritter objects eat each other?
Critter类规定不吃Rock和Critter，CrabCritter属于Critter
```
// @file: info/gridworld/actor/Critter.java
// @line: 71~78
public void processActors(ArrayList<Actor> actors)
{
	for (Actor a : actors)
	{
		if (!(a instanceof Rock) && !(a instanceof Critter))
			a.removeSelfFromGrid();
	}
}
```