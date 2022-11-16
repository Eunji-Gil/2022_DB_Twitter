package temp;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class JdbcConnection {
    static final String ERROR = "Error Occurred";
    private static String server;
    private static String database;
    private static String userName;
    private static String password;

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

    // DB object
    public static Connection getConnection() {
        Connection connection = null;
        // Connect
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database
                    + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ERROR);
        }

        return connection;
    }

    ///////////////////////////////////// Login Start
    ///////////////////////////////////// ///////////////////////////////////////
    // userID
    public boolean existUserID(String userID) {
        String sql = " SELECT * FROM userinfo WHERE userId = '" + userID + "'";

        PreparedStatement pstm = null;
        Connection connect = getConnection();
        ResultSet rs = null;
        try {
            pstm = connect.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // DB close
        try {
            if (connect != null) {
                connect.close();
            }
            if (pstm != null) {
                pstm.close();
            }
        } catch (SQLException e) {
        }
        return false;
    }

    // Email
    public boolean existEmail(String email) {
        String sql = " SELECT * FROM userinfo WHERE userEmail = '" + email + "'";

        PreparedStatement pstm = null;
        Connection connect = getConnection();
        ResultSet rs = null;
        try {
            pstm = connect.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // DB close
        try {
            if (connect != null) {
                connect.close();
            }
            if (pstm != null) {
                pstm.close();
            }
        } catch (SQLException e) {
        }
        return false;
    }

    // phone number
    public boolean existPhoneNumber(String phoneNumber) {
        String sql = " SELECT * FROM userinfo WHERE phoneNumber = '" + phoneNumber + "'";

        PreparedStatement pstm = null;
        Connection connect = getConnection();
        ResultSet rs = null;
        try {
            pstm = connect.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // DB close
        try {
            if (connect != null) {
                connect.close();
            }
            if (pstm != null) {
                pstm.close();
            }
        } catch (SQLException e) {
        }
        return false;
    }

    // userConfirm Email
    public boolean userConfirm(String userID, String email) {
        String sql = " SELECT * FROM userinfo WHERE userEmail = '" + email + "'";

        PreparedStatement pstm = null;
        Connection connect = getConnection();
        ResultSet rs = null;
        try {
            pstm = connect.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                if (rs.getString("userID").equalsIgnoreCase(userID)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // DB close
        try {
            if (connect != null) {
                connect.close();
            }
            if (pstm != null) {
                pstm.close();
            }
        } catch (SQLException e) {
        }
        return false;
    }

    // 휴대폰 번호 회원가입
    public boolean singUpPhoneNumber(String userID, String phoneNumber, String userPassword, String userName) {
        String sql = " INSERT INTO userinfo(userId, phoneNumber, userPassword, userName) " + " VALUES(?, ?, ?, ?) ";

        Connection connect = getConnection();
        PreparedStatement ptsm = null;

        int count = 0;

        try {
            ptsm = connect.prepareStatement(sql);
            ptsm.setString(1, userID);
            ptsm.setString(2, phoneNumber);
            ptsm.setString(3, userPassword);
            ptsm.setString(4, userName);

            count = ptsm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ERROR);
        } finally {
            // DB close
            try {
                if (connect != null) {
                    connect.close();
                }
                if (ptsm != null) {
                    ptsm.close();
                }
            } catch (SQLException e) {
                System.out.println(ERROR);
            }
        }
        return count > 0 ? true : false;
    }

    // 이메일 회원가입
    public boolean signUp(String userID, String email, String userPassword, String userName) {
        String sql = " INSERT INTO userinfo(userId, userEmail, userPassword, userName) " + " VALUES(?, ?, ?, ?) ";

        Connection connect = getConnection();
        PreparedStatement ptsm = null;

        int count = 0;

        try {
            ptsm = connect.prepareStatement(sql);
            ptsm.setString(1, userID);
            ptsm.setString(2, email);
            ptsm.setString(3, userPassword);
            ptsm.setString(4, userName);

            count = ptsm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ERROR);
        } finally {
            // DB close
            try {
                if (connect != null) {
                    connect.close();
                }
                if (ptsm != null) {
                    ptsm.close();
                }
            } catch (SQLException e) {
                System.out.println(ERROR);
            }
        }
        return count > 0 ? true : false;
    }

    public boolean changeUserPassword(String userID, String newUserPassword) {
        String sql = "UPDATE userInfo SET userPassword = '" + newUserPassword + "' WHERE userId = '" + userID + "'";

        Connection connect = getConnection();
        PreparedStatement ptsm = null;

        int count = 0;

        try {
            ptsm = connect.prepareStatement(sql);
            count = ptsm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // DB close
            try {
                if (connect != null) {
                    connect.close();
                }
                if (ptsm != null) {
                    ptsm.close();
                }
            } catch (SQLException e) {
                System.out.println(ERROR);
            }
        }
        return count > 0 ? true : false;
    }

    public boolean changeUserPassword(int userIdx, String newUserPassword) {
        String sql = "UPDATE userinfo SET userPassword = '" + newUserPassword + "' WHERE userInfoIdx = " + userIdx;

        Connection connect = getConnection();
        PreparedStatement ptsm = null;

        int count = 0;
        try {
            ptsm = connect.prepareStatement(sql);
            count = ptsm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // DB close
            try {
                if (connect != null) {
                    connect.close();
                }
                if (ptsm != null) {
                    ptsm.close();
                }
            } catch (SQLException e) {
                System.out.println(ERROR);
            }
        }
        return count > 0 ? true : false;
    }

    public int login(String userID, String password) {
        String sql = " SELECT * FROM userinfo WHERE userId = '" + userID + "'";

        PreparedStatement ptsm = null;
        PreparedStatement ptsm2 = null;
        Connection connect = getConnection();

        ResultSet rs = null;
        ResultSet rs2 = null;

        int userIdx = 0;

        try {
            ptsm = connect.prepareStatement(sql);
            ptsm2 = connect.prepareStatement(sql);

            rs = ptsm.executeQuery();
            rs2 = ptsm2.executeQuery(sql);

            if (rs.next())
                if (rs.getString("userPassword").equalsIgnoreCase(password)) {
                    return rs.getInt("userInfoIdx");
                }

//            userIdx = rs2.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // DB close
        try {
            if (connect != null) {
                connect.close();
            }
            if (ptsm != null) {
                ptsm.close();
            }
        } catch (SQLException e) {
        }

        return userIdx;
    }

    ///////////////////////////////////// Login Finish
    ///////////////////////////////////// ///////////////////////////////////////

    ///////////////////////////////////// ProFile Start
    ///////////////////////////////////// ///////////////////////////////////////
    public void postList() {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "select postIdx, posUserIdx, content, photAddress from Post left join \n"
                + "(select postIdx as postIdx2, photAddress from postPhoto natural join Photo) PhotoAd\n"
                + "on Post.postIdx = PhotoAd.postIdx2; ";

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                int postIdx = resultSet.getInt(1);
                int posUseridx = resultSet.getInt(2);
                String content = resultSet.getString(3);
                if (resultSet.wasNull())
                    content = "null";
                String photoAddress = resultSet.getString(4);
                if (resultSet.wasNull())
                    photoAddress = "null";
                System.out.println(postIdx + " \t" + posUseridx + "\t" + content + "\t" + photoAddress);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ERROR);
        } finally {
            // DB close
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(ERROR);
            }
        }
    }

    public void myPostList(int userIdx) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "select postIdx, posUserIdx, content, photAddress from Post left join \n"
                + "(select postIdx as postIdx2, photAddress from postPhoto natural join Photo) PhotoAd\n"
                + "on Post.postIdx = PhotoAd.postIdx2 and Post.posUserIdx =" + userIdx + ";";

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                int postIdx = resultSet.getInt(1);
                int posUseridx = resultSet.getInt(2);
                String content = resultSet.getString(3);
                if (resultSet.wasNull())
                    content = "null";
                String photoAddress = resultSet.getString(4);
                if (resultSet.wasNull())
                    photoAddress = "null";
                System.out.println(postIdx + " \t" + posUseridx + "\t" + content + "\t" + photoAddress);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ERROR);
        } finally {
            // DB close
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(ERROR);
            }
        }
    }

    // Upload Post
    public int uploadPost(int posUserIdx, String content) {
        int PK = 0;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = " INSERT INTO post(posUserIdx, content) " + " VALUES( ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, posUserIdx);
            preparedStatement.setString(2, content);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                PK = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ERROR);
            return 0;
        } finally {
            // DB close
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(ERROR);
            }
        }
        return PK;
    }

    //
    public void HashTag(int postIdx, String Hash) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = " INSERT INTO HashTag(hash,postidx) " + " VALUES( ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Hash);
            preparedStatement.setInt(2, postIdx);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ERROR);
        } finally {
            // DB close
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(ERROR);
            }
        }
    }

    // Upload Photo
    public int Photo(String photAddress) {
        int PK = 0;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = " INSERT INTO Photo(photAddress) " + " VALUES(?) ";
        try {
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, photAddress);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                PK = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ERROR);
            return 0;
        } finally {
            // DB close
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(ERROR);
            }
        }
        return PK;
    }

    // Upload PostPhoto
    public int PostPhoto(int photoIdx, int postIdx) {
        int PK = 0;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = " INSERT INTO PostPhoto(photoIdx, postIdx) " + " VALUES(?, ?) ";

        try {
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, photoIdx);
            preparedStatement.setInt(2, postIdx);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                PK = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ERROR);
            return 0;
        } finally {
            // DB close
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(ERROR);
            }
        }
        return PK;
    }

    // Delete post
    public void deleteMyPost(int myPostIdx) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "delete from Post where postIdx = " + myPostIdx + ";";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ERROR);
        } finally {
            // DB close
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(ERROR);
            }
        }
    }

    //
    public void PostLike(int postIdx, int userIdx) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = " INSERT INTO PostLike(postIdx,userIdx) " + "VALUES(?, ?);";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, postIdx);
            preparedStatement.setInt(2, userIdx);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ERROR);
        } finally {
            // DB close
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(ERROR);
            }
        }
    }

    public int PostLikeNum(int postIdx) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int postLikeCount = 0;

        String sql = "select count(postIdx) from PostLike group by postIdx having postIdx = " + postIdx + ";";

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                postLikeCount = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ERROR);
        } finally {
            // DB close
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(ERROR);
            }
        }
        return postLikeCount;
    }

    public void PostLIkeList(int postIdx) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "select userIdx from PostLike where postIdx = " + postIdx + ";";

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                int userIdx = resultSet.getInt(1);

                System.out.println(userIdx);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ERROR);
        } finally {
            // DB close
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(ERROR);
            }
        }
    }

    // 원하는 포스트의 댓글 추가
    public void addComment(int postIdx, String content, int userIdx) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = " INSERT INTO Comment(userIdx,content,postIdx) " + "VALUES(?, ?,?);";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userIdx);
            preparedStatement.setString(2, content);
            preparedStatement.setInt(3, postIdx);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ERROR);
        } finally {
            // DB close
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(ERROR);
            }
        }
    }

    // 내 포스트 댓글 불러오기
    public void myPostCommentList(int myPostIdx) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "select * from Comment where postIdx = " + myPostIdx + ";";

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                int commentIdx = resultSet.getInt(1);
                int userIdx = resultSet.getInt(2);
                String content = resultSet.getString(3);
                if (resultSet.wasNull())
                    content = "null";

                System.out.println(commentIdx + " \t" + userIdx + "\t" + content + "\t" + myPostIdx);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ERROR);
        } finally {
            // DB close
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(ERROR);
            }
        }
    }

    // 내 포스트 댓글 삭제
    public void deleteComment(int commentIdx) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = " delete from Comment where commentIdx = " + commentIdx + ";";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ERROR);
        } finally {
            // DB close
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(ERROR);
            }
        }
    }

    ///////////////////////////////////// Post Finish
    ///////////////////////////////////// ///////////////////////////////////////
    ///////////////////////////////////// ProFile Start
    ///////////////////////////////////// ///////////////////////////////////////
    public static boolean isExistingID(String ID) {
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

    public static String[] profile(int ID) {
        String sql = "select user.userName,user.userLocatoin,user.userBio,user.userId,user.createAt,count(CASE when follow.userIdx = user.userIdx THEN 1 END) as following,count( CASE when follow.followedUser = user.userIdx THEN 1 END) as follower\r\n"
                + "from follow,user\r\n" + "where user.userIdx = '" + ID + "';";
        String profilephotoSql = "select photoAdress\r\n"
                + "from photo join user u on photo.photoIdx = u.photoIdx where userIdx='" + ID + "';";
        String headphotoSql = "select photoAdress\r\n"
                + "from photo join user u on photo.photoIdx = u.headerPhotoIdx where userIdx='" + ID + "';";
        PreparedStatement preparedStatement = null;
        Connection connection = getConnection();
        ResultSet selectResult = null;
        String[] res = new String[10];
        try {
            preparedStatement = connection.prepareStatement(sql);
            selectResult = preparedStatement.executeQuery();
            if (selectResult.next()) {
                for (int i = 1; i <= 7; i++) {
                    res[i] = selectResult.getString(i);
                }
                preparedStatement = connection.prepareStatement(profilephotoSql);
                selectResult = preparedStatement.executeQuery();
                if (selectResult.next()) {
                    res[8] = selectResult.getString(1);
                    preparedStatement = connection.prepareStatement(headphotoSql);
                    selectResult = preparedStatement.executeQuery();
                    selectResult.next();
                    res[9] = selectResult.getString(1);
                    return res;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void editProfile(int ID, String photoAdrress, String headerPhotoAdrress, String userBio,
                                   String userLocatoin, String userName) {
        String sqlPhoto = "insert into photo(photoAdress) value('" + photoAdrress + "')";
        String AdrressIdx1 = "SELECT LAST_INSERT_ID()";
        String sqlheadPhoto = "insert into photo(photoAdress) value ('" + headerPhotoAdrress + "')";
        String AdrressIdx2 = "SELECT LAST_INSERT_ID()";
        String ads1 = null;
        String ads2 = null;
        PreparedStatement preparedStatement = null;
        Connection connection = getConnection();
        ResultSet selectResult = null;
        try {
            preparedStatement = connection.prepareStatement(sqlPhoto);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(AdrressIdx1);
            selectResult = preparedStatement.executeQuery();
            if (selectResult.next()) {
                ads1 = selectResult.getString(1);
                preparedStatement = connection.prepareStatement(sqlheadPhoto);
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement(AdrressIdx2);
                selectResult = preparedStatement.executeQuery();
                selectResult.next();
                ads2 = selectResult.getString(1);
                String sql = "update user \r\n" + "set userBio = \"" + userBio + "\",photoIdx = \"" + ads1
                        + "\",headerPhotoIdx = \"" + ads2 + "\",userLocatoin = \"" + userLocatoin + "\",userName = \""
                        + userName + "\"\r\n" + "where userIdx = '" + ID + "'";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int[] postlikeCountNum(int ID) {
        String sql = "select count(postLikeIdx) as postlikeCount\r\n"
                + "from post left outer join postlike on post.postIdx = postlike.postIdx where post.posUserIdx= '" + ID
                + "'";
        String sql2 = "select count(postIdx)as postCount\r\n" + "from post\r\n" + "where posUserIdx= '" + ID + "'";
        PreparedStatement preparedStatement = null;
        Connection connection = getConnection();
        ResultSet selectResult = null;
        int[] result = null;
        int j = 0;
        try {
            preparedStatement = connection.prepareStatement(sql2);
            selectResult = preparedStatement.executeQuery();
            if (selectResult.next()) {
                int count = Integer.parseInt(selectResult.getString(1));
                result = new int[count+1];
                preparedStatement = connection.prepareStatement(sql);
                selectResult = preparedStatement.executeQuery();
                while (selectResult.next()) {
                    result[j] = selectResult.getInt(1);
                    j++;
                }
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int[] postCommentNum(int ID) {
        String sql = "select count(commentIdx) as commentCount\r\n"
                + "from post left outer join comment c on post.postIdx = c.postIdx where post.posUserIdx= '" + ID + "'";
        String sql2 = "select count(postIdx)as postCount\r\n" + "from post\r\n" + "where posUserIdx= '" + ID + "'";
        PreparedStatement preparedStatement = null;
        Connection connection = getConnection();
        ResultSet selectResult = null;
        int[] result = null;
        int j = 0;
        try {
            preparedStatement = connection.prepareStatement(sql2);
            selectResult = preparedStatement.executeQuery();
            if (selectResult.next()) {
                int count = Integer.parseInt(selectResult.getString(1));
                result = new int[count+1];
                preparedStatement = connection.prepareStatement(sql);
                selectResult = preparedStatement.executeQuery();
                while (selectResult.next()) {
                    result[j] = selectResult.getInt(1);
                    j++;
                }
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String[][] postView(int ID) {
        String sql = "select post.content,photo.photoAdress, post.postIdx\r\n"
                + "from ((postphoto natural join photo)\r\n"
                + "    right outer join post on postPhoto.postIdx = post.postIdx)\r\n" + "where posUserIdx= '" + ID
                + "'";
        String sql2 = "select count(postIdx)as postCount\r\n" + "from post\r\n" + "where posUserIdx= '" + ID + "'";
        PreparedStatement preparedStatement = null;
        Connection connection = getConnection();
        ResultSet selectResult = null;
        String[][] result = null;
        try {
            preparedStatement = connection.prepareStatement(sql2);
            selectResult = preparedStatement.executeQuery();
            if (selectResult.next()) {
                int count = Integer.parseInt(selectResult.getString(1));
                result = new String[count+1][4];
                preparedStatement = connection.prepareStatement(sql);
                selectResult = preparedStatement.executeQuery();
                int j = 0;
                while (selectResult.next()) {
                    for (int i = 1; i < 4; i++) {
                        result[j][i] = selectResult.getString(i);
                    }
                    j++;
                }
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean existfollow(int userIdx, int otherIdx) {
        String sql = "select EXISTS(\r\n"
                + "    select *\r\n"
                + "    from follow\r\n"
                + "    where userIdx = \'" + userIdx + "\' and followedUser = \'" + otherIdx + "\'\r\n"
                + "           ) as Success";
        PreparedStatement preparedStatement = null;
        Connection connection = getConnection();
        ResultSet selectResult = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            selectResult = preparedStatement.executeQuery();
            if(selectResult.next()) {
                if(selectResult.getInt(1)==1) {
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void addFollow(int userId, int otherId) {
        String sql = "insert into follow(userIdx, followedUser) values('" + userId + "','" + otherId + "')";
        PreparedStatement preparedStatement = null;
        Connection connection = getConnection();
        try {
            // 쿼리 실행
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rmFollow(int userId, int otherId) {
        String sql = "delete from follow where userIdx = '" + userId + "' and followedUser = " + otherId;
        PreparedStatement preparedStatement = null;
        Connection connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String[][] viewFollowing(int userId) {
        String sql = "select followed.userName,followed.userBio, followed.userID, f.followedUser, photo.photoAdress\r\n"
                + "from (user as followed join follow f on followed.userIdx = f.followedUser)left outer join photo on followed.photoIdx = photo.photoIdx\r\n"
                + "where f.userIdx = " + userId;
        String sql2 = "select count(followed.userIdx) as followCount\r\n"
                + "from user as followed join follow on followed.userIdx = follow.userIdx\r\n"
                + "where follow.userIdx = " + userId;
        PreparedStatement preparedStatement = null;
        Connection connection = getConnection();
        ResultSet selectResult = null;
        String[][] str = null;
        try {
            preparedStatement = connection.prepareStatement(sql2);
            selectResult = preparedStatement.executeQuery();
            int count = 0;
            if (selectResult.next()) {
                count = Integer.parseInt(selectResult.getString(1));

                str = new String[count+1][5 + 1];
                preparedStatement = connection.prepareStatement(sql);
                selectResult = preparedStatement.executeQuery();
                int j = 0;
                while (selectResult.next()) {
                    for (int i = 1; i <= 5; i++) {
                        str[j][i] = selectResult.getString(i);
                    }
                    j++;
                }
                return str;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String[][] viewFollower(int userId) {
        String sql = "select followed.userName,followed.userBio, followed.userID, f.userIdx, photo.photoAdress\r\n"
                + "from (user as followed join follow f on followed.userIdx = f.userIdx)left outer join photo on followed.photoIdx = photo.photoIdx\r\n"
                + "where f.followedUser =" + userId;
        String sql2 = "select count(followed.userIdx) as followCount\r\n"
                + "from user as followed join follow on followed.userIdx = follow.userIdx\r\n"
                + "where follow.followedUser = " + userId;
        PreparedStatement preparedStatement = null;
        Connection connection = getConnection();
        ResultSet selectResult = null;
        String[][] str = null;
        try {
            preparedStatement = connection.prepareStatement(sql2);
            selectResult = preparedStatement.executeQuery();
            int count;
            if (selectResult.next()) {
                count = Integer.parseInt(selectResult.getString(1));

                str = new String[count+1][5 + 1];

                preparedStatement = connection.prepareStatement(sql);
                selectResult = preparedStatement.executeQuery();
                int j = 0;

                while (selectResult.next()) {
                    for (int i = 1; i <= 5; i++) {
                        str[j][i] = selectResult.getString(i);
                    }
                    j++;
                }
                return str;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return str;
    }

    ///////////////////////////////////// ProFile Finish
    ///////////////////////////////////// ///////////////////////////////////////
    ///////////////////////////////////// Special Function Start
    ///////////////////////////////////// ///////////////////////////////////////
    // NOTIFICATION
    static int[] insertNoticeIdx(int userIdx, java.sql.Timestamp d) {
        Connection conn = null;
        int returnValue[] = new int[10]; // 알림 10개까지만

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/TWITTER";
            conn = DriverManager.getConnection(url, "root", "2240");

            Statement stmt = null;
            ResultSet rs = null;

            while (true) {
                stmt = conn.createStatement();
                String s1 = "insert into twitter.Notifications(userIdx,notificationDate,notification) "
                        + "values ((select userIdx,createAt from Follow where followedUser=" + userIdx + "and createAt>"
                        + d + "),'FOLLOW');"; // 입력된 시간을 기준 이후로 생긴 팔로우 알람
                stmt.executeQuery(s1);
                String s2 = "select last_insert_id();"; // 알림 시작지점
                s1 = "insert into twitter.Notifications(userIdx,notificationDate,notification) "
                        + "values (select Post.userIdx, Comment.createAt from Comment"
                        + "natural join Post where Post.userIdx=(select Comment.postIdx"
                        + "from Comment where Comment.createAt >" + d + ")),'COMMENT');"; // 입력된 시간을 기준 이후로 생긴 comment
                // 알람
                stmt.executeQuery(s1);

                rs = stmt.executeQuery(s2);
                int i = 0;

                if (rs.next()) {
                    returnValue[i] = rs.getInt(1);
                    i++;
                }
                return returnValue;
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패");
            return returnValue;
        } catch (SQLException e) {
            System.out.println("에러: " + e);
            return returnValue;
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return returnValue;
            }
        }
    }

    static String returnNotification(int noticeIdx) { // noticeidx에 맞는 notice 내용
        Connection conn = null;
        String returnValue = ""; // 이후 문제있으면 null로 수정가능

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/TWITTER";
            conn = DriverManager.getConnection(url, "root", "2240");

            Statement stmt = null;
            ResultSet rs = null;
            while (true) {
                stmt = conn.createStatement();
                String s3 = "select content from Notifications where notificationIdx='" + noticeIdx + "'"; // postIdx에
                // 맞는
                // postContent
                // 뽑아내는 쿼리문
                rs = stmt.executeQuery(s3);
                if (rs.next()) {
                    returnValue = rs.getString(1);
                }
                return returnValue;
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패");
            return returnValue;
        } catch (SQLException e) {
            System.out.println("에러: " + e);
            return returnValue;
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static java.sql.Timestamp returnNoticeDate(int noticeIdx) {
        // noticeidx에 맞는 notice 시간
        Connection conn = null;
        java.sql.Timestamp returnValue = null; // timestamp는 사용자시점으로 좋지 못한 시간값을 출력해줌 //그래서 이후 문제없으면 Date형식으로 바꿔주는 걸
        // 추천!!!!!!!!

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/TWITTER";
            conn = DriverManager.getConnection(url, "root", "2240");

            Statement stmt = null;
            ResultSet rs = null;
            while (true) {
                stmt = conn.createStatement();
                String s4 = "select content from Notifications where notificationIdx='" + noticeIdx + "'"; // noticeidx에
                // 맞는
                // noticetime
                rs = stmt.executeQuery(s4);
                if (rs.next()) {
                    returnValue = rs.getTimestamp(1);
                }
                return returnValue;
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패");
            return returnValue;
        } catch (SQLException e) {
            System.out.println("에러: " + e);
            return returnValue;
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // NOTIFICATION END

    // TREND
    static String[] trendHash() {
        Connection conn = null;
        String[] trendResult = new String[10];

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/TWITTER";
            conn = DriverManager.getConnection(url, "root", "2240");

            Statement stmt = null;
            ResultSet rs = null;

            while (true) {
                stmt = conn.createStatement();
                String s1 = "select hash from (select hash,count(hash) from HashTag order by count(hash) desc) A"; // 상위
                // 10개
                // hash
                // 찾기
                rs = stmt.executeQuery(s1);

                int i = 1;

                if (rs.next()) {
                    do {
                        trendResult[i] = rs.getString(1);
                        i++;

                        if (i > 101)
                            return trendResult;
                    } while (rs.next());
                } else {
                    System.out.println("NO TREND RESULT");
                }
                return trendResult;
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패");
            return trendResult;
        } catch (SQLException e) {
            System.out.println("에러: " + e);
            return trendResult;
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return trendResult;
            }
        }
    }

    static int returnHashNumber(String hash) { // 특정 Hash에 대해서 hash갯수를 return하는 class
        Connection conn = null;
        int returnValue = 0; // 이후 문제있으면 null로 수정가능

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/TWITTER";
            conn = DriverManager.getConnection(url, "root", "2240");

            Statement stmt = null;
            ResultSet rs = null;
            while (true) {
                stmt = conn.createStatement();
                String s2 = "select count(hash) from HashTag where hash='" + hash + "'"; // hash에 맞는 Hash 갯수 뽑아내는 쿼리문
                rs = stmt.executeQuery(s2);
                if (rs.next()) {
                    returnValue = rs.getInt(1);
                }
                return returnValue;
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패");
            return returnValue;
        } catch (SQLException e) {
            System.out.println("에러: " + e);
            return returnValue;
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // TREND END

    // SEARCH
    static int[] searchPostidx() {
        Connection conn = null;

        int[] searchResult = new int[100]; // 초기값 0

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/TWITTER";
            conn = DriverManager.getConnection(url, "root", "2240");

            Statement stmt = null;
            ResultSet rs = null;

            while (true) {
                Scanner in = new Scanner(System.in);

                String searchContents = null;

                System.out.println("Enter search keywords");
                searchContents = in.nextLine();
                in.close();

                stmt = conn.createStatement();
                String s1 = "select distinct postIdx from POST where content LIKE '%" + searchContents + "%'"; // 검색 쿼리문
                // 쿼리문 수정예정
                rs = stmt.executeQuery(s1);
                int i = 0;

                if (rs.next()) {
                    do {
                        searchResult[i] = rs.getInt(1);
                        i++;

                        if (i > 101)
                            return searchResult;
                    } while (rs.next());
                } else {
                    System.out.println("NO SEARCH RESULT");
                }

                stmt.close();
                rs.close();
                return searchResult;
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패");
            return searchResult;
        } catch (SQLException e) {
            System.out.println("에러: " + e);
            return searchResult;
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return searchResult;
            }
        }
    }

    static String returnUserID(int postIdx) { // postidx에 대해서 postUserId를 return하는 class
        Connection conn = null;
        String returnValue = ""; // 이후 문제있으면 null로 수정가능

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/TWITTER";
            conn = DriverManager.getConnection(url, "root", "2240");

            Statement stmt = null;
            ResultSet rs = null;
            while (true) {
                stmt = conn.createStatement();
                String s2 = "select postUserIdx from POST where postIdx = '" + postIdx + "'"; // postIdx에 맞는 userid 뽑아내는
                // 쿼리문
                rs = stmt.executeQuery(s2);
                if (rs.next()) {
                    returnValue = rs.getString(1);
                }
                return returnValue;
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패");
            return returnValue;
        } catch (SQLException e) {
            System.out.println("에러: " + e);
            return returnValue;
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static String returnPostContent(int postIdx) { // postidx에 대해서 post 내용물 return하는 class
        Connection conn = null;
        String returnValue = ""; // 이후 문제있으면 null로 수정가능

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/TWITTER";
            conn = DriverManager.getConnection(url, "root", "2240");

            Statement stmt = null;
            ResultSet rs = null;
            while (true) {
                stmt = conn.createStatement();
                String s3 = "select content from Post where postUserIdx='" + postIdx + "'"; // postIdx에 맞는 postContent
                // 뽑아내는 쿼리문
                rs = stmt.executeQuery(s3);
                if (rs.next()) {
                    returnValue = rs.getString(1);
                }
                return returnValue;
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패");
            return returnValue;
        } catch (SQLException e) {
            System.out.println("에러: " + e);
            return returnValue;
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // SEARCH END
    ///////////////////////////////////// Special Function Finish
    // ///////////////////////////////////////

}
