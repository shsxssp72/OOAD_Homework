package OOAD_Lab03_gizmo.Utilities;

import java.util.List;

public interface Observable
{
	default void RegisterObserver(Observer observer)
	{
		if(observer==null)
			throw new NullPointerException();
		if(!getObserverList().contains(observer))
			getObserverList().add(observer);
	}
	default void UnregisterObserver(Observer observer)
	{
		if(observer==null)
			throw new NullPointerException();
		getObserverList().remove(observer);
	}

	default void NotifyAll()
	{
		for(Observer observer: getObserverList())
		{
			observer.update();
		}
	}

	List<Observer> getObserverList();
}
