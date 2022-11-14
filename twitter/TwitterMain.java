import java.util.Scanner;

public class TwitterMain {
    static final String TRY_AGAIN= "Try agian";
    public static void main(String[] args){
        int postMenu = 0;
        Scanner scanner = new Scanner(System.in);


        System.out.println("Hellow, welcome to Twitter");
        System.out.println("-----------------------------------------------");
        // login start
        // login end
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