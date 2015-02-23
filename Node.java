public class Node<T>
{
	private T data;
	private Node<T> next;
	private Node<T> prev;

	private Node()
	{
		data = null;
		next = null;
		prev = null;
	}

	public Node(T data, Node<T> next, Node<T> prev)
	{
		this.data = data;
		this.next = next;
		this.prev = prev;
	}
	public Node(T data, Node<T> next)
	{
		this.data = data;
		this.next = next;
		this.prev = null;
	}

	public Node(Node<T> source)
	{
		data = source.data;
		next = source.next;
		prev = source.prev;
	}

	public T getData()
	{
		return data;
	}
	public Node<T> getNext()
	{
		return next;
	}
	public Node<T> getPrev()
	{
		return prev;
	}

	public T setData(T data)
	{
		return this.data = data;
	}
	public Node<T> setNext(Node<T> next)
	{
		return this.next = next;
	}
	public Node<T> setPrev(Node<T> prev)
	{
		return this.prev = prev;
	}
}
