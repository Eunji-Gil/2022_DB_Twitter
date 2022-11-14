package twitter;

import java.util.Scanner;

public class TwitterMain {
    static final String WrongInput = "Something wrong. Try again.";

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        
        int option = -1;
        int userIdx = -1;
        int postMenu = 0;

        System.out.println("Hello, welcome to Twitter!");
        System.out.println("-----------------------------------------------");

         while (option != 0){
            System.out.println("| (1) Sign Up | (2) Login | (3) Change Password | (0) Exit |");
            option = scanner.nextInt();
            if(option == 1){
                signUp();
            } else if(option == 2){
                userIdx = login();
                if(userIdx > 0){
                    System.out.println("Login Success!");
                    twitter(userIdx);
                } else {
                    System.out.println(WrongInput);
                }

            } else if(option == 3){
            	changePasswordNotLogin();
                option = -1;
            } else if(option == 0) {
                System.out.println("· · · · · · Loading · · · · · ·");
                break;
            } else {
                System.out.println(WrongInput);
            }
        }
        System.out.println("Quit Twitter!");
        System.out.println("-----------------------------------------------");
    }

    public static void signUp() {
        Scanner scanner = new Scanner(System.in);
        String userID = null, userPassword = null, email = null, phoneNumber = null, userName = null;
        int selection = 0;
        
        System.out.println("----------- Create an account -----------");
        System.out.println("| (1) Sign up to email | (2) Sign up to phone number |");
        selection = scanner.nextInt();
        scanner.nextLine();
        
       // Sign up for email
       if (selection == 1) {
    	   try {
               JdbcConnection JdbcConnection = new JdbcConnection();
               boolean flag = false;

               while(!flag){
                   System.out.println("| Enter your ID | ");
                   userID = scanner.nextLine();
                   flag = !JdbcConnection.existUserID(userID);
               }

               flag = false;

               while(!flag){
                   System.out.println("| Enter your email | ");
                   email = scanner.nextLine();
                   flag = !JdbcConnection.existEmail(email);
               }
               
               flag = false;
               
               while(!flag){
                   System.out.println("| Enter your name | ");
                   userName = scanner.nextLine();
                   flag = !JdbcConnection.existEmail(userName);
               }

               System.out.println("| Enter your password | ");
               userPassword = scanner.nextLine();
               flag = JdbcConnection.signUp(userID, email, userPassword, userName);
               
               
               if(flag) {
                   System.out.println("Welcome to Twitter!");
               }
           } catch (Exception e){
               e.printStackTrace();
               System.out.println(WrongInput);
           }
       } // Sign up for phone number
       else {
    	   try {
               JdbcConnection JdbcConnection = new JdbcConnection();
               boolean flag = false;

               while(!flag){
                   System.out.println("| Enter your ID | ");
                   userID = scanner.nextLine();
                   flag = !JdbcConnection.existUserID(userID);
               }

               flag = false;

               while(!flag){
                   System.out.println("| Enter your phone number | ");
                   phoneNumber = scanner.nextLine();
                   flag = !JdbcConnection.existPhoneNumber(phoneNumber);
               }
               
               flag = false;
               
               while(!flag){
                   System.out.println("| Enter your name | ");
                   userName = scanner.nextLine();
                   flag = !JdbcConnection.existPhoneNumber(userName);
               }

               System.out.println("| Enter your password | ");
               userPassword = scanner.nextLine();
               flag = JdbcConnection.singUpPhoneNumber(userID, phoneNumber, userPassword, userName);
               
               if(flag) {
                   System.out.println("Welcome to Twitter!");
               }
           } catch (Exception e){
               e.printStackTrace();
               System.out.println(WrongInput);
           }
       }     
    }
    
    // Login
    public static int login() {
        Scanner scanner = new Scanner(System.in);
        
        int userIdx = -1;
        String userID = null, userPassword = null;

        System.out.println("----------- Log in to Twitter -----------");
        
        try{
            JdbcConnection JdbcConnection = new JdbcConnection();
            boolean flag = false;

            while(flag == false){
                System.out.println("| Enter your ID | ");
                userID = scanner.nextLine();
                flag = JdbcConnection.existUserID(userID);
            }

            System.out.println("| Enter your password |");
            userPassword = scanner.nextLine();

            userIdx = JdbcConnection.login(userID, userPassword);

            if(userIdx < 1) {
                System.out.println(WrongInput);
            }  

        } catch (Exception e){
            e.printStackTrace();
            System.out.println(WrongInput);
        }

        return userIdx;
    }

    // 로그인하지 않은 상태에서 change password
    public static void changePasswordNotLogin(){
        Scanner scanner = new Scanner(System.in);
        String userID = null, userPassword = null, email = null;

        System.out.println("----------- Setting a new password -----------");

        try{
        	JdbcConnection dbConnection = new JdbcConnection();
            boolean flag = false;

            while(flag == false){
                System.out.println("| Enter your ID |");
                userID = scanner.nextLine();
                flag = dbConnection.existUserID(userID);
            }

            flag = false;

            while(flag == false){
                System.out.println("| Enter your email |");
                email = scanner.nextLine();
                flag = dbConnection.userConfirm(userID, email);
            }

            System.out.println("| Enter your new password |");
            userPassword = scanner.nextLine();

            flag = dbConnection.changeUserPassword(userID, userPassword);

            if(flag) {
                System.out.println("Password's changed!");
            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(WrongInput);
        }
    }

    // 로그인한 상태에서 비밀번호 변경
    public static void changePasswordLogin(int userIdx){
        Scanner scanner = new Scanner(System.in);
        String userPassword = null;

        System.out.println("----------- Setting a new password -----------");

        try{
        	JdbcConnection JdbcConnection = new JdbcConnection();
            boolean flag = false;

            System.out.println("| Enter your new password |");
            userPassword = scanner.nextLine();
            flag = JdbcConnection.changeUserPassword(userIdx, userPassword);

            if(flag) {
                System.out.println("Password's changed!");
            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(WrongInput);
        }
    }
    
    // Twitter 첫 화면
    public static void twitter(int userIdx){
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        
        while(option != 0){
            System.out.println("| (1) Following | (2) Follower | (3) Timeline | (4) Write | (5) Change Password | (6) Logout |");
            option = scanner.nextInt();

            if(option == 1){
               //여기서 팔로우할 닉네임 입력값 받기 
               //(로그인 한 사람의 userID)
               //insert to JDBC로 닉네임 넘겨서 리스트에 추가
               //팔로잉 리스트 JDBCconnect로 넘기기 (리스트 뽑기)
            } else if(option == 2){
               //팔로우 리스트 JDBCconnect 넘기기 (리스트 뽑기) 
               //(팔로우한nickname 사람의 userID)
            } else if(option == 3){

            } else if(option == 4){

            } else if(option == 5){
            	changePasswordLogin(userIdx);
            } else if(option == 6) {
            	break;
            }
        }
        System.out.println("Logout a Twitter");
        System.out.println("-----------------------------------------------");
    }

        // Main(Search, Popular Title, upload, menu  )
        while (postMenu != 9){
            System.out.println("----------------------- Post List ---------------------");
            postList();
            System.out.println("-----------------------------------------------");
            System.out.println("1.upload post 2. My post 3. delete post 4.post Like \n 5. Like Num 6.Like List 7. Add comment 8. delete comment" +
                    "9. exit");
            postMenu = scanner.nextInt();
            switch (postMenu){
                case 1:
                    uploadPost(1);
                    System.out.println("Post upload completed");
                    break;
                case 2:
                    System.out.println("----------------------- My Post List ---------------------");
                    myPostList(1);
                    System.out.println("-----------------------------------------------");
                    break;
                case 3:
                    System.out.println("----------------------- My Post List ---------------------");
                    myPostList(1);
                    System.out.println("-----------------------------------------------");
                    deletePost();
                    System.out.println("----------------------- My Post List ---------------------");
                    myPostList(1);
                    System.out.println("-----------------------------------------------");
                    break;
                case 4:
                    System.out.println("-----------------------  like Post  ---------------------");
                    postLike(1);
                    break;
                case 5:
                    System.out.println("-----------------------  LikeCount Post  ---------------------");
                    postLikeCount();
                    break;
                case 6:
                    System.out.println("-----------------------  Post like List  ---------------------");
                    postLikeList();
                    break;
                case 7:
                    System.out.println("----------------------- Post List ---------------------");
                    myPostList(1);
                    System.out.println("-----------------------------------------------");
                    addComment(1);
                    break;
                case 8:
                    System.out.println("----------------------- My Post List ---------------------");
                    myPostList(1);
                    System.out.println("-----------------------------------------------");
                    MyPostCommentList(1);
                    deleteComment(1);
                    break;
            }

        }
        System.out.println("Twitter");
        System.out.println("-----------------------------------------------");
    }
    // Post 업로드
    public static void uploadPost(int userIdx) {
        Scanner scanner = new Scanner(System.in);
        int postIdx = 0, posUserIdx = 0;
        int photoIdx = 0, phostPhotoIdx = 0;
        int Answer = 0;
        String content = null, Hash = null;
        String pictureAd = null;
        System.out.println("----------------------Post---------------------");

        try{
            JdbcConnection jdbcConnectionConnect = new JdbcConnection();
            posUserIdx = userIdx;
            // userIdx 1 로 고정
            System.out.println("Enter a content : 1");
            content = scanner.nextLine();

            System.out.println("\nWould you like to post a picture too?");
            System.out.println("\n1. Yes 2. No");
            Answer = scanner.nextInt();
            scanner.nextLine();
            if (Answer == 1 ){
                System.out.println("\nEnter the photo address :\n");
                pictureAd = scanner.nextLine();
                photoIdx = jdbcConnectionConnect.Photo(pictureAd);
                postIdx = jdbcConnectionConnect.uploadPost(posUserIdx, content);
                phostPhotoIdx = jdbcConnectionConnect.PostPhoto(photoIdx,postIdx);
            }
            else if (Answer == 2) {
                postIdx = jdbcConnectionConnect.uploadPost(posUserIdx, content);
            }
            // Hash 입력 받기
            System.out.println("Enter a HashTag : ");
            Hash = scanner.nextLine();
            jdbcConnectionConnect.HashTag(postIdx, Hash);

            System.out.println("Uploding Post....");
            if (postIdx == 0 && photoIdx == 0 && phostPhotoIdx == 0){
                System.out.println("Falied");
            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(TRY_AGAIN);
        }
    }
    // Post 삭제
    public static void deletePost(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the post number to be deleted.");
        int postNum = scanner.nextInt();
        scanner.nextLine();
        try{
            JdbcConnection jdbcConnectionConnect = new JdbcConnection();
            jdbcConnectionConnect.deleteMyPost(postNum);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(TRY_AGAIN);
        }

    }
    // post 좋아요
    public static void postLike(int userIdx){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Like postNum :");
        int postIdx = scanner.nextInt();
        scanner.nextLine();
        try{
            JdbcConnection jdbcConnectionConnect = new JdbcConnection();
            jdbcConnectionConnect.PostLike(postIdx, userIdx);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(TRY_AGAIN);
        }
    }
    // post 좋아요 개수
    public static void postLikeCount(){
        int postLIkeCount = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter post LIke show :");
        int postIdx = scanner.nextInt();
        scanner.nextLine();
        try{
            JdbcConnection jdbcConnectionConnect = new JdbcConnection();
            postLIkeCount = jdbcConnectionConnect.PostLikeNum(postIdx);
            System.out.println(postIdx + "=" + postLIkeCount);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(TRY_AGAIN);
        }
    }
    // post 좋아요 누른 사람 목록
    public static void postLikeList(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter post LIke Userid show :");
        int postIdx = scanner.nextInt();
        scanner.nextLine();
        try{
            JdbcConnection jdbcConnectionConnect = new JdbcConnection();
            jdbcConnectionConnect.PostLIkeList(postIdx);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(TRY_AGAIN);
        }
    }
    // 원하는 포스트의 댓글 추가
    public static void addComment(int userIdx){
        Scanner scanner = new Scanner(System.in);

        int postIdx = 0;
        String content = null;
        System.out.println("Enter the postid to enter a comment");
        postIdx = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the  c7omment");
        content = scanner.nextLine();
        try{
            JdbcConnection jdbcConnectionConnect = new JdbcConnection();
            jdbcConnectionConnect.addComment(postIdx,content,userIdx);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(TRY_AGAIN);
        }
    }
    // 내 포스트의 댓글 목록
    public static void MyPostCommentList(int userIdx){
        Scanner scanner = new Scanner(System.in);
        int myPostIdx = 0;

        System.out.println("Enter the your PostIdx");
        myPostIdx = scanner.nextInt();
        scanner.nextLine();
        try{
            JdbcConnection jdbcConnectionConnect = new JdbcConnection();
            jdbcConnectionConnect.myPostCommentList(myPostIdx);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(TRY_AGAIN);
        }
    }

    // 내 포스트의 댓글 삭제
    public static void deleteComment(int userIdx){
        Scanner scanner = new Scanner(System.in);

        int commentIdx = 0;
        System.out.println("Enter the  delete commentIdx");
        commentIdx = scanner.nextInt();
        scanner.nextLine();
        try{
            JdbcConnection jdbcConnectionConnect = new JdbcConnection();
            jdbcConnectionConnect.deleteComment(commentIdx);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(TRY_AGAIN);
        }

    }
    //

    public static void postList() {
        try{
            JdbcConnection jdbcConnectionConnect = new JdbcConnection();
            jdbcConnectionConnect.postList();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(TRY_AGAIN);
        }
    }
    public static void myPostList(int userIdx) {
        try{
            JdbcConnection jdbcConnectionConnect = new JdbcConnection();
            jdbcConnectionConnect.myPostList(userIdx);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(TRY_AGAIN);
        }
    }
}
