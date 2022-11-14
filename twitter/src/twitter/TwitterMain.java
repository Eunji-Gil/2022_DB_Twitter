package twitter;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;

public class TwitterMain {
	static final String TRY_AGAIN = "Try again";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int menu = -1;
		int userId = -1;

		System.out.println("Loading...");
		System.out.println("Connect to Ninestagram");
		System.out.println("-----------------------------------------------");
		// TODO Auto-generated method stub
		while (menu != 0) {
			System.out.println("1. login 0. Exit");
			menu = scanner.nextInt();
			if (menu == 1) {
				userId = login();
				if (userId > 0) {
					System.out.println("Login Success!");
				} else {
					System.out.println(TRY_AGAIN);
				}
			} else if (menu == 0) {
				System.out.println("Loading...");
				break;
			} else {
				System.out.println(TRY_AGAIN);
			}
		}
	}

	public static int login() {
		Scanner scanner = new Scanner(System.in);
		int userId = -1;
		String ID = null, password = null;

		System.out.println("----------------------LOGIN---------------------");

		try {
			JdbcConnection jdbcConnection = new JdbcConnection();
			boolean flag = false;

			while (flag == false) {
				System.out.print("Enter a ID: ");
				ID = scanner.nextLine();
				flag = jdbcConnection.isExistingID(ID);
			}

			System.out.print("Enter a password: ");
			password = scanner.nextLine();
			userId = jdbcConnection.login(ID, password);

			if (userId < 0) {
				System.out.println(TRY_AGAIN);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TRY_AGAIN);
		}

		return userId;
	}
	
	public static void search()
	{
		String userID=null, postContent=null; //더 받아올 내용 있으면 추가하기
		
		int[] searchResult=new int[100];
		searchResult=twitterSearch.searchPostidx();
		for(int idx:searchResult)
		{
			if(idx!=0)
			{
			System.out.println("RESULTID:" + twitterSearch.returnUserID(idx));//postidx에 대한 userid 받아오기 (twitterSearch.returnUserID 사용)
			System.out.println("RESULTCONTENT:"+twitterSearch.returnPostContent(idx-1)); //postidx에 대한 content 받아오기 //idx-1 이면 오류가 안나는데 이유를 모르겠음
			}
		}
	}
	
	public static void trend()
	{
		String hashtag=null;
		int hashtagNum;
		
		String[] trendResult=new String[10];
		trendResult=twitterTrend.trendHash();
		for(String idx:trendResult)
		{
			if(!(idx==null||idx.isEmpty()))
			{
				System.out.println("RESULTHASH:"+idx);
				System.out.println("RESULTTREND:"+twitterTrend.returnHashNumber(idx));
			}
		}
	}
}