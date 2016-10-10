package List;

import structure.ListInterface;

public class RecursiveLList<T> implements ListInterface<T> {
	
	private int size;
	private Node<T> head;

	public RecursiveLList()
	{
		head=null;
		size=0;		
	}
	/**
	 * Returns the number of elements in this {@link ListInterface}. This method
	 * runs in O(1) time.
	 * 
	 * @return the number of elements in this {@link ListInterface}
	 */
	public int size()
	{
		return size;
	}

	/**
	 * Adds an element to the front of this {@link ListInterface}. This method
	 * runs in O(1) time. For convenience, this method returns the
	 * {@link ListInterface} that was modified.
	 * 
	 * @param elem
	 *            the element to add
	 * @throws NullPointerException
	 *             if {@code elem} is {@code null}
	 * @return The modified {@link ListInterface}
	 */
	public ListInterface<T> insertFirst(T elem)
	{
		if(elem==null)
			throw new NullPointerException();
		else
		{
		if(head==null)
		{
			head=new Node<T>(elem);
			size++;
			return this;
		}
		else
		{
			Node<T> temp=new Node<T>(elem);
			temp.setLink(head);
			head=temp;
			size++;
			return this;
		}
		}
	}

	/**
	 * Adds an element to the end of this {@link ListInterface}. This method
	 * runs in O(size) time. For convenience, this method returns the
	 * {@link ListInterface} that was modified.
	 * 
	 * @param elem
	 *            the element to add
	 * @throws NullPointerException
	 *             if {@code elem} is {@code null}
	 * @return the modified {@link ListInterface}
	 */
	public ListInterface<T> insertLast(T elem)
	{
		if(isEmpty())
			head=new Node<T>(elem);
		else
		{
		findLastElement(head).setLink(new Node<T>(elem));
		}
		size++;
		return this;
		
	}

	/**
	 * Adds an element at the specified index such that a subsequent call to
	 * {@link ListInterface#get(int)} at {@code index} will return the inserted
	 * value. This method runs in O(index) time. For convenience, this method
	 * returns the {@link ListInterface} that was modified.
	 * 
	 * @param index
	 *            the index to add the element at
	 * @param elem
	 *            the element to add
	 * @throws NullPointerException
	 *             if {@code elem} is {@code null}
	 * @throws IndexOutOfBoundsException
	 *             if {@code index} is less than 0 or greater than
	 *             {@link ListInterface#size()}
	 * @return The modified {@link ListInterface}
	 */
	public ListInterface<T> insertAt(int index, T elem)
	{
		if(index>size||index<0)
			throw new IndexOutOfBoundsException();
		if(elem==null)			
			throw new NullPointerException();
		if(index==0)
		{
              insertFirst(elem);
		}
		else
		{
			if(index==size)
			{
				insertLast(elem);
			}
			else
			{
				Node<T> temp=findPreviousNode(head,index);
				Node<T> insertNode=new Node<T>(elem);
				insertNode.setLink(temp.getLink());
				temp.setLink(insertNode); 
				size++;
			}
		}
			
		
		return this;
	}

	/**
	 * Removes the first element from this {@link ListInterface} and returns it.
	 * This method runs in O(1) time.
	 * 
	 * @throws IllegalStateException
	 *             if the {@link ListInterface} is empty.
	 * @return the removed element
	 */
	public T removeFirst()
	{
		if(isEmpty())
			throw new IllegalStateException();
		Node<T> temp=head;
		head=head.getLink();
		size--;
		return temp.getInfo();
	}

	/**
	 * <p>
	 * Removes the last element from this {@link ListInterface} and returns it.
	 * This method runs in O(size) time.
	 *</p>
	 * 
	 * @throws IllegalStateException
	 *             if the {@link ListInterface} is empty.
	 * @return the removed element
	 */
	public T removeLast()
	{
		if(isEmpty())
			throw new IllegalStateException();
		else
		{
			if(size==1)
			{
				return removeFirst();
			}
			else
			{			
			Node<T> returnValue=findPreviousNode(head,size);
			T removedNode=returnValue.getLink().getInfo();
			returnValue.setLink(null);
			size--;
			return removedNode;
			}
		}
	}

