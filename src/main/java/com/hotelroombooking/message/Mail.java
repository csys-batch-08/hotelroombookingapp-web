package com.hotelroombooking.message;

import com.hotelroombooking.model.MeetingHallTransaction;
import com.hotelroombooking.model.RoomTransaction;
import com.hotelroombooking.model.WeddingHallTransaction;

public class Mail 
{
	public static String bookRoomMail(RoomTransaction roomTransObj)
	{
		String message="Welcome HR Residensy"+"\nYour Booking is Confirmed"+"\nRoom Number :"+roomTransObj.getroomNumber()
		+"\nCheck-In :"+roomTransObj.getCheckIn()+"\nCheck-Out :"+roomTransObj.getCheckOut()+"\nCategory :"+roomTransObj.getCategory()+
		"\nLocation :"+roomTransObj.getLocation();
		return message;
	}
	
	public static String cancelRoomMail(RoomTransaction roomTransObj)
	{
		String message="Welcome HR Residensy"+"\nYour Booking is Cancelled"+"\nRoom Number :"+roomTransObj.getroomNumber()+"\nYour amount"
				+ " will be credited back in 24 hrs.";
		return message;
	}
	
	public static String updateRoomMail(RoomTransaction roomTransObj)
	{
		String message="Welcome HR Residensy"+"\nYour Booking is Updated"+"\nRoom Number :"+roomTransObj.getroomNumber()
		+"\nCheck-In :"+roomTransObj.getCheckIn()+"\nCheck-Out :"+roomTransObj.getCheckOut()+"\nCategory :"+roomTransObj.getCategory()+
		"\nLocation :"+roomTransObj.getLocation()+"\nYour amount"
				+ " will be credited back in 24 hrs.";
		return message;
	}
	
	
	
	
	public static String bookWeddingHallMail(WeddingHallTransaction weddingHallTransObj)
	{
		String message="Welcome HR Residensy"+"\nYour Booking is Confirmed"+"\nWedding Hall Number :"+weddingHallTransObj.getroomNumber()
		+"\nCheck-In :"+weddingHallTransObj.getCheckIn()+"\nCheck-Out :"+weddingHallTransObj.getCheckOut()+"\nCategory :"+weddingHallTransObj.getCategory()+
		"\nLocation :"+weddingHallTransObj.getLocation();
		return message;
	}
	
	public static String cancelWeddingHallMail(WeddingHallTransaction weddingHallTransObj)
	{
		String message="Welcome HR Residensy"+"\nYour Booking is Cancelled"+"\nWedding Hall Number :"+weddingHallTransObj.getroomNumber()+"\nYour amount"
				+ " will be credited back in 24 hrs.";
		return message;
	}
	
	public static String updateWeddingHallMail(WeddingHallTransaction weddingHallTransObj)
	{
		String message="Welcome HR Residensy"+"\nYour Booking is Updated"+"\nWedding Hall Number :"+weddingHallTransObj.getroomNumber()
		+"\nCheck-In :"+weddingHallTransObj.getCheckIn()+"\nCheck-Out :"+weddingHallTransObj.getCheckOut()+"\nCategory :"+weddingHallTransObj.getCategory()+
		"\nLocation :"+weddingHallTransObj.getLocation()+"\nYour amount"
				+ " will be credited back in 24 hrs.";
		return message;
	}
	
	
	
	
	
	
	
	public static String bookMeetingHallMail(MeetingHallTransaction meetingHallTransObj)
	{
		String message="Welcome HR Residensy"+"\nYour Booking is Confirmed"+"\nMeeting Hall Number :"+meetingHallTransObj.getroomNumber()
		+"\nCheck-In :"+meetingHallTransObj.getCheckIn()+"\nCheck-Out :"+meetingHallTransObj.getCheckOut()+"\nCategory :"+meetingHallTransObj.getCategory()+
		"\nLocation :"+meetingHallTransObj.getLocation();
		return message;
	}
	
	public static String cancelMeetingHallMail(MeetingHallTransaction meetingHallTransObj)
	{
		String message="Welcome HR Residensy"+"\nYour Booking is Cancelled"+"\nMeeting Hall Number :"+meetingHallTransObj.getroomNumber()+"\nYour amount"
				+ " will be credited back in 24 hrs.";
		return message;
	}
	
	public static String updateMeetingHallMail(MeetingHallTransaction meetingHallTransObj)
	{
		String message="Welcome HR Residensy"+"\nYour Booking is Updated"+"\nWedding Hall Number :"+meetingHallTransObj.getroomNumber()
		+"\nCheck-In :"+meetingHallTransObj.getCheckIn()+"\nCheck-Out :"+meetingHallTransObj.getCheckOut()+"\nCategory :"+meetingHallTransObj.getCategory()+
		"\nLocation :"+meetingHallTransObj.getLocation()+"\nYour amount"
				+ " will be credited back in 24 hrs.";
		return message;
	}
}
