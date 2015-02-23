import java.util.Iterator;
public class DoublyCircularList<T extends Comparable<T>> extends LinkedList<T>implements Iterable<T>
{
	Node<T> first;
	int size;
	public DoublyCircularList(){
		first = null;
		size = 0;
	}
	public DoublyCircularList( DoublyCircularList<T> source)
	{
		if(source.first != null){
			Node<T> at = source.first;
			insert(at.getData());
			at = at.getNext();
			while(at!= source.first)
			{
			insert(at.getData());
			at = at.getNext();
			}
		}
	}
	@Override
	public boolean insert(T value)
	{
		Node<T> newNode = new Node<T>(value,null,null);
		if(first == null)
		{
			size++;
			first = newNode;
			first.setPrev(first);
			first.setNext(first);
			return true;
		}
		size++;
		Node<T> temp = first.getPrev();
		temp.setNext(newNode);
		newNode.setPrev(temp);
		newNode.setNext(first);
		first.setPrev(newNode);
		return true;
	}
	@Override
	public boolean insertFront(T value)
	{
		Node<T> newNode =  new Node<T>(value,null,null);
		if(first == null)
		{
			size++;
			first = newNode;
                        first.setPrev(first);
                        first.setNext(first);
			return true;
                }
		size++;
                Node<T> temp = first.getPrev();
                temp.setNext(newNode);
                newNode.setPrev(temp);
                newNode.setNext(first);
		first = newNode;
                return true;
	}
	public class iterator implements Iterator<T>
	{
		boolean check;
		int  counter = 0;
		private Node<T> pos;
		private Node<T> prev;
		public iterator()
		{
			prev = null;
			pos = first;
		}
	  	public boolean hasNext()
		{
			return (counter != size);
		}
		public T next()
		{	check = true;
			Node<T> temp = pos;
			prev = pos;
			pos = pos.getNext();
			counter++;
			return temp.getData();
		}
		public void insert(T value)
		{
			Node<T> newNode = new Node<T>(value,null,null);
         if(first == null)
			{
            size++;
            newNode.setPrev(newNode);
            newNode.setNext(newNode);
            pos = newNode;
				first = newNode;
				return;
          }
          size++;
          newNode.setNext(pos);
          newNode.setPrev(pos.getPrev());
			 pos.getPrev().setNext(newNode);
          pos.setPrev(newNode);
			if(pos == first){
				first = newNode;

			}
			pos = newNode;
		}
		public boolean prevoius()
		{
			pos=pos.getPrev();
			counter--;
			return true;
		}
		public Node<T> peek()
		{
			return pos;
		}
		public void promote()
		{
			T temp = pos.getData();
			next();
			remove();
			pos = first;
			insert(temp);
			counter = 0;
		}
		public int indexOf()
		{
		   if(counter == size)
			{
				return -1;
			}
			return counter;
		}
		public void remove() throws IllegalStateException
		{
		  if(check == false){
			Node<T> newNode =  null;
			newNode.setNext(newNode);

		  }
			if(pos == first)
			{
				if(prev == null)
				{
					throw new IllegalStateException();
				}
			}
			if(size == 1)
			{
				first = null;
				prev = null;
				pos = null;
				size = 0;
				return;
			}
			Node<T> prev = pos.getPrev();
         size--;
         if(prev == first)
         {
            first = pos;
         }
         Node<T> superPrev = prev.getPrev();
         pos.setPrev(superPrev);
         if(superPrev != null)
         {
            superPrev.setNext(pos);
         }
         this.prev = pos.getPrev();
			check = false;
		}
	}
	public Iterator<T> iterator()
	{
		return new iterator();
	}
	@Override
	public boolean insertInOrder(T value)
	{
      Node<T> newNode =  new Node<T>(value,null,null);
      if(first == null)
		{
         size++;
			first = newNode;
         first.setPrev(first);
         first.setNext(first);
         return true;
      }
		if(value.compareTo(first.getData())<0)
		{
			return insertFront(value);

		}
		if(value.compareTo(first.getPrev().getData())>0)
		{
			return insert(value);
		}
		size++;
      while(newNode.getNext() != first)
		{
         if(value.compareTo(newNode.getData())<= 0)
			{
            Node<T>newestNode = new Node<T>(value,null,null);
            newNode.setNext(newestNode);
				newNode.setPrev(newestNode.getPrev());
				newestNode.setPrev(newNode);
				return true;
          }
			newNode.getNext();
		}
		return false;
   }
	@Override
	public boolean remove(T value)
	{
		if(first == null)
		{
			return false;
		}
		Node<T> newNode = first;
		while(newNode.getNext() != first)
		{
			if(newNode.getData().equals(value))
			{
				Node<T>prev = newNode.getPrev();
				Node<T>next = newNode.getNext();
				prev.setNext(next);
				next .setPrev(prev);
				if(newNode == first)
				{
					if(size == 1){
						first = null;
					}
					else{
						first = first.getNext();
					}
				}
				size--;
				return true;
			}
		}
		if(newNode.getData().equals(value))
		{
	      Node<T>prev = newNode.getPrev();
         Node<T>next = newNode.getNext();
	      prev.setNext(next);
         next .setPrev(prev);
		}
		return false;
	}
	public int find(T value,int index)
	{
		int counter = 0;
      Node<T> newNode = first;
		if(index > size)
		{
		   return -1;
		}
		for(int i = 0;i<index ;i++)
		{
			newNode = newNode.getNext();
		}
		for(int i = index;i<size;i++)
		{
			if(newNode.getData().equals(value))
			{
				return counter;
			}
			counter++;
			newNode = newNode.getNext();
		}
		return -1;
	}
	public DoublyCircularList<T> subset(int begin,int end)
	{
		DoublyCircularList<T> mysubset = new DoublyCircularList<T>();
		Node<T> start = first;
		int count = 0;
		int nomore =0;
		if(( first == null)||(end <0 ) || (end <= begin)){
			return mysubset;
		}
		if (begin<0){
			begin = 0;
		}
		while(start != null)
		{
			if(nomore >= size)
			{
				return  mysubset;
			}
			if((count >= begin)&&(count<end))
			{
				nomore++;
				mysubset.insert(start.getData());
			}
			if(count >= end)
			{
				return mysubset;
			}
			count++;
			start = start.getNext();
		}
		return mysubset;
	}
	public String toString()
	{
		String result = "";
		Node<T>newNode = first;
		if(first == null)
		{
			return null;
		}
		while(newNode.getNext() != first)
		{
			result += newNode.getData() + " ";
			newNode=newNode.getNext();
		}
		return result;
	}
   public static void main (String[] args){

      DoublyCircularList<String> myLink = new DoublyCircularList<String>();
      for(String word : myLink)
      {
         System.out.printf("%s\n", word);
      }
      DoublyCircularList<String>.iterator iter = myLink.new iterator();
      iter.insert("nana");
      iter.insert("taco");
      iter.insert("puu");
      iter.insert("2");
      iter.next();
      iter.next();
      for(String word : myLink)
      {
         System.out.printf("%s\n", word);
      }
      iter.promote();
      iter.promote();
      for(String word : myLink)
      {
          System.out.printf("%s\n", word);
      }
      iter.insert("????");
      for(String word : myLink)
      {
         System.out.printf("%s\n", word);
      }
      while(iter.hasNext())
      {
           System.out.printf("%d   %s \n", iter.indexOf(), iter.next());
      }
      if(iter.hasNext()){
         System.out.printf("%d   %s \n", iter.indexOf(), iter.next());
      }
         System.out.printf("%d    \n", iter.indexOf());
         myLink.insertInOrder("zebracake");
         myLink.insertInOrder("yellow");
         myLink.insertInOrder("turnip");
         myLink.insertInOrder("moocow");
         myLink.insertInOrder("banana");
         myLink.insertInOrder("apple");
         myLink.insert("cabage");
         myLink.insertFront("carrosdfgt ");
         myLink.insertFront("test ");
         myLink.insert("carrot");
         myLink.insert("carrot");
         myLink.insert("carrot--");
         myLink.insert("carrot--------");
         DoublyCircularList<String> sub = new DoublyCircularList<String>();
         DoublyCircularList<String> sub2 = new DoublyCircularList<String>(sub);
         sub2 = myLink.subset(13,100);
         System.out.printf("%s\n", myLink);
         System.out.printf("%s\n", sub2);
         System.out.printf("%d\n", myLink.find("carrot",1));
   }
}