	/**
	 * Removes the ith element in this {@link ListInterface} and returns it.
	 * This method runs in O(i) time.
	 * 
	 * @param i
	 *            the index of the element to remove
	 * @throws IndexOutOfBoundsException
	 *             if {@code i} is less than 0 or {@code i} is greater than or
	 *             equal to {@link ListInterface#size()}
	 * @return The removed element
	 */
	public T removeAt(int i)
	{
		if(i>=size||i<0)
			throw new IndexOutOfBoundsException();
		if(isEmpty())			
			throw new IndexOutOfBoundsException();
		if(i==0)
		{
            return  removeFirst();
		}
		else
		{
			if(i==size-1)
			{
				return removeLast();
			}
			else
			{
				Node<T> temp=findPreviousNode(head,i);
				Node<T> removedNode=temp.getLink();
				temp.setLink(temp.getLink().getLink()); 
				size--;
				return removedNode.getInfo();
				
			}
		}
	}

	/**
	 * Returns the first element in this {@link ListInterface}. This method runs
	 * in O(1) time.
	 * 
	 * @throws IllegalStateException
	 *             if the {@link ListInterface} is empty.
	 * @return the first element in this {@link ListInterface}.
	 */
	public T getFirst()
	{
		if(isEmpty())
			throw new  IllegalStateException();
		return head.getInfo();
	}

	/**
	 * Returns the last element in this {@link ListInterface}. This method runs
	 * in O(size) time.
	 * 
	 * @throws IllegalStateException
	 *             if the {@link ListInterface} is empty.
	 * @return the last element in this {@link ListInterface}.
	 */
	public T getLast()
	{
		if(isEmpty())
			throw new  IllegalStateException();
		Node<T> temp=head;
		return findLastElement(temp).getInfo();
	}

	/**
	 * Returns the ith element in this {@link ListInterface}. This method runs
	 * in O(i) time.
	 * 
	 * @param i
	 *            the index to lookup
	 * @throws IndexOutOfBoundsException
	 *             if {@code i} is less than 0 or {@code i} is greater than or
	 *             equal to {@link ListInterface#size()}
	 * @return the ith element in this {@link ListInterface}.
	 */
	public T get(int i)
	{
		if(i>=size||i<0)
			throw new IndexOutOfBoundsException();
		else
		{
			if(i==0)
			{
				return getFirst();
			}
			else
			{
				if(i==size-1)
					return getLast();
				else
				{
					return findPreviousNode(head,i).getLink().getInfo();
				}
			}
						
		}
	}

	/**
	 * Removes {@code elem} from this {@link ListInterface} if it exists. If
	 * multiple instances of {@code elem} exist in this {@link ListInterface}
	 * the one associated with the smallest index is removed. This method runs
	 * in O(size) time.
	 * 
	 * @param elem
	 *            the element to remove
	 * @return {@code true} if this {@link ListInterface} was altered and
	 *         {@code false} otherwise.
	 */
	public boolean remove(T elem)
	{
		if(contains(elem)!=-1)
		{
			removeAt(contains(elem));
			return true;
		}
		return false;
	}

	/**
	 * Returns the smallest index which contains {@code elem}. If there is no
	 * instance of {@code elem} in this {@link ListInterface} then -1 is
	 * returned. This method runs in O(size) time.
	 * 
	 * @param elem
	 *            the element to search for
	 * @return the smallest index which contains {@code elem} or -1 if
	 *         {@code elem} is not in this {@link ListInterface}
	 */
	public int contains(T elem)
	{
		return indexOfElement(head,elem,0);
	}

	/**
	 * Returns {@code true} if this {@link ListInterface} contains no elements
	 * and {@code false} otherwise. This method runs in O(1) time.
	 * 
	 * @return {@code true} if this {@link ListInterface} contains no elements
	 *         and {@code false} otherwise.
	 */
	public boolean isEmpty()
	{
		return head==null;
	}
	
	private Node<T> findLastElement(Node<T> temp)
	{
		if(temp.getLink()==null)
			return temp;
		else
		{
			temp=temp.getLink();
			return findLastElement(temp);
		}
	}
	
	private Node<T> findPreviousNode(Node<T> temp, int n)
	{
		if(n-1==0||temp.getLink().getLink()==null)
			return temp;
		else
		{
			temp=temp.getLink();
			return findPreviousNode(temp,n-1);
		}
	}
	private int indexOfElement(Node<T> temp, T elem,int index)
	{
		if(temp==null)
			return -1;
		else
		{
			if(temp.getInfo()==elem)
				return index;
			else
			{
				temp=temp.getLink();
				index+=1;
				return indexOfElement(temp,elem,index);
			}
		}
	}

}
