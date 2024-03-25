package com.algoExpert.demo.Service.Impl;

import com.algoExpert.demo.Entity.Comment;
import com.algoExpert.demo.Entity.Member;
import com.algoExpert.demo.Entity.Task;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Repository.CommentRepository;
import com.algoExpert.demo.Repository.MemberRepository;
import com.algoExpert.demo.Repository.TaskRepository;
import com.algoExpert.demo.Repository.UserRepository;
import com.algoExpert.demo.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private MemberRepository memberRepository;

//    create comment
    @Override
    public Task createComment(Comment comment,int member_id,int task_id)throws InvalidArgument {
        Member findMember = memberRepository.findById(member_id).orElseThrow(() ->
                new InvalidArgument("Task with ID " + member_id + " not found"));

        Task task = taskRepository.findById(task_id).orElseThrow(() ->
                new InvalidArgument("Task with ID " + task_id + " not found"));

        User user = userRepository.findById(findMember.getUser_id()).get();
        comment.setUsername(user.getUsername());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
        comment.setDate_created(simpleDateFormat.format(new Date()));
        List<Comment> commentList = task.getComments();
        commentList.add(comment);

        task.setComments(commentList);

        return taskRepository.save(task);
    }

//    get all comments
    @Override
    public List<Comment> getAllComments(){
    return commentRepository.findAll();
}

// update comment by id
    @Override
    public Comment editComment(int commentId,Comment newComment){
        return commentRepository.findById(commentId)
                .map(oldComment -> {oldComment.setCommentBody(newComment.getCommentBody());
                return commentRepository.save(oldComment);}).orElseThrow();
    }

//    delete comment by id
    @Override
    public List<Comment> deleteComment(int commentId){
        Comment findComment = commentRepository.findById(commentId).orElseThrow(()->new IllegalArgumentException("comment with Id "+commentId+" does not exist"));
        if(findComment !=null){
            commentRepository.deleteById(commentId);
            return commentRepository.findAll();
        }

        return null;
    }

}
