package com;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import java.util.Map.Entry;

public class Problem2 {
	public int solution(String S){
		final int callLimit = 300;
		final int belowLimitCost = 3;
		final int aboveLimitCost = 150;
		Map<Integer, Integer> callMap = new HashMap<>();
		String logs[] = S.split("\n");
		for (String callLog : logs) {
			//System.out.println(callLog);
			int totalCallPrice =0;
			String logDetails[] = callLog.split(",");
			String duration = logDetails[0].trim();
			int phoneNum = Integer.parseInt(logDetails[1].trim().replace("-", ""));
			String timeParts[] = duration.split(":");
			int hours = Integer.parseInt(timeParts[0]);
			int minutes = Integer.parseInt(timeParts[1]);
			int secs = Integer.parseInt(timeParts[2]);
			int totalSecs = (hours*3600)+(minutes*60)+secs;
			int totalMin = (hours*60)+minutes+((secs>0)?1:0);
			if(totalSecs<callLimit){
				totalCallPrice = totalSecs*belowLimitCost;
			}
			else if(totalSecs>=callLimit){
				totalCallPrice = totalMin*aboveLimitCost;
			}
			callMap.put(phoneNum, ((callMap.get(phoneNum)==null)?0:callMap.get(phoneNum))+totalCallPrice);
		}
		
		
		//System.out.println(callMap);
		TreeSet<Integer> price = new TreeSet<>(callMap.values());
		int maxValue =price.last(); 
		//System.out.println(maxValue);
		TreeSet<Integer> phoneNums = new TreeSet();
		Iterator<Entry<Integer, Integer>> itr = callMap.entrySet().iterator();
		while(itr.hasNext()){
			Entry entry = itr.next();
			if((int)entry.getValue()==maxValue) phoneNums.add((Integer)entry.getKey());
		}
	
		callMap.put(phoneNums.first(), 0);
		int totalPrice = 0;
		for (Integer integer : callMap.values()) {
			totalPrice+=integer;
		}
		//System.out.println(totalPrice);
		return totalPrice;
	}
	
	public static void main(String[] args) {
		Problem2 test=new Problem2();
		String a =  "00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090";
		test.solution(a);
	}
}
