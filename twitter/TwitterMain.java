package gui;

import java.util.Arrays;
import java.util.Scanner;

public class TwitterMain {
    static final String WrongInput = "Something wrong. Try again.";
    static final String TRY_AGAIN= "Try agian";
    public static void main(String[] args){
        int menu = -1;
        int userIdx = -1;
        int postMenu = 0;
        int option = -1;
        Scanner scanner = new Scanner(System.in);


        System.out.println("Hellow, welcome to Twitter");
        System.out.println("-----------------------------------------------");
        // login start
        while (option != 0){
            System.out.println("| (1) Sign Up | (2) Login | (3) Change Password | (0) Exit |");
            option = scanner.nextInt();
            if(option == 1){
                signUp();
            } else if(option == 2){
                userIdx = login();
                if(userIdx > 0){
                    System.out.println("Login Success!");
                } else {
                    System.out.println(WrongInput);
                }

            } else if(option == 3){
                changePasswordNotLogin();
                option = -1;
            } else if(option == 0) {
                System.out.println("¬∑ ¬∑ ¬∑ ¬∑ ¬∑ Loading ¬∑ ¬∑ ¬∑ ¬∑ ¬∑");
                break;
            } else {
                System.out.println(WrongInput);
            }
        }
        System.out.println("Quit Twitter");
        System.out.println("----------------------------------");
        // login end
        // Post start
        while (postMenu != 9){
            System.out.println("----------------------- Post List ---------------------");
            postList();
            System.out.println("-----------------------------------------------");
            System.out.println("1.upload post 2. My post 3. delete post 4.post Like \n 5. Like Num 6.Like List 7. Add comment 8. delete comment" +
                    "9. exit");
            postMenu = scanner.nextInt();
            switch (postMenu){
                case 1:
                    uploadPost(userIdx);
                    System.out.println("Post upload completed");
                    break;
                case 2:
                    System.out.println("----------------------- My Post List ---------------------");
                    myPostList(userIdx);
                    System.out.println("-----------------------------------------------");
                    break;
                case 3:
                    System.out.println("----------------------- My Post List ---------------------");
                    myPostList(userIdx);
                    System.out.println("-----------------------------------------------");
                    deletePost();
                    System.out.println("----------------------- My Post List ---------------------");
                    myPostList(userIdx);
                    System.out.println("-----------------------------------------------");
                    break;
                case 4:
                    System.out.println("-----------------------  like Post  ---------------------");
                    postLike(userIdx);
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
                    myPostList(userIdx);
                    System.out.println("-----------------------------------------------");
                    addComment(userIdx);
                    break;
                case 8:
                    System.out.println("----------------------- My Post List ---------------------");
                    myPostList(userIdx);
                    System.out.println("-----------------------------------------------");
                    MyPostCommentList(userIdx);
                    deleteComment(userIdx);
                    break;
            }
        }
        // Post finish
        // Profile start
        while (menu != 0) {
            System.out.println("1. login 0. Exit");
            menu = scanner.nextInt();
            if (menu == 1) {
                userIdx = login();
                if (userIdx > 0) {
                    System.out.println("Login Success!");
                    twitter(userIdx);
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
        // Profile finsih
        ////////////////////////////////// Special Function Start  //////////////////////////////////
        while (menu != 0) {
            System.out.println("1. login 0. Exit");
            menu = scanner.nextInt();
            if (menu == 1) {
                userIdx = login();
                if (userIdx > 0) {
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
        System.out.println("Twitter");
        System.out.println("-----------------------------------------------");
        ////////////////////////////////// Special Function Finish  //////////////////////////////////
    }



    ////////////////////////////////// Login Start  //////////////////////////////////
    public static void signUp() {
        Scanner scanner = new Scanner(System.in);
        String userID = null, userPassword = null, email = null, phoneNumber = null, userName = null;
        int selection = 0;

        System.out.println("----------- Create an account -----------");
        System.out.println("| (1) Sign up to email | (2) Sign up to phone number |");
        selection = scanner.nextInt();
        scanner.nextLine();

        // ?ù¥Î©îÏùºÎ°? ?öå?õêÍ∞??ûÖ
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
        } // ?ú¥???è∞ Î≤àÌò∏Î°? ?öå?õêÍ∞??ûÖ
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

//    public static void twitter(int userIdx){
//        Scanner scanner = new Scanner(System.in);
//        int option = -1;
//
//        while(option != 0){
//            System.out.println("| (1) Following | (2) Follower | (3) Timeline | (4) Write | (5) Change Password | (6) Logout |");
//            option = scanner.nextInt();
//
//            if(option == 1){
//                //?ó¨Í∏∞ÏÑú ?åîÎ°úÏö∞?ï† ?ãâ?Ñ§?ûÑ ?ûÖ?†•Í∞? Î∞õÍ∏∞
//                //(Î°úÍ∑∏?ù∏ ?ïú ?Ç¨?ûå?ùò userID)
//                //insert to JDBCÎ°? ?ãâ?Ñ§?ûÑ ?ÑòÍ≤®ÏÑú Î¶¨Ïä§?ä∏?óê Ï∂îÍ?
//                //?åîÎ°úÏûâ Î¶¨Ïä§?ä∏ JDBCconnectÎ°? ?ÑòÍ∏∞Í∏∞ (Î¶¨Ïä§?ä∏ ÎΩëÍ∏∞)
//            } else if(option == 2){
//                //?åîÎ°úÏö∞ Î¶¨Ïä§?ä∏ JDBCconnect ?ÑòÍ∏∞Í∏∞ (Î¶¨Ïä§?ä∏ ÎΩëÍ∏∞)
//                //(?åîÎ°úÏö∞?ïúnickname ?Ç¨?ûå?ùò userID)
//            } else if(option == 3){
//
//            } else if(option == 4){
//
//            } else if(option == 5){
//                changePasswordLogin(userIdx);
//            } else if(option == 6) {
//                break;
//            }
//        }
//        System.out.println("Logout a Twitter");
//        System.out.println("-----------------------------------------------");
//    }



    ////////////////////////////////// Login Finish //////////////////////////////////

    ////////////////////////////////// Post Start  //////////////////////////////////
    // Post ?óÖÎ°úÎìú
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
            // userIdx 1 Î°? Í≥†Ï†ï
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
            // Hash ?ûÖ?†• Î∞õÍ∏∞
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
    // Post ?Ç≠?†ú
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
    // post Ï¢ãÏïÑ?öî
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
    // post Ï¢ãÏïÑ?öî Í∞úÏàò
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
    // post Ï¢ãÏïÑ?öî ?àÑÎ•? ?Ç¨?ûå Î™©Î°ù
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
    // ?õê?ïò?äî ?è¨?ä§?ä∏?ùò ?åìÍ∏? Ï∂îÍ?
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
    // ?Ç¥ ?è¨?ä§?ä∏?ùò ?åìÍ∏? Î™©Î°ù
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

    // ?Ç¥ ?è¨?ä§?ä∏?ùò ?åìÍ∏? ?Ç≠?†ú
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

    // ?†ÑÏ≤? ?è¨?ä§?ä∏ Î¶¨Ïä§?ä∏
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
    ////////////////////////////////// Post Finish  //////////////////////////////////
    ////////////////////////////////// Profile start  //////////////////////////////////
    public static void twitter(int userIdx) {
        Scanner scanner = new Scanner(System.in);
        int menu = -1;
        String[] pofileInfo = new String[8];
        try {
            JdbcConnection jdbcConnection = new JdbcConnection();
            while (menu != 0) {
                System.out.println("1. Profile 2. edit profile 3. following 4. follower");
                menu = scanner.nextInt();
                if (menu == 1) {
                    pofileInfo = jdbcConnection.profile(userIdx);
                    System.out.println("User Name: " + pofileInfo[1]);
                    System.out.println("User Bio: " + pofileInfo[2]);
                    System.out.println("User Location: " + pofileInfo[3]);
                    System.out.println("USer idx: " + pofileInfo[4]);
                    System.out.println("User CreatAt: " + pofileInfo[5]);
                    System.out.println("User following: " + pofileInfo[6]);
                    System.out.println("USer follower: " + pofileInfo[7]);
                } else if (menu == 2) {
                    String photoIdx, headerPhotoIdx, userBio, userLocatoin, userName;
                    System.out.printf("insert photoAdress, headerPhotoAdress, userBio, userLocatoin, userName");
                    photoIdx = scanner.next();
                    headerPhotoIdx = scanner.next();
                    userBio = scanner.next();
                    userLocatoin = scanner.next();
                    userName = scanner.next();
                    jdbcConnection.editProfile(userIdx, photoIdx, headerPhotoIdx, userBio, userLocatoin, userName);
                } else if (menu == 3) {
                    String[][] str = jdbcConnection.viewFollowing(userIdx);
                    for (int i = 0; i < str.length; i++) {
                        System.out.println(Arrays.toString(str[i]));
                    }
                } else if (menu == 4) {
                    String[][] str = jdbcConnection.viewFollower(userIdx);
                    for (int i = 0; i < str.length; i++) {
                        System.out.println(Arrays.toString(str[i]));
                    }
                } else
                    break;

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(TRY_AGAIN);
        }

    }
    ////////////////////////////////// Profile Finish  //////////////////////////////////

    ////////////////////////////////// Special Function Start  //////////////////////////////////
    public static void search()
    {
        String userID=null, postContent=null; //?çî Î∞õÏïÑ?ò¨ ?Ç¥?ö© ?ûà?úºÎ©? Ï∂îÍ??ïòÍ∏?

        int[] searchResult=new int[100];
        searchResult=JdbcConnection.searchPostidx();
        for(int idx:searchResult)
        {
            if(idx!=0)
            {
                System.out.println("RESULTID:" + JdbcConnection.returnUserID(idx));//postidx?óê ???ïú userid Î∞õÏïÑ?ò§Í∏? (twitterSearch.returnUserID ?Ç¨?ö©)
                System.out.println("RESULTCONTENT:"+JdbcConnection.returnPostContent(idx-1)); //postidx?óê ???ïú content Î∞õÏïÑ?ò§Í∏? //idx-1 ?ù¥Î©? ?ò§Î•òÍ? ?ïà?Çò?äî?ç∞ ?ù¥?ú†Î•? Î™®Î•¥Í≤†Ïùå
            }
        }
    }

    public static void trend()
    {
        String hashtag=null;
        int hashtagNum;

        String[] trendResult=new String[10];
        trendResult=JdbcConnection.trendHash();
        for(String idx:trendResult)
        {
            if(!(idx==null||idx.isEmpty()))
            {
                System.out.println("RESULTHASH:"+idx);
                System.out.println("RESULTTREND:"+JdbcConnection.returnHashNumber(idx));
            }
        }
    }
    ////////////////////////////////// Special Function Finish  //////////////////////////////////
}