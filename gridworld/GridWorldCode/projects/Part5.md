## Set 10
The source code for the AbstractGrid class is in Appendix D.
### Where is the isValid method specified? Which classes provide an implementation of this method?
Grid接口中. BoundedGrid, UnboundedGrid
```
// @file: info/gridworld/grid/Grid.java
// @line: 50
boolean isValid(Location loc);
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 60
public boolean isValid(Location loc)
// @file: info/gridworld/grid/UnboundedGrid.java
// @line: 53
public boolean isValid(Location loc)
```
### Which AbstractGrid methods call the isValid method? Why don't the other methods need to call it?
getValidAdjacentLocations调用isValid，因为getEmptyAdjacentLocations和getOccupiedAdjacentLocations调用了getValidAdjacentLocations，getNeighbors调用了getOccupiedAdjacentLocations，都间接调用了isValid
```
// @file: info/gridworld/grid/AbstractGrid.java
// @line: 44
if (isValid(neighborLoc))
```
### Which methods of the Grid interface are called in the getNeighbors method? Which classes provide implementations of these methods?
get和getOccupiedAdjacentLocations，AbstractGrid实现getOccupiedAdjacentLocations，BoundedGrid和UnboundedGrid分别实现get
```
// @file: info/gridworld/grid/AbstractGrid.java
// @line: 62
public ArrayList<Location> getOccupiedAdjacentLocations(Location loc)
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 85
public E get(Location loc)
// @file: info/gridworld/grid/UnboundedGrid.java
// @line: 66
public E get(Location loc)
```
### Why must the get method, which returns an object of type E, be used in the getEmptyAdjacentLocations method when this method returns locations, not objects of type E?
get返回grid中指定位置的对象的引用（没有对象返回空），getEmptyAdjacentLocations调用get判断位置上是否有对象，最终将没有对象的若干位置返回，返回type E空对象没有意义
```
// @file: info/gridworld/grid/AbstractGrid.java
// @line: 54~58
for (Location neighborLoc : getValidAdjacentLocations(loc))
{
	if (get(neighborLoc) == null)
		locs.add(neighborLoc);
}
```
### What would be the effect of replacing the constant Location.HALF_RIGHT with Location.RIGHT in the two places where it occurs in the getValidAdjacentLocations method?
原来返回八个方向中相邻valid位置，改成Location.RIGHT只返回给定位置北东南西四个方向中相邻valid位置。
```
// @file: info/gridworld/grid/AbstractGrid.java
// @line: 41~47
for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
	{
		Location neighborLoc = loc.getAdjacentLocation(d);
		if (isValid(neighborLoc))
			locs.add(neighborLoc);
		d = d + Location.HALF_RIGHT;
	}
```
## Set 11
The source code for the BoundedGrid class is in Appendix D.
### What ensures that a grid has at least one valid location?
如果rows <= 0或cols <= 0，BoundedGrid的构造器会抛出IllegalArgumentException异常
```
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 41~44
if (rows <= 0)
	throw new IllegalArgumentException("rows <= 0");
if (cols <= 0)
	throw new IllegalArgumentException("cols <= 0");
```
### How is the number of columns in the grid determined by the getNumCols method? What assumption about the grid makes this possible?
```
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 57
occupantArray[0].length
```
返回occupantArray第0个一维数组的元素个数  
makes this possible的原因：由Question 6可知，这个数组的长度最小为1
### What are the requirements for a Location to be valid in a BoundedGrid? In the next four questions, let r = number of rows, c = number of columns, and n = number of occupied locations.
该位置的row序号>=0且小于BoundedGrid的行数，col序号>=0且小于BoundedGrid的列数
```
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 62~63
return 0 <= loc.getRow() && loc.getRow() < getNumRows()
	&& 0 <= loc.getCol() && loc.getCol() < getNumCols();
```
### What type is returned by the getOccupiedLocations method? What is the time complexity (Big-Oh) for this method?
ArrayList<Location>  
$O(行数\times列数)$遍历检验位置是否被占，$O(1)$添加被占的位置
```
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 66~83
public ArrayList<Location> getOccupiedLocations()
{
	ArrayList<Location> theLocations = new ArrayList<Location>();

	// Look at all grid locations.
	for (int r = 0; r < getNumRows(); r++)
	{
		for (int c = 0; c < getNumCols(); c++)
		{
			// If there's an object at this location, put it in the array.
			Location loc = new Location(r, c);
			if (get(loc) != null)
				theLocations.add(loc);
		}
	}

	return theLocations;
}
```
### What type is returned by the get method? What parameter is needed? What is the time complexity (Big-Oh) for this method?
返回类型为E，occupantArray存放的类型  
需要Location对象  
$O(1)$，因为数组是随机访问的
```
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 85~91
public E get(Location loc)
{
	if (!isValid(loc))
		throw new IllegalArgumentException("Location " + loc
			+ " is not valid");
	return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
}
```
### What conditions may cause an exception to be thrown by the put method? What is the time complexity (Big-Oh) for this method?
要添加的目标位置不在grid中会抛出IllegalArgumentException异常，要添加的对象是空会抛出NullPointerException异常  
$O(1)$ 数组随机访问
```
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 93~105
public E put(Location loc, E obj)
{
	if (!isValid(loc))
		throw new IllegalArgumentException("Location " + loc
			+ " is not valid");
	if (obj == null)
		throw new NullPointerException("obj == null");

	// Add the object to the grid.
	E oldOccupant = get(loc);
	occupantArray[loc.getRow()][loc.getCol()] = obj;
	return oldOccupant;
}
```
### What type is returned by the remove method? What happens when an attempt is made to remove an item from an empty location? What is the time complexity (Big-Oh) for this method?
返回类型为E，occupantArray存放的类型  
occupantArray该位置存空并返回空对象（不会抛出异常）  
$O(1)$ 数组随机访问
```
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 97~107
public E remove(Location loc)
{
	if (!isValid(loc))
		throw new IllegalArgumentException("Location " + loc
			+ " is not valid");

	// Remove the object from the grid.
	E r = get(loc);
	occupantArray[loc.getRow()][loc.getCol()] = null;
	return r;
}
```
### Based on the answers to questions 4, 5, 6, and 7, would you consider this an efficient implementation? Justify your answer.
5，6，7是$O(1)$，efficient  
4是$O(行数\times列数)$，inefficient，BoundedGrid中的occupants存放的值是稀疏的，可以用散列表来降维使其efficient
## Set 12
The source code for the UnboundedGrid class is in Appendix D.
### Which method must the Location class implement so that an instance of HashMap can be used for the map? What would be required of the Location class if a TreeMap were used instead? Does Location satisfy these requirements?
Location实现了equals和hashCode方法才能使用HashMap，hashCode保证了映射一致  
Location实现了Comparable接口的comparTo方法才能使用TreeMap  
满足
```
// @file: info/gridworld/grid/Location.java
// @line: 205
public boolean equals(Object other)
// @line: 218
public int hashCode()
// @line: 234
public int compareTo(Object other)
```
### Why are the checks for null included in the get, put, and remove methods? Why are no such checks included in the corresponding methods for the BoundedGrid?
当为null时，UnboundedGrid的isValid只会返回真，Map允许key value为null，进行get, put, and remove会造成逻辑错误，所以需要在get, put, and remove中检查null并抛出NullPointerException异常；而BoundedGrid的isValid调用loc.getRow()会自动抛出NullPointerException异常，get, put, and remove不需要再次检查
```
// @file: info/gridworld/grid/UnboundedGrid.java
// @line: 53~56
public boolean isValid(Location loc)
{
	return true;
}
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 60~64
public boolean isValid(Location loc)
{
	return 0 <= loc.getRow() && loc.getRow() < getNumRows()
		&& 0 <= loc.getCol() && loc.getCol() < getNumCols();
}
```
### What is the average time complexity (Big-Oh) for the three methods: get, put, and remove? What would it be if a TreeMap were used instead of a HashMap?
都是$O(1)$,HashMap的特点  
TreeMap是$O(\log n)$，n为树的大小
### How would the behavior of this class differ, aside from time complexity, if a TreeMap were used instead of a HashMap?
getOccupiedLocations中对occupants元素遍历的顺序不同，HashMap遍历顺序取决于hashCode和表大小，TreeMap会依据Location的compareTo从小到大遍历
### Could a map implementation be used for a bounded grid? What advantage, if any, would the two-dimensional array implementation that is used by the BoundedGrid class have over a map implementation?
可以  
map的优点是getOccupiedLocations方法的时间复杂度会下降到$O(n)$，n为occupants的个数  
二维数组的优点是当grid的occupants稠密时比map节省空间，因为map要给每个occupant存位置信息而二维数组不用