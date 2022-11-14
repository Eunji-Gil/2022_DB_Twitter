package twitter;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class JdbcConnection {
	final String ERROR = "Something wrong.";
	private String server;
	private String database;
	private String userName;
	private String password;

	public JdbcConnection() throws Exception {

		try {
			FileReader dbConfig = new FileReader("src/config/db.properties");
			Properties properties = new Properties();
			properties.load(dbConfig);
			server = properties.getProperty("server");
			database = properties.getProperty("database");
			userName = properties.getProperty("user_name");
			password = properties.getProperty("password");

			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	public Connection getConnection() {
		Connection connection = null;
		// Connect
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://" + server + "/" + database + "?useSSL=false&allowPublicKeyRetrieval=true", userName,
					password);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(ERROR);
		}

		return connection;
	}

	public boolean isExistingID(String ID) {
		String sql = " SELECT * FROM userinfo WHERE userId = '" + ID + "'";

		PreparedStatement preparedStatement = null;
		Connection connection = getConnection();
		ResultSet selectResult = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			selectResult = preparedStatement.executeQuery();
			if (selectResult.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// DB close
		try {
			if (connection != null) {
				connection.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
		}
		return false;
	}

	public int login(String ID, String password) {
		String sql = " SELECT * FROM userinfo WHERE userId = '" + ID + "'";

		PreparedStatement preparedStatement = null;
		Connection connection = getConnection();
		ResultSet selectResult = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			selectResult = preparedStatement.executeQuery();
			if (selectResult.next())
				if (selectResult.getString("userPassword").equalsIgnoreCase(password)) {
					return selectResult.getInt("userInfoIdx");
				}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// DB close
		try {
			if (connection != null) {
				connection.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
		}
		return -1;
	}

	//NOTIFICATION
	static int[] insertNoticeIdx(int userIdx, java.sql.Timestamp d) {
        Connection conn = null;
        int returnValue[]=new int[10]; //알림 10개까지만
        
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/TWITTER";
            conn = DriverManager.getConnection(url, "root", "2240");
            
            Statement stmt = null;
			ResultSet rs=null;
			
				while(true)
				{
					stmt=conn.createStatement();
					String s1="insert into twitter.Notifications(userIdx,notificationDate,notification) "
							+ "values ((select userIdx,createAt from Follow where followedUser="+userIdx
							+"and createAt>"+d+"),'FOLLOW');"; //입력된 시간을 기준 이후로 생긴 팔로우 알람
					stmt.executeQuery(s1);
					String s2="select last_insert_id();"; //알림 시작지점
					s1="insert into twitter.Notifications(userIdx,notificationDate,notification) "
							+ "values (select Post.userIdx, Comment.createAt from Comment"
							+ "natural join Post where Post.userIdx=(select Comment.postIdx"
							+ "from Comment where Comment.createAt >"+d+")),'COMMENT');"; //입력된 시간을 기준 이후로 생긴 comment 알람
					stmt.executeQuery(s1);
					
					rs=stmt.executeQuery(s2);
					int i=0;
					
					if(rs.next()) {
						returnValue[i]=rs.getInt(1);
						i++;
					}
					return returnValue;
				}

        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
            return returnValue;
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
            return returnValue;
        }
        finally{
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();            
                }
            }
            catch( SQLException e){
                e.printStackTrace();
                return returnValue;
            }
        }
    }
    
    static String returnNotification(int noticeIdx) { //noticeidx에 맞는 notice 내용
        Connection conn = null;
        String returnValue = ""; //이후 문제있으면 null로 수정가능
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/TWITTER";
            conn = DriverManager.getConnection(url, "root", "2240");
            
            Statement stmt = null;
			ResultSet rs=null;
			while(true)
			{
				stmt=conn.createStatement();
				String s3="select content from Notifications where notificationIdx='"+noticeIdx+"'"; //postIdx에 맞는 postContent 뽑아내는 쿼리문
				rs=stmt.executeQuery(s3);
				if(rs.next()) {
					returnValue=rs.getString(1);
				}
				return returnValue;
			}

        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
            return returnValue;
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
            return returnValue;
        }
        finally{
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();            
                }
            }
            catch( SQLException e){
                e.printStackTrace();
            }
        }
    }
    static java.sql.Timestamp returnNoticeDate(int noticeIdx)
    {
    	//noticeidx에 맞는 notice 시간
        Connection conn = null;
        java.sql.Timestamp returnValue = null; //timestamp는 사용자시점으로 좋지 못한 시간값을 출력해줌 //그래서 이후 문제없으면 Date형식으로 바꿔주는 걸 추천!!!!!!!!
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/TWITTER";
            conn = DriverManager.getConnection(url, "root", "2240");
            
            Statement stmt = null;
			ResultSet rs=null;
			while(true)
			{
				stmt=conn.createStatement();
				String s4="select content from Notifications where notificationIdx='"+noticeIdx+"'"; //noticeidx에 맞는 noticetime
				rs=stmt.executeQuery(s4);
				if(rs.next()) {
					returnValue=rs.getTimestamp(1); 
				}
				return returnValue;
			}

        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
            return returnValue;
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
            return returnValue;
        }
        finally{
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();            
                }
            }
            catch( SQLException e){
                e.printStackTrace();
            }
        }
    }
    //NOTIFICATION END
    
    //TREND
    static String[] trendHash() {
        Connection conn = null;
        String[] trendResult=new String[10];
        
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/TWITTER";
            conn = DriverManager.getConnection(url, "root", "2240");
            
            Statement stmt = null;
			ResultSet rs=null;
			
			
				while(true)
				{
					stmt=conn.createStatement();
					String s1="select hash from (select hash,count(hash) from HashTag order by count(hash) desc) A"; //상위 10개 hash 찾기
					rs=stmt.executeQuery(s1);
					
					int i=1;
					
					if(rs.next()) {
						do {
							trendResult[i]=rs.getString(1);
							i++;
							
							if(i>101)
								return trendResult;
							}while(rs.next());
					}
					else
					{
						System.out.println("NO TREND RESULT");
					}
					return trendResult;
				}

        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
            return trendResult;
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
            return trendResult;
        }
        finally{
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();            
                }
            }
            catch( SQLException e){
                e.printStackTrace();
                return trendResult;
            }
        }
    }
    
    static int returnHashNumber(String hash) { //특정 Hash에 대해서 hash갯수를 return하는 class
        Connection conn = null;
        int returnValue=0; //이후 문제있으면 null로 수정가능
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/TWITTER";
            conn = DriverManager.getConnection(url, "root", "2240");
            
            Statement stmt = null;
			ResultSet rs=null;
			while(true)
			{
				stmt=conn.createStatement();
				String s2="select count(hash) from HashTag where hash='"+hash+"'"; //hash에 맞는 Hash 갯수 뽑아내는 쿼리문
				rs=stmt.executeQuery(s2);
				if(rs.next()) {
					returnValue=rs.getInt(1);
				}
				return returnValue;
			}

        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
            return returnValue;
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
            return returnValue;
        }
        finally{
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();            
                }
            }
            catch( SQLException e){
                e.printStackTrace();
            }
        }
}
    //TREND END
    
    //SEARCH
    static int[] searchPostidx() {
        Connection conn = null;
        
        int[] searchResult=new int[100]; //초기값 0
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/TWITTER";
            conn = DriverManager.getConnection(url, "root", "2240");
            
            Statement stmt = null;
			ResultSet rs=null;
			
			
		 
			
				while(true)
				{
					Scanner in=new Scanner(System.in);
					
					String searchContents=null;
					
					System.out.println("Enter search keywords");
					searchContents=in.nextLine();
					in.close();
					
					stmt=conn.createStatement();
					String s1="select distinct postIdx from POST where content LIKE '%"+ searchContents +"%'"; //검색 쿼리문
					//쿼리문 수정예정
					rs=stmt.executeQuery(s1);
					int i=0; 
			
					
					if(rs.next()) {	
						do {
						searchResult[i]=rs.getInt(1);
						i++;
						
						if(i>101)
							return searchResult;
						}while(rs.next());
					}
					else
					{
						System.out.println("NO SEARCH RESULT");
					}
					
					stmt.close();
					rs.close();
					return searchResult;
				}

        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
            return searchResult;
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
            return searchResult;
        }
        finally{
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();            
                }
            }
            catch( SQLException e){
                e.printStackTrace();
                return searchResult;
            }
        }
    }
    
    static String returnUserID(int postIdx) { //postidx에 대해서 postUserId를 return하는 class
        Connection conn = null;
        String returnValue = ""; //이후 문제있으면 null로 수정가능
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/TWITTER";
            conn = DriverManager.getConnection(url, "root", "2240");
            
            Statement stmt = null;
			ResultSet rs=null;
			while(true)
			{
				stmt=conn.createStatement();
				String s2="select postUserIdx from POST where postIdx = '"+postIdx+"'"; //postIdx에 맞는 userid 뽑아내는 쿼리문
				rs=stmt.executeQuery(s2);
				if(rs.next()) {
					returnValue=rs.getString(1);
				}
				return returnValue;
			}

        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
            return returnValue;
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
            return returnValue;
        }
        finally{
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();            
                }
            }
            catch( SQLException e){
                e.printStackTrace();
            }
        }
			}
    
    static String returnPostContent(int postIdx) { //postidx에 대해서 post 내용물 return하는 class
        Connection conn = null;
        String returnValue = ""; //이후 문제있으면 null로 수정가능
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/TWITTER";
            conn = DriverManager.getConnection(url, "root", "2240");
            
            Statement stmt = null;
			ResultSet rs=null;
			while(true)
			{
				stmt=conn.createStatement();
				String s3="select content from Post where postUserIdx='"+postIdx+"'"; //postIdx에 맞는 postContent 뽑아내는 쿼리문
				rs=stmt.executeQuery(s3);
				if(rs.next()) {
					returnValue=rs.getString(1);
				}
				return returnValue;
			}

        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
            return returnValue;
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
            return returnValue;
        }
        finally{
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();            
                }
            }
            catch( SQLException e){
                e.printStackTrace();
            }
        }
			}
    //SEARCH END
    
}