package com.example.seoulwalk.retrofit;


import com.example.seoulwalk.data.DetailCourse_Data;
import com.example.seoulwalk.model.StepCount;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ApiInterface {

    /** 주별 걸음수 데이터 가져오기 **/
    @GET("tim_php/fetch_weekly_step_count.php")
    Call<List<StepCount>> fetchWeeklyStepCount(
            @Query("userid") String userid,
            @Query("week_num") int week_num
    );

    /** 세부코스별 리사이클러뷰 데이터 가져오기 **/
    @GET("tim_php/fetch_detail_course_data.php")
    Call<List<DetailCourse_Data>> fetchDetailCourseData(
            @Query("sort") String sort
    );

//    /** 로그인 **/
//    @FormUrlEncoded
//    @POST("login.php")
//    Call<String> getUserLogin(
//            @Field("email") String email,
//            @Field("password") String password
//    );
//
//    /** 회원가입 **/
//    @FormUrlEncoded
//    @POST("register.php")
//    Call<String> getUserRegistration(
//            /** @Field는 POST로 서버에 값을 보낼 때 붙여야 하는 어노테이션 **/
//            @Field("email") String email,
//            @Field("username") String username,
//            @Field("password1") String password1,
//            @Field("password2") String password2
//    );
//
//    /** 회원가입 시 이메일 중복체크 **/
//    @FormUrlEncoded
//    @POST("email_check.php")
//    Call<String> checkEmail(
//            @Field("email") String email
//    );
//
//    /** 회원가입 시 닉네임 중복체크 **/
//    @FormUrlEncoded
//    @POST("username_check.php")
//    Call<String> checkUsername(
//            @Field("username") String username
//    );
//
//    /** 닉네임 변경 **/
//    @FormUrlEncoded
//    @POST("edit_username.php")
//    Call<String> editUsername(
//            @Field("email") String email,
//            @Field("username") String username
//    );
//
//    /** 채널 설명 변경 **/
//    @FormUrlEncoded
//    @POST("edit_channel_description.php")
//    Call<String> editChannelDescription(
//            @Field("email") String email,
//            @Field("description") String description
//    );
//
//    /** 비밀번호 변경 **/
//    @FormUrlEncoded
//    @POST("change_password.php")
//    Call<String> changePassword(
//            @Field("email") String email,
//            @Field("current_password") String currentPassword,
//            @Field("new_password1") String newPassword1,
//            @Field("new_password2") String newPassword2
//    );
//
//    /** 프로필 이미지 확인 **/
//    @GET("check_profile_image.php")
//    Call<String> getUserInfo(
//            @Query("email") String email
//    );
//
//    /** 프로필 이미지 삭제 **/
//    @FormUrlEncoded
//    @POST("delete_profile_image.php")
//    Call<String> deleteProfileImage(
//            @Field("email") String email
//    );
//
//    /** 프로필 배경화면 삭제 **/
//    @FormUrlEncoded
//    @POST("delete_profile_background.php")
//    Call<String> deleteProfileBackground(
//            @Field("email") String email
//    );
//
//    /** 프로필 이미지 업로드 **/
////    @FormUrlEncoded
////    @POST("upload_profile_image.php")
////    Call<Image> uploadImage(
////            @Field("email") String email,
////            @Field("image") String image
////    );
//
////    @Multipart
////    @POST("upload_profile_image.php")
////    Call<ServerResponse> uploadFile(
////            @Part MultipartBody.Part file,
////            @Part("file") RequestBody name
////    );
//
//    // https://link2me.tistory.com/1880?category=1019566
////    @Multipart
////    @POST("upload_profile_image.php")
////    Call<UploadResult> uploadImage(
////            @Part MultipartBody.Part uploaded_file,
////            @Part("idx") RequestBody idx
////    );
//
//    @Multipart
//    @POST("upload_profile_image.php")
//    // 이렇게 해야 함!!!!!
//    // MyProfileActivity.java 에서는
////    String emailString = preferenceHelper.getEmail();
////    RequestBody email = RequestBody.create(MediaType.parse("text/plain"), emailString);
////    map.put("email", email);
//    // 그리고 Call<ServerResponse> call = apiInterface.upload("token", map);
//    Call<ServerResponse> uploadProfileImage(
//            @Header("Authorization") String authorization,
//            @PartMap Map<String, RequestBody> map
//    );
//    // 이렇게 하면
//    // UPDATE users SET image = 'uploads/d3d04ec1-0f6a-4901-b272-c916d705c375.jpg' \
//    // WHERE email = '{"email":"nono@gmail.com","success":false}'
//    // 이런 식으로 들어감!!
//    // MyProfileActivity.java 에서는
////    ServerResponse email = new ServerResponse();
////    email.setEmail(preferenceHelper.getEmail());
//    // Call<ServerResponse> call = apiInterface.upload("token", map, email);
////    Call<ServerResponse> upload(
////            @Header("Authorization") String authorization,
////            @PartMap Map<String, RequestBody> map,
////            @Part("email") ServerResponse email
////    );
//
//    /** 커뮤니티 게시물 업로드 **/
////    @POST("upload_post.php")
////    Call<ServerResponse> uploadPost(@Body RequestBody file);
////    @Multipart
////    @POST("upload_post.php")
////    Call<ServerResponse> uploadPost(@Part ArrayList<MultipartBody.Part> files, @Part("fileCount") RequestBody fileCount);
//    @Multipart
//    @POST("upload_post.php")
//    Call<ServerResponse> uploadPost(
//            @Part ArrayList<MultipartBody.Part> files,
//            @Part("fileCount") RequestBody fileCount,
//            @Part("email") RequestBody email,
//            @Part("text") RequestBody text
//    );
//
//    /** 커뮤니티 게시물 수정 **/
//    @Multipart
//    @POST("edit_post.php")
//    Call<ServerResponse> editPost(
//            @Part("postId") RequestBody postId,
//            @Part ArrayList<MultipartBody.Part> originalImages,
//            @Part ArrayList<MultipartBody.Part> files,
//            @Part("originalImagesCount") RequestBody originalImagesCount,
//            @Part("fileCount") RequestBody fileCount,
//            @Part("email") RequestBody email,
//            @Part("text") RequestBody text
//    );
//
//    /** 프로필 배경화면 업로드 **/
//    @Multipart
//    @POST("upload_profile_background.php")
//    Call<ServerResponse> uploadProfileBackground(
//            @Header("Authorization") String authorization,
//            @PartMap Map<String, RequestBody> map
//    );
//
//    /** 동영상 업로드 **/
//    @Multipart
//    @POST("upload_video.php")
//    Call<ServerResponse> uploadVideo(
//            @Header("Authorization") String authorization,
//            @PartMap Map<String, RequestBody> map
//    );
//
//    /** 내 동영상 삭제 **/
//    @FormUrlEncoded
//    @POST("delete_my_video.php")
//    Call<String> deleteMyVideo(
//            @Field("id") int id
//    );
//
//    /** 나중에 볼 동영상 삭제 **/
//    @FormUrlEncoded
//    @POST("delete_watch_later_video.php")
//    Call<String> deleteWatchLater(
//            @Field("id") int id,
//            @Field("email") String email
//    );
//
//    /** 커뮤니티 게시물(post) 삭제 **/
//    @FormUrlEncoded
//    @POST("delete_post.php")
//    Call<String> deletePost(
//            @Field("postId") int postId
//    );
//
//    /** 좋아요 표시한 동영상 삭제 **/
//    @FormUrlEncoded
//    @POST("delete_like_video.php")
//    Call<String> deleteLikeVideo(
//            @Field("id") int id,
//            @Field("email") String email
//    );
//
//    /** 최근 동영상 삭제 **/
//    @FormUrlEncoded
//    @POST("delete_recent_video.php")
//    Call<String> deleteRecentVideo(
//            @Field("id") int id,
//            @Field("email") String email
//    );
//
//    /** 내 동영상 수정 **/
//    @FormUrlEncoded
//    @POST("update_my_video.php")
//    Call<String> updateMyVideo(
//            @Field("id") int id,
//            @Field("title") String title,
//            @Field("description") String description,
//            @Field("visibility") String visibility,
//            @Field("forKids") String forKids,
//            @Field("location") String location,
//            @Field("category") String category
//    );
//
//    /** 채널 총 조회수 계산 **/
//    @GET("count_total_views.php")
//    Call<String> countTotalViews(
//            @Query("email") String email
//    );
//
//    /** 시청 기록 날짜 중복제거해서 가져오기 **/
//    @GET("fetch_history_date.php")
//    Call<String> fetchHistoryDate(
//            @Query("email") String email
//    );
//
//    /** 시청 기록 날짜 중복제거 개수 **/
//    @GET("fetch_history_date_count.php")
//    Call<String> fetchHistoryDateCount(
//            @Query("email") String email
//    );
//
//    /** 채널 인기 업로드 리사이클러뷰로 가져오기 **/
//    @GET("fetch_channel_popular_videos.php")
//    Call<List<Video>> fetchChannelPopularVideos(
//            @Query("email") String email
//    );
//


//    /** 채널 게시물 리사이클러뷰로 가져오기 **/
//    @GET("fetch_posts.php")
//    Call<List<Post>> fetchPosts(
//            // 해당 채널의 게시물 가져오기
//            @Query("channel_email") String channel_email,
//            // 현 유저의 게시물 좋아요 여부 체크하기 위한 현재 유저 이메일
//            @Query("current_user") String current_user
//    );
//
//    /** 시청 기록 리사이클러뷰로 가져오기 **/
//    @GET("fetch_history.php")
//    Call<List<Video>> fetchHistory(
//            @Query("email") String email
//    );
//
//    /** 특정 날짜 시청 기록 리사이클러뷰로 가져오기 **/
//    @GET("fetch_selected_date_history.php")
//    Call<List<Video>> fetchSelectedDateHistory(
//            @Query("email") String email,
//            @Query("date") String date
//    );
//
//    /** 나중에 볼 동영상 리사이클러뷰로 가져오기 **/
//    @GET("fetch_watch_later_video.php")
//    Call<List<Video>> fetchWatchLaterVideo(
//            @Query("email") String email
//    );
//
//    /** 좋아요 표시한 동영상 리사이클러뷰로 가져오기 **/
//    @GET("fetch_like_video.php")
//    Call<List<Video>> fetchLikeVideo(
//            @Query("email") String email
//    );
//
//    /** 최근 동영상 리사이클러뷰로 가져오기 **/
//    @GET("fetch_recent_video.php")
//    Call<List<Video>> fetchRecentVideo(
//            @Query("email") String email
//    );
//
//    /** 최근 검색어 가져오기 **/
//    @GET("fetch_search_history.php")
//    Call<List<SearchHistory>> fetchSearchHistory (
//            @Query("email") String email
//    );
//
//    /** 채널 검색된 결과 가져오기 **/
//    @GET("search_channel.php")
//    Call<List<Channel>> searchChannel (
//            @Query("keyword") String keyword
//    );
//
//    /** 구독목록 가로 리사이클러뷰로 가져오기 **/
//    @GET("fetch_horizontal_subscribes.php")
//    Call<List<Subscribe>> fetchHorizontalSubscribes (
//            @Query("email") String email
//    );
//
//    /** 구독목록 리사이클러뷰로 가져오기 **/
//    @GET("fetch_subscribes.php")
//    Call<List<Subscribe>> fetchSubscribes (
//            @Query("email") String email
//    );
//
//    /** 동영상 검색 **/
//    @GET("search_video.php")
//    Call<List<Video>> searchVideo(
//            @Query("keyword") String keyword,
//            @Query("email") String email
//    );
//
//    /** 시청 기록 검색 **/
//    @GET("search_history.php")
//    Call<List<Video>> searchHistory(
//            @Query("email") String email,
//            @Query("search") String search
//    );
//
//    /** 대표 동영상 설정하기 위한 내 동영상 목록 가져오기 **/
//    @GET("fetch_my_public_videos.php")
//    Call<List<Video>> fetchMyPublicVideos(
//            @Query("email") String email
//    );
//
//    /** 내 동영상 리사이클러뷰로 가져오기 **/
//    @GET("fetch_my_video.php")
//    Call<List<Video>> fetchMyVideo(
//            @Query("email") String email
//    );
//
//    /** 내 동영상 정렬 **/
//    @GET("sort_my_video.php")
//    Call<List<Video>> sortMyVideo(
//            @Query("email") String email,
//            @Query("sort") String sort
//    );
//
//    /** 채널 동영상 정렬 **/
//    @GET("sort_channel_video.php")
//    Call<List<Video>> sortChannelVideo(
//            @Query("email") String email,
//            @Query("sort") String sort
//    );
//
//    /** 나중에 볼 동영상 정렬 **/
//    @GET("sort_watch_later.php")
//    Call<List<Video>> sortWatchLater(
//            @Query("email") String email,
//            @Query("sort") String sort
//    );
//
//    /** 회원 username (채널 이름) 가져오기 **/
//    @GET("get_username.php")
//    Call<List<Video>> getUsername(
//            @Query("email") String email
//    );
//
//    /** 메인 동영상 리사이클러뷰로 가져오기 **/
//    @GET("fetch_main_video.php")
//    Call<List<Video>> fetchMainVideo(
//            @Query("category") String category,
//            @Query("pageNum") int pageNum
//    );
//
//    /** 나중에 볼 동영상 개수 **/
//    @GET("get_watch_later_video_count.php")
//    Call<String> getWatchLaterVideoCount(
//            @Query("email") String email
//    );
//
//    /** 시청기록 개수 **/
//    @GET("get_history_count.php")
//    Call<String> getHistoryCount(
//            @Query("email") String email
//    );
//
//    /** 좋아요 표시한 동영상 개수 **/
//    @GET("get_like_video_count.php")
//    Call<String> getLikeVideoCount(
//            @Query("email") String email
//    );
//
//    /** 구독자수 **/
//    @GET("get_subscribe_count.php")
//    Call<String> getSubscribeCount(
//            @Query("channel_email") String channel_email
//    );
//
//    /** 동영상 좋아요 수 계산 **/
//    @GET("count_video_likes.php")
//    Call<String> countVideoLikes(
//            @Query("id") int id
//    );
//
//    /** 동영상 정보 가져오기 **/
//    @GET("get_video_info.php")
//    Call<String> getVideoInfo(
//            @Query("video_id") int video_id
//    );
//
//    /** 동영상 정보 가져오기 (Video model 이용) **/
//    @GET("fetch_video_info.php")
//    Call<String> fetchVideoInfo(
//            @Query("video_id") int video_id
//    );
//
//    /** 대표 동영상 설정하기 **/
//    @FormUrlEncoded
//    @POST("set_channel_main_video.php")
//    Call<String> setChannelMainVideo(
//            @Field("video_id") int video_id,
//            @Field("email") String email
//    );
//
//    /** 대표 동영상 해제하기 **/
//    @FormUrlEncoded
//    @POST("remove_channel_main_video.php")
//    Call<String> removeChannelMainVideo(
//            @Field("email") String email
//    );
//
//    /** 게시물 대댓글 추천 토글 **/
//    @FormUrlEncoded
//    @POST("toggle_like_post_reply.php")
//    Call<String> togglePostReplyLike(
//            @Field("reply_id") int reply_id,
//            @Field("email") String email
//    );
//
//    /** 동영상 대댓글 추천 토글 **/
//    @FormUrlEncoded
//    @POST("toggle_like_reply.php")
//    Call<String> toggleReplyLike(
//            @Field("reply_id") int reply_id,
//            @Field("email") String email
//    );
//
//    /** 게시물 댓글 추천 토글 **/
//    @FormUrlEncoded
//    @POST("toggle_like_post_comment.php")
//    Call<String> togglePostCommentLike(
//            @Field("comment_id") int comment_id,
//            @Field("email") String email
//    );
//
//    /** 동영상 댓글 추천 토글 **/
//    @FormUrlEncoded
//    @POST("toggle_like_comment.php")
//    Call<String> toggleCommentLike(
//            @Field("comment_id") int comment_id,
//            @Field("email") String email
//    );
//
//    /** 게시물 좋아요 토글 **/
//    @FormUrlEncoded
//    @POST("toggle_post_likes.php")
//    Call<String> toggleLikePost(
//            @Field("post_id") int post_id,
//            @Field("email") String email
//    );
//
//    /** 동영상 좋아요 토글 **/
//    @FormUrlEncoded
//    @POST("toggle_video_likes.php")
//    Call<String> toggleLikeVideo(
//            @Field("video_id") int video_id,
//            @Field("email") String email
//    );
//
//    /** 구독 토글 **/
//    @FormUrlEncoded
//    @POST("toggle_subscribe.php")
//    Call<String> toggleSubscribe(
//            @Field("channel_email") String channel_email,
//            @Field("email") String email
//    );
//
//    /** 나중에 볼 동영상에 추가 **/
//    @FormUrlEncoded
//    @POST("add_watch_later.php")
//    Call<String> addWatchLater(
//            @Field("id") int id,
//            @Field("email") String email
//    );
//
//    /** 현재 유저 동영상 좋아요 여부 **/
//    @FormUrlEncoded
//    @POST("current_user_video_like.php")
//    Call<String> currentUserVideoLike(
//            @Field("video_id") int video_id,
//            @Field("email") String email
//    );
//
//    /** 현재 유저 채널 구독 여부 **/
//    @FormUrlEncoded
//    @POST("current_user_channel_subscribe.php")
//    Call<String> currentUserChannelSubscribe(
//            @Field("channel_email") String channel_email,
//            @Field("email") String email
//    );
//
//    /** 동영상 싫어요 수 계산 **/
//    @GET("count_video_dislikes.php")
//    Call<String> countVideoDislikes(
//            @Query("id") int id
//    );
//
//    /** 동영상 싫어요 토글 **/
//    @FormUrlEncoded
//    @POST("toggle_video_dislikes.php")
//    Call<String> toggleDislikeVideo(
//            @Field("video_id") int video_id,
//            @Field("email") String email
//    );
//
//    /** 현재 유저 동영상 싫어요 여부 **/
//    @FormUrlEncoded
//    @POST("current_user_video_dislike.php")
//    Call<String> currentUserVideoDislike(
//            @Field("video_id") int video_id,
//            @Field("email") String email
//    );
//
//    /** 동영상 조회수 올리기 **/
//    @FormUrlEncoded
//    @POST("update_view_count.php")
//    Call<String> updateViewCount(
//            @Field("id") int id
//    );
//
//    /** 동영상 시청기록에 추가하기 **/
//    @FormUrlEncoded
//    @POST("update_history.php")
//    Call<String> updateHistory(
//            @Field("id") int id,
//            @Field("email") String email
//    );
//
//    /** 커뮤니티 게시물 댓글 작성 **/
//    @FormUrlEncoded
//    @POST("upload_post_comment.php")
//    Call<String> uploadPostComment(
//            @Field("post_id") int post_id,
//            @Field("comment") String comment,
//            @Field("email") String email
//    );
//
//    /** 동영상 댓글 작성 **/
//    @FormUrlEncoded
//    @POST("upload_comment.php")
//    Call<String> uploadComment(
//            @Field("video_id") int video_id,
//            @Field("comment") String comment,
//            @Field("email") String email
//    );
//
//    /** 동영상 대댓글에 답글 달기 **/
//    @FormUrlEncoded
//    @POST("reply_to_reply.php")
//    Call<String> replyToReply(
//            @Field("commentId") int commentId,
//            @Field("reply") String reply,
//            @Field("currentUser") String currentUser,
//            @Field("replyUser") String replyUser
//    );
//
//    /** 게시물 대댓글 작성 **/
//    @FormUrlEncoded
//    @POST("upload_post_reply.php")
//    Call<String> uploadPostReply(
//            @Field("comment_id") int comment_id,
//            @Field("comment") String comment,
//            @Field("email") String email
//    );
//
//    /** 동영상 대댓글 작성 **/
//    @FormUrlEncoded
//    @POST("upload_comment_reply.php")
//    Call<String> uploadCommentReply(
//            @Field("comment_id") int comment_id,
//            @Field("comment") String comment,
//            @Field("email") String email
//    );
//
//    /** 게시물 대댓글 가져오기 **/
//    @GET("fetch_post_replies.php")
//    Call<List<PostComment>> fetchPostReplies(
//            @Query("parent_id") int parent_id,
//            @Query("email") String email
//    );
//
//    /** 동영상 대댓글 가져오기 **/
//    @GET("fetch_video_replies.php")
//    Call<List<Comment>> fetchVideoReplies(
//            @Query("parent_id") int parent_id,
//            @Query("email") String email
//    );
//
//    /** 게시물 대댓글 개수 가져오기 **/
//    @GET("count_post_replies.php")
//    Call<String> countPostReplies(
//            @Query("parent_id") int parent_id
//    );
//
//    /** 동영상 대댓글 개수 가져오기 **/
//    @GET("count_video_replies.php")
//    Call<String> countVideoReplies(
//            @Query("parent_id") int parent_id
//    );
//
//    /** 게시물 대댓글의 좋아요 개수 가져오기 **/
//    @GET("count_post_reply_likes.php")
//    Call<String> countPostReplyLikes(
//            @Query("reply_id") int reply_id
//    );
//
//    /** 동영상 대댓글의 좋아요 개수 가져오기 **/
//    @GET("count_video_reply_likes.php")
//    Call<String> countVideoReplyLikes(
//            @Query("reply_id") int reply_id
//    );
//
//    /** 게시물 좋아요 개수 가져오기 **/
//    @GET("count_post_likes.php")
//    Call<String> countPostLikes(
//            @Query("post_id") int post_id
//    );
//
//    /** 게시물 댓글의 좋아요 개수 가져오기 **/
//    @GET("check_current_user_post_comment_like.php")
//    Call<String> checkCurrentUserPostCommentLike(
//            @Query("comment_id") int comment_id,
//            @Query("email") String email
//    );
//
//    /** 게시물 댓글의 좋아요 개수 가져오기 **/
//    @GET("count_post_comment_likes.php")
//    Call<String> countPostCommentLikes(
//            @Query("comment_id") int comment_id
//    );
//
//    /** 동영상 댓글의 좋아요 개수 가져오기 **/
//    @GET("count_video_comment_likes.php")
//    Call<String> countVideoCommentLikes(
//            @Query("comment_id") int comment_id
//    );
//
//    /** 게시물 댓글 가져오기 **/
//    @GET("fetch_post_comments.php")
//    Call<List<PostComment>> fetchPostComments(
//            @Query("post_id") int post_id,
//            @Query("sort") String sort,
//            @Query("email") String email
//    );
//
//    /** 동영상 댓글 가져오기 **/
//    @GET("fetch_video_comments.php")
//    Call<List<Comment>> fetchVideoComments(
//            @Query("video_id") int video_id,
//            @Query("sort") String sort,
//            @Query("email") String email
//    );
//
//    /** 동영상 댓글 정렬해서 가져오기 **/
//    @GET("fetch_sorted_comments.php")
//    Call<List<Comment>> fetchSortedComments(
//            @Query("video_id") int video_id,
//            @Query("sort") String sort
//    );
//
//    /** 게시물 댓글 개수 가져오기 **/
//    @GET("count_post_comments.php")
//    Call<String> countPostComments(
//            @Query("post_id") int post_id
//    );
//
//    /** 동영상 댓글 개수 가져오기 **/
//    @GET("count_video_comments.php")
//    Call<String> countVideoComments(
//            @Query("video_id") int video_id
//    );
//
//    /** 게시물 답글 수정 **/
//    @FormUrlEncoded
//    @POST("edit_post_reply.php")
//    Call<String> editPostReply(
//            @Field("reply_id") int reply_id,
//            @Field("reply") String reply
//    );
//
//    /** 동영상 답글 수정 **/
//    @FormUrlEncoded
//    @POST("edit_reply.php")
//    Call<String> editVideoReply(
//            @Field("reply_id") int reply_id,
//            @Field("reply") String reply
//    );
//
//    /** 게시물 댓글 수정 **/
//    @FormUrlEncoded
//    @POST("edit_post_comment.php")
//    Call<String> editPostComments(
//            @Field("comment_id") int comment_id,
//            @Field("comment") String comment
//    );
//
//    /** 동영상 댓글 수정 **/
//    @FormUrlEncoded
//    @POST("edit_comment.php")
//    Call<String> editVideoComments(
//            @Field("comment_id") int comment_id,
//            @Field("comment") String comment
//    );
//
//    /** 게시물 답글 삭제 **/
//    @FormUrlEncoded
//    @POST("delete_post_reply.php")
//    Call<String> deletePostReply(
//            @Field("replyId") int replyId
//    );
//
//    /** 동영상 답글 삭제 **/
//    @FormUrlEncoded
//    @POST("delete_reply.php")
//    Call<String> deleteVideoReply(
//            @Field("replyId") int replyId
//    );
//
//    /** 동영상 댓글 삭제 (답글 없는 경우) **/
//    @FormUrlEncoded
//    @POST("delete_comment.php")
//    Call<String> deleteVideoComment(
//            @Field("comment_id") int comment_id
//    );
//
//    /** 동영상 댓글 삭제 (답글 있는 경우) **/
//    @FormUrlEncoded
//    @POST("delete_comment_with_replies.php")
//    Call<String> deleteVideoCommentWithReplies(
//            @Field("comment_id") int comment_id
//    );
//
//    /** 게시물 댓글 삭제 (답글 없는 경우) **/
//    @FormUrlEncoded
//    @POST("delete_post_comment.php")
//    Call<String> deletePostComment(
//            @Field("comment_id") int comment_id
//    );
//
//    /** 게시물 댓글 삭제 (답글 있는 경우) **/
//    @FormUrlEncoded
//    @POST("delete_post_comment_with_replies.php")
//    Call<String> deletePostCommentWithReplies(
//            @Field("comment_id") int comment_id
//    );

}