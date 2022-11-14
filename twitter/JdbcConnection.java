import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

public class JdbcConnection {
    final String ERROR = "Error Occurred";
    private String server;
    private String database;
    private String userName;
    private String password;

    public JdbcConnection() throws Exception {

        try {
            FileReader dbConfig= new FileReader("src/config/db.properties");
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
    public Connection getConnection() {
        Connection connection = null;
        // Connect
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", userName, password);
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println(ERROR);
        }

        return connection;
    }



    // Twitter post
    // Post list
    public void postList() {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "select postIdx, posUserIdx, content, photAddress from Post left join \n" +
                "(select postIdx as postIdx2, photAddress from postPhoto natural join Photo) PhotoAd\n" +
                "on Post.postIdx = PhotoAd.postIdx2; ";

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()){
                int postIdx = resultSet.getInt(1);
                int posUseridx = resultSet.getInt(2);
                String content = resultSet.getString(3);
                if (resultSet.wasNull()) content = "null";
                String photoAddress = resultSet.getString(4);
                if (resultSet.wasNull()) photoAddress = "null";
                System.out.println(postIdx +" \t" + posUseridx + "\t" + content + "\t" + photoAddress);
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

        String sql = "select postIdx, posUserIdx, content, photAddress from Post left join \n" +
                "(select postIdx as postIdx2, photAddress from postPhoto natural join Photo) PhotoAd\n" +
                "on Post.postIdx = PhotoAd.postIdx2 and Post.posUserIdx =" + userIdx + ";";

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()){
                int postIdx = resultSet.getInt(1);
                int posUseridx = resultSet.getInt(2);
                String content = resultSet.getString(3);
                if (resultSet.wasNull()) content = "null";
                String photoAddress = resultSet.getString(4);
                if (resultSet.wasNull()) photoAddress = "null";
                System.out.println(postIdx +" \t" + posUseridx + "\t" + content + "\t" + photoAddress);
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

        String sql = " INSERT INTO post(posUserIdx, content) "
                + " VALUES( ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, posUserIdx);
            preparedStatement.setString(2, content);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
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
    public void HashTag(int postIdx , String Hash){
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = " INSERT INTO HashTag(hash,postidx) "
                + " VALUES( ?, ?)";

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
    public int Photo(String photAddress){
        int PK = 0;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql =  " INSERT INTO Photo(photAddress) "
                + " VALUES(?) ";
        try {
            preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, photAddress);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
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

        String sql = " INSERT INTO PostPhoto(photoIdx, postIdx) "
                + " VALUES(?, ?) ";

        try {
            preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, photoIdx);
            preparedStatement.setInt(2, postIdx);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
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

        String sql = "delete from Post where postIdx = " + myPostIdx +";";

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

        String sql = " INSERT INTO PostLike(postIdx,userIdx) "
                              + "VALUES(?, ?);" ;
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

        String sql = "select count(postIdx) from PostLike group by postIdx having postIdx = " + postIdx+";";

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()){
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

    public void PostLIkeList(int postIdx){
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "select userIdx from PostLike where postIdx = " + postIdx +";";

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()){
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
    public void addComment(int postIdx, String content,int userIdx) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = " INSERT INTO Comment(userIdx,content,postIdx) "
                + "VALUES(?, ?,?);" ;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userIdx);
            preparedStatement.setString(2,content);
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

        String sql = "select * from Comment where postIdx = " + myPostIdx +";";

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()){
                int commentIdx = resultSet.getInt(1);
                int userIdx = resultSet.getInt(2);
                String content = resultSet.getString(3);
                if (resultSet.wasNull()) content = "null";

                System.out.println(commentIdx +" \t" + userIdx + "\t" + content + "\t" + myPostIdx);
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

        String sql =" delete from Comment where commentIdx = " + commentIdx +";";
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


}
