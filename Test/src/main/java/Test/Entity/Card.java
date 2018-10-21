package Test.Entity;

public class Card
{
	enum CardPatternType
	{
		Club,Diamond,Heart,Spade
	}

	public Card(CardPatternType pattern,int figure,int faceValue,int parentIndex)
	{
		this.pattern=pattern;
		this.figure=figure;
		this.faceValue=faceValue;
		this.parentIndex=parentIndex;
	}

	public CardPatternType getPattern()
	{
		return pattern;
	}

	public int getFigure()
	{
		return figure;
	}

	public int getFaceValue()
	{
		return faceValue;
	}

	public int getParentIndex()
	{
		return parentIndex;
	}

	@Override
	public String toString()
	{
		String result;
		switch(pattern)
		{
			case Club:
				result="Club ";
				break;
			case Diamond:
				result="Diamond ";
				break;
			case Heart:
				result="Heart ";
				break;
			case Spade:
				result="Spade ";
				break;
			default:
				result="";
				break;
		}
		if(faceValue<11)
		{
			result+=faceValue;
		}
		else if(faceValue==11)
		{
			result+="J";
		}
		else if(faceValue==12)
		{
			result+="Q";
		}
		else
		{
			result+="K";
		}
		return result;
	}

	private CardPatternType pattern;
	private int figure;
	private int faceValue;
	private int parentIndex;
}
