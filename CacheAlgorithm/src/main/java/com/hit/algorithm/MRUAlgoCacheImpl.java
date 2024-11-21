package com.hit.algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class MRUAlgoCacheImpl<K,V> extends AbstractAlgoCache<K,V> 
{
	 public MRUAlgoCacheImpl(int capacity) throws Exception 
	 {
		super(capacity);
		pLinked=new LinkedList<V>();
		map=new HashMap<K,V>();
	}
	 
	@Override
	public V getElement(K key)
	{
		if(map.containsKey(key)) 
		{
			V value=map.get(key);
			removeElement(key);
			pLinked.addLast(value);
			map.put(key,value);
			return value;
		}
		return null;
	}

	@Override
	public V putElement(K key, V value)
	{
		V replaced = value;
		if(pLinked.size()+1>getCapacity())
		{
			if(map.containsKey(key))
			{
				replaced=value;
				removeElement(key);
				pLinked.addLast(value);
				map.put(key,value);
			}
			else
			{
				replaced=pLinked.getLast();
				map.remove(pLinked.getLast());
				pLinked.removeLast();
				pLinked.addLast(value);
				map.put(key,value);	
			}
		}
		else
		{
			pLinked.addLast(value);
			map.put(key, value);
		}
		return replaced;
	}

	@Override
	public void removeElement(K key)
	{
		V last=map.get(key);
		map.remove(key);
		pLinked.remove(last);
	}
	
	public void printList() 
	{
		System.out.print("MRU result\n");
		 Iterator<V> it=pLinked.iterator();
		while(it.hasNext())
		{
			System.out.print(it.next()+" ");
		}
		System.out.print("\n");
		
	}
	
	@Override
	public String getCacheContent() 
	{
		String content="";
		Iterator<V> it=pLinked.iterator();
		while(it.hasNext())
		{
			content+=it.next().toString()+"\n";
		}
		return content;
	}
	
}
