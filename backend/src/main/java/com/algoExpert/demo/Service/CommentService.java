package com.algoExpert.demo.Service;

import com.algoExpert.demo.Dto.CommentDto;
import com.algoExpert.demo.Dto.TaskDto;
import com.algoExpert.demo.Entity.Comment;
import com.algoExpert.demo.Entity.Member;
import com.algoExpert.demo.Entity.Task;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Mapper.CommentMapper;
import com.algoExpert.demo.Mapper.TaskMapper;
import com.algoExpert.demo.Repository.CommentRepository;
import com.algoExpert.demo.Repository.MemberRepository;
import com.algoExpert.demo.Repository.TaskRepository;
import com.algoExpert.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository ;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private TaskMapper taskMapper;

//    create comment
    public TaskDto createComment(CommentDto commentDto, int member_id, int task_id)throws InvalidArgument {
        Comment comment = commentMapper.commentDtoToComment(commentDto);
        Member findMember = memberRepository.findById(member_id).orElseThrow(() -> new InvalidArgument("Task with ID " + member_id + " not found"));
        Task task = taskRepository.findById(task_id).orElseThrow(() -> new InvalidArgument("Task with ID " + task_id + " not found"));
        User user = userRepository.findById(findMember.getUser_id()).get();
        comment.setUsername(user.getUsername());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
        comment.setDate_created(simpleDateFormat.format(new Date()));
        List<Comment> commentList = task.getComments();
        commentList.add(comment);

        task.setComments(commentList);
        Task taskResult = taskRepository.save(task);
        return taskMapper.taskToTaskDto(taskResult);
    }

//    get all comments
    public List<CommentDto> getAllComments(){
    List<Comment> comments = commentRepository.findAll();
    return commentMapper.commentDtos(comments);
}

// update comment by id
    public CommentDto editComment(int commentId,CommentDto newCommentDto) throws InvalidArgument{
        Comment comment= commentRepository.findById(commentId)
                .map(oldComment -> {
                    if(newCommentDto != null){
                        Optional.ofNullable(newCommentDto.getCommentBody()).ifPresent(oldComment::setCommentBody);
                    }
                return commentRepository.save(oldComment);}).orElseThrow(()-> new InvalidArgument("Comment ID "+commentId+" not fount"));
        return commentMapper.commentToCommentDto(comment);
    }

//    delete comment by id
    public List<Comment> deleteComment(int commentId){
        Comment findComment = commentRepository.findById(commentId).orElseThrow(()->new IllegalArgumentException("comment with Id "+commentId+" does not exist"));
        if(findComment !=null){
            commentRepository.deleteById(commentId);
            return commentRepository.findAll();
        }

        return null;
    }

}
