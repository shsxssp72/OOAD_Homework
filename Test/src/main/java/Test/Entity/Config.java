package Test.Entity;//!

import Test.Utility.Pair;
import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Config
{
	private static Map<String,Pair<Double,Double>> parameterRange=new HashMap<>();//参数名，下限，上限

	public static void Init()
	{
		parameterRange.put("CardDeckNumberPerPile",new Pair<>(1.0,6.0));
		parameterRange.put("PlayerNumber",new Pair<>(1.0,8.0));
		parameterRange.put("MaxCardNumber",new Pair<>(1.0,5.0));
		parameterRange.put("TargetPoint",new Pair<>(10.0,21.0));
		parameterRange.put("DealerPointInferiorLimit",new Pair<>(16.0,21.0));
		parameterRange.put("BonusRatio",new Pair<>(1.0,2.0));
		parameterRange.put("PlayerInitialMoney",new Pair<>(100.0,1000.0));
	}

	public static void readConfig()
	{
		try
		{
			String fileName="config.ini";
			Wini ini=new Wini(new File(fileName));
			int CardDeckNumberPerPile=ini.get("BlackjackConfig","CardDeckNumberPerPile",int.class);
			int PlayerNumber=ini.get("BlackjackConfig","PlayerNumber",int.class);
			int MaxCardNumber=ini.get("BlackjackConfig","MaxCardNumber",int.class);
			int TargetPoint=ini.get("BlackjackConfig","TargetPoint",int.class);
			int DealerPointInferiorLimit=ini.get("BlackjackConfig","DealerPointInferiorLimit",int.class);
			double BonusRatio=ini.get("BlackjackConfig","BonusRatio",double.class);
			double PlayerInitialMoney=ini.get("BlackjackConfig","PlayerInitialMoney",double.class);
			Parameters.setCardDeckNumberPerPile(CardDeckNumberPerPile);
			Parameters.setMaxCardNumber(MaxCardNumber);
			Parameters.setPlayerNumber(PlayerNumber);
			Parameters.setTargetPoint(TargetPoint);
			Parameters.setDealerPointInferiorLimit(DealerPointInferiorLimit);
			Parameters.setBonusRatio(BonusRatio);
			Parameters.setPlayerInitialMoney(PlayerInitialMoney);
		}
		catch(IOException e)
		{
			System.out.print("config.ini not found. Using default configuration.\n");
		}
	}

	public static void VerifyConfig()
	{
		if(Parameters.getCardDeckNumberPerPile()<parameterRange.get("CardDeckNumberPerPile").getKey())
		{
			System.out.print("WARNING: CardDeckNumberPerPile underflow. Using default configuration "+parameterRange
					.get("CardDeckNumberPerPile").getKey()+"\n");
		}
		if(Parameters.getPlayerNumber()<parameterRange.get("PlayerNumber").getKey())
		{
			System.out.print("WARNING: PlayerNumber underflow. Using default configuration "+parameterRange.get("PlayerNumber")
					.getKey()+"\n");
		}
		if(Parameters.getMaxCardNumber()<parameterRange.get("MaxCardNumber").getKey())
		{
			System.out.print("WARNING: MaxCardNumber underflow. Using default configuration "+parameterRange.get("MaxCardNumber")
					.getKey()+"\n");
		}
		if(Parameters.getTargetPoint()<parameterRange.get("TargetPoint").getKey())
		{
			System.out.print("WARNING: TargetPoint underflow. Using default configuration "+parameterRange.get("TargetPoint")
					.getKey()+"\n");
		}
		if(Parameters.getDealerPointInferiorLimit()<parameterRange.get("DealerPointInferiorLimit").getKey())
		{
			System.out.print("WARNING: DealerPointInferiorLimit underflow. Using default configuration "+parameterRange
					.get("DealerPointInferiorLimit").getKey()+"\n");
		}
		if(Parameters.getBonusRatio()<parameterRange.get("BonusRatio").getKey())
		{
			System.out.print("WARNING: BonusRatio underflow. Using default configuration "+parameterRange.get("BonusRatio")
					.getKey()+"\n");
		}
		if(Parameters.getPlayerInitialMoney()<parameterRange.get("PlayerInitialMoney").getKey())
		{
			System.out.print("WARNING: PlayerInitialMoney underflow. Using default configuration "+parameterRange
					.get("PlayerInitialMoney").getKey()+"\n");
		}


		if(Parameters.getCardDeckNumberPerPile()>parameterRange.get("CardDeckNumberPerPile").getValue())
		{
			System.out.print("WARNING: CardDeckNumberPerPile overflow. Using default configuration "+parameterRange
					.get("CardDeckNumberPerPile").getValue()+"\n");
		}
		if(Parameters.getPlayerNumber()>parameterRange.get("PlayerNumber").getValue())
		{
			System.out.print("WARNING: PlayerNumber overflow. Using default configuration "+parameterRange.get("PlayerNumber")
					.getValue()+"\n");
		}
		if(Parameters.getMaxCardNumber()>parameterRange.get("MaxCardNumber").getValue())
		{
			System.out.print("WARNING: MaxCardNumber overflow. Using default configuration "+parameterRange.get("MaxCardNumber")
					.getValue()+"\n");
		}
		if(Parameters.getTargetPoint()>parameterRange.get("TargetPoint").getValue())
		{
			System.out.print("WARNING: TargetPoint overflow. Using default configuration "+parameterRange.get("TargetPoint")
					.getValue()+"\n");
		}
		if(Parameters.getDealerPointInferiorLimit()>parameterRange.get("DealerPointInferiorLimit").getValue())
		{
			System.out.print("WARNING: DealerPointInferiorLimit overflow. Using default configuration "+parameterRange
					.get("DealerPointInferiorLimit").getValue()+"\n");
		}
		if(Parameters.getBonusRatio()>parameterRange.get("BonusRatio").getValue())
		{
			System.out.print("WARNING: BonusRatio overflow. Using default configuration "+parameterRange.get("BonusRatio")
					.getValue()+"\n");
		}
		if(Parameters.getPlayerInitialMoney()>parameterRange.get("PlayerInitialMoney").getValue())
		{
			System.out.print("WARNING: PlayerInitialMoney overflow. Using default configuration "+parameterRange
					.get("PlayerInitialMoney").getValue()+"\n");
		}


	}
}
