
1. ArrayList is the most widely used implementation of the List interface <br>
2. Order of Insertion preserved, duplicate & null elements allowed </br>
3. Initialized with zero capacity, when first element added then capacity increases to 10. (Lazy initialization) <br/>
   further capacity increases by creating new array of capacity n+n/2+1 & copying elements to new one. <br>
4. Time complexities
   * Adding element -> O(1) if array is full & needs resizing then it will take more time
   * Remove element -> Best case O(1) , worst case O(n)
                       1. Input is index of ele to be rmved then O(1) if its last ele or O(n) as its to be removed & next elems to be shifted back.
                       2. Input is ele, then O(n) as needs scanning + shifting
   * Fetching element -> O(1) if inedex , O(n) if ele input 
5. Methods
     <pre>
     Create: List<Type> l1 = new ArrayList<Type>(options) //options can be initialSize or an existingArrayList
     Add: l1.add(ele), 
          l1.add(index, ele), 
          l1.addAll(list), 
          l1.addAll(index, list)
     Retrieve: l1.get(index), 
               l1.size(),
               contains(Object o), 
               indexOf(Object o), 
               lastIndexOf(Object o)
     Remove: l1.remove(index), 
             l1.remove(new Object(value)), 
             l1.clear()
     Replace: l1.set(index, ele),
              l1.replaceAll((element) -> element.toUpperCase());
     </pre>
6. Iterate 
    using normal for loop or for(<Type> a: list)
    using Iterator
      Iterator() method returns an Iterator obj. Iterator interface has below methods
      hasNext() , next(), remove() removes last ele, forEachRemaining(element -> doSomething(element))
      If we try to remove an ele from list after iterator created it will throw ConcurrentModificationException so use as below instead of l1.remove(new Integer(30))
      <pre> <code>
      Iterator<Integer> itr = l1.Iterator();
      while(itr.hasNext()) {
			int next = itr.next();
			if(next == 30) {
				itr.remove();
			}}
      </code> </pre>
      NOTE: If an element is added to the ArrayList after the iterator is created then also ConcurrentModificationException will be thrown.
7. ListIterator 
    Iterator Cons -> Can only iterator forward , cannot add/update list while iterating
    Methods:
    <pre>
      next(), hasNext()
      previous(), hasPrevious()
      nextIndex() ->returns index of ele returned by next next() call. returns list size if end of list
      previousIndex() -> -1 if beginning of list
      remove() -> removes last ele returned by next/previous. cannot call if add has been called after last call to next/previous
      set() -> can only be called if add/remove has not been called after the last call to next/previous
      add() -> element is inserted immediately before the element that would be returned by next(), after the element that would be returned by previous() (IF ANY)
    </pre>

    
